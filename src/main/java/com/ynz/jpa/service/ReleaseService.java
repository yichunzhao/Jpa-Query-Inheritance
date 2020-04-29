package com.ynz.jpa.service;

import com.ynz.jpa.entities.Release;
import com.ynz.jpa.repositories.ReleaseRepository;
import org.springframework.stereotype.Service;

@Service
public class ReleaseService implements IReleaseService{
    private ReleaseRepository releaseRepository;

    public ReleaseService(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    @Override
    public void addRelease(Release release) {

        releaseRepository.save(release);

    }

    @Override
    public void addApplication(Integer appId, Integer releaseId) {



    }
}
