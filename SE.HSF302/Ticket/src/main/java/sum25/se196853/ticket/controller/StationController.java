package sum25.se196853.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sum25.se196853.ticket.model.Station;
import sum25.se196853.ticket.service.StationService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping
    public String listStations(Model model) {
        List<Station> stations = stationService.findAll();
        model.addAttribute("stations", stations);
        return "stations/list";
    }

    @GetMapping("/new")
    public String showStationForm(Model model) {
        model.addAttribute("station", new Station());
        model.addAttribute("stationTypes", Station.StationType.values());
        model.addAttribute("stationStatuses", Station.StationStatus.values());
        return "stations/form";
    }

    @PostMapping
    public String saveStation(@ModelAttribute Station station, RedirectAttributes redirectAttributes) {
        stationService.save(station);
        redirectAttributes.addFlashAttribute("successMessage", "Ga đã được lưu thành công!");
        return "redirect:/stations";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Station> stationOpt = stationService.findById(id);
        if (stationOpt.isPresent()) {
            model.addAttribute("station", stationOpt.get());
            model.addAttribute("stationTypes", Station.StationType.values());
            model.addAttribute("stationStatuses", Station.StationStatus.values());
            return "stations/form";
        }
        return "redirect:/stations";
    }

    @GetMapping("/delete/{id}")
    public String deleteStation(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        stationService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Ga đã được xóa thành công!");
        return "redirect:/stations";
    }
}
