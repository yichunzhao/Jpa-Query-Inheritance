package com.ynz.jpa.service;


import com.ynz.jpa.entities.Ticket;

import java.util.List;

public interface ITicketService<T extends Ticket> {
    List<T> getAllTickets();

    T getTicketById(int ticketId);

    T addTicket(T ticket);

    T updateTicket(T ticket);

    void deleteTicket(int ticketId);

    void closeTicket(int ticketId);
}
