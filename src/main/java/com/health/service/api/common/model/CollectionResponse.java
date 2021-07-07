package com.health.service.api.common.model;

import lombok.Getter;

import java.util.List;

@Getter
public class CollectionResponse<T> implements APIResponseBody {

    private Integer count;

    private List<T> contents;

    public CollectionResponse(Integer count, List<T> contents) {
        this.count = count;
        this.contents = contents;
    }
}
