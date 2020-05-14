package com.ynz.jpa.service;


import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Enhancement;

import java.util.List;

public interface IApplicationService {
    Application addApplication(Application application);

    Application getApplicationById(int applicationId);

    List<Enhancement> getEnhancementsWithApps(int applicationId);

    void updateApplication(Application application);

    void deleteApplication(int applicationId);

}
