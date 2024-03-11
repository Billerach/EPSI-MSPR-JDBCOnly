package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Commande;
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
public class CommandeDAOImpl implements CommandeDAO {

    private static final Logger logger = LoggerFactory.getLogger(CommandeDAOImpl.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public CommandeDAOImpl(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("commandes")
                .usingGeneratedKeyColumns("id_commande");
    }

    @Override
    public List<Commande> getAllCommandesWithPersonne() {
        return jdbcTemplate.query("SELECT * FROM commandes", new BeanPropertyRowMapper<>(Commande.class));
    }

    @Override
    public List<Commande> findAll() {
        String sql = "SELECT Commandes.id_commande, produits.nom AS nom_produit, personnes.nom AS nom_client, " +
                "personnes.prenom AS prenom_client, Contenu_commande.quantite, Commandes.montant_total " +
                "FROM Commandes JOIN Contenu_commande ON Commandes.id_commande = Contenu_commande.id_commande " +
                "JOIN produits ON Contenu_commande.id_produit = produits.id_produit " +
                "JOIN personnes ON Commandes.id_personne = personnes.id_personne";

        logger.info("Récupération de toutes les commandes depuis la base de données.");
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Commande.class));
    }

    @Override
    public Commande create(Commande commande) {
        // Création du paramètre source pour l'insertion
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id_personne", commande.getId_personne())
                .addValue("date_commande", commande.getDate_commande())
                .addValue("montant_total", commande.getMontant_total());

        // Exécution de la requête d'insertion
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameterSource);

        // Mise à jour de l'ID de la commande
        commande.setId_commande(newId.intValue());

        // Retour de la commande mise à jour avec l'ID
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
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id_personne", commande.getId_personne())
                .addValue("date_commande", commande.getDate_commande())
                .addValue("montant_total", commande.getMontant_total())
                .addValue("id_commande", commande.getId_commande());
        jdbcTemplate.update("UPDATE commandes SET id_personne = :id_personne, date_commande = :date_commande, montant_total = :montant_total WHERE id_commande = :id_commande", parameterSource);

        return commande;
    }

    @Override
    public void deleteById(int id_commande) {
        // Implémentez la suppression en fonction de vos besoins.
    }

    @Override
    public String getNomProduit(int id_commande) {
        String sql = "SELECT produits.nom FROM crm_sansjpa.produits " +
                "JOIN crm_sansjpa.contenu_commande ON crm_sansjpa.contenu_commande.id_produit = crm_sansjpa.produits.id_produit " +
                "WHERE crm_sansjpa.contenu_commande.id_commande = :id_commande";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id_commande", id_commande);
        return jdbcTemplate.queryForObject(sql, parameterSource, String.class);
    }

    @Override
    public String getClient(int id_personne) {
        String sql = "SELECT nom FROM personnes WHERE est_client = true AND id_personne = :id_personne";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id_personne", id_personne);
        return jdbcTemplate.queryForObject(sql, parameterSource, String.class);
    }

    @Override
    public int getQuantite(int id_commande) {
        String sql = "SELECT quantite FROM crm_sansjpa.contenu_commande WHERE id_commande = :id_commande";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id_commande", id_commande);
        return jdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
    }

    @Override
    public float getTotal(int id_commande) {
        String sql = "SELECT montant_total FROM commandes WHERE id_commande = :id_commande";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id_commande", id_commande);
        return jdbcTemplate.queryForObject(sql, parameterSource, Float.class);
    }
}
