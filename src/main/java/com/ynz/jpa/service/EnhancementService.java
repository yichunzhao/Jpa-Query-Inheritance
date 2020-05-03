package com.ynz.jpa.service;

import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.repositories.EnhancementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnhancementService implements IEnhancementService {
    @Autowired
    private EnhancementRepository enhancementRepository;

    @Override
    public Enhancement addEnhancement(Enhancement enhancement) {
        return enhancementRepository.save(enhancement);
    }
}
