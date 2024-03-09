package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.dao.StatistiqueDAO;
import fr.epsi.msprsansjdbc.entities.Statistique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatistiqueDAOImpl extends StatistiqueDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StatistiqueDAOImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Statistique> getProduitLesPlusVendu() {
        String sqlQuery = "SELECT produits.nom, COUNT(contenu_commande.id_contenu_commande) " +
                "FROM produits " +
                "LEFT JOIN contenu_commande ON produits.id_produit = contenu_commande.id_produit " +
                "GROUP BY produits.nom " +
                "ORDER BY COUNT(contenu_commande.id_contenu_commande) DESC LIMIT 10";

        return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(Statistique.class));
    }

    @Override
    public List<Statistique> getProduitLesMoinsVendu() {
        String sqlQuery = "SELECT produits.nom, COUNT(contenu_commande.id_contenu_commande) " +
                "FROM produits " +
                "LEFT JOIN contenu_commande ON produits.id_produit = contenu_commande.id_produit " +
                "GROUP BY produits.nom " +
                "ORDER BY COUNT(contenu_commande.id_contenu_commande) ASC LIMIT 10";

        return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(Statistique.class));
    }

    @Override
    public List<Statistique> getChiffreAffaireParMois() {
        String sqlQuery = "SELECT DATE_FORMAT(commandes.date_commande, '%Y-%m') AS mois, SUM(commandes.montant_total) AS chiffreAffaire " +
                "FROM commandes " +
                "GROUP BY DATE_FORMAT(commandes.date_commande, '%Y-%m') " +
                "ORDER BY DATE_FORMAT(commandes.date_commande, '%Y-%m')";

        return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(Statistique.class));
    }

    @Override
    public List<Statistique> getChiffreAffaireParAnnee() {
        String sqlQuery = "SELECT YEAR(commandes.date_commande) AS annee, SUM(commandes.montant_total) AS chiffreAffaire " +
                "FROM commandes " +
                "GROUP BY YEAR(commandes.date_commande) " +
                "ORDER BY YEAR(commandes.date_commande)";

        return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(Statistique.class));
    }
}


