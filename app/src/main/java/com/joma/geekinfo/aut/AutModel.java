package com.joma.geekinfo.aut;

public class AutModel {
    private String name, phoneNumber;
    private int id;

    public AutModel(String name, String phoneNumber, int id) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getId() {
        return id;
    }
}
