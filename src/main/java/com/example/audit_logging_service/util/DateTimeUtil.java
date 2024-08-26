package com.example.audit_logging_service.util;


import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtil {

    public static ZonedDateTime getCurrentDateTime(String zoneStr) {
        return ZonedDateTime.now(ZoneId.of(zoneStr));
    }
}

