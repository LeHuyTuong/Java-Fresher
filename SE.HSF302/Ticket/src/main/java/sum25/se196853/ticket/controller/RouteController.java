package sum25.se196853.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sum25.se196853.ticket.model.Route;
import sum25.se196853.ticket.service.RouteService;
import sum25.se196853.ticket.service.StationService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private StationService stationService;

    @GetMapping
    public String listRoutes(Model model) {
        List<Route> routes = routeService.findAll();
        model.addAttribute("routes", routes);
        return "routes/list";
    }

    @GetMapping("/new")
    public String showRouteForm(Model model) {
        model.addAttribute("route", new Route());
        model.addAttribute("stations", stationService.findActiveStations());
        model.addAttribute("routeStatuses", Route.RouteStatus.values());
        return "routes/form";
    }

    @PostMapping
    public String saveRoute(@ModelAttribute Route route, RedirectAttributes redirectAttributes) {
        routeService.save(route);
        redirectAttributes.addFlashAttribute("successMessage", "Tuyến đường đã được lưu thành công!");
        return "redirect:/routes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Route> routeOpt = routeService.findById(id);
        if (routeOpt.isPresent()) {
            model.addAttribute("route", routeOpt.get());
            model.addAttribute("stations", stationService.findActiveStations());
            model.addAttribute("routeStatuses", Route.RouteStatus.values());
            return "routes/form";
        }
        return "redirect:/routes";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoute(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        routeService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Tuyến đường đã được xóa thành công!");
        return "redirect:/routes";
    }
}
