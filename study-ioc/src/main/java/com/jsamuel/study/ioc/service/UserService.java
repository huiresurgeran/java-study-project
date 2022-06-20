package com.jsamuel.study.ioc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
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
