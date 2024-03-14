package com.hackaton.booking.api.repository;

import com.hackaton.booking.api.domain.model.AddOn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddOnRepository extends JpaRepository<AddOn, Long> {
}
