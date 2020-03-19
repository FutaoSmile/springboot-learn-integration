package com.futao.learn.kit.kit;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author futao
 * @date 2020/3/16.
 */
public class TimeKit {


    /**
     * LocalDateTime 转为 Date
     *
     * @param localDateTime
     * @return
     */
    public Date parseLocalDateTime2Date(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneOffset.ofHours(8)).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalDate 转为 Date
     *
     * @param localDate
     * @return
     */
    public Date parseLocalDate2Date(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant();
        return Date.from(instant);
    }
}
