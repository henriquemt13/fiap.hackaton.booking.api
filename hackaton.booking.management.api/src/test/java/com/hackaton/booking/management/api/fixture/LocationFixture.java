package com.hackaton.booking.management.api.fixture;

import com.hackaton.booking.management.api.domain.model.Location;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class LocationFixture {

    private static final OffsetDateTime defaultDate = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0,
            ZoneOffset.of("-03:00"));

    public LocationFixture() {

    }

    public static Location buildLocation() {
        return new Location(1L, "Test", "Test", "32423434", "Test", "Test", defaultDate, defaultDate);
    }

    public static List<Location> buildLocations() {
        return List.of(buildLocation());
    }
}
