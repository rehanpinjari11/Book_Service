package com.example.demo.entity;

import lombok.Data;

@Data
public class Response {

    private String message;
    private Object data;

    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }

}
