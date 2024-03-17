package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Client;
import fr.epsi.msprsansjdbc.entities.ContenuCommande;

import java.util.List;

public interface ContenuCommandeDAO {
    ContenuCommande create(ContenuCommande contenuCommande);
    List<ContenuCommande> getContenuCommandeList(int id_commande);
}
