package com.abbydiode.cinemaApplication.models;

public class User {
    private String name;
    private String password;
    private UserType userType;

    public User(String name, String password, UserType userType) {
        this.name = name;
        this.password = password;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public UserType getUserType() {
        return userType;
    }

    /**
     * @param password The password to test against
     * @return True if the password is equal to this user's password, otherwise false
     */
    public boolean isPasswordEqualTo(String password) {
        return this.password == password;
    }
}
