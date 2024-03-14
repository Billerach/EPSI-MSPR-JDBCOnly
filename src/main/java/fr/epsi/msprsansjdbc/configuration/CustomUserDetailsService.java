package fr.epsi.msprsansjdbc.configuration;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.msprsansjdbc.dao.DBUserImpl;
import fr.epsi.msprsansjdbc.entities.DBUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Indique à Spring que cette classe est un service et doit être gérée par le conteneur Spring
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired // Injection de dépendance : Spring va automatiquement injecter une instance de DBUserImpl lors de la création de CustomUserDetailsService
    private DBUserImpl DBUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Recherche de l'utilisateur dans la base de données en fonction de son nom d'utilisateur
        DBUser user = DBUser.findByUsername(username);

        // Création et retour d'un objet UserDetails basé sur les informations de l'utilisateur trouvé en base de données
        return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRole()));
    }

    // Méthode privée pour obtenir les rôles de l'utilisateur sous forme d'une liste de GrantedAuthority
    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role)); // Ajoute le préfixe "ROLE_" aux rôles pour respecter la convention de Spring Security
        return authorities;
    }
}
