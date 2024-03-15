package com.hackaton.booking.management.api.fixture;

import com.hackaton.booking.management.api.domain.enums.AddOnTypeEnum;
import com.hackaton.booking.management.api.domain.model.AddOn;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class AddOnFixture {

    private static final OffsetDateTime defaultDate = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0,
            ZoneOffset.of("-03:00"));
    public AddOnFixture() {

    }

    public static List<AddOn> buildAddOns() {
        return List.of(buildAddOn());
    }

    public static AddOn buildAddOn() {
        return new AddOn(1L, AddOnTypeEnum.SERVICE.name(), "Teste", BigDecimal.TEN,
                defaultDate, defaultDate);
    }
}
