package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.Client;
import fr.epsi.msprsansjdbc.entities.Commande;
import fr.epsi.msprsansjdbc.entities.Produit;
import fr.epsi.msprsansjdbc.service.ClientService;
import fr.epsi.msprsansjdbc.service.CommandeService;
import fr.epsi.msprsansjdbc.service.ProduitService;
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

    @PostMapping("/creer")
    public String creerCommande(@RequestParam("personne") int idPersonne, @ModelAttribute Commande commande) {
        commande.setId_personne(idPersonne);
        commande.setClient(clientService.findById(idPersonne));
        service.create(commande);
        return "redirect:/commandes";
    }
//depuis la vue

//     <label for="produit">Produit:</label>
//                            <select id="produit" class="product-select" name="produit" onchange="updatePrice()">
//                                <option th:each="produit : ${produits}" th:value="${produit.id_produit}" th:data-prix="${produit.prix}" th:text="${produit.nom}"></option>
//                            </select>
//
//                            <label for="quantite">Quantité:</label>
//                            <input id ="quantite" type="number" class="quantity" name="quantite" oninput="updatePrice()" required/>
//
//                            <label for="prixUnitaire">Prix Unitaire:</label>
//                            <input type="number" class="prixUnitaire" id="prixUnitaire" name="prixUnitaire" readonly/>
//
//                            <label for="prixTotal">Prix Total:</label>
//                            <input id="prixTotal" type="number" class="prixTotal" name="prixTotal" readonly/>
//
//                            <button type="button" onclick="removeProductRow(this.parentNode)">Supprimer</button>
//                            <button type="button" onclick="addProductRow()">Ajouter un produit</button>

//**********



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