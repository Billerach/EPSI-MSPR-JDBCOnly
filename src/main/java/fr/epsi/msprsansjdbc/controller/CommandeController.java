package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.Client;
import fr.epsi.msprsansjdbc.entities.Commande;
import fr.epsi.msprsansjdbc.service.ClientService;
import fr.epsi.msprsansjdbc.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/commandes")
public class CommandeController {
    private final CommandeService service;
    private final ClientService clientService;

    @Autowired
    public CommandeController(CommandeService service, ClientService clientService) {
        this.service = service;
        this.clientService = clientService;
    }

    @GetMapping()
    public String afficherListeCommande(Model model) {
        List<Commande> commandesDetailsList = service.getAllCommandesWithPersonne();
        model.addAttribute("commandes", commandesDetailsList);
        return "view-commande-list";
    }

    @GetMapping("/creer")
    public String creerCommande(Model model) {
        List<Client> clients = clientService.findAllClients();
        model.addAttribute("clients", clients);
        model.addAttribute("commande", new Commande());
        return "view-commande-form-creation";
    }

    @PostMapping("/creer")
    public String creerCommande(@ModelAttribute Commande commande) {
        service.create(commande);
        return "redirect:/commandes";
    }

    @GetMapping("/{id_commande}/edition")
    public String modifierCommande(@PathVariable int id_commande, Model model) {
        model.addAttribute("commande", service.findById(id_commande));
        return "view-commande-form-edition";
    }

    @PostMapping("/{id_commande}/edition")
    public String modifierCommande(@PathVariable int id_commande, @ModelAttribute Commande commande) {
        commande.setId_commande(id_commande);
        service.update(commande);
        return "redirect:/commandes";
    }
}