package sum25.se196853.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sum25.se196853.ticket.model.Booking;
import sum25.se196853.ticket.model.Seat;
import sum25.se196853.ticket.model.Ticket;
import sum25.se196853.ticket.model.Trip;
import sum25.se196853.ticket.service.BookingService;
import sum25.se196853.ticket.service.SeatService;
import sum25.se196853.ticket.service.TicketService;
import sum25.se196853.ticket.service.TripService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TripService tripService;

    @Autowired
    private SeatService seatService;

    @GetMapping
    public String listBookings(Model model) {
        List<Booking> bookings = bookingService.findAll();
        model.addAttribute("bookings", bookings);
        return "bookings/list";
    }

    @GetMapping("/start/{tripId}")
    public String startBookingProcess(@PathVariable Integer tripId, Model model) {
        Optional<Trip> tripOpt = tripService.findById(tripId);

        if (tripOpt.isPresent()) {
            Trip trip = tripOpt.get();
            // Get available seats for the trip
            List<Seat> availableSeats = seatService.findByTrainCodeAndIsActiveTrue(trip.getTrainCode());

            model.addAttribute("trip", trip);
            model.addAttribute("availableSeats", availableSeats);
            model.addAttribute("booking", new Booking());

            return "bookings/start";
        }

        return "redirect:/trips";
    }

    @PostMapping
    public String saveBooking(@ModelAttribute Booking booking, @RequestParam Integer tripId,
                              @RequestParam List<Integer> seatIds, RedirectAttributes redirectAttributes) {

        // Create a new booking
        Booking savedBooking = bookingService.createBooking(booking);

        // Create tickets for each selected seat
        for (Integer seatId : seatIds) {
            Ticket ticket = new Ticket();
            ticket.setBooking(savedBooking);

            Optional<Trip> tripOpt = tripService.findById(tripId);
            Optional<Seat> seatOpt = seatService.findById(seatId);

            if (tripOpt.isPresent() && seatOpt.isPresent()) {
                ticket.setTrip(tripOpt.get());
                ticket.setSeat(seatOpt.get());
                // More ticket details would be set here in a real implementation

                ticketService.createTicket(ticket);
            }
        }

        redirectAttributes.addFlashAttribute("successMessage", "Đặt vé thành công! Vui lòng thanh toán trong vòng 15 phút.");
        return "redirect:/bookings/details/" + savedBooking.getId();
    }

    @GetMapping("/details/{id}")
    public String viewBookingDetails(@PathVariable Integer id, Model model) {
        Optional<Booking> bookingOpt = bookingService.findById(id);

        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            List<Ticket> tickets = ticketService.findByBooking(booking);

            model.addAttribute("booking", booking);
            model.addAttribute("tickets", tickets);

            return "bookings/details";
        }

        return "redirect:/bookings";
    }

    @GetMapping("/pay/{id}")
    public String payForBooking(@PathVariable Integer id, Model model) {
        Optional<Booking> bookingOpt = bookingService.findById(id);

        if (bookingOpt.isPresent()) {
            model.addAttribute("booking", bookingOpt.get());
            model.addAttribute("paymentMethods", Booking.PaymentMethod.values());

            return "bookings/payment";
        }

        return "redirect:/bookings";
    }

    @PostMapping("/pay/{id}")
    public String processPayment(@PathVariable Integer id,
                                 @RequestParam Booking.PaymentMethod paymentMethod,
                                 RedirectAttributes redirectAttributes) {

        // In a real app, this would integrate with payment gateways
        bookingService.updateBookingStatus(id, Booking.BookingStatus.paid);

        // Update all tickets status to paid
        Optional<Booking> bookingOpt = bookingService.findById(id);
        if (bookingOpt.isPresent()) {
            List<Ticket> tickets = ticketService.findByBooking(bookingOpt.get());
            for (Ticket ticket : tickets) {
                ticketService.updateTicketStatus(ticket.getId(), Ticket.TicketStatus.paid);
            }
        }

        redirectAttributes.addFlashAttribute("successMessage", "Thanh toán thành công!");
        return "redirect:/bookings/details/" + id;
    }

    @GetMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        bookingService.updateBookingStatus(id, Booking.BookingStatus.cancelled);

        // Update all tickets status to cancelled
        Optional<Booking> bookingOpt = bookingService.findById(id);
        if (bookingOpt.isPresent()) {
            List<Ticket> tickets = ticketService.findByBooking(bookingOpt.get());
            for (Ticket ticket : tickets) {
                ticketService.updateTicketStatus(ticket.getId(), Ticket.TicketStatus.cancelled);
            }
        }

        redirectAttributes.addFlashAttribute("successMessage", "Đã hủy đặt vé!");
        return "redirect:/bookings";
    }
}
