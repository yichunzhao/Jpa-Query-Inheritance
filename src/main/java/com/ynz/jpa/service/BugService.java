package com.ynz.jpa.service;

import com.ynz.jpa.entities.Bug;
import com.ynz.jpa.repositories.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BugService implements IBugService {

    private BugRepository bugRepository;

    @Autowired
    public BugService(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    @Override
    public Bug addBug(Bug bug) {
        return bugRepository.save(bug);
    }
}
