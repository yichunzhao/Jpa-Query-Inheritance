package com.ynz.jpa.service;

import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.exceptions.NotFoundException;
import com.ynz.jpa.repositories.ApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApplicationService implements IApplicationService {
    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application addApplication(Application application) {
        boolean existed = applicationRepository.applicationExists(application.getName(), application.getOwner());
        if (existed) return null;

        return applicationRepository.save(application);
    }

    @Override
    public Application getApplicationById(int applicationId) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application " + applicationId + " is not found."));
    }

    @Override
    public List<Enhancement> getEnhancementsWithApps(int applicationId) {
        return applicationRepository.getEnhancementsWithApps(applicationId);
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
