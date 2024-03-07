package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Commande;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommandeDAOImpl implements CommandeDAO {

    private static final Logger logger = LoggerFactory.getLogger(CommandeDAOImpl.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public CommandeDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
                .withTableName("commandes")
                .usingGeneratedKeyColumns("id_commande");
    }

    @Override
    public List<Commande> getAllCommandesWithPersonne() {
        String sql = "SELECT c.id_commande, c.id_personne, c.date_commande, c.montant_total, p.nom " +
                "FROM commandes c " +
                "JOIN personnes p ON c.id_personne = p.id_personne";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Commande.class));
    }

    @Override
    public List<Commande> findAll() {
        logger.info("Récupération de toutes les commandes depuis la base de données.");
        return jdbcTemplate.query("SELECT * FROM commandes", new BeanPropertyRowMapper<>(Commande.class));
    }

    @Override
    public Commande create(Commande commande) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(commande);
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameterSource);
        commande.setId_commande(newId.intValue());

        return commande;
    }

    @Override
    public Commande findById(int id_commande) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_commande", id_commande);
        return jdbcTemplate.queryForObject("SELECT id_commande, id_personne, date_commande, montant_total FROM commandes WHERE id_commande = :id_commande", parameterSource, new BeanPropertyRowMapper<>(Commande.class));
    }

    @Override
    public Commande update(Commande commande) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(commande);
        jdbcTemplate.update("UPDATE commandes SET id_personne = :id_personne, date_commande = :date_commande, montant_total = :montant_total WHERE id_commande = :id_commande", parameterSource);

        return commande;
    }

    @Override
    public void deleteById(int id_commande) {
        // Implémentez la suppression en fonction de vos besoins.
    }

    @Override
    public String getNomProduit(int idCommande) {
        String sql = "SELECT produits.nom FROM crm_sansjpa.produits " +
                "JOIN crm_sansjpa.contenu_commande ON crm_sansjpa.contenu_commande.id_produit = crm_sansjpa.produits.id_produit " +
                "WHERE crm_sansjpa.contenu_commande.id_commande = :idCommande";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("idCommande", idCommande);
        return jdbcTemplate.queryForObject(sql, parameterSource, String.class);
    }

    @Override
    public String getClient(int idPersonne) {
        String sql = "SELECT nom FROM personnes WHERE est_client = true AND id_personne = :idPersonne";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("idPersonne", idPersonne);
        return jdbcTemplate.queryForObject(sql, parameterSource, String.class);
    }

    @Override
    public int getQuantite(int idCommande) {
        String sql = "SELECT quantite FROM crm_sansjpa.contenu_commande WHERE id_commande = :idCommande";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("idCommande", idCommande);
        return jdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
    }

    @Override
    public float getTotal(int idCommande) {
        String sql = "SELECT montant_total FROM commandes WHERE id_commande = :idCommande";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("idCommande", idCommande);
        return jdbcTemplate.queryForObject(sql, parameterSource, Float.class);
    }
}