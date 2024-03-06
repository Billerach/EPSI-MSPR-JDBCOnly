package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Commande;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommandeDAOImpl implements CommandeDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    private static final String SELECT_ALL = "SELECT * FROM commande";
    private static final String INSERT = "INSERT INTO commande (id_commande, date_commande, id_client) VALUES (:id_commande, :date_commande, :id_client)";


    @Override
    public List<Commande> findAll() {
        return null;
    }

    @Override
    public Commande create(Commande commande) {
        return null;
    }

    @Override
    public Commande findById(int id) {
        return null;
    }
}
