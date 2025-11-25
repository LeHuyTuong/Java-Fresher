package sum25.se196853.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sum25.se196853.ticket.model.Route;
import sum25.se196853.ticket.model.Station;
import sum25.se196853.ticket.service.RouteService;
import sum25.se196853.ticket.service.StationService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private StationService stationService;

    @Autowired
    private RouteService routeService;

    @GetMapping("/")
    public String home(Model model) {
        List<Station> stations = stationService.findActiveStations();
        List<Route> routes = routeService.findActiveRoutes();

        model.addAttribute("stations", stations);
        model.addAttribute("routes", routes);

        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
