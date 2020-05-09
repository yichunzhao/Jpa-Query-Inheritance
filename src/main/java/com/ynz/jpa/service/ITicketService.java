package com.ynz.jpa.service;


import com.ynz.jpa.entities.Ticket;

import java.util.List;

public interface ITicketService<T extends Ticket> {
    List<T> getAllTickets();

    T getTicketById(int ticketId);

    void addTicket(T ticket);

    void updateTicket(T ticket);

    void deleteTicket(int ticketId);

    void closeTicket(int ticketId);
}
