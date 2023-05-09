package com.example.chanzyhebat.Model;

public class RegisterRequest {
    private String name;
    private String username;
    private String address;
    private String password;
    private String password_confirmation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public RegisterRequest(String name, String username, String address, String password, String password_confirmation) {
        this.name = name;
        this.username = username;
        this.address = address;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }
}
