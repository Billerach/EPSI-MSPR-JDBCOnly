package fr.epsi.msprsansjdbc.entities;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

// Définition de la classe Produit
public class Produit {
    // Attributs de la classe Produit
    private int id_produit;
    private String nom;
    private String departement;
    private String lait;
    private Float prix;
    private boolean actif = true; // Par défaut, le produit est actif
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

    // Getters et Setters pour les attributs de la classe Produit
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

    // Méthode pour vérifier si le produit est actif
    public boolean isActif() {
        return actif;
    }
    // Méthode pour activer ou désactiver un produit
    public void setActif(boolean b) {
        this.actif = b;
    }

    // Méthode pour récupérer l'état actif du produit
    public Object getActif() {
        return actif;
    }
}
