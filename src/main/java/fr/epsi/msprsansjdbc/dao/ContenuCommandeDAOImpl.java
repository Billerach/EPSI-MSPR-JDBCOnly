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

import javax.sql.DataSource;
import java.util.List;

public class ContenuCommandeDAOImpl implements ContenuCommandeDAO{

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    private static final Logger logger = LoggerFactory.getLogger(ClientDAOImpl.class);

    private static final String FIND_ALL_QUERY = "SELECT * FROM contenu_commande WHERE id_commande = :id_commande";

    @Autowired
    public ContenuCommandeDAOImpl(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("content_commande")
                .usingGeneratedKeyColumns("id_contenu_commande");
    }

    public List<ContenuCommande> tousLesProduitsDeUneCommande() {
        logger.info("Récupération de tous les produits de la commande en cours depuis la bdd");
        return jdbcTemplate.query(FIND_ALL_QUERY, new BeanPropertyRowMapper<>(ContenuCommande.class));
    }

    public ContenuCommande create(ContenuCommande contenuCommande) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_commande", contenuCommande.getNom());
        parameterSource.addValue("produit", contenuCommande.getPrenom());
        parameterSource.addValue("quantite", contenuCommande.getNumeroVoie());

        // Exécutez la requête avec les valeurs définies dans parameterSource
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameterSource);

        // Mettez à jour l'ID du contenu_commande avec la nouvelle valeur générée
        contenuCommande.setId_personne(newId.intValue());

        return contenuCommande;
    }


}
