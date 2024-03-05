package fr.epsi.poec24.mspr.epsimspr.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Classe Produit. Cette classe est une entité JPA.
// Elle est mappée à la table "produits" dans la base de données.
@Entity
@Table(name = "produits")
public class Produit {

    @Id
    private Long id;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "departement", nullable = false, length = 50)
    private String departement;

    @Column(name = "lait", nullable = false, length = 50)
    private String lait;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
