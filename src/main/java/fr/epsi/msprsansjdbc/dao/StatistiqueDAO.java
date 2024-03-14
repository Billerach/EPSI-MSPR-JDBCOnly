package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Statistique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Indique que cette classe est un composant de persistance géré par Spring
public class StatistiqueDAO {

    // Injection du bean JdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    // Constructeur avec injection de dépendances
    @Autowired // Injection du bean JdbcTemplate
    public StatistiqueDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Méthode pour récupérer les produits les plus vendus
    public List<Statistique> getProduitLesPlusVendu() {
        String sqlQuery = "SELECT produits.nom, COUNT(contenu_commande.id_contenu_commande) " +
                "FROM produits " +
                "LEFT JOIN contenu_commande ON produits.id_produit = contenu_commande.id_produit " +
                "GROUP BY produits.nom " +
                "ORDER BY COUNT(contenu_commande.id_contenu_commande) DESC LIMIT 10";

        // Exécute la requête SQL et mappe les résultats sur des objets Statistique
        return jdbcTemplate.query(sqlQuery, (rs, rowNum) -> {
            Statistique statistique = new Statistique();
            statistique.setNomProduit(rs.getString(1));
            statistique.setNombreVentes(rs.getInt(2));
            return statistique;
        });
    }

    // Méthode pour récupérer les produits les moins vendus
    public List<Statistique> getProduitLesMoinsVendu() {
        String sqlQuery = "SELECT produits.nom, COUNT(contenu_commande.id_contenu_commande) " +
                "FROM produits " +
                "LEFT JOIN contenu_commande ON produits.id_produit = contenu_commande.id_produit " +
                "GROUP BY produits.nom " +
                "ORDER BY COUNT(contenu_commande.id_contenu_commande) ASC LIMIT 10";

        // Exécute la requête SQL et mappe les résultats sur des objets Statistique
        return jdbcTemplate.query(sqlQuery, (rs, rowNum) -> {
            Statistique statistique = new Statistique();
            statistique.setNomProduit(rs.getString(1));
            statistique.setNombreVentes(rs.getInt(2));
            return statistique;
        });
    }

    // Méthode pour récupérer le chiffre d'affaires par mois
    public List<Statistique> getChiffreAffaireParMois() {
        String sqlQuery = "SELECT DATE_FORMAT(commandes.date_commande, '%Y-%m'), SUM(commandes.montant_total) " +
                "FROM commandes " +
                "GROUP BY DATE_FORMAT(commandes.date_commande, '%Y-%m') " +
                "ORDER BY DATE_FORMAT(commandes.date_commande, '%Y-%m')";

        // Exécute la requête SQL et mappe les résultats sur des objets Statistique
        return jdbcTemplate.query(sqlQuery, (rs, rowNum) -> {
            Statistique statistique = new Statistique();
            statistique.setMois(rs.getString(1));
            statistique.setChiffreAffaire(rs.getBigDecimal(2));
            return statistique;
        });
    }

    // Méthode pour récupérer le chiffre d'affaires par année
    public List<Statistique> getChiffreAffaireParAnnee() {
        String sqlQuery = "SELECT YEAR(commandes.date_commande), SUM(commandes.montant_total) " +
                "FROM commandes " +
                "GROUP BY YEAR(commandes.date_commande) " +
                "ORDER BY YEAR(commandes.date_commande)";

        // Exécute la requête SQL et mappe les résultats sur des objets Statistique
        return jdbcTemplate.query(sqlQuery, (rs, rowNum) -> {
            Statistique statistique = new Statistique();
            statistique.setAnnee(rs.getInt(1));
            statistique.setChiffreAffaire(rs.getBigDecimal(2));
            return statistique;
        });
    }
}
