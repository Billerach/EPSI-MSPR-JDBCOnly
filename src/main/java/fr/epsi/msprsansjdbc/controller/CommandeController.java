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
        for (Commande commandeEnCour : commandesDetailsList) {
            Client client = commandeEnCour.getClient();
            if (client.getNom() == null || client.getPrenom() == null) {
                client.setNom("Client ");
                client.setPrenom("archivé");
            }
        }
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
        String montantTotalParam = request.getParameter("prixTotalGlobal");
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
            Client client = clientService.findById(commande.getId_personne());
            if (client != null) {

                if (client.isEstArchive() == true) {
                    client.setNom("Client archivé");
                    client.setPrenom("");
                    client.setNumeroVoie("");
                    client.setTypeVoie("");
                    client.setLibelleVoie("");
                    client.setCommune("");
                    client.setCodePostal(44000);
                    client.setEmail("");
                    client.setTelephone("");
                }

                // Ajouter les détails du client à l'objet Model
                model.addAttribute("client", client);
            } else {
                if (client.isEstArchive() == true) {
                client.setNom("Client archivé");
                client.setPrenom("");
                client.setNumeroVoie("");
                client.setTypeVoie("");
                client.setLibelleVoie("");
                client.setCommune("");
                client.setCodePostal(44000);
                client.setEmail("");
                client.setTelephone("");
                }
                logger.error("Le client associé à la commande n'est pas trouvé");
            }

            // Récupérer les données du produit associé à la commande :

            //Tout d'abord, je créé la liste des Objets contenuCommande associés à la commande
            List<ContenuCommande> contenusCommandes = contenuCommandeController.getContenuCommandeList(id_commande);
            //puis on s'assure que la requete a bien renvoyé des résultats
            if (contenusCommandes != null) {
                //On créé des Objets Produits que l'on ajoute à chaque contenuCommande pour faciliter la manipulation de l'ensemble
                //Pour ça, on itère sur la liste des contenusCommandes,
                //les instructions suivantes sont répétées pour chaque Objet contenuCommande
                for (ContenuCommande contenuCommande : contenusCommandes) {
                    int produitId = contenuCommande.getId_produit();//Récupération de l'id du produit
                    Produit produit = produitService.findById(produitId);//Puis des données du Produit lui-même
                    if (produit != null) {
                        contenuCommande.setProduit(produit);//On ajoute cet Objet Produit comme attribut de l'Objet contenuCommande
                    } else {
                        logger.error("L'identifiant du produit associé à ce contenuCommande est invalide");
                    }
                    //On s'assure que l'id récupéré correspond bien à un produit en base
                }
                //On ajoute la liste des contenusCommandes à l'objet Model
                model.addAttribute("produits", contenusCommandes);
            } else {
                logger.error("Aucun contenuCommande n'est associé à cette commande");
            }

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