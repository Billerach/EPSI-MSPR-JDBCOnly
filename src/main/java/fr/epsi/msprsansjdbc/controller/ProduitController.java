package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.Produit;
import fr.epsi.msprsansjdbc.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Indique à Spring que cette classe est un contrôleur et doit être gérée par le conteneur Spring
@RequestMapping("/produits") // Toutes les méthodes de cette classe répondent aux requêtes avec le préfixe "/produits"
public class ProduitController {

    // Service responsable de la logique métier liée aux produits.
    private final ProduitService service;

    // Injection du service via l'annotation @Autowired lors de la création du contrôleur.
    @Autowired
    public ProduitController(ProduitService service) {
        this.service = service;
    }

    // Méthode pour afficher la liste des produits actifs
    @GetMapping()
    public String afficherListeProduits(Model model) {
        List<Produit> mesProduits = service.findAllActifs(); // Récupérer la liste des produits actifs depuis le service
        model.addAttribute("produits", mesProduits); // Envoyer la liste des produits à la vue
        System.out.println(mesProduits); // Afficher la liste des produits dans la console (à des fins de débogage)
        return "view-produit-list"; // Retourner le nom de la vue à afficher
    }

    // Méthode pour afficher le formulaire de création d'un produit
    @GetMapping("/creer")
    public String creerProduit(Model model) {
        model.addAttribute("produit", new Produit()); // Envoyer un objet produit vide à la vue pour le remplissage du formulaire
        return "view-produit-form-creation"; // Retourner le nom de la vue du formulaire de création
    }

    // Méthode pour traiter la soumission du formulaire de création d'un produit
    @PostMapping("/creer")
    public String creerProduit(@ModelAttribute Produit produit) {
        service.create(produit); // Créer un nouveau produit en utilisant le service
        return "redirect:/produits"; // Rediriger vers la liste des produits après la création
    }

    // Méthode pour afficher le formulaire de modification d'un produit
    @GetMapping("/edition")
    public String afficherFormulaireEdition(@RequestParam("id_produit") int id_produit, Model model) {
        Produit produit = service.findById(id_produit); // Récupérer le produit à éditer depuis le service
        model.addAttribute("produit", produit); // Envoyer le produit à la vue pour le pré-remplissage du formulaire
        return "view-produit-form-edition"; // Retourner le nom de la vue du formulaire de modification
    }

    // Méthode pour traiter la soumission du formulaire de modification d'un produit
    @PostMapping("/edition")
    public String modifierProduit(@RequestParam("id_produit") int id_produit, @ModelAttribute Produit produit) {
        produit.setId_produit(id_produit); // Définir l'identifiant du produit
        service.update(produit); // Mettre à jour les informations du produit en utilisant le service
        return "redirect:/produits"; // Rediriger vers la liste des produits après la modification
    }

    // Méthode pour supprimer un produit (passer en inactif)
    @GetMapping("/suppression")
    public String supprimerProduit(@RequestParam("id_produit") int id_produit) {
        Produit produit = service.findById(id_produit); // Récupérer le produit à supprimer depuis le service
        produit.setActif(false); // Marquer le produit comme inactif
        service.deleteById(id_produit); // Supprimer le produit de la base de données
        return "redirect:/produits"; // Rediriger vers la liste des produits après la suppression
    }
}