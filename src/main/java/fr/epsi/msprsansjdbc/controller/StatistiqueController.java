package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.Statistique;
import fr.epsi.msprsansjdbc.service.StatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/statistiques")
public class StatistiqueController {

    private final StatistiqueService statistiqueService;

    @Autowired
    public StatistiqueController(StatistiqueService statistiqueService) {
        this.statistiqueService = statistiqueService;
    }

    @GetMapping
    public String afficherPageStatistiques() {
        return "view-statistiques";
    }

    @GetMapping("/produits-plus-vendus")
    public String produitsPlusVendus(Model model) {
        List<Statistique> produitsPlusVendus = statistiqueService.getProduitLesPlusVendu();
        model.addAttribute("produitsPlusVendus", produitsPlusVendus);
        return "view-statistiques-produits-plus-vendus";
    }

    @GetMapping("/produits-moins-vendus")
    public String produitsMoinsVendus(Model model) {
        List<Statistique> produitsMoinsVendus = statistiqueService.getProduitLesMoinsVendu();
        model.addAttribute("produitsMoinsVendus", produitsMoinsVendus);
        return "view-statistiques-produits-moins-vendus";
    }

    @GetMapping("/chiffre-affaire-par-mois")
    public String chiffreAffaireParMois(Model model) {
        List<Statistique> chiffreAffaireParMois = statistiqueService.getChiffreAffaireParMois();
        model.addAttribute("chiffreAffaireParMois", chiffreAffaireParMois);
        return "view-statistiques-chiffre-affaire-par-mois";
    }

    @GetMapping("/chiffre-affaire-par-annee")
    public String chiffreAffaireParAnnee(Model model) {
        List<Statistique> chiffreAffaireParAnnee = statistiqueService.getChiffreAffaireParAnnee();
        model.addAttribute("chiffreAffaireParAnnee", chiffreAffaireParAnnee);
        return "view-statistiques-chiffre-affaire-par-annee";
    }
}




