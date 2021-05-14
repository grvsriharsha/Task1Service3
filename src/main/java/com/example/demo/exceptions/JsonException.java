package com.example.demo.exceptions;

public class JsonException extends RuntimeException {
    public JsonException(String mssg) {
        super(mssg);
    }

    public JsonException(String mssg, Throwable t) {
        super(mssg, t);
    }

}
