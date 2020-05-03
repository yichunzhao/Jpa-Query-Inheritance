package com.ynz.jpa.service;


import com.ynz.jpa.entities.Application;

public interface IApplicationService {
    Application addApplication(Application application);

    Application getApplicationById(int applicationId);

    void updateApplication(Application application);

    void deleteApplication(int applicationId);
}
