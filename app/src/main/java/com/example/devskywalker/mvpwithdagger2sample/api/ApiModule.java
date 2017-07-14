package com.example.devskywalker.mvpwithdagger2sample.api;

import android.annotation.SuppressLint;
import android.app.Application;

import com.example.devskywalker.mvpwithdagger2sample.app.AppConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by admin on 14/7/17.
 */

@Module
public class ApiModule {

    private IApiAuthenticator mApiAuthenticator;


    public ApiModule(IApiAuthenticator apiAuthenticator) {
        mApiAuthenticator = apiAuthenticator;
    }

    @Provides
    @Singleton
    public ApiConfig provideApiConfig(Application application) {
        return new ApiConfig(application);
    }

    @Provides
    @Singleton
    Authenticator provideAuthenticator(final AppConfig appConfig) {
        // Automatically Retry 401 Requests
        return new Authenticator() {

            private int mCounter = 0;

            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                if (mCounter++ > 0) {
                    return null;
                }

                String credential = String.format("Bearer %s", appConfig.getAccessToken());

                if (credential.equals(response.request().header("Authorization"))) {
                    return null;
                } else {
                    return response.request().newBuilder()
                            .header("Authorization", credential)
                            .build();
                }
            }
        };
    }



    @Provides
    @Singleton
    Interceptor authorizationInterceptor(final AppConfig appConfig) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                // Build new request
                Request.Builder builder = request.newBuilder();

                String accessToken = appConfig.getAccessToken();
                if (!accessToken.isEmpty()) {
                    setAuthenticationHeader(builder, accessToken);
                }

                // Override old request
                request = builder.build();
                Response response = chain.proceed(request); //perform request, here original request will be executed

                // Check if Unauthorized
                if (response.code() == 401) {
                    synchronized (appConfig) {
                        String currentToken = appConfig.getAccessToken();

                        // Check if the Current Token for this request is different
                        if (!currentToken.isEmpty() && currentToken.equals(accessToken)) {

                            // Refresh Token
                            int code = mApiAuthenticator.refreshToken();

                            // Force Logout if the Token Refresh is not Successful
                            if (code != 200) {
                                mApiAuthenticator.forceLogout();
                                return response; //if token refresh failed - show error to user
                            }
                        }

                        String freshAccessToken = appConfig.getAccessToken();
                        if (!freshAccessToken.isEmpty()) {
                            setAuthenticationHeader(builder, freshAccessToken);
                            request = builder.build();
                            return chain.proceed(request);
                        }
                    }
                }

                return response;
            }

            private void setAuthenticationHeader(Request.Builder builder, String accessToken) {
                builder.header("Authorization", String.format("Bearer %s", accessToken));
            }

        };
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(ApiConfig apiConfig, Authenticator authenticator,
                                     Interceptor authorizationInterceptor) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (apiConfig.isDebugBuild()) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authorizationInterceptor)
                .authenticator(authenticator)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @SuppressLint("SimpleDateFormat")
    @Provides
    @Singleton
    JacksonConverterFactory provideJacksonConverterFactory() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        return JacksonConverterFactory
                .create(objectMapper);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(ApiConfig apiConfig,
                                    JacksonConverterFactory jacksonConverterFactory,
                                    OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(apiConfig.getApiUrl())
                .client(okHttpClient)
                .addConverterFactory(jacksonConverterFactory)
                .build();
    }

    @Provides
    @Singleton
    public IApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(IApiService.class);
    }


}
