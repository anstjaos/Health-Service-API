package com.health.service.api.common.model;

import lombok.Getter;

@Getter
public class APIResponse {
    private APIResponseHeader header;

    private APIResponseBody body;

    public APIResponse() {}
    public APIResponse(APIResponseHeader header, APIResponseBody body) {
        this.header = header;
        this.body = body;
    }
}
