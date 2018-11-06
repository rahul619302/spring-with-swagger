package com.cg.SpringWithSwagger.util;

public enum Constants {

    S200("Success"), S400("Some Thing Went Wrong");

    private String message;

    Constants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
