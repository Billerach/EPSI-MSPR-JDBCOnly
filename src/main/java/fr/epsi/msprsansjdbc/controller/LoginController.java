package fr.epsi.msprsansjdbc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indique à Spring que cette classe est un contrôleur REST
public class LoginController {

    // Méthode pour gérer les requêtes GET sur "/user"
    @GetMapping("/user")
    public String getUser() {
        return "Welcome, User"; // Retourne un message de bienvenue pour les utilisateurs
    }

    // Méthode pour gérer les requêtes GET sur "/admin"
    @GetMapping("/admin")
    public String getAdmin() {
        return "Welcome, Admin"; // Retourne un message de bienvenue pour les administrateurs
    }
}
