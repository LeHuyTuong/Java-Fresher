package sum25.se196853.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sum25.se196853.ticket.model.Booking;
import sum25.se196853.ticket.model.Ticket;
import sum25.se196853.ticket.model.Trip;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByBooking(Booking booking);
    List<Ticket> findByTrip(Trip trip);
    Ticket findByCode(String code);
}
