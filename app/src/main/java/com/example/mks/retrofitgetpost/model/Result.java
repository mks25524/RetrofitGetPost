package com.example.mks.retrofitgetpost.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mks on 6/17/2017.
 */

public class Result {
    @SerializedName("error")
    private Boolean error;
    @SerializedName("message")
    private String message;
    @SerializedName("user")
    private Students students;

    public Result(Boolean error,String message,Students students){
        this.error=error;
        this.message=message;
        this.students=students;
    }

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Students getStudents() {
        return students;
    }
}
