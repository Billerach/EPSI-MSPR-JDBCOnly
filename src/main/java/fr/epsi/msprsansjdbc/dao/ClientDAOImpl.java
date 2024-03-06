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
public class ClientDAOImpl implements ClientDAO{
    private NamedParameterJdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    private static final String FIND_BY_ID_QUERY = "SELECT ID_PERSONNE, NOM FROM personnes WHERE ID_PERSONNE = :id";
    private static final String DELETE_BY_ID_QUERY = "UPDATE personnes SET EST_CLIENT = FALSE WHERE ID_PERSONNE = :id";
    private static final String UPDATE_QUERY = "UPDATE personnes SET NOM = :nom WHERE ID_PERSONNE = :id";
    private static final String INSERT_QUERY = "INSERT INTO clients (NOM) VALUES (:nom)";

    @Autowired
    public ClientDAOImpl(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("clients").usingGeneratedKeyColumns("ID");
    }

    // Logger pour la journalisation
    private static final Logger logger = LoggerFactory.getLogger(ClientDAOImpl.class);

    @Override
    public List<Client> findAll() {
        logger.info("Récupération de tous les clients depuis la base de données.");
        return jdbcTemplate.query(FIND_ALL_QUERY, new BeanPropertyRowMapper<>(Client.class));
    }

    @Override
    public Client create(Client client) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nom", client.getNom());
        parameterSource.addValue("prenom", client.getPrenom());
        client.setId(simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue());
        return client;
    }

    @Override
    public Client findById(int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, parameterSource, new BeanPropertyRowMapper<>(Client.class));
    }

    @Override
    public Client update(Client client) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", client.getId());
        parameterSource.addValue("nom", client.getNom());
        parameterSource.addValue("prenom", client.getPrenom());
        jdbcTemplate.update(UPDATE_QUERY, parameterSource);
        return client;
    }

    @Override
    public void deleteById(int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        jdbcTemplate.update(DELETE_BY_ID_QUERY, parameterSource);
    }
}
