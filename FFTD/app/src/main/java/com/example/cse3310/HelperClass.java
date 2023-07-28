package com.example.cse3310;

public class HelperClass {

    String name, email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HelperClass(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public HelperClass() {

    }
}
