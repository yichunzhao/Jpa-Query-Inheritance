package com.ynz.jpa.service;

import com.ynz.jpa.entities.Application;
import com.ynz.jpa.repositories.ApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService implements IApplicationService {
    private ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public boolean addApplication(Application application) {
        return false;
    }

    @Override
    public Application getApplicationById(int applicationId) {
        return null;
    }

    @Override
    public void updateApplication(Application application) {

    }

    @Override
    public void deleteApplication(int applicationId) {

    }
}
