package sum25.se196853.ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.se196853.ticket.model.Booking;
import sum25.se196853.ticket.model.User;
import sum25.se196853.ticket.repository.BookingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> findById(Integer id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> findByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    public List<Booking> findPendingBookings() {
        return bookingRepository.findByStatus(Booking.BookingStatus.pending);
    }

    public Booking createBooking(Booking booking) {
        // Set default values
        booking.setStatus(Booking.BookingStatus.pending);
        booking.setExpiresAt(LocalDateTime.now().plusMinutes(15)); // Booking expires in 15 minutes

        return bookingRepository.save(booking);
    }

    public Booking updateBookingStatus(Integer id, Booking.BookingStatus status) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setStatus(status);

            if (status == Booking.BookingStatus.paid) {
                booking.setPaidAt(LocalDateTime.now());
            }

            return bookingRepository.save(booking);
        }
        return null;
    }

    public void deleteById(Integer id) {
        bookingRepository.deleteById(id);
    }
}
