package com.ynz.jpa.service;

import com.ynz.jpa.entities.Bug;
import com.ynz.jpa.repositories.BugRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugService implements IBugService, ITicketService<Bug> {

    private BugRepository bugRepository;

    public BugService(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    @Override
    public Bug addBug(Bug bug) {
        return null;
    }

    @Override
    public List<Bug> getAllTickets() {
        return null;
    }

    @Override
    public Bug getTicketById(int ticketId) {
        return null;
    }

    @Override
    public void addTicket(Bug ticket) {

    }

    @Override
    public void updateTicket(Bug ticket) {

    }

    @Override
    public void deleteTicket(int ticketId) {

    }

    @Override
    public void closeTicket(int ticketId) {

    }
}

