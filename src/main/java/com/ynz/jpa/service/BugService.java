package com.ynz.jpa.service;

import com.ynz.jpa.entities.Bug;
import com.ynz.jpa.exceptions.NotFoundException;
import com.ynz.jpa.repositories.BugRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("bugService")
@Transactional
public class BugService implements ITicketService<Bug> {

    private BugRepository bugRepository;

    public BugService(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    @Override
    public Bug addTicket(Bug bug) {
        return bugRepository.save(bug);
    }

    @Override
    public List<Bug> getAllTickets() {
        List<Bug> bugs = new ArrayList<>();
        bugRepository.findAll().forEach(bug -> bugs.add(bug));
        return bugs;
    }

    @Override
    public Bug getTicketById(int ticketId) {
        return bugRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Ticket: " + ticketId + " is not found."));
    }

    @Override
    public Bug updateTicket(Bug ticket) {
        getTicketById(ticket.getId());
        return bugRepository.save(ticket);
    }

    @Override
    public void deleteTicket(int ticketId) {
        getTicketById(ticketId);
        bugRepository.deleteById(ticketId);
    }

    @Override
    public void closeTicket(int ticketId) {

    }
}

