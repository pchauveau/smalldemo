package com.smalldemo.pch.smalldemo.webservices;

import com.smalldemo.pch.smalldemo.model.BasicOject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebserviceService {
    String ADDRESS = "http://jsonplaceholder.typicode.com";

    @GET("/photos")
    Call<List<BasicOject>> getList();
}
