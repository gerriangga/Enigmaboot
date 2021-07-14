package com.enima.enigmaboot.utils;

public class ResponseData<T> {
    private String message;
    private T payLoad;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(T payLoad) {
        this.payLoad = payLoad;
    }
}
