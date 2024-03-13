package fr.epsi.msprsansjdbc.service;

import fr.epsi.msprsansjdbc.dao.StatistiqueDAO;
import fr.epsi.msprsansjdbc.entities.Statistique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatistiqueService {
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


