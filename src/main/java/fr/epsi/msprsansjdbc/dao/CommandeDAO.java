package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Commande;

import java.util.List;

public interface CommandeDAO {
    List<Commande> getAllCommandes();
    List<Commande> findAll();
    Commande create(Commande commande);
    Commande findById(int id_commande);
    Commande update(Commande commande);
    void deleteCommmande(int id_commande);
}
