package sum25.se196853.ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.se196853.ticket.model.Booking;
import sum25.se196853.ticket.model.Ticket;
import sum25.se196853.ticket.model.Trip;
import sum25.se196853.ticket.repository.TicketRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findById(Integer id) {
        return ticketRepository.findById(id);
    }

    public Ticket findByCode(String code) {
        return ticketRepository.findByCode(code);
    }

    public List<Ticket> findByBooking(Booking booking) {
        return ticketRepository.findByBooking(booking);
    }

    public List<Ticket> findByTrip(Trip trip) {
        return ticketRepository.findByTrip(trip);
    }

    public Ticket createTicket(Ticket ticket) {
        // Generate a unique ticket code
        ticket.setCode("TKT" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        ticket.setStatus(Ticket.TicketStatus.booked);
        ticket.setBookedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public Ticket updateTicketStatus(Integer id, Ticket.TicketStatus status) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setStatus(status);

            if (status == Ticket.TicketStatus.checked_in) {
                ticket.setCheckedInAt(LocalDateTime.now());
            }

            return ticketRepository.save(ticket);
        }
        return null;
    }

    public void deleteById(Integer id) {
        ticketRepository.deleteById(id);
    }
}
