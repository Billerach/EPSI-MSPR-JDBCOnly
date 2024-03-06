package fr.epsi.msprsansjdbc.entities;

public class Produit {
    // Attributs
    private int id;
    private String nom;
    private String departement;
    private String lait;
    private Object prix;

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
    public Produit(int id, String nom, String departement, String lait) {
        this.id = id;
        this.nom = nom;
        this.departement = departement;
        this.lait = lait;
    }

    // Getters et Setters
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

    public Object getPrix() {
        return prix;
    }

    public void setPrix(Object prix) {
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
