package com.football_championship_api.demo.data.repository;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilterCriteria {
    private String field;
    private FilterOperation operation;
    private Object value;
}
