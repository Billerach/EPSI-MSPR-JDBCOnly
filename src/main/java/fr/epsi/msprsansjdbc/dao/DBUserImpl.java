package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.DBUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DBUserImpl {
    private static final String FIND_USER = "SELECT * FROM dbuser WHERE username = ?";
    private static final String FIND_ROLE = "SELECT role FROM dbuser WHERE role = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DBUser findByUsername(String username) {
        return jdbcTemplate.queryForObject(FIND_USER, new Object[]{username}, (rs, rowNum) -> {
            DBUser dbuser = new DBUser();
            dbuser.setId(rs.getInt("id"));
            dbuser.setUsername(rs.getString("username"));
            dbuser.setPassword(rs.getString("password"));
            dbuser.setRole(rs.getString("role"));
            return dbuser;
        });
    }
}

