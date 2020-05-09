package com.ynz.jpa.service;

import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.repositories.EnhancementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnhancementService implements IEnhancementService, ITicketService<Enhancement> {

    private EnhancementRepository enhancementRepository;

    public EnhancementService(EnhancementRepository enhancementRepository) {
        this.enhancementRepository = enhancementRepository;
    }

    @Override
    public Enhancement addEnhancement(Enhancement enhancement) {
        return enhancementRepository.save(enhancement);
    }

    @Override
    public List<Enhancement> getAllTickets() {
        return null;
    }

    @Override
    public Enhancement getTicketById(int ticketId) {
        return null;
    }

    @Override
    public void addTicket(Enhancement ticket) {

    }

    @Override
    public void updateTicket(Enhancement ticket) {

    }

    @Override
    public void deleteTicket(int ticketId) {

    }

    @Override
    public void closeTicket(int ticketId) {

    }
}
