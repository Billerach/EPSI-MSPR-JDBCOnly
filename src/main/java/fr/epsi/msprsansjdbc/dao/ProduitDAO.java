package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Produit;

import java.util.List;

public interface ProduitDAO {

    // Récupère tous les produits actifs
    List<Produit> findAllActifs();

    // Crée un nouveau produit
    Produit create(Produit produit);

    // Récupère un produit par son ID
    Produit findById(int id_produit);

    // Met à jour les informations d'un produit
    Produit update(Produit produit);

    // Supprime un produit par son ID
    void desactiverById(int id_produit);
}