package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Commande;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommandeDAOImpl implements CommandeDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    private static final String SELECT_ALL = "SELECT * FROM commande";
    private static final String INSERT = "INSERT INTO commandes (date_commande, id_client) VALUES (:date_commande, :id_client)";

    public CommandeDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
                .withTableName("commandes")
                .usingGeneratedKeyColumns("id_commande");
    }

    @Override
    public List<Commande> findAll() {
        // Implémenter la logique pour récupérer toutes les commandes de la base de données
        return null;
    }

    @Override
    public Commande create(Commande commande) {

        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(commande);
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameterSource);
        commande.setId_commande(newId.intValue());

        return commande;
    }



    @Override
    public Commande findById(int id) {
        // Implémenter la logique pour récupérer une commande par son ID
        return null;
    }
}
