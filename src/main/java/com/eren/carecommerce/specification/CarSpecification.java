package com.eren.carecommerce.specification;

import com.eren.carecommerce.model.*;
import com.eren.carecommerce.request.SearchCriteria;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class CarSpecification implements Specification<Car> {

    private final SearchCriteria searchCriteria;

    public CarSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }


    @Override
    public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        String valueFromJSON = searchCriteria.getValue().toString();
        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))){
            case GREATER_THAN:
                if(searchCriteria.getFilterKey().equals("minyear")){
                    return cb.greaterThan(root.get("year"), valueFromJSON);
                }

                if(searchCriteria.getFilterKey().equals("minprice")) {
                    return cb.greaterThan(root.get("price"), valueFromJSON);
                }

                if(searchCriteria.getFilterKey().equals("minmileage")) {
                    return cb.greaterThan(root.get("mileage"), valueFromJSON);
                }
            case LESS_THAN:
                if(searchCriteria.getFilterKey().equals("maxyear")) {
                    return cb.lessThan(root.get("year"), valueFromJSON);
                }

                if(searchCriteria.getFilterKey().equals("maxprice")){
                    return cb.lessThan(root.get("price"), valueFromJSON);
                }

                if(searchCriteria.getFilterKey().equals("maxmileage")){
                    return cb.lessThan(root.get("mileage"), valueFromJSON);
                }
            case EQUAL:
                if(searchCriteria.getFilterKey().equals("brand")){
                    Join<Car, Brand> brand = root.join("brand");
                    return cb.equal(brand.get("name"), searchCriteria.getValue());
                }
                if(searchCriteria.getFilterKey().equals("model")){
                    Join<Car, Model> model = root.join("model");
                    return cb.equal(model.get("name"), searchCriteria.getValue());
                }
                if(searchCriteria.getFilterKey().equals("fuel")){
                    if(searchCriteria.getValue().toString().isEmpty()) {
                        return null;
                    }
                    return cb.equal(root.get("fuelType"), FuelType.valueOf(searchCriteria.getValue().toString()));
                }

                if(searchCriteria.getFilterKey().equals("color")){
                    if(searchCriteria.getValue().toString().isEmpty()) {
                        return null;
                    }
                    return cb.equal(root.get(searchCriteria.getFilterKey()),Color.valueOf(searchCriteria.getValue().toString()));
                }

                if(searchCriteria.getFilterKey().equals("transmissiontype")) {
                    if(searchCriteria.getValue().toString().isEmpty()) {
                        return null;
                    }
                    return cb.equal(root.get("transmissionType"), TransmissionType.valueOf(searchCriteria.getValue().toString()));
                }

                if(searchCriteria.getFilterKey().equals("doors")){
                    if(searchCriteria.getValue().toString().isEmpty()) {
                        return null;
                    }
                    return cb.equal(root.get("numberOfDoors"), NumberOfDoors.valueOf(searchCriteria.getValue().toString()));
                }



        }
        return null;
    }

    private Join<Car, Brand> brandJoin(Root<Car> root){
        return root.join("brand");

    }

    private Join<Car, Model> modelJoin(Root<Car> root){
        return root.join("model");

    }

}
