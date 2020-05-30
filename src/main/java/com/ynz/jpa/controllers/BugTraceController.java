package com.ynz.jpa.controllers;

import com.ynz.jpa.dto.ApplicationDto;
import com.ynz.jpa.dto.BugDto;
import com.ynz.jpa.dto.EnhancementDto;
import com.ynz.jpa.dto.ReleaseDto;
import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Bug;
import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.entities.Release;
import com.ynz.jpa.service.IApplicationService;
import com.ynz.jpa.service.IReleaseService;
import com.ynz.jpa.service.ITicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/trace")
@Slf4j
public class BugTraceController {

    @Autowired
    private IApplicationService applicationService;

    @Autowired
    @Qualifier("bugService")
    private ITicketService<Bug> bugService;

    @Autowired
    @Qualifier("enhancementService")
    private ITicketService<Enhancement> enhancementService;

    @Autowired
    private IReleaseService releaseService;

    @PostMapping("/application")
    public ResponseEntity<ApplicationDto> createApplication(@RequestBody ApplicationDto applicationDto, UriComponentsBuilder builder) {
        log.info("in http request handler: createApplication ");
        Application added = applicationService.addApplication(applicationDto.toDomain());

        if (added == null) return new ResponseEntity(null, null, HttpStatus.CONFLICT);

        URI callback = builder.path("/application").buildAndExpand(added.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(callback);

        return new ResponseEntity(ApplicationDto.toDto(added), headers, HttpStatus.CREATED);
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<ApplicationDto> getApplication(@PathVariable("id") int id) {
        log.info("in http request handler: getApplication ");
        Application found = applicationService.getApplicationById(id);
        return new ResponseEntity(ApplicationDto.toDto(found), HttpStatus.FOUND);
    }

    @PostMapping("/bug")
    public ResponseEntity<BugDto> createBug(@RequestBody BugDto bugDto, UriComponentsBuilder builder) {
        log.info("In http request handler: createBug ");
        //Bug added = bugService.addBug(bugDto.toDomain());
        Bug added = bugService.addTicket(bugDto.toDomain());

        if (added == null) return new ResponseEntity(HttpStatus.CONFLICT);

        URI callback = builder.path("/bug").buildAndExpand(added.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(callback);

        return new ResponseEntity(BugDto.toDto(added), headers, HttpStatus.CREATED);
    }

    @PostMapping("/enhancement")
    public ResponseEntity<Enhancement> createEnhancement(@RequestBody EnhancementDto enhancementDto, UriComponentsBuilder builder) {
        log.info("In http request handler: createEnhancement ");
        //Enhancement added = enhancementService.addEnhancement(enhancementDto.toDomain());
        Enhancement added = enhancementService.addTicket(enhancementDto.toDomain());

        if (added == null) return new ResponseEntity<>(HttpStatus.CONFLICT);

        URI callback = builder.path("/enhancement").buildAndExpand(added.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(callback);

        return new ResponseEntity(EnhancementDto.toDto(added), headers, HttpStatus.CREATED);
    }

    @PostMapping("/release")
    public ResponseEntity<ReleaseDto> addRelease(@RequestBody ReleaseDto releaseDto, UriComponentsBuilder builder) {
        log.info("In http request handler: addRelease ");
        Release added = releaseService.addRelease(releaseDto.toDomain());
        if (added == null) return new ResponseEntity(HttpStatus.CONFLICT);

        URI callback = builder.path("/release").buildAndExpand(added.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(callback);

        return new ResponseEntity(ReleaseDto.toDto(added), headers, HttpStatus.CREATED);
    }

    @PostMapping("/release/{appId}/{releaseId}")
    public ResponseEntity<ReleaseDto> addApplicationToRelease(@PathVariable("appId") int appId, @PathVariable("releaseId") int releaseId) {
        log.info("In http request handler: addApplicationToRelease ");

        Release updated = releaseService.addApplication(appId, releaseId);

        return new ResponseEntity(ReleaseDto.toDto(updated), HttpStatus.OK);
    }

    @GetMapping("/enhancementsWithApp/{applicationId}")
    public ResponseEntity<EnhancementDto> getEnhancementsWithApp(@PathVariable("applicationId") int applicationId) {
        log.info("get enhancements by application id ");

        List<EnhancementDto> found = applicationService.getEnhancementsWithApps(applicationId)
                .stream()
                .map(enhancement -> EnhancementDto.toDto(enhancement))
                .collect(toList());

        return new ResponseEntity(found, HttpStatus.FOUND);

    }


}
