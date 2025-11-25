package sum25.se196853.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sum25.se196853.ticket.model.Trip;
import sum25.se196853.ticket.service.RouteService;
import sum25.se196853.ticket.service.TripService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private RouteService routeService;

    @GetMapping
    public String listTrips(Model model) {
        List<Trip> trips = tripService.findAll();
        model.addAttribute("trips", trips);
        return "trips/list";
    }

    @GetMapping("/search")
    public String searchTrips(
            @RequestParam String routeCode,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {
        List<Trip> trips = tripService.findByRouteAndDate(routeCode, date);
        model.addAttribute("trips", trips);
        model.addAttribute("searchDate", date);
        return "trips/search-results";
    }

    @GetMapping("/new")
    public String showTripForm(Model model) {
        model.addAttribute("trip", new Trip());
        model.addAttribute("routes", routeService.findActiveRoutes());
        model.addAttribute("tripStatuses", Trip.TripStatus.values());
        return "trips/form";
    }

    @PostMapping
    public String saveTrip(@ModelAttribute Trip trip, RedirectAttributes redirectAttributes) {
        tripService.save(trip);
        redirectAttributes.addFlashAttribute("successMessage", "Chuyến đi đã được lưu thành công!");
        return "redirect:/trips";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Trip> tripOpt = tripService.findById(id);
        if (tripOpt.isPresent()) {
            model.addAttribute("trip", tripOpt.get());
            model.addAttribute("routes", routeService.findActiveRoutes());
            model.addAttribute("tripStatuses", Trip.TripStatus.values());
            return "trips/form";
        }
        return "redirect:/trips";
    }

    @GetMapping("/delete/{id}")
    public String deleteTrip(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        tripService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Chuyến đi đã được xóa thành công!");
        return "redirect:/trips";
    }
}
