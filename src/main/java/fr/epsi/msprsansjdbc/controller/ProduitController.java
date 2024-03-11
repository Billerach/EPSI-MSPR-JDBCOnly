package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.Produit;
import fr.epsi.msprsansjdbc.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produits")
public class ProduitController {

    // Service responsable de la logique métier liée aux produits.
    private final ProduitService service;

    // Injection du service via l'annotation @Autowired lors de la création du contrôleur.
    @Autowired
    public ProduitController(ProduitService service) {
        this.service = service;
    }

    @GetMapping()
    public String afficherListeProduits(Model model) {

        //On charge la liste des PRODUITS pour affichage dans la vue
        List<Produit> mesProduits = service.findAll();
        //On envoie la liste à la vue à travers le modèle du MVC
        model.addAttribute("produits", mesProduits);
        System.out.println(mesProduits);
        return "view-produit-list";
    }

    @GetMapping("/creer")
    public String creerProduit(Model model) {
        //On envoie à la vue un nouvel objet Produit vide à remplir depuis le formulaire
        model.addAttribute("produit", new Produit());
        return "view-produit-form-creation";
    }

    @PostMapping("/creer")
    public String creerProduit(@ModelAttribute Produit produit) {
        service.create(produit);
        return "redirect:/produits";
    }

    @GetMapping("/edition")
    public String afficherFormulaireEdition(@RequestParam("id_produit") int id_produit, Model model) {
        // Récupérer le produit à éditer depuis la base de données
        Produit produit = service.findById(id_produit);
        // Ajouter le produit au modèle pour le pré-remplissage du formulaire
        model.addAttribute("produit", produit);

        return "view-produit-form-edition";
    }

    @PostMapping("/edition")
    public String modifierProduit(@RequestParam("id_produit") int id_produit, @ModelAttribute Produit produit) {
        // Comme sur la validation du formulaire de création ici, on fait à peu près la même chose
        produit.setId_produit(id_produit);
        service.update(produit);
        return "redirect:/produits";
    }

    // Exemple de code pour déplacer les données vers la table d'historique avant la suppression
    @GetMapping("/suppression")
    public String supprimerProduit(@RequestParam("id_produit") int id_produit) {
        Produit produit = service.findById(id_produit);

        // Déplacer les données vers la table d'historique
        service.deplacerVersHistorique(produit);

        // Supprimer le produit de la table principale
        service.deleteById(id_produit);

        return "redirect:/produits";
    }
}
