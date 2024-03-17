package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.Client;
import fr.epsi.msprsansjdbc.entities.Commande;
import fr.epsi.msprsansjdbc.entities.ContenuCommande;
import fr.epsi.msprsansjdbc.entities.Produit;
import fr.epsi.msprsansjdbc.service.ClientService;
import fr.epsi.msprsansjdbc.service.CommandeService;
import fr.epsi.msprsansjdbc.service.ProduitService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(CommandeController.class);
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
    public String afficherListeCommande(Model commande) {
        List<Commande> commandesDetailsList = service.getAllCommandes();
        commandesDetailsList.sort(Comparator.comparingInt(Commande::getId_commande));
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
                                HttpServletRequest request) { //Là tu récupère la requete. Elle contient la liste des Produits et les quantités
        //Puis tu prends l'id du client et tu le mets à id_personne
        commande.setId_personne(idPersonne);

        //Pareil pour l'Objet Client lui-même
        commande.setClient(clientService.findById(idPersonne));

        //Pareil pour la date
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        commande.setDate_commande(date);

        //Récupération du montant total de la commande
        float montantTotal;
        String montantTotalParam = request.getParameter("prixTotal");
        if (montantTotalParam != null && !montantTotalParam.isEmpty()) {
            try {
                montantTotal = Float.parseFloat(montantTotalParam);
                commande.setMontant_total(montantTotal);
            } catch (NumberFormatException e) {
                logger.error("Le montant total de la commande n'est pas un nombre valide : {}", montantTotalParam, e);
                throw new IllegalArgumentException("Le montant total de la commande n'est pas un nombre valide");
            }
        }

        //Puis tu créé l'Objet Commande avec les données récupérées
        service.create(commande);

        // Tu isoles la liste des produits contenu dans la requête
        String[] produitsIds = request.getParameterValues("produit");
        String[] produitsQuantites = request.getParameterValues("quantite_${produit.id_produit}");

        //Là, en base, la table de jointure contenu_commande sert à lier les commandes
        //aux produits. Donc, pour chaque fromage différent, tu créé un objet de type
        // ContenuCommande qui va contenir son propre id, l'id de la commande,
        // l'id du produit et la quantité. Pour pas que tout se retrouve ici,
        // on a créé un autre controller pour les Objets ContenuCommande. Il contient
        // la méthode creerContenuCommande que l'on appelle pour chaque produit.


        // On s'assure que les deux tableaux ont la même longueur
        if (produitsIds.length == produitsQuantites.length) {
            //A chaque index des 2 tableaux, on récupère l'id du produit et la quantité
            for (int i = 0; i < produitsIds.length; i++) {
                int produitId = Integer.parseInt(produitsIds[i]);
                int quantite = Integer.parseInt(produitsQuantites[i]);
                //Et on les injecte dans un Objet ContenuCommande
                contenuCommandeController.creerContenuCommande(commande.getId_commande(), produitId, quantite);
                }
            }
        else{
            throw new IllegalArgumentException("Les tableaux produitsIds et produitsQuantites n'ont pas la même longueur");
            }

        return "redirect:/commandes";
    }

    @GetMapping("/{id_commande}/show")
    public String detailsCommande(@PathVariable int id_commande, Model model) {
        // Récupérer les détails de la commande à partir de son identifiant
        Commande commande = service.findById(id_commande);

        // Vérifier si la commande existe
        if (commande != null) {
            // Ajouter les détails de la commande à l'objet Model
            model.addAttribute("commande", commande);

            // Récupérer les données du client associé à la commande
            Client client = commande.getClient();
            if (client != null) {
                // Ajouter les détails du client à l'objet Model
                model.addAttribute("client", client);
            } else {
                logger.error("Le client associé à la commande n'est pas trouvé");
            }

            // Récupérer les données du produit associé à la commande
            //TODO : créer une méthode pour récupérer les contenus de la commande
//            List<ContenuCommande> contenusCommandes = commande.getContenuCommande();
//            if (contenuCommande != null) {
//                int produitId = contenuCommande.getId_produit();
//                Produit produit = produitService.findById(produitId);
//                if (produit != null) {
//                    // Ajouter les détails du produit à l'objet Model
//                    model.addAttribute("produit", produit);
//                } else {
//                    logger.error("Ce produit n'est pas associé à cette commande(Pas de contenuCommande pour ce produit)");
//                }
//            } else {
//                logger.error("Aucun contenuCommande n'est associé à cette commande");
//            }

            // Retourner la vue pour afficher les détails de la commande
            return "view-commande-form-show";
        } else {
            logger.error("La commande avec l'identifiant {} n'est pas trouvée", id_commande);
            return "redirect:/commandes"; // Rediriger vers la page des commandes
        }
    }


    // Méthode pour traiter la suppression d'une commande
    @GetMapping("/suppression")
    public String supprimerCommande(@RequestParam("id") int id_commande) {
        service.deleteCommmande(id_commande);
        return "redirect:/commandes";
    }
}