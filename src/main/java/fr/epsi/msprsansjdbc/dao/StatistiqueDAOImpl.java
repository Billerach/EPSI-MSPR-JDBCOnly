package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Statistique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

// Classe pour accéder et manipuler les données des statistiques
@Repository
public class StatistiqueDAOImpl extends StatistiqueDAO {

    // Injection du bean JdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    // Constructeur avec injection de dépendances
    @Autowired
    public StatistiqueDAOImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate); // Appelle le constructeur de la classe mère
        this.jdbcTemplate = jdbcTemplate; // Injection du bean JdbcTemplate
    }

    // Méthode pour récupérer les produits les plus vendus
    @Override
    public List<Statistique> getProduitLesPlusVendu() {
        String sqlQuery = "SELECT produits.nom, COUNT(contenu_commande.id_contenu_commande) " + // Sélectionne le nom du produit et le nombre de ventes
                "FROM produits " + // Sélectionne les produits
                "LEFT JOIN contenu_commande ON produits.id_produit = contenu_commande.id_produit " + // Jointure avec la table contenu_commande
                "GROUP BY produits.nom " + // Regroupe les résultats par nom de produit
                "ORDER BY COUNT(contenu_commande.id_contenu_commande) DESC LIMIT 10"; // Trie les résultats par nombre de ventes et limite à 10

        // Exécute la requête SQL et mappe les résultats sur des objets Statistique
        return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(Statistique.class));
    }

    // Méthode pour récupérer les produits les moins vendus
    @Override
    public List<Statistique> getProduitLesMoinsVendu() {
        String sqlQuery = "SELECT produits.nom, COUNT(contenu_commande.id_contenu_commande) " + // Sélectionne le nom du produit et le nombre de ventes
                "FROM produits " + // Sélectionne les produits
                "LEFT JOIN contenu_commande ON produits.id_produit = contenu_commande.id_produit " + // Jointure avec la table contenu_commande
                "GROUP BY produits.nom " + // Regroupe les résultats par nom de produit
                "ORDER BY COUNT(contenu_commande.id_contenu_commande) ASC LIMIT 10"; // Trie les résultats par nombre de ventes et limite à 10
        // Exécute la requête SQL et mappe les résultats sur des objets Statistique
        return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(Statistique.class));
    }

    // Méthode pour récupérer le chiffre d'affaires par mois
    @Override
    public List<Statistique> getChiffreAffaireParMois() {
        String sqlQuery = "SELECT DATE_FORMAT(commandes.date_commande, '%Y-%m') AS mois, SUM(commandes.montant_total) AS chiffreAffaire " + // Sélectionne le mois et le chiffre d'affaires
                "FROM commandes " + // Sélectionne les commandes
                "GROUP BY DATE_FORMAT(commandes.date_commande, '%Y-%m') " + // Regroupe les résultats par mois
                "ORDER BY DATE_FORMAT(commandes.date_commande, '%Y-%m')"; // Trie les résultats par mois

        // Exécute la requête SQL et mappe les résultats sur des objets Statistique
        return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(Statistique.class));
    }

    // Méthode pour récupérer le chiffre d'affaires par année
    @Override
    public List<Statistique> getChiffreAffaireParAnnee() {
        String sqlQuery = "SELECT YEAR(commandes.date_commande) AS annee, SUM(commandes.montant_total) AS chiffreAffaire " + // Sélectionne l'année et le chiffre d'affaires
                "FROM commandes " + // Sélectionne les commandes
                "GROUP BY YEAR(commandes.date_commande) " + // Regroupe les résultats par année
                "ORDER BY YEAR(commandes.date_commande)"; // Trie les résultats par année

        // Exécute la requête SQL et mappe les résultats sur des objets Statistique
        return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(Statistique.class));
    }
}