package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.HistoriqueProduit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoriqueProduitDAOImpl implements HistoriqueProduitDAO {

    private final JdbcTemplate jdbcTemplate;

    // Injection du JdbcTemplate via le constructeur
    public HistoriqueProduitDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<HistoriqueProduit> findAll() {
        // Implémentation de la récupération de tous les historiques depuis la base de données
        // Utilisez le jdbcTemplate pour exécuter des requêtes SQL
        return null;
    }

    @Override
    public HistoriqueProduit findById(int id) {
        // Implémentation de la récupération d'un historique par son ID depuis la base de données
        return null;
    }

    @Override
    public HistoriqueProduit save(HistoriqueProduit historiqueProduit) {
        return null;
    }

    @Override
    public void create(HistoriqueProduit historiqueProduit) {

    }

    @Override
    public void deleteById(int id) {
        // Implémentation de la suppression d'un historique par son ID depuis la base de données
    }
}
