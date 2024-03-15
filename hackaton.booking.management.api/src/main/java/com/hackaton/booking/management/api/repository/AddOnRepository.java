package com.hackaton.booking.management.api.repository;

import com.hackaton.booking.management.api.domain.model.AddOn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddOnRepository extends JpaRepository<AddOn, Long> {
}
