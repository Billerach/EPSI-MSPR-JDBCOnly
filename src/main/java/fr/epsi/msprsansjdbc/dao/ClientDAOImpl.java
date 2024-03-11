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
    private static final String FIND_BY_ID_QUERY = "SELECT ID_PERSONNE, NOM, PRENOM FROM personnes WHERE ID_PERSONNE = :id_personne";
    private static final String INSERT_QUERY = "INSERT INTO personnes (NOM, PRENOM) VALUES (:nom, :prenom)";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM personnes WHERE ID_PERSONNE = :id_personne";
    private static final String UPDATE_QUERY = "UPDATE personnes SET NOM = :nom, PRENOM = :prenom WHERE ID_PERSONNE = :id_personne";

    private static final Logger logger = LoggerFactory.getLogger(ClientDAOImpl.class);

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
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nom", client.getNom());
        parameterSource.addValue("prenom", client.getPrenom());
        parameterSource.addValue("numerovoie", client.getNumeroVoie());
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
    public Client update(Client client) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_personne", client.getId_personne());
        parameterSource.addValue("nom", client.getNom());
        parameterSource.addValue("prenom", client.getPrenom());
        jdbcTemplate.update(UPDATE_QUERY, parameterSource);
        return client;
    }

    @Override
    public void deleteById(int id_personne) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_personne", id_personne);
        jdbcTemplate.update(DELETE_BY_ID_QUERY, parameterSource);
    }
}