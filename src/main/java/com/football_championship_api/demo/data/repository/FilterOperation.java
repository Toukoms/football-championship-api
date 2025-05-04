package com.football_championship_api.demo.data.repository;

import com.football_championship_api.demo.data.repository.FilterCriteria;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public enum FilterOperation {
    EQUALS,
    NOT_EQUALS,
    GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_OR_EQUALS,
    LESS_THAN_OR_EQUALS,
    LIKE,
    IN,
    IS_NULL,
    IS_NOT_NULL
}