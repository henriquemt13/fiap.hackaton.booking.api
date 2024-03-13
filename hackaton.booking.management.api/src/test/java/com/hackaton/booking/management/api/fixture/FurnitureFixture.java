package com.hackaton.booking.management.api.fixture;

import com.hackaton.booking.management.api.domain.model.Furniture;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class FurnitureFixture {

    private static final OffsetDateTime defaultDate = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0,
            ZoneOffset.of("-03:00"));

    public FurnitureFixture() {
    }

    public static Furniture buildFurniture() {
        return new Furniture(1L, 1L, "Test", 1L, defaultDate, defaultDate);
    }

    public static List<Furniture> buildFurnitures() {
        return List.of(buildFurniture());
    }
}
