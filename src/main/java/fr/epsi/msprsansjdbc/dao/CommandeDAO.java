package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Commande;

import java.util.List;

public interface CommandeDAO {

    // Méthode pour récupérer toutes les commandes
    List<Commande> findAll();

    // Méthode pour créer une commande
    Commande create(Commande commande);

    // Méthode pour récupérer une commande par son id
    Commande findById(int id);

}
