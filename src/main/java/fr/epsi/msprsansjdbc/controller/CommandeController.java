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

    //Dans le développement multi-couche et notamment le MVC, le controller s'appuie sur d'autres briques
    // pour réaliser ces traitements... c'est la raison pour laquelle, nous avons besoin ici d'un objet service
    // dont la création est déléguée à Spring Core
    private final CommandeService service;

    // Injection du service via l'annotation @Autowired lors de la création du contrôleur.
    @Autowired
    public CommandeController(CommandeService service) {
        this.service = service;
    }

    @GetMapping()
    public String afficherListeCommande(Model model) {
        List<Commande> commandesDetailsList = service.getAllCommandesWithPersonne();
        model.addAttribute("commandes", commandesDetailsList);
        return "view-commande-list";
    }

    @GetMapping("/creer")
    public String creerCommande(Model model) {
        Commande nouvelleCommande = new Commande();
        service.create(nouvelleCommande);
        model.addAttribute("commande", nouvelleCommande);
        return "view-commande-form-creation";
    }


    @PostMapping("/creer")
    public String creerCommande(@ModelAttribute Commande commande, @RequestParam("id_personne") int id_personne, Model model) {
        // Assurez-vous que la propriété id_personne est correctement définie
        commande.setId_personne(id_personne);

        // Ajoutez la commande au modèle pour qu'elle soit disponible dans la vue
        model.addAttribute("commande", commande);

        // Appel du service pour créer la commande
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