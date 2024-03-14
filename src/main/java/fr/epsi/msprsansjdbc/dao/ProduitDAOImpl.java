package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Produit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository // Indique à Spring que cette classe est un composant de persistance et doit être gérée par le conteneur Spring
public class ProduitDAOImpl implements ProduitDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    // Requête SQL pour récupérer tous les produits actifs
    static final String FIND_ALL_ACTIFS_QUERY = "SELECT * FROM produits WHERE actif = true";

    // Requête SQL pour récupérer un produit par son ID
    private static final String FIND_BY_ID_QUERY = "SELECT id_produit, nom, departement, lait, prix, date_de_fin FROM produits WHERE id_produit = :id_produit";

    // Requête SQL pour désactiver un produit par son ID
    private static final String DESACTIVATE_BY_ID_QUERY = "UPDATE produits SET actif = false WHERE id_produit = :id_produit";

    // Requête SQL pour mettre à jour un produit
    private static final String UPDATE_QUERY = "UPDATE produits SET nom = :nom, departement = :departement, lait = :lait, prix = :prix, date_de_fin = :date_de_fin WHERE id_produit = :id_produit";

    // Requête SQL pour insérer un nouveau produit
    private static final String INSERT_QUERY = "INSERT INTO produits (nom, departement, lait, prix, date_de_fin) VALUES (:nom, :departement, :lait, :prix, :date_de_fin)";

    // Logger pour la journalisation
    private static final Logger logger = LoggerFactory.getLogger(ProduitDAOImpl.class);

    @Autowired
    public ProduitDAOImpl(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        // Initialisation de l'objet SimpleJdbcInsert avec le nom de la table et la colonne générée automatiquement
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("produits")
                .usingGeneratedKeyColumns("id_produit");
    }

    @Override
    public List<Produit> findAllActifs() {
        logger.info("Récupération de tous les produits depuis la base de données.");
        // Exécute la requête SQL et mappe les résultats sur des objets Produit à l'aide de BeanPropertyRowMapper
        return jdbcTemplate.query(FIND_ALL_ACTIFS_QUERY, new BeanPropertyRowMapper<>(Produit.class));
    }

    @Override
    public Produit create(Produit produit) {
        // Crée un objet MapSqlParameterSource avec les valeurs du produit à insérer
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nom", produit.getNom());
        parameterSource.addValue("departement", produit.getDepartement());
        parameterSource.addValue("lait", produit.getLait());
        parameterSource.addValue("prix", produit.getPrix());
        parameterSource.addValue("date_fin", produit.getDate_de_fin());
        parameterSource.addValue("actif", produit.isActif()); // Ajout de la gestion de la colonne actif

        // Exécute l'insertion et retourne la clé générée pour le nouvel enregistrement
        Number idProduit = simpleJdbcInsert.executeAndReturnKey(parameterSource);
        produit.setId_produit(idProduit.intValue());

        return produit;
    }

    @Override
    public Produit findById(int id_produit) {
        // Crée un objet MapSqlParameterSource avec l'ID du produit à rechercher
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_produit", id_produit);
        // Exécute la requête SQL et mappe le résultat sur un objet Produit
        return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, parameterSource, new BeanPropertyRowMapper<>(Produit.class));
    }

    @Override
    public Produit update(Produit produit) {
        // Crée un objet MapSqlParameterSource avec les nouvelles valeurs du produit à mettre à jour
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_produit", produit.getId_produit());
        parameterSource.addValue("nom", produit.getNom());
        parameterSource.addValue("departement", produit.getDepartement());
        parameterSource.addValue("lait", produit.getLait());
        parameterSource.addValue("prix", produit.getPrix());
        parameterSource.addValue("date_de_fin", produit.getDate_de_fin());
        // Exécute la mise à jour
        jdbcTemplate.update(UPDATE_QUERY, parameterSource);
        return produit;
    }

    @Override
    public void desactiverById(int id_produit) {
        // Crée un objet MapSqlParameterSource avec l'ID du produit à désactiver
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_produit", id_produit);
        // Exécute la requête SQL pour désactiver le produit
        jdbcTemplate.update(DESACTIVATE_BY_ID_QUERY, parameterSource);
    }
}
