package com.football_championship_api.demo.data.repository;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Filter {
    private List<FilterCriteria> criteria;
    private List<String> orderBy;
    
    public Filter() {
        this.criteria = new ArrayList<>();
        this.orderBy = new ArrayList<>();
    }
}
