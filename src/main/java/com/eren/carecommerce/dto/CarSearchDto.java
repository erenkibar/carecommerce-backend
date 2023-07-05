package com.eren.carecommerce.dto;

import com.eren.carecommerce.request.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarSearchDto {

    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;


}
