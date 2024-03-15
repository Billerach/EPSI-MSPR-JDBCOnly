package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.Client;
import fr.epsi.msprsansjdbc.entities.Commande;
import fr.epsi.msprsansjdbc.entities.Produit;
import fr.epsi.msprsansjdbc.entities.ContenuCommande;
import fr.epsi.msprsansjdbc.service.ClientService;
import fr.epsi.msprsansjdbc.service.CommandeService;
import fr.epsi.msprsansjdbc.service.ProduitService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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
                                @ModelAttribute Commande commande, //Là tu dis que ce que tu veux créer, c'est un Objet de type Commande
                                HttpServletRequest request) { //Là tu récupère la requete. Elle contient la liste des Produits
        //Puis tu prends l'id du client et tu le mets à id_personne
        commande.setId_personne(idPersonne);

        //Pareil pour l'Objet Client lui-même
        commande.setClient(clientService.findById(idPersonne));

        //Pareil pour la date
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        commande.setDate_commande(date);

        //Puis tu créé l'Objet Commande avec les données récupérées
        service.create(commande);

        // Tu isoles la liste des produits contenu dans la requête
        String[] produitsIds = request.getParameterValues("produit");

        //Là, en base, la table de jointure contenu_commande sert à lier les commandes
        //aux produits. Donc, pour chaque fromage différent, tu créé un objet de type
        // ContenuCommande qui va contenir son propre id, l'id de la commande,
        // l'id du produit et la quantité. Pour pas que tout se retrouve ici,
        // on a créé un autre controller pour les Objets ContenuCommande. Il contient
        // la méthode creerContenuCommande que l'on appelle pour chaque produit.

        //Création d'une Map pour stocker les quantités
        Map<Integer, Integer> quantites = new HashMap<>();
        //Pour chaque produit, on récupère la quantité et on l'associe à son produit
        for (String produitId : produitsIds) {
            int quantite = Integer.parseInt(request.getParameter("quantite_" + produitId));
            quantites.put(Integer.parseInt(produitId), quantite);
        }

        // Appeler la méthode creerContenuCommande pour chaque couple produit/quantité
        for (Map.Entry<Integer, Integer> entry : quantites.entrySet()) {
            int produitId = entry.getKey();
            int quantite = entry.getValue();

            ContenuCommande contenuCommande = new ContenuCommande();
            contenuCommande.setCommande(commande);
            contenuCommande.setProduit(produit);
            contenuCommande.setQuantite(quantite);

            // Enregistrer le ContenuCommande en base de données ou effectuer toute autre opération nécessaire
             contenuCommandeService.save(contenuCommande);
        }


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