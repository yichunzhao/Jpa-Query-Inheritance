package com.ynz.jpa.service;

import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Release;
import com.ynz.jpa.exceptions.NotFoundException;
import com.ynz.jpa.repositories.ReleaseRepository;
import org.springframework.stereotype.Service;

@Service
public class ReleaseService implements IReleaseService {
    private final ReleaseRepository releaseRepository;
    private final ApplicationService applicationService;

    public ReleaseService(ReleaseRepository releaseRepository, ApplicationService applicationService) {
        this.releaseRepository = releaseRepository;
        this.applicationService = applicationService;
    }

    @Override
    public Release addRelease(Release release) {
        return releaseRepository.save(release);
    }

    public Release getReleaseById(int releaseId) {
        return releaseRepository.findById(releaseId)
                .orElseThrow(() -> new NotFoundException("Release " + releaseId + " is not found"));
    }

    public Release updateRelease(Release release) {
        Release found = getReleaseById(release.getId());
        found.setApplication(release.getApplication());
        found.setBugs(release.getBugs());
        found.setEnhancements(release.getEnhancements());
        found.setDescription(release.getDescription());
        found.setReleaseDate(release.getReleaseDate());

        return releaseRepository.save(found);
    }

    //associate a release to an application.
    @Override
    public Release addApplication(Integer appId, Integer releaseId) {
        Release release = getReleaseById(releaseId);
        Application application = applicationService.getApplicationById(appId);

        //this method is designed to build a bilateral relationship.
        release.linkToApplication(application);

        return releaseRepository.save(release);
    }
}
