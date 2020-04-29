package com.ynz.jpa.service;

import com.ynz.jpa.entities.Ticket;
import com.ynz.jpa.repositories.BugRepository;
import com.ynz.jpa.repositories.EnhancementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements ITicketService {
    private BugRepository bugRepository;
    private EnhancementRepository enhancementRepository;

    public TicketService(BugRepository bugRepository, EnhancementRepository enhancementRepository) {
        this.bugRepository = bugRepository;
        this.enhancementRepository = enhancementRepository;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return null;
    }

    @Override
    public Ticket getTicketById(int ticketId) {
        return null;
    }

    @Override
    public void addTicket(Ticket ticket) {

    }

    @Override
    public void updateTicket(Ticket ticket) {

    }

    @Override
    public void deleteTicket(int ticketId) {

    }

    @Override
    public void closeTicket(int ticketId) {

    }
}
