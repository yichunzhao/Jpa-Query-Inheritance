package com.ynz.jpa.controllers;

import com.ynz.jpa.controllers.dto.ApplicationDto;
import com.ynz.jpa.entities.Application;
import com.ynz.jpa.repositories.ApplicationRepository;
import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trace")
public class BugTraceController {

    @Autowired



    @PostMapping("/application")
    public ResponseEntity<ApplicationDto> createApplication(@RequestBody Application application){
        ResponseEntity<ApplicationDto> responseEntity = new ResponseEntity(application,HttpStatus.CREATED);



        return new ResponseEntity(application,HttpStatus.CREATED);
    }


}
