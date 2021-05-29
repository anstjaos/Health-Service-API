package com.health.service.api.common.model;

import lombok.Getter;

@Getter
public class SingleResponse<T> implements APIResponseBody {

    private T content;

    public SingleResponse(T content) {
        this.content = content;
    }
}
