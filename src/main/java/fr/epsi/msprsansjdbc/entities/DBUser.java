package fr.epsi.msprsansjdbc.entities;

// Définition de la classe DBUser
// Cette classe permet de définir les attributs de connexion pour l'application
public class DBUser {
    // Définition des attributs de la classe DBUser
    private int id;
    private String username;
    private String password;
    private String role;

    // Getters et setters pour les attributs de la classe DBUser
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Constructeurs de la classe DBUser
    public DBUser() {
    }

    public DBUser(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
