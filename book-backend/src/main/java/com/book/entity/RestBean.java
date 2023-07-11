package com.book.entity;

import lombok.Data;

@Data
public class RestBean<T> {
    private int status;
    private boolean success;
    T msg;

    private RestBean(int status, boolean success, T msg) {
        this.status = status;
        this.success = success;
        this.msg = msg;
    }

    public static <T> RestBean<T> success(int status, T msg) {
        return new RestBean<>(status, true, msg);
    }
    public static <T> RestBean<T> failure(int status, T msg) {
        return new RestBean<>(status, false, msg);
    }


    public static <T> RestBean<T> success(T msg) {
        return new RestBean<>(200, true, msg);

    }

}
