package com.example.mks.retrofitgetpost.API;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by mks on 6/17/2017.
 */

public interface APIService {
    //register call
    @FormUrlEncoded
    @POST("register")
    Call<Result>createStudent(
            @Field("name")String name,
            @Field("department")String department
    );
}
