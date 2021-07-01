package com.health.service.api.common.model;

import lombok.Getter;

@Getter
public class CollectionResponse<T> implements APIResponseBody {

    private Integer count;

    private T contents;

    public CollectionResponse(Integer count, T contents) {
        this.count = count;
        this.contents = contents;
    }
}
