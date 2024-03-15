package com.hackaton.booking.management.api.fixture;

import com.hackaton.booking.management.api.domain.model.Amenity;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class AmenityFixture {

    private static final OffsetDateTime defaultDate = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0,
            ZoneOffset.of("-03:00"));

    public AmenityFixture() {
    }

    public static List<Amenity> buildAmenities() {
        return List.of(buildAmenity());
    }

    public static Amenity buildAmenity() {
        return new Amenity(1L, 1L, "Test", 1L, defaultDate, defaultDate);
    }
}
