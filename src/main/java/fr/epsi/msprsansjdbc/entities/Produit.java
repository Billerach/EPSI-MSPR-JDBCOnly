package fr.epsi.msprsansjdbc.entities;

import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

public class Produit {
    // Attributs
    private int id_produit;
    private String nom;
    private String departement;
    private String lait;
    private Float prix;
    private boolean actif = true;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_de_fin;

    // Constructeurs
    public Produit() {
    }

    public Produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public Produit(int id_produit, String nom) {
        this.id_produit = id_produit;
        this.nom = nom;
    }

    public Produit(int id_produit, String nom, String departement) {
        this(id_produit, nom);  // Utilisation du constructeur existant pour éviter la redondance
        this.departement = departement;
    }

    public Produit(int id_produit, String nom, String departement, String lait) {
        this(id_produit, nom, departement);
        this.lait = lait;
    }

    public Produit(int id_produit, String nom, String departement, String lait, Float prix) {
        this(id_produit, nom, departement, lait);
        this.prix = prix;
    }

    public Produit(int id_produit, String nom, String departement, String lait, Float prix, Date date_de_fin) {
        this(id_produit, nom, departement, lait, prix);
        this.date_de_fin = date_de_fin;
    }

    // Getters et Setters
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

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Date getDate_de_fin() {
        return date_de_fin;
    }

    public void setDate_de_fin(Date date_de_fin) {
        this.date_de_fin = date_de_fin;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean b) {
        this.actif = b;
    }

    public Object getActif() {
        return actif;
    }
}
