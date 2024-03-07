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

@Repository
public class ProduitDAOImpl implements ProduitDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
    static final String FIND_ALL_QUERY = "SELECT * FROM produits";
    private static final String FIND_BY_ID_QUERY = "SELECT id_produit, nom, departement, lait FROM produits WHERE id_produit = :id_produit";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM produits WHERE id_produit = :id_produit";
    private static final String UPDATE_QUERY = "UPDATE produits SET nom = :nom WHERE id_produit = :id_produit";
    private static final String INSERT_QUERY = "INSERT INTO produits (nom, departement, lait) VALUES (:nom, :departement, :lait)";

    @Autowired
    public ProduitDAOImpl(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("produits").usingGeneratedKeyColumns("id");
    }

    // Logger pour la journalisation
    private static final Logger logger = LoggerFactory.getLogger(ProduitDAOImpl.class);

    @Override
    public List<Produit> findAll() {
        logger.info("Récupération de tous les produits depuis la base de données.");
        return jdbcTemplate.query(FIND_ALL_QUERY, new BeanPropertyRowMapper<>(Produit.class));
    }

    @Override
    public Produit create(Produit produit) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nom", produit.getNom());
        parameterSource.addValue("departement", produit.getDepartement());
        parameterSource.addValue("lait", produit.getLait());
        produit.setId_produit(simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue());
        return produit;
    }

    @Override
    public Produit findById(int id_produit) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id_produit);
        return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, parameterSource, new BeanPropertyRowMapper<>(Produit.class));
    }

    @Override
    public Produit update(Produit produit) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", produit.getId_produit());
        parameterSource.addValue("nom", produit.getNom());
        jdbcTemplate.update(UPDATE_QUERY, parameterSource);
        return produit;
    }

    @Override
    public void deleteById(int id_produit) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id_produit);
        jdbcTemplate.update(DELETE_BY_ID_QUERY, parameterSource);
    }
}
