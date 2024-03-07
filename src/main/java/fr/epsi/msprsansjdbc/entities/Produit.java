package fr.epsi.msprsansjdbc.entities;

public class Produit {
    // Attributs
    private int id_produit;
    private String nom;
    private String departement;
    private String lait;
    private float prix;

    // Constructeur par défaut
    public Produit() {
    }

    // Constructeur avec un paramètre
    public Produit(String nom) {
        this.nom = nom;
    }

    // Constructeur avec deux paramètres
    public Produit(String nom, String departement) {
        this.nom = nom;
        this.departement = departement;
    }

    // Constructeur avec trois paramètres
    public Produit(String nom, String departement, String lait) {
        this.nom = nom;
        this.departement = departement;
        this.lait = lait;
    }

    // Constructeur avec quatre paramètres
    public Produit(int id_produit, String nom, String departement, String lait) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.departement = departement;
        this.lait = lait;
    }

    // Constructeur avec cinq paramètres
    public Produit(int id_produit, String nom, String departement, String lait, float prix) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.departement = departement;
        this.lait = lait;
        this.prix = prix;
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}

// Autre version de la classe Produit.java

//public class Produit {
//    String nom;
//    String departement;
//    String lait;
//
//    // Constructeur par défaut
//    public Produit() {
//    }
//
//    // Constructeur avec trois paramètres
//    public Produit(String nom, String departement, String lait) {
//        this.nom = nom;
//        this.departement = departement;
//        this.lait = lait;
//    }
//
//    public String getNom() {
//        return nom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public String getDepartement() {
//        return departement;
//    }
//
//    public void setDepartement(String departement) {
//        this.departement = departement;
//    }
//
//    public String getLait() {
//        return lait;
//    }
//
//    public void setLait(String lait) {
//        this.lait = lait;
//    }
//}
