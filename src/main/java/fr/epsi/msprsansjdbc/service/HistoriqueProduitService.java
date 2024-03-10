package fr.epsi.msprsansjdbc.service;

import fr.epsi.msprsansjdbc.dao.HistoriqueProduitDAO;
import fr.epsi.msprsansjdbc.entities.HistoriqueProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriqueProduitService {

    private final HistoriqueProduitDAO historiqueProduitDAO;

    // Injection du DAO via le constructeur
    @Autowired
    public HistoriqueProduitService(HistoriqueProduitDAO historiqueProduitDAO) {
        this.historiqueProduitDAO = historiqueProduitDAO;
    }

    public List<HistoriqueProduit> findAll() {
        return historiqueProduitDAO.findAll();
    }

    public HistoriqueProduit findById(int id) {
        return historiqueProduitDAO.findById(id);
    }

    public HistoriqueProduit create(HistoriqueProduit historiqueProduit) {
        return historiqueProduitDAO.save(historiqueProduit);
    }

    public void deleteById(int id) {
        historiqueProduitDAO.deleteById(id);
    }
}
