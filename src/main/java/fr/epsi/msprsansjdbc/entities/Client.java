package fr.epsi.msprsansjdbc.entities;

import java.util.Objects;

public class Client {

    private int id;
    private String nom;
    private String prenom;
    private String numeroVoie;
    private String typeVoie;
    private String libelleVoie;
    private String commune;
    private String codePostal;
    private String email;
    private String telephone;
    private boolean estClient;
    private boolean estEmploye;

    public Client() {
    }

    public Client(String prenom, String nom, int id) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    }
