package fr.epsi.msprsansjdbc.service;

import fr.epsi.msprsansjdbc.dao.HistoriqueProduitDAO;
import fr.epsi.msprsansjdbc.dao.ProduitDAO;
import fr.epsi.msprsansjdbc.entities.HistoriqueProduit;
import fr.epsi.msprsansjdbc.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProduitService {

    private final ProduitDAO dao;

    @Autowired
    public ProduitService(ProduitDAO dao) {
        this.dao = dao;
    }

    @Autowired
    private HistoriqueProduitDAO historiqueProduitDAO;

    public List<Produit> findAllActifs() {
        return dao.findAllActifs();
    }

    public Produit create(Produit Produit) {
        return dao.create(Produit);
    }

    public Produit findById(int id) {
        return dao.findById(id);
    }

    public void update(Produit Produit) {
        dao.update(Produit);
    }

    public void deleteById(int id_produit) {
        Produit produit = dao.findById(id_produit);

        if (produit != null) {
            // Désactiver le produit au lieu de le supprimer
            produit.setActif(false);
            dao.desactiverById(id_produit);
        }
    }

    public void deplacerVersHistorique(Produit produit) {
        // Créer une instance d'historique avec les mêmes données que le produit
        HistoriqueProduit historiqueProduit = new HistoriqueProduit(
                produit.getId_produit(),
                produit.getNom(),
                produit.getDepartement(),
                produit.getLait(),
                produit.getPrix(),
                LocalDateTime.now()
        );

        // Enregistrer dans la table d'historique
        historiqueProduitDAO.create(historiqueProduit);
    }
}