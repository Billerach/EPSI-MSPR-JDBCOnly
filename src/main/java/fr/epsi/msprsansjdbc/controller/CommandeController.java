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

        //On charge la liste des COMMANDES pour affichage dans la vue
        List<Commande> mesCommandes = service.findAll();
        //On envoie la liste à la vue à travers le modèle du MVC
        model.addAttribute("commandes", mesCommandes);
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
}
