package fr.epsi.msprsansjdbc.entities;

import org.springframework.data.annotation.Id;

import java.util.Objects;

// Définition de la classe Client
public class Client {
    // Définition des attributs de la classe Client
    private int id_personne;
    private String nom;
    private String prenom;
    private String numeroVoie;
    private String typeVoie;
    private String libelleVoie;
    private String commune;
    private int codePostal;
    private String email;
    private String telephone;
    private boolean estClient;
    private boolean estEmploye;
    private boolean estArchive;

    // Constructeurs de la classe Client avec les différents attributs
    public Client() {
    }

    public Client(String prenom, String nom, int id_personne) {
        this.id_personne = id_personne;
        this.prenom = prenom;
        this.nom = nom;
    }

    public Client(int id_personne, String nom, String prenom, String numeroVoie) {
        this.id_personne = id_personne;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroVoie = numeroVoie;
    }

    public Client(int id_personne, String nom, String prenom, String numeroVoie, String typeVoie) {
        this.id_personne = id_personne;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroVoie = numeroVoie;
        this.typeVoie = typeVoie;
    }

    public Client(int id_personne, String nom, String prenom, String numeroVoie, String typeVoie, String libelleVoie) {
        this.id_personne = id_personne;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroVoie = numeroVoie;
        this.typeVoie = typeVoie;
        this.libelleVoie = libelleVoie;
    }

    public Client(int id_personne, String nom, String prenom, String numeroVoie, String typeVoie, String libelleVoie, String commune) {
        this.id_personne = id_personne;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroVoie = numeroVoie;
        this.typeVoie = typeVoie;
        this.libelleVoie = libelleVoie;
        this.commune = commune;
    }

    public Client(int id_personne, String nom, String prenom, String numeroVoie, String typeVoie, String libelleVoie, String commune, int codePostal) {
        this.id_personne = id_personne;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroVoie = numeroVoie;
        this.typeVoie = typeVoie;
        this.libelleVoie = libelleVoie;
        this.commune = commune;
        this.codePostal = codePostal;
    }

    public Client(int id_personne, String nom, String prenom, String numeroVoie, String typeVoie, String libelleVoie, String commune, int codePostal, String email) {
        this.id_personne = id_personne;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroVoie = numeroVoie;
        this.typeVoie = typeVoie;
        this.libelleVoie = libelleVoie;
        this.commune = commune;
        this.codePostal = codePostal;
        this.email = email;
    }



    public Client(int id_personne, String nom, String prenom, String numeroVoie, String typeVoie, String libelleVoie, String commune, int codePostal, String email, String telephone) {
        this.id_personne = id_personne;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroVoie = numeroVoie;
        this.typeVoie = typeVoie;
        this.libelleVoie = libelleVoie;
        this.commune = commune;
        this.codePostal = codePostal;
        this.email = email;
        this.telephone = telephone;
    }

    // Getters et Setters des attributs de la classe Client
    public int getId_personne() {
        return id_personne;
    }

    public void setId_personne(int id_personne) {
        this.id_personne = id_personne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumeroVoie() {
        return numeroVoie;
    }

    public void setNumeroVoie(String numeroVoie) {
        this.numeroVoie = numeroVoie;
    }

    public String getTypeVoie() {
        return typeVoie;
    }

    public void setTypeVoie(String typeVoie) {
        this.typeVoie = typeVoie;
    }

    public String getLibelleVoie() {
        return libelleVoie;
    }

    public void setLibelleVoie(String libelleVoie) {
        this.libelleVoie = libelleVoie;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isEstClient() {
        return estClient;
    }

    public void setEstClient(boolean estClient) {
        this.estClient = estClient;
    }

    public boolean isEstEmploye() {
        return estEmploye;
    }

    public void setEstEmploye(boolean estEmploye) {
        this.estEmploye = estEmploye;
    }

    public boolean isEstArchive() {
        return estArchive; }

    public void setEstArchive(boolean estArchive) {
        this.estArchive = estArchive; }

    // Méthode toString pour afficher les informations de la classe Client
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("id=").append(id_personne);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append('}');
        return sb.toString();
    }

    // Méthode equals pour comparer deux objets de la classe Client
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client that)) return false;
        return id_personne == that.id_personne;
    }

    // Méthode hashCode pour retourner le code de hachage d'un objet de la classe Client
    @Override
    public int hashCode() {
        return Objects.hash(id_personne);
    }
}
