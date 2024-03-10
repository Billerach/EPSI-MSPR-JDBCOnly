package fr.epsi.msprsansjdbc.entities;

import java.time.LocalDateTime;

public class HistoriqueProduit {

    private int id;
    private int id_produit;
    private String nom;
    private String departement;
    private String lait;
    private double prix;
    private LocalDateTime dateSuppression;

    // Constructeurs, getters et setters

    public HistoriqueProduit(int id_produit, String nom, String departement, String lait, double prix, LocalDateTime dateSuppression) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.departement = departement;
        this.lait = lait;
        this.prix = prix;
        this.dateSuppression = dateSuppression;
    }

    public HistoriqueProduit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getLait() {
        return lait;
    }

    public void setLait(String lait) {
        this.lait = lait;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public LocalDateTime getDateSuppression() {
        return dateSuppression;
    }

    public void setDateSuppression(LocalDateTime dateSuppression) {
        this.dateSuppression = dateSuppression;
    }
}

