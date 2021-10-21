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

    /**
     * @return This user's username
     */
    public String getName() {
        return name;
    }

    /**
     * @return Either UserType.USER if this user is a regular user or UserType.ADMINISTRATOR if this user is an admin
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * @param password The password to test against
     * @return True if the password is equal to this user's password, otherwise false
     */
    public boolean isPasswordEqualTo(String password) {
        return this.password.equals(password);
    }
}
