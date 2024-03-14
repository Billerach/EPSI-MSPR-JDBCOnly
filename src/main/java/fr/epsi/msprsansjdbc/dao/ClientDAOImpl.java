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
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;


    private static final String FIND_ALL_QUERY = "SELECT * FROM personnes WHERE EST_CLIENT = TRUE";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM personnes WHERE ID_PERSONNE = :id_personne";
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

    private static final Logger logger = LoggerFactory.getLogger(ClientDAOImpl.class);

    @Autowired
    public ClientDAOImpl(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
        .withTableName("personnes")
        .usingGeneratedKeyColumns("ID_PERSONNE");
    }

//    @Override
    public List<Client> findAllClients() {
        logger.info("Récupération de tous les clients depuis la base de données.");
        return jdbcTemplate.query(FIND_ALL_QUERY, new BeanPropertyRowMapper<>(Client.class));
    }

//    @Override
    public Client create(Client client) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
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

        // Exécutez la requête avec les valeurs définies dans parameterSource
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameterSource);

        // Mettez à jour l'ID du client avec la nouvelle valeur générée
        client.setId_personne(newId.intValue());

        return client;
    }


    @Override
    public Client findById(int id_personne) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_personne", id_personne);
        return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, parameterSource, new BeanPropertyRowMapper<>(Client.class));
    }

    @Override
    public void update(Client client) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
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
        jdbcTemplate.update(UPDATE_QUERY, parameterSource);
    }

    public void archiveById(Client client) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_personne",client.getId_personne());
        jdbcTemplate.update(ARCHIVAGE_CLIENT, parameterSource);
    }
}