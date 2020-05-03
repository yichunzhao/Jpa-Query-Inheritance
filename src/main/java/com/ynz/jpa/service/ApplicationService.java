package com.ynz.jpa.service;

import com.ynz.jpa.entities.Application;
import com.ynz.jpa.exceptions.NotFoundException;
import com.ynz.jpa.repositories.ApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService implements IApplicationService {
    private ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application addApplication(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public Application getApplicationById(int applicationId) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application " + applicationId + " is not found."));
    }

    @Override
    public void updateApplication(Application application) {
        Application found = getApplicationById(application.getId());
        found.setOwner(application.getOwner());
        found.setDescription(application.getDescription());
        found.setName(application.getName());
        applicationRepository.save(found);
    }

    @Override
    public void deleteApplication(int applicationId) {
        getApplicationById(applicationId);
        applicationRepository.deleteById(applicationId);
    }
}
