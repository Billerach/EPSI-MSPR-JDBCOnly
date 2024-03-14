package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.DBUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // Indique à Spring que cette classe est un composant de persistance et doit être gérée par le conteneur Spring
public class DBUserImpl {

    // Requête SQL pour trouver un utilisateur par nom d'utilisateur
    private static final String FIND_USER = "SELECT * FROM dbuser WHERE username = ?";

    // Requête SQL pour trouver le rôle d'un utilisateur
    private static final String FIND_ROLE = "SELECT role FROM dbuser WHERE role = ?";

    @Autowired // Injection du bean JdbcTemplate
    private JdbcTemplate jdbcTemplate;

    // Méthode pour trouver un utilisateur par nom d'utilisateur
    public DBUser findByUsername(String username) {
        return jdbcTemplate.queryForObject(FIND_USER, new Object[]{username}, (rs, rowNum) -> {
            // Mapping des données du résultat de la requête à un objet DBUser
            DBUser dbuser = new DBUser();
            dbuser.setId(rs.getInt("id"));
            dbuser.setUsername(rs.getString("username"));
            dbuser.setPassword(rs.getString("password"));
            dbuser.setRole(rs.getString("role"));
            return dbuser;
        });
    }
}

