package fr.epsi.poec24.mspr.epsimspr.dao;

import fr.epsi.poec24.mspr.epsimspr.entities.Produit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProduitDAO {

    // Informations de connexion à la base de données
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mspr_crm_acme";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "Root";

    // Logger pour la journalisation
    private static final Logger logger = LoggerFactory.getLogger(ProduitDAO.class);

    // Récupérer tous les produits depuis la base de données
    public List<Produit> getAllProduits() {
        logger.info("Récupération de tous les produits depuis la base de données.");
        List<Produit> produits = new ArrayList<>();

        // Connexion à la base de données
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            // Requête SQL pour récupérer tous les produits
            String sql = "SELECT * FROM produits";

            // Préparer la déclaration SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Exécuter la requête SQL
                try (ResultSet result = statement.executeQuery()) {
                    // Parcourir les résultats
                    while (result.next()) {
                        // Créer un objet Produit pour chaque résultat
                        Produit produit = new Produit();
                        produit.setNom(result.getString("nom"));
                        produit.setDepartement(result.getString("departement"));
                        produit.setLait(result.getString("lait"));

                        // Ajouter le produit à la liste
                        produits.add(produit);
                    }
                }
            }
        } catch (SQLException exception) {
            // Gérer les exceptions liées à la base de données
            logger.error("Erreur lors de la récupération de tous les produits.", exception);
            throw new IllegalStateException("Impossible de se connecter à la base de données !", exception);
        }

        // Renvoyer la liste des produits récupérés
        return produits;
    }

    // Ajouter un nouveau produit dans la base de données
    public Produit addProduit(Produit produit) {
        logger.info("Ajout du produit : {}", produit);

        // Connexion à la base de données
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            // Requête SQL pour ajouter un nouveau produit
            String sql = "INSERT INTO produits (nom, departement, lait) VALUES (?, ?, ?)";

            // Préparer la déclaration SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Remplir les paramètres de la requête
                statement.setString(1, produit.getNom());
                statement.setString(2, produit.getDepartement());
                statement.setString(3, produit.getLait());

                // Exécuter la requête SQL
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            // Gérer les exceptions liées à la base de données
            logger.error("Erreur lors de l'ajout du produit.", exception);
            throw new IllegalStateException("Impossible de se connecter à la base de données !", exception);
        }

        // Renvoyer le produit ajouté
        return produit;
    }

    // Modifier un produit existant dans la base de données
    public Produit updateProduit(Produit produit) {
        logger.info("Modification du produit : {}", produit);

        // Connexion à la base de données
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            // Requête SQL pour mettre à jour un produit existant
            String sql = "UPDATE produits SET departement = ?, lait = ? WHERE nom = ?";

            // Préparer la déclaration SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Remplir les paramètres de la requête
                statement.setString(1, produit.getDepartement());
                statement.setString(2, produit.getLait());
                statement.setString(3, produit.getNom());

                // Exécuter la requête SQL
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            // Gérer les exceptions liées à la base de données
            logger.error("Erreur lors de la modification du produit.", exception);
            throw new IllegalStateException("Impossible de se connecter à la base de données !", exception);
        }

        // Renvoyer le produit modifié
        return produit;
    }

    // Supprimer un produit de la base de données
    public void deleteProduit(Produit produit) {
        logger.info("Suppression du produit : {}", produit);

        // Connexion à la base de données
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            // Requête SQL pour supprimer un produit
            String sql = "DELETE FROM produits WHERE nom = ?";

            // Préparer la déclaration SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Remplir les paramètres de la requête
                statement.setString(1, produit.getNom());

                // Exécuter la requête SQL
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            // Gérer les exceptions liées à la base de données
            logger.error("Erreur lors de la suppression du produit.", exception);
            throw new IllegalStateException("Impossible de se connecter à la base de données !", exception);
        }
    }

    // Récupérer un produit par son ID depuis la base de données
    public Produit getProduit(int id) {
        logger.info("Récupération du produit par ID : {}", id);

        // Connexion à la base de données
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            // Requête SQL pour récupérer un produit par ID
            String sql = "SELECT * FROM produits WHERE id = ?";

            // Préparer la déclaration SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Remplir les paramètres de la requête
                statement.setInt(1, id);

                // Exécuter la requête SQL
                try (ResultSet result = statement.executeQuery()) {
                    // Vérifier s'il y a des résultats
                    if (result.next()) {
                        // Créer un objet Produit avec les données récupérées
                        Produit produit = new Produit();
                        produit.setNom(result.getString("nom"));
                        produit.setDepartement(result.getString("departement"));
                        produit.setLait(result.getString("lait"));

                        // Renvoyer le produit récupéré
                        return produit;
                    }
                }
            }
        } catch (SQLException exception) {
            // Gérer les exceptions liées à la base de données
            logger.error("Erreur lors de la récupération du produit par ID.", exception);
            throw new IllegalStateException("Impossible de se connecter à la base de données !", exception);
        }
        // Renvoyer null si aucun produit n'est trouvé
        return null;
    }

    // Récupérer un produit par son nom depuis la base de données
    public Produit getProduit(String nom) {
        logger.info("Récupération du produit par nom : {}", nom);

        // Connexion à la base de données
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            // Requête SQL pour récupérer un produit par nom
            String sql = "SELECT * FROM produits WHERE nom = ?";

            // Préparer la déclaration SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Remplir les paramètres de la requête
                statement.setString(1, nom);

                // Exécuter la requête SQL
                try (ResultSet result = statement.executeQuery()) {
                    // Vérifier s'il y a des résultats
                    if (result.next()) {
                        // Créer un objet Produit avec les données récupérées
                        Produit produit = new Produit();
                        produit.setNom(result.getString("nom"));
                        produit.setDepartement(result.getString("departement"));
                        produit.setLait(result.getString("lait"));

                        // Renvoyer le produit récupéré
                        return produit;
                    }
                }
            }
        } catch (SQLException exception) {
            // Gérer les exceptions liées à la base de données
            logger.error("Erreur lors de la récupération du produit par nom.", exception);
            throw new IllegalStateException("Impossible de se connecter à la base de données !", exception);
        }

        // Renvoyer null si aucun produit n'est trouvé
        return null;
    }

    // Récupérer un produit par son nom et son département depuis la base de données
    public Produit getProduit(String nom, String departement) {
        logger.info("Récupération du produit par nom et département : {} - {}", nom, departement);

        // Connexion à la base de données
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            // Requête SQL pour récupérer un produit par nom et département
            String sql = "SELECT * FROM produits WHERE nom = ? AND departement = ?";

            // Préparer la déclaration SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Remplir les paramètres de la requête
                statement.setString(1, nom);
                statement.setString(2, departement);

                // Exécuter la requête SQL
                try (ResultSet result = statement.executeQuery()) {
                    // Vérifier s'il y a des résultats
                    if (result.next()) {
                        // Créer un objet Produit avec les données récupérées
                        Produit produit = new Produit();
                        produit.setNom(result.getString("nom"));
                        produit.setDepartement(result.getString("departement"));
                        produit.setLait(result.getString("lait"));

                        // Renvoyer le produit récupéré
                        return produit;
                    }
                }
            }
        } catch (SQLException exception) {
            // Gérer les exceptions liées à la base de données
            logger.error("Erreur lors de la récupération du produit par nom et département.", exception);
            throw new IllegalStateException("Impossible de se connecter à la base de données !", exception);
        }
        // Renvoyer null si aucun produit n'est trouvé
        return null;
    }

    // Récupérer un produit par son nom, son département et le type de lait depuis la base de données
    public Produit getProduit(String nom, String departement, String lait) {
        logger.info("Récupération du produit par nom, département et lait : {} - {} - {}", nom, departement, lait);
        //Connexion à la base de données
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            // Requête SQL pour récupérer un produit par nom, département et lait
            String sql = "SELECT * FROM produits WHERE nom = ? AND departement = ? AND lait = ?";

            // Préparer la déclaration SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Remplir les paramètres de la requête
                statement.setString(1, nom);
                statement.setString(2, departement);
                statement.setString(3, lait);

                // Exécuter la requête SQL
                try (ResultSet result = statement.executeQuery()) {
                    // Vérifier s'il y a des résultats
                    if (result.next()) {
                        // Créer un objet Produit avec les données récupérées
                        Produit produit = new Produit();
                        produit.setNom(result.getString("nom"));
                        produit.setDepartement(result.getString("departement"));
                        produit.setLait(result.getString("lait"));

                        // Renvoyer le produit récupéré
                        return produit;
                    }
                }
            }
        } catch (SQLException exception) {
            // Gérer les exceptions liées à la base de données
            logger.error("Erreur lors de la récupération du produit par nom, département et lait.", exception);
            throw new IllegalStateException("Impossible de se connecter à la base de données !", exception);
        }
        // Renvoyer null si aucun produit n'est trouvé
        return null;
    }
}