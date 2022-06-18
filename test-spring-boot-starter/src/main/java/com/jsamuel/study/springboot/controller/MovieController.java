package com.jsamuel.study.springboot.controller;

import com.jsamuel.study.service.MovieService;
import com.jsamuel.study.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private SportService sportService;

    @RequestMapping(value = "/movie")
    public String movie() {
        return movieService.toString();
    }

    @RequestMapping(value = "/sport")
    public String sport() {
        return sportService.toString();
    }
}
