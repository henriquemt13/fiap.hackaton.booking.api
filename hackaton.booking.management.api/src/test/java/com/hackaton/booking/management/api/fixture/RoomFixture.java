package com.hackaton.booking.management.api.fixture;

import com.hackaton.booking.management.api.domain.enums.BathroomTypeEnum;
import com.hackaton.booking.management.api.domain.enums.RoomTypeEnum;
import com.hackaton.booking.management.api.domain.model.Room;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class RoomFixture {

    private static final OffsetDateTime defaultDate = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0,
            ZoneOffset.of("-03:00"));

    public RoomFixture() {

    }

    public static Room buildRoom() {
        return new Room(1L, 1L, RoomTypeEnum.STANDARD.name(), 1L, 1L, 1L, BigDecimal.TEN,
                BathroomTypeEnum.STANDARD.name(), defaultDate, defaultDate);
    }

    public static List<Room> buildRooms() {
        return List.of(buildRoom());
    }
}
