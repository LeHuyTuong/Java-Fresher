package sum25.se196853.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sum25.se196853.ticket.model.Ticket;
import sum25.se196853.ticket.service.TicketService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public String listTickets(Model model) {
        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("tickets", tickets);
        return "tickets/list";
    }

    @GetMapping("/{id}")
    public String viewTicketDetails(@PathVariable Integer id, Model model) {
        Optional<Ticket> ticketOpt = ticketService.findById(id);

        if (ticketOpt.isPresent()) {
            model.addAttribute("ticket", ticketOpt.get());
            return "tickets/details";
        }

        return "redirect:/tickets";
    }

    @GetMapping("/check-in/{id}")
    public String checkInTicket(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Ticket ticket = ticketService.updateTicketStatus(id, Ticket.TicketStatus.checked_in);

        if (ticket != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Check-in thành công cho vé: " + ticket.getCode());
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể check-in cho vé này");
        }

        return "redirect:/tickets/" + id;
    }

    @GetMapping("/search")
    public String searchTicketForm() {
        return "tickets/search";
    }

    @PostMapping("/search")
    public String searchTicket(@RequestParam String code, Model model) {
        Ticket ticket = ticketService.findByCode(code);

        if (ticket != null) {
            return "redirect:/tickets/" + ticket.getId();
        } else {
            model.addAttribute("errorMessage", "Không tìm thấy vé với mã: " + code);
            return "tickets/search";
        }
    }
}
