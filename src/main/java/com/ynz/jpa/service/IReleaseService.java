package com.ynz.jpa.service;


import com.ynz.jpa.entities.Release;

public interface IReleaseService {
    Release addRelease(Release release);
    Release addApplication(Integer appId, Integer releaseId);
}
