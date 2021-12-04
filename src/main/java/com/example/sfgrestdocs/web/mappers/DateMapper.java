package com.example.sfgrestdocs.web.mappers;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Created by berkson
 * Date: 03/12/2021
 * Time: 20:11
 */
@Component
public class DateMapper {
    public OffsetDateTime asOffsetDateTime(LocalDateTime date) {
        if (date != null) {
            return OffsetDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(),
                    date.getHour(), date.getMinute(), date.getSecond(), date.getNano(), ZoneOffset.UTC);
        } else
            return null;
    }

    public LocalDateTime asLocalDateTime(OffsetDateTime dateTime){
        if (dateTime != null)
            return dateTime.toLocalDateTime();
        else
            return null;
    }


}
