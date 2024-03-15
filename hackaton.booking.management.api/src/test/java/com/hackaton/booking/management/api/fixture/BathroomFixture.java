package com.hackaton.booking.management.api.fixture;

import com.hackaton.booking.management.api.domain.enums.BathroomTypeEnum;
import com.hackaton.booking.management.api.domain.model.Bathroom;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class BathroomFixture {

    private static final OffsetDateTime defaultDate = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0,
            ZoneOffset.of("-03:00"));

    public BathroomFixture() {

    }

    public static Bathroom buildBathroom() {
       return new Bathroom(1L, BathroomTypeEnum.STANDARD.name(), "Test", defaultDate, defaultDate);
    }

    public static List<Bathroom> buildBathrooms() {
        return List.of(buildBathroom());
    }
}
