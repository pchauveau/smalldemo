package com.smalldemo.pch.smalldemo.webservices;

import com.smalldemo.pch.smalldemo.event.WebserviceGetListEvent;
import com.smalldemo.pch.smalldemo.model.BasicOject;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class to call a webservice and get a List of BasicObjects.
 */
public class GetList {

    public void getList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebserviceService.ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebserviceService webserviceService = retrofit.create(WebserviceService.class);
        Call<List<BasicOject>> basicObjectsListCall = webserviceService.getList();

        basicObjectsListCall.enqueue(new Callback<List<BasicOject>>() {
            @Override
            public void onResponse(Call<List<BasicOject>> call, Response<List<BasicOject>> response) {
                List<BasicOject> basicOjects = response.body();

                EventBus.getDefault().post(new WebserviceGetListEvent(true, basicOjects));
            }

            @Override
            public void onFailure(Call<List<BasicOject>> call, Throwable t) {
                EventBus.getDefault().post(new WebserviceGetListEvent(false, t.getLocalizedMessage()));
            }
        });
    }
}
