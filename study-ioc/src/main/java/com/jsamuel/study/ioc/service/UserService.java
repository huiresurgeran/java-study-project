package com.jsamuel.study.ioc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private MailService mailService;

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public void login() {
        logger.info(">>> login");
        mailService.sendLoginMail();
    }

    public void register() {
        logger.info(">>> register");
        mailService.sendRegisterMail();
    }

}
