package com.ynz.jpa.service;

import com.ynz.jpa.entities.Enhancement;

import java.util.List;


public interface IEnhancementService {
    Enhancement addEnhancement(Enhancement enhancement);
    List<Enhancement> getEnhancementsWithApp();
}
