package com.example.mks.retrofitgetpost.model;

/**
 * Created by mks on 6/17/2017.
 */

public class Students {
    private int id;
    private String name;
    private String department;

    public Students(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public Students(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}
