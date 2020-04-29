package com.ynz.jpa.controllers;

import com.ynz.jpa.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BugTraceController {

    @Autowired
    private ApplicationRepository applicationRepository;


}
