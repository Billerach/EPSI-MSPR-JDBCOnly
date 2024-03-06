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
