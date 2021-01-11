package com.example.listview;

public class Customer {

    private String id;
    private String name;
    private String age;
    private String description;

    public Customer(String id ,String name, String age, String description) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public Customer(){

    }
    public String getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
