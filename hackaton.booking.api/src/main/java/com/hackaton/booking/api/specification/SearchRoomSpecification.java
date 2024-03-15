package com.hackaton.booking.api.specification;

import com.hackaton.booking.api.domain.model.Room;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class SearchRoomSpecification {
    private SearchRoomSpecification() {
    }

    public static Specification<Room> search(Room filter) {
        return (location, query, cb) -> {
            final Collection<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(filter.getMaxCapacity())) {
                predicates.add(cb.greaterThanOrEqualTo(location.get("maxCapacity"),
                        filter.getMaxCapacity()));
            }

            if (Objects.nonNull(filter.getTotalBeds())) {
                predicates.add(cb.equal(location.get("totalBeds"),
                        filter.getTotalBeds()));
            }

            if (Objects.nonNull(filter.getTotalDailyValue())) {
                predicates.add(cb.lessThanOrEqualTo(location.get("totalDailyValue"),
                        filter.getTotalDailyValue()));
            }

            if (Objects.nonNull(filter.getType())) {
                predicates.add(cb.like(location.get("type"),
                        filter.getType()));
            }

            if (Objects.nonNull(filter.getIdBuilding())) {
                predicates.add(cb.equal(location.get("idBuilding"),
                        filter.getIdBuilding()));
            }


            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
