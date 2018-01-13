package com.smalldemo.pch.smalldemo.event;

public class WebserviceEvent {
    boolean isOk = false;

    public WebserviceEvent(boolean isOk) {
        this.isOk = isOk;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }
}
