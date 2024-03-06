package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Produit;

import java.util.List;

public interface ProduitDAO {

    List<Produit> findAll();

    Produit create(Produit produit);

    Produit findById(int id);

    Produit update(Produit produit);



    void deleteById(int id);
}
