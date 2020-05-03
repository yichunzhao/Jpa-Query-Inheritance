package com.ynz.jpa.controllers;

import com.ynz.jpa.dto.ApplicationDto;
import com.ynz.jpa.entities.Application;
import com.ynz.jpa.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/trace")
public class BugTraceController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/application")
    public ResponseEntity<ApplicationDto> createApplication(@RequestBody ApplicationDto applicationDto, UriComponentsBuilder builder) {
        Application added = applicationService.addApplication(applicationDto.toDomain());

        if (added == null) return new ResponseEntity(null, HttpStatus.CONFLICT);

        URI callback = builder.path("/application").buildAndExpand(added.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(callback);

        return new ResponseEntity(applicationDto, headers, HttpStatus.CREATED);
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<Application> getApplication(@PathVariable("id") int id) {
        return new ResponseEntity(applicationService.getApplicationById(id), HttpStatus.FOUND);
    }


}
