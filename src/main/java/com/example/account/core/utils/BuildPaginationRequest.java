package com.example.account.core.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Map;

/**
 * This class is responsible for building a PageRequest object based on the provided parameters.
 * It is used to handle pagination and sorting in the application.
 */
public class BuildPaginationRequest {
    public static PageRequest build(Map<String, Object> params) {
        int page = 0;
        int size = 10;
        Sort sort = Sort.by(Sort.Direction.ASC, "id");

        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page").toString());
            params.remove("page");
        }

        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size").toString());
            params.remove("size");
        }

        if (params.containsKey("sort")) {
            String[] sortParams = params.get("sort").toString().split(",");
            sort = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
            params.remove("sort");
        }

        return PageRequest.of(page, size, sort);
    }
}

