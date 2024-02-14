package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.TicketService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/buy")
    public String getById(@ModelAttribute Ticket ticket, Model model, RedirectAttributes attributes) {
        Ticket ticketSaved = ticketService.save(ticket);
        if (ticketSaved != null) {
            attributes.addFlashAttribute("success", ticketSaved);
            return "redirect:/film-sessions";
        }
        attributes.addFlashAttribute("error", "");
        return "redirect:/film-sessions";
    }
}
