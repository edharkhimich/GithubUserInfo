package com.edgar.restapiapplication.model;


public class User {

    String bio;
    String avatar_url;
    String name;

    User(){
    }

    public String getBio() {
        return bio;
    }

    public String getName() {
        return name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

}
