package com.ynz.jpa.service;

import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.exceptions.NotFoundException;
import com.ynz.jpa.repositories.EnhancementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnhancementService implements ITicketService<Enhancement> {

    private EnhancementRepository enhancementRepository;

    public EnhancementService(EnhancementRepository enhancementRepository) {
        this.enhancementRepository = enhancementRepository;
    }

    @Override
    public Enhancement addTicket(Enhancement ticket) {
        return enhancementRepository.save(ticket);
    }

    @Override
    public List<Enhancement> getAllTickets() {
        List<Enhancement> enhancements = new ArrayList<>();
        enhancementRepository.findAll().forEach(enhancement -> enhancements.add(enhancement));
        return enhancements;
    }

    @Override
    public Enhancement getTicketById(int ticketId) {
        return enhancementRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Enhancement: " + ticketId + " is not found."));
    }

    @Override
    public Enhancement updateTicket(Enhancement ticket) {
        getTicketById(ticket.getId());
        return enhancementRepository.save(ticket);
    }

    @Override
    public void deleteTicket(int ticketId) {
        getTicketById(ticketId);
        enhancementRepository.deleteById(ticketId);
    }

    @Override
    public void closeTicket(int ticketId) {

    }
}
