package com.hackaton.booking.management.api.fixture;

import com.hackaton.booking.management.api.domain.model.Building;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class BuildingFixture {

    private static final OffsetDateTime defaultDate = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0,
            ZoneOffset.of("-03:00"));

    public BuildingFixture() {

    }

    public static Building buildBuilding() {
        return new Building(1L, 1L, "Test", defaultDate, defaultDate);
    }

    public static List<Building> buildBuildings() {
        return List.of(buildBuilding());
    }
}
