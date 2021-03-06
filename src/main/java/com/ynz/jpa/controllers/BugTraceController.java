package com.ynz.jpa.controllers;

import com.ynz.jpa.converter.ApplicationConverter;
import com.ynz.jpa.converter.BugConverter;
import com.ynz.jpa.converter.EnhancementConverter;
import com.ynz.jpa.converter.ReleaseConverter;
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
@CrossOrigin
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

    @Autowired
    private ApplicationConverter applicationConverter;

    @Autowired
    private BugConverter bugConverter;

    @Autowired
    private ReleaseConverter releaseConverter;

    @Autowired
    private EnhancementConverter enhancementConverter;

    @PostMapping("/application")
    public ResponseEntity<ApplicationDto> createApplication(@RequestBody ApplicationDto applicationDto,
                                                            UriComponentsBuilder builder) {
        log.info("in http request handler: createApplication ");
        Application added = applicationService.addApplication(applicationConverter.toDomain(applicationDto));

        if (added == null) return new ResponseEntity(null, null, HttpStatus.CONFLICT);

        URI callback = builder.path("/trace/application").pathSegment("{id}").buildAndExpand(added.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(callback);

        return new ResponseEntity(applicationConverter.toDto(added), headers, HttpStatus.CREATED);
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<ApplicationDto> getApplication(@PathVariable("id") int id) {
        log.info("in http request handler: getApplication ");
        Application found = applicationService.getApplicationById(id);
        return new ResponseEntity(applicationConverter.toDto(found), HttpStatus.FOUND);
    }

    @PostMapping("/bug")
    public ResponseEntity<BugDto> createBug(@RequestBody BugDto bugDto, UriComponentsBuilder builder) {
        log.info("In http request handler: createBug ");
        //Bug added = bugService.addBug(bugDto.toDomain());
        Bug added = bugService.addTicket(bugConverter.toDomain(bugDto));

        if (added == null) return new ResponseEntity(HttpStatus.CONFLICT);

        URI callback = builder.path("/bug").buildAndExpand(added.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(callback);

        return new ResponseEntity(bugConverter.toDto(added), headers, HttpStatus.CREATED);
    }

    @PostMapping("/enhancement")
    public ResponseEntity<Enhancement> createEnhancement(@RequestBody EnhancementDto enhancementDto, UriComponentsBuilder builder) {
        log.info("In http request handler: createEnhancement ");
        //Enhancement added = enhancementService.addEnhancement(enhancementDto.toDomain());
        Enhancement added = enhancementService.addTicket(enhancementConverter.toDomain(enhancementDto));

        if (added == null) return new ResponseEntity<>(HttpStatus.CONFLICT);

        URI callback = builder.path("/enhancement").buildAndExpand(added.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(callback);

        return new ResponseEntity(enhancementConverter.toDto(added), headers, HttpStatus.CREATED);
    }

    @PostMapping("/release")
    public ResponseEntity<ReleaseDto> createRelease(@RequestBody ReleaseDto releaseDto, UriComponentsBuilder builder) {
        log.info("In http request handler: addRelease ");
        Release added = releaseService.addRelease(releaseConverter.toDomain(releaseDto));
        if (added == null) return new ResponseEntity(HttpStatus.CONFLICT);

        return new ResponseEntity(releaseConverter.toDto(added), HttpStatus.CREATED);
    }

    @PostMapping("/release/{releaseId}/application/{appId}")
    public ResponseEntity<ReleaseDto> addApplicationToRelease(@PathVariable("appId") int appId,
                                                              @PathVariable("releaseId") int releaseId) {
        log.info("In http request handler: addApplicationToRelease ");

        Release updated = releaseService.addApplication(appId, releaseId);

        return new ResponseEntity(releaseConverter.toDto(updated), HttpStatus.OK);
    }

    @GetMapping("/enhancementsWithApp/{applicationId}")
    public ResponseEntity<EnhancementDto> getEnhancementsWithApp(
            @PathVariable("applicationId") int applicationId, UriComponentsBuilder builder) {

        log.info("get enhancements by application id ");

        List<EnhancementDto> found = applicationService.getEnhancementsWithApps(applicationId)
                .stream()
                .map(enhancement -> enhancementConverter.toDto(enhancement))
                .collect(toList());

        URI callBack = builder.path("trace").path("enhancementsWithApp")
                .pathSegment("{applicationId}").buildAndExpand(applicationId).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(callBack);

        return new ResponseEntity(found, headers, HttpStatus.FOUND);
    }


}
