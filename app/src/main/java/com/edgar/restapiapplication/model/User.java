package com.edgar.restapiapplication.model;


import java.util.List;

public class User {

    String bio;

    String avatar_url;

    String name;

    String user_repositories;



    User(){
    }

    public String getBio() {
        return bio;
    }

    public String getName() {
        return name;
    }

    public String getUser_repositories() {
        return user_repositories;
    }

    public String getAvatar_url() {
        return avatar_url;
    }



}
