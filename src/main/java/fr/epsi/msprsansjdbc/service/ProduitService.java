package fr.epsi.msprsansjdbc.service;

import fr.epsi.msprsansjdbc.dao.ProduitDAO;
import fr.epsi.msprsansjdbc.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service pour gérer les opérations liées aux produits
@Service
public class ProduitService {

    private final ProduitDAO dao; // Référence vers le DAO pour interagir avec la base de données

    // Injection de dépendance du DAO via le constructeur
    @Autowired
    public ProduitService(ProduitDAO dao) {
        this.dao = dao;
    }

    // Méthode pour trouver tous les produits actifs
    public List<Produit> findAllActifs() {
        return dao.findAllActifs();
    }

    // Méthode pour créer un nouveau produit
    public Produit create(Produit produit) {
        return dao.create(produit);
    }

    // Méthode pour trouver un produit par son ID
    public Produit findById(int id) {
        return dao.findById(id);
    }

    // Méthode pour mettre à jour les informations d'un produit
    public void update(Produit produit) {
        dao.update(produit);
    }

    // Méthode pour désactiver un produit par son ID
    public void deleteById(int id_produit) {
        Produit produit = dao.findById(id_produit);

        if (produit != null) {
            // Désactiver le produit au lieu de le supprimer
            produit.setActif(false);
            dao.desactiverById(id_produit);
        }
    }
}