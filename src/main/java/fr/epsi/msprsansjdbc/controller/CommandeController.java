package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.Commande;
import fr.epsi.msprsansjdbc.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/commandes")
public class CommandeController {

    // Service responsable de la logique métier liée aux commandes.
    private final CommandeService service;

    // Injection du service via l'annotation @Autowired lors de la création du contrôleur.
    @Autowired
    public CommandeController(CommandeService service) {
        this.service = service;
    }

    @GetMapping()
    public String afficherListeCommande(Model model) {

        // Récupérez toutes les informations nécessaires dans le service (CommandeService)
        List<Commande> commandes = service.findAll();

        // Ajoutez les informations au modèle
        model.addAttribute("commandes", commandes);

        return "view-commande-list";
    }

    @GetMapping("/creer")
    public String creerCommande(Model model) {
        //On envoie à la vue l'objet vide à remplir depuis le formulaire
        model.addAttribute("commande", new Commande());
        return "view-commande-form-creation";
    }

    @PostMapping("/creer")
    public String creerCommande(@ModelAttribute Commande commande) {
        service.create(commande);
        return "redirect:/commandes";
    }

    @GetMapping("/{id}/edition")
    public String modifierCommande(@PathVariable int id_commande, Model model) {
        model.addAttribute("commande", service.findById(id_commande));
        return "view-commande-form-edition";
    }

    @PostMapping("/{id}/edition")
    public String modifierCommande(@PathVariable int id_commande, @ModelAttribute Commande commande) {
        //Comme sur la validation du formulaire de création, ici on fait à peu près la même chose
        commande.setId_commande(id_commande);
        service.update(commande);
        return "redirect:/commandes";
    }
}