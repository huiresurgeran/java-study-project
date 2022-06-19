package com.jsamuel.study.ioc.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailService {

    private ZoneId zoneId = ZoneId.systemDefault();
    private int count = 0;

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    public void sendLoginMail() {
        logger.info(String.format("Hi, %s! You are logged in at %s, count is %d",
                "totoroyang", getTime(), count));
    }

    public void sendRegisterMail() {
        logger.info(String.format("Welcome, %s!, count is %d", "totoroyang", count));
    }

    public String getTime() {
        return ZonedDateTime.now(this.zoneId).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
}
