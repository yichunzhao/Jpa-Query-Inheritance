package com.ynz.jpa.service;


import com.ynz.jpa.entities.Release;

public interface IReleaseService {
    void addRelease(Release release);
    void addApplication(Integer appId, Integer releaseId);
}
