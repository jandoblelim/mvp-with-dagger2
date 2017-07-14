package com.example.devskywalker.mvpwithdagger2sample.database.model;

import io.realm.RealmObject;

/**
 * Created by admin on 14/7/17.
 */

public class Sample extends RealmObject {

    public Sample() {

    }
    private Long id;

    private String firstName;

    private Integer age;

    private String gender;

    private String lastName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
