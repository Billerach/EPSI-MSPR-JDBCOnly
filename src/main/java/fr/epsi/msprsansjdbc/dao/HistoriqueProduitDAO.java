package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.HistoriqueProduit;

import java.util.List;

public interface HistoriqueProduitDAO {

    List<HistoriqueProduit> findAll();

    HistoriqueProduit findById(int id);

    HistoriqueProduit save(HistoriqueProduit historiqueProduit);

    void deleteById(int id);

    void create(HistoriqueProduit historiqueProduit);
}
