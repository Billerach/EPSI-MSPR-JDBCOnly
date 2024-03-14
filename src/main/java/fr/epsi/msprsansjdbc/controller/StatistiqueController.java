package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.Statistique;
import fr.epsi.msprsansjdbc.service.StatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // Indique à Spring que cette classe est un contrôleur et doit être gérée par le conteneur Spring
@RequestMapping("/statistiques") // Toutes les méthodes de cette classe répondent aux requêtes avec le préfixe "/statistiques"
public class StatistiqueController {

    private final StatistiqueService statistiqueService;

    @Autowired // Injection de dépendance : Spring va automatiquement injecter une instance de StatistiqueService lors de la création du contrôleur
    public StatistiqueController(StatistiqueService statistiqueService) {
        this.statistiqueService = statistiqueService;
    }

    // Méthode pour afficher la page des statistiques
    @GetMapping
    public String afficherPageStatistiques() {
        return "view-statistiques"; // Retourne le nom de la vue à afficher
    }

    // Méthode pour afficher les produits les plus vendus
    @GetMapping("/produits-plus-vendus")
    public String produitsPlusVendus(Model model) {
        List<Statistique> produitsPlusVendus = statistiqueService.getProduitLesPlusVendu(); // Récupère les produits les plus vendus depuis le service
        model.addAttribute("produitsPlusVendus", produitsPlusVendus); // Ajoute les produits les plus vendus à l'objet Model pour l'affichage dans la vue
        return "view-statistiques-produits-plus-vendus"; // Retourne le nom de la vue à afficher
    }

    // Méthode pour afficher les produits les moins vendus
    @GetMapping("/produits-moins-vendus")
    public String produitsMoinsVendus(Model model) {
        List<Statistique> produitsMoinsVendus = statistiqueService.getProduitLesMoinsVendu(); // Récupère les produits les moins vendus depuis le service
        model.addAttribute("produitsMoinsVendus", produitsMoinsVendus); // Ajoute les produits les moins vendus à l'objet Model pour l'affichage dans la vue
        return "view-statistiques-produits-moins-vendus"; // Retourne le nom de la vue à afficher
    }

    // Méthode pour afficher le chiffre d'affaires par mois
    @GetMapping("/chiffre-affaire-par-mois")
    public String chiffreAffaireParMois(Model model) {
        List<Statistique> chiffreAffaireParMois = statistiqueService.getChiffreAffaireParMois(); // Récupère le chiffre d'affaires par mois depuis le service
        model.addAttribute("chiffreAffaireParMois", chiffreAffaireParMois); // Ajoute le chiffre d'affaires par mois à l'objet Model pour l'affichage dans la vue
        return "view-statistiques-chiffre-affaire-par-mois"; // Retourne le nom de la vue à afficher
    }

    // Méthode pour afficher le chiffre d'affaires par année
    @GetMapping("/chiffre-affaire-par-annee")
    public String chiffreAffaireParAnnee(Model model) {
        List<Statistique> chiffreAffaireParAnnee = statistiqueService.getChiffreAffaireParAnnee(); // Récupère le chiffre d'affaires par année depuis le service
        model.addAttribute("chiffreAffaireParAnnee", chiffreAffaireParAnnee); // Ajoute le chiffre d'affaires par année à l'objet Model pour l'affichage dans la vue
        return "view-statistiques-chiffre-affaire-par-annee"; // Retourne le nom de la vue à afficher
    }
}




