package fr.epsi.msprsansjdbc.service;

import fr.epsi.msprsansjdbc.dao.StatistiqueDAO;
import fr.epsi.msprsansjdbc.entities.Statistique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatistiqueService {
 // Implémentez des méthodes dans cette classe pour récupérer les informations requises,
 //telles que les produits les plus vendus, les moins vendus, le chiffre d'affaires par mois et par année, etc.

    private final StatistiqueDAO statistiqueDAO;

        @Autowired
        public StatistiqueService(StatistiqueDAO statistiqueDAO) {
            this.statistiqueDAO = statistiqueDAO;
        }

        public List<Statistique> getProduitLesPlusVendu() {
            return statistiqueDAO.getProduitLesPlusVendu();
        }

        public List<Statistique> getProduitLesMoinsVendu() {
            return statistiqueDAO.getProduitLesMoinsVendu();
        }

        public List<Statistique> getChiffreAffaireParMois() {
            return statistiqueDAO.getChiffreAffaireParMois();
        }

        public List<Statistique> getChiffreAffaireParAnnee() {
            return statistiqueDAO.getChiffreAffaireParAnnee();
        }
}


