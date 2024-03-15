package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.Client;
import fr.epsi.msprsansjdbc.entities.Commande;
import fr.epsi.msprsansjdbc.entities.Produit;
import fr.epsi.msprsansjdbc.entities.ContenuCommande;
import fr.epsi.msprsansjdbc.service.ClientService;
import fr.epsi.msprsansjdbc.service.CommandeService;
import fr.epsi.msprsansjdbc.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/commandes")
public class CommandeController {

    private final CommandeService service;
    private final ClientService clientService;
    private final ProduitService produitService;

    @Autowired
    public CommandeController(CommandeService service, ClientService clientService, ProduitService produitService) {
        this.service = service;
        this.clientService = clientService;
        this.produitService = produitService;
    }

    @GetMapping()
    public String afficherListeCommande(Model commande, Model client) {
        List<Commande> commandesDetailsList = service.getAllCommandes();
        commande.addAttribute("commandes", commandesDetailsList);
        return "view-commande-list";
    }

    @GetMapping("/creer")
    public String creerCommande(Model model) {
        List<Client> clients = clientService.findAllClients();
        model.addAttribute("clients", clients);
        model.addAttribute("commande", new Commande());
        List<Produit> produits = produitService.findAllActifs();
        model.addAttribute("produits", produits);

        return "view-commande-form-creation";
    }

    @Autowired
    private ContenuCommandeController contenuCommandeController;

    @PostMapping("/creer")//Méthode qui récupère les données du formulaire
    public String creerCommande(@RequestParam("personne") int idPersonne,
                                @ModelAttribute Commande commande) { //Là tu dis que ce que tu veux créer, c'est un Objet de type Commande
        commande.setId_personne(idPersonne); //Puis tu prends l'id du client et tu le mets à id_personne
        commande.setClient(clientService.findById(idPersonne));//Pareil pour l'Objet Client lui-même
        //Pareil pour la date
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        commande.setDate_commande(date);
        //Puis tu créé l'Objet Commande avec les données récupérées
        service.create(commande);

        //Là, en base, la table de jointure contenu_commande sert à lier les commandes aux produits
        //Donc, pour chaque fromage différent, tu créé un objet de type ContenuCommande
        //qui va contenir son propre id, l'id de la commande, l'id du produit et la quantité
        // Pour pas que tout se retrouve ici, on a créé un autre ctrler pour les Objets ContenuCommande
        // Appel de la méthode creerContenuCommande du ContenuCommandeController
        contenuCommandeController.creerContenuCommande(commande);

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