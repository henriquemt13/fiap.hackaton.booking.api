package com.hackaton.booking.api.specification;

import com.hackaton.booking.api.domain.model.Location;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class SearchLocationSpecification {
    private SearchLocationSpecification() {
    }

    public static Specification<Location> search(Location filter) {
        return (location, query, cb) -> {
            final Collection<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(filter.getCity())) {
                predicates.add(cb.like(location.get("city"),
                        filter.getCity()));
            }

            if (Objects.nonNull(filter.getState())) {
                predicates.add(cb.like(location.get("state"),
                        filter.getState()));
            }

            if (Objects.nonNull(filter.getName())) {
                predicates.add(cb.like(location.get("name"),
                        filter.getName()));
            }


            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
