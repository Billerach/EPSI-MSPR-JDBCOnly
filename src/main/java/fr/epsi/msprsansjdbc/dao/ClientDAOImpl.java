package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Client;
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

@Repository
public class ClientDAOImpl implements ClientDAO {

    // Logger pour enregistrer les informations de débogage
    private static final Logger logger = LoggerFactory.getLogger(ClientDAOImpl.class);
    // JDBC template pour exécuter des requêtes SQL nommées avec des paramètres
    private final NamedParameterJdbcTemplate jdbcTemplate;
    // Simple JDBC insert pour insérer des données dans la base de données
    private final SimpleJdbcInsert simpleJdbcInsert;

    // Requête SQL pour récupérer tous les clients actifs

    private static final String FIND_ALL_QUERY = "SELECT * FROM personnes WHERE EST_CLIENT = TRUE";
    // Requête SQL pour récupérer un client par son identifiant

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM personnes WHERE ID_PERSONNE = :id_personne";
    // Requête SQL pour archiver un client

    private static final String ARCHIVAGE_CLIENT = "UPDATE personnes SET " +
            "nom = null," +
            "prenom = null," +
            "numero_voie = null," +
            "type_voie = null," +
            "libelle_voie = null," +
            "commune = null," +
            "code_postal = null," +
            "email = null," +
            "telephone = null," +
            "est_client = null," +
            "est_employe = null," +
            "est_archive = true " +
            "WHERE ID_PERSONNE = :id_personne";
    // Requête SQL pour mettre à jour les informations d'un client

    private static final String UPDATE_QUERY = "UPDATE personnes SET " +
            "nom = :nom," +
            "prenom = :prenom," +
            "numero_voie = :numero_voie," +
            "type_voie = :type_voie," +
            "libelle_voie = :libelle_voie," +
            "commune = :commune," +
            "code_postal = :code_postal," +
            "email = :email," +
            "telephone = :telephone " +
            "WHERE id_personne = :id_personne";

    // Constructeur pour l'injection des dépendances

    @Autowired
    public ClientDAOImpl(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
        .withTableName("personnes")
        .usingGeneratedKeyColumns("ID_PERSONNE");
    }

    @Override
    public List<Client> findAllClients() {
        logger.info("Récupération de tous les clients depuis la base de données.");
        return jdbcTemplate.query(FIND_ALL_QUERY, new BeanPropertyRowMapper<>(Client.class));
    }

    @Override
    public Client create(Client client) {
        // Création d'un objet MapSqlParameterSource pour définir les valeurs des paramètres de la requête
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        // Définition des valeurs des paramètres de la requête avec les valeurs de l'objet Client
        parameterSource.addValue("nom", client.getNom());
        parameterSource.addValue("prenom", client.getPrenom());
        parameterSource.addValue("numero_voie", client.getNumeroVoie());
        parameterSource.addValue("type_voie", client.getTypeVoie());
        parameterSource.addValue("libelle_voie", client.getLibelleVoie());
        parameterSource.addValue("commune", client.getCommune());
        parameterSource.addValue("code_postal", client.getCodePostal());
        parameterSource.addValue("email", client.getEmail());
        parameterSource.addValue("telephone", client.getTelephone());
        client.setEstClient(true);
        parameterSource.addValue("est_client", client.isEstClient());
        client.setEstEmploye(false);
        parameterSource.addValue("est_employe", client.isEstEmploye());
        client.setEstArchive(false);
        parameterSource.addValue("est_archive", client.isEstArchive());

        // Exécution de la requête d'insertion et récupération de la clé générée
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameterSource);

        // Mise à jour de l'ID du client avec la nouvelle valeur générée
        client.setId_personne(newId.intValue());

        return client;
    }


    @Override
    public Client findById(int id_personne) {
        // Création d'un objet MapSqlParameterSource pour définir les valeurs des paramètres de la requête
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        // Définition de la valeur du paramètre avec l'identifiant du client
        parameterSource.addValue("id_personne", id_personne);
        // Exécution de la requête et récupération du résultat
        return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, parameterSource, new BeanPropertyRowMapper<>(Client.class));
    }

    @Override
    public void update(Client client) {
        // Création d'un objet MapSqlParameterSource pour stocker les paramètres de requête
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        // Définition des valeurs des paramètres avec les propriétés mises à jour du client
        parameterSource.addValue("id_personne",client.getId_personne());
        parameterSource.addValue("nom", client.getNom());
        parameterSource.addValue("prenom", client.getPrenom());
        parameterSource.addValue("numero_voie", client.getNumeroVoie());
        parameterSource.addValue("type_voie", client.getTypeVoie());
        parameterSource.addValue("libelle_voie", client.getLibelleVoie());
        parameterSource.addValue("commune", client.getCommune());
        parameterSource.addValue("code_postal", client.getCodePostal());
        parameterSource.addValue("email", client.getEmail());
        parameterSource.addValue("telephone", client.getTelephone());
        // Exécution de la requête de mise à jour
        jdbcTemplate.update(UPDATE_QUERY, parameterSource);
    }

    public void archiveById(Client client) {
        // Création d'un objet MapSqlParameterSource pour stocker les paramètres de requête
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        // Définition de la valeur du paramètre avec l'identifiant du client à archiver
        parameterSource.addValue("id_personne",client.getId_personne());
        // Exécution de la requête d'archivage
        jdbcTemplate.update(ARCHIVAGE_CLIENT, parameterSource);
    }
}