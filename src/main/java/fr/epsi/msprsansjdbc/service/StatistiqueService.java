package fr.epsi.msprsansjdbc.service;

import fr.epsi.msprsansjdbc.dao.StatistiqueDAO;
import fr.epsi.msprsansjdbc.entities.Statistique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service pour gérer les opérations liées aux statistiques
@Service
public class StatistiqueService {

    private final StatistiqueDAO statistiqueDAO; // Référence vers le DAO pour interagir avec la base de données

    // Injection de dépendance du DAO via le constructeur
    @Autowired
    public StatistiqueService(StatistiqueDAO statistiqueDAO) {
        this.statistiqueDAO = statistiqueDAO;
    }

    // Méthode pour récupérer les produits les plus vendus
    public List<Statistique> getProduitLesPlusVendu() {
        return statistiqueDAO.getProduitLesPlusVendu();
    }

    // Méthode pour récupérer les produits les moins vendus
    public List<Statistique> getProduitLesMoinsVendu() {
        return statistiqueDAO.getProduitLesMoinsVendu();
    }

    // Méthode pour récupérer le chiffre d'affaires par mois
    public List<Statistique> getChiffreAffaireParMois() {
        return statistiqueDAO.getChiffreAffaireParMois();
    }

    // Méthode pour récupérer le chiffre d'affaires par année
    public List<Statistique> getChiffreAffaireParAnnee() {
        return statistiqueDAO.getChiffreAffaireParAnnee();
    }
}


