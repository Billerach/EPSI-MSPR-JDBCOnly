package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Commande;

import java.util.List;

public interface CommandeDAO {

    List<Commande> getAllCommandesWithPersonne();

    List<Commande> findAll();

    Commande create(Commande commande);

    Commande findById(int id_commande);

    Commande update(Commande commande);

    void deleteById(int id_commande);

    String getNomProduit(int idCommande);

    String getClient(int idPersonne);

    int getQuantite(int idCommande);

    float getTotal(int idCommande);
}
