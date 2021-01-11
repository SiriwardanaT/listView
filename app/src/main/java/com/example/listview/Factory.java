package com.example.listview;

public class Factory {
    private String name;
    private String cusName;

    public String getName() {
        return name;
    }

    public String getCusName() {
        return cusName;
    }

    public Factory(String name, String cusName) {
        this.name = name;
        this.cusName = cusName;
    }


}
