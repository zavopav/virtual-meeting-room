package com.zonelab.vmr.chat.controllers;

import lombok.Data;

@Data
class Response<T> {
    private boolean success;
    private T data;
    private String details;

    static <T> Response success(final T data) {
        final Response<T> response = new Response<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    static <T> Response fail(final String details) {
        final Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setDetails(details);
        return response;
    }
}
