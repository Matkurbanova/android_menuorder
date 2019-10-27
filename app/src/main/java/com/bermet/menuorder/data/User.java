package com.bermet.menuorder.data;

public class User {
    public long id;
    public String name;

    public String login;
    public String password;

    public User() {
    }

    public User(String lg, String pass) {
        login = lg;
        password = pass;
    }
}
