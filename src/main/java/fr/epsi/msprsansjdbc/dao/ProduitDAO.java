package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Produit;

import java.util.List;

// Interface définissant les méthodes pour accéder et manipuler les données des produits
public interface ProduitDAO {

    // Récupère tous les produits actifs
    List<Produit> findAllActifs();

    // Crée un nouveau produit
    Produit create(Produit produit);

    // Récupère un produit par son ID
    Produit findById(int id_produit);

    // Met à jour les informations d'un produit
    Produit update(Produit produit);

    // Désactive un produit par son ID (par exemple, le marque comme inactif)
    void desactiverById(int id_produit);
}
