package com.tuonglh.coffee.samplecode.dto.response;

import org.springframework.http.HttpStatusCode;

public class ResponseFailure extends ResponseSuccess{
    public ResponseFailure(int status, String message) {
        super(HttpStatusCode.valueOf(status), message);
    }
}
