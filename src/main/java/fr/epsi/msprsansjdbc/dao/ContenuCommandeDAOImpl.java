package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Client;
import fr.epsi.msprsansjdbc.entities.ContenuCommande;
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
class ContenuCommandeDAOImpl implements ContenuCommandeDAO{

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    private static final Logger logger = LoggerFactory.getLogger(ClientDAOImpl.class);

    private static final String FIND_ALL_QUERY = "SELECT * FROM contenu_commande WHERE id_commande = :id_commande";

    @Autowired
    public ContenuCommandeDAOImpl(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("contenu_commande")
                .usingGeneratedKeyColumns("id_contenu_commande");
    }

    public List<ContenuCommande> getContenuCommandeList(int id_commande) {
        logger.info("Récupération de tous les produits de la commande en cours depuis la bdd");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id_commande", id_commande);
        return jdbcTemplate.query(FIND_ALL_QUERY, parameters, new BeanPropertyRowMapper<>(ContenuCommande.class));
    }

    public ContenuCommande create(ContenuCommande contenuCommande) {
        // Création d'un objet MapSqlParameterSource pour définir les valeurs des paramètres de la requête
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        // Définition des valeurs des paramètres de la requête avec les valeurs de l'objet ContentCommande
        parameterSource.addValue("id_commande", contenuCommande.getId_commande());
        parameterSource.addValue("id_produit", contenuCommande.getId_produit());
        parameterSource.addValue("quantite", contenuCommande.getQuantite());

        // Exécution de la requête d'insertion et récupération de la clé générée
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameterSource);

        // Mise à jour de l'ID du client avec la nouvelle valeur générée
        contenuCommande.setId_contenu_commande(newId.intValue());

        return contenuCommande;
    }
}
