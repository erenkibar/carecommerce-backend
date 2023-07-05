package com.eren.carecommerce.model;

import com.eren.carecommerce.request.SearchCriteria;
import com.eren.carecommerce.specification.CarSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CarSpecificationBuilder {
    private final List<SearchCriteria> params;

    public CarSpecificationBuilder(){
        this.params = new ArrayList<>();
    }


    public final CarSpecificationBuilder with(String key,
                                              String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final CarSpecificationBuilder with(SearchCriteria
                                                      searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<Car> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<Car> result =
                new CarSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result =  SearchOperation.getDataOption(criteria
                    .getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new
                    CarSpecification(criteria))
                    : Specification.where(result).or(
                    new CarSpecification(criteria));
        }
        return result;
    }


}
