package com.smalldemo.pch.smalldemo.event;

import com.smalldemo.pch.smalldemo.model.BasicOject;

import java.util.List;

public class WebserviceGetListEvent {
    private boolean isOk = false;
    private String error;
    private List<BasicOject> basicOjects;

    public WebserviceGetListEvent(boolean isOk, String error) {
        this.isOk = isOk;
        this.error = error;
    }

    public WebserviceGetListEvent(boolean isOk, List<BasicOject> basicOjects) {
        this.isOk = isOk;
        this.basicOjects = basicOjects;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<BasicOject> getBasicOjects() {
        return basicOjects;
    }

    public void setBasicOjects(List<BasicOject> basicOjects) {
        this.basicOjects = basicOjects;
    }
}
