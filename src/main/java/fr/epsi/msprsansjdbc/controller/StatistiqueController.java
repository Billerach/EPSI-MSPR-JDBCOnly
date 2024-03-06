package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.Statistique;
import fr.epsi.msprsansjdbc.service.StatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistiques")
public class StatistiqueController {

    private final StatistiqueService service;

    @Autowired
    public StatistiqueController(StatistiqueService service) {
        this.service = service;
    }

    @GetMapping("/afficher-statistiques")
    public String afficherStatistiques(Model model) {
        // Appeler les méthodes de service pour récupérer les statistiques
        // Clients
        List<Statistique> clientsAvecNombreProduits = service.getClientsAvecNombreProduitsAchetes();
        List<Statistique> clientsAvecNombreCommandes = service.getClientsAvecNombreCommandes();
        List<Statistique> clientsAvecMontantTotalDepense = service.getClientsAvecMontantTotalDepense();
        List<Statistique> clientsAvecMontantTotalDepenseAsc = service.getClientsAvecMontantTotalDepenseAsc();
        List<Statistique> clientsAvecMontantMoyenDepenseParCommande = service.getClientsAvecMontantMoyenDepenseParCommande();
        // Produits
        List<Statistique> produitsAvecNombreClients = service.getProduitsAvecNombreClientsAchetes();
        List<Statistique> produitsAvecNombreCommandes = service.getProduitsAvecNombreCommandes();
        List<Statistique> produitsAvecChiffreAffairesTotal = service.getProduitsAvecChiffreAffairesTotal();
        List<Statistique> produitsAvecNombreClientsAsc = service.getProduitsAvecNombreClientsAchetesAsc();
        List<Statistique> produitsAvecNombreCommandesAsc = service.getProduitsAvecNombreCommandesAsc();
        List<Statistique> produitsAvecTypeLaitEtChiiffreAffairesTotal = service.getProduitsAvecTypeLaitEtChiffreAffairesTotal();
        List<Statistique> produitsAvecNombreMoyenCommandes = service.getProduitsAvecNombreMoyenCommandes();
        List<Statistique> produitsAvecChiffreAffairesTotalAsc = service.getProduitsAvecChiffreAffairesTotalAsc();
        List<Statistique> produitsAvecTypeLaitEtNombreCommandes = service.getProduitsAvecTypeLaitEtNombreCommandes();
        List<Statistique> produitsAvecDepartementEtNombreCommandes = service.getProduitsAvecDepartementEtNombreCommandes();
        List<Statistique> produitsPlusVendus = service.getProduitsPlusVendus();
        List<Statistique> produitsMoinsVendus = service.getProduitsMoinsVendus();

        // Ajouter toutes les statistiques au modèle
        model.addAttribute("clientsAvecNombreProduits", clientsAvecNombreProduits);
        model.addAttribute("produitsAvecNombreClients", produitsAvecNombreClients);
        model.addAttribute("clientsAvecNombreCommandes", clientsAvecNombreCommandes);
        model.addAttribute("produitsAvecNombreCommandes", produitsAvecNombreCommandes);
        model.addAttribute("clientsAvecMontantTotalDepense", clientsAvecMontantTotalDepense);
        model.addAttribute("produitsAvecChiffreAffairesTotal", produitsAvecChiffreAffairesTotal);
        model.addAttribute("produitsAvecNombreClientsAsc", produitsAvecNombreClientsAsc);
        model.addAttribute("produitsAvecNombreCommandesAsc", produitsAvecNombreCommandesAsc);
        model.addAttribute("clientsAvecMontantTotalDepenseAsc", clientsAvecMontantTotalDepenseAsc);
        model.addAttribute("produitsAvecTypeLaitEtChiiffreAffairesTotal", produitsAvecTypeLaitEtChiiffreAffairesTotal);
        model.addAttribute("clientsAvecMontantMoyenDepenseParCommande", clientsAvecMontantMoyenDepenseParCommande);
        model.addAttribute("produitAvecNombreMoyenCommandes", produitsAvecNombreMoyenCommandes);
        model.addAttribute("produitsAvecChiffreAffairesTotalAsc", produitsAvecChiffreAffairesTotalAsc);
        model.addAttribute("produitsAvecTypeLaitEtNombreCommandes", produitsAvecTypeLaitEtNombreCommandes);
        model.addAttribute("produitsAvecDepartementEtNombreCommandes", produitsAvecDepartementEtNombreCommandes);
        model.addAttribute("produitsPlusVendus", produitsPlusVendus);
        model.addAttribute("produitsMoinsVendus", produitsMoinsVendus);

        return "view-statistiques";
    }
}

