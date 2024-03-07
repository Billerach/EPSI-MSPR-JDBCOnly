package fr.epsi.msprsansjdbc.entities;

import java.util.Date;

public class Commande {

    // Attributs
    private int id_commande;
    private int id_personne;
    private Date date_commande;
    private float montant_total;
    private Produit produit;

    // Constructeur par défaut
    public Commande() {
    }

    // Constructeur avec un paramètre
    public Commande(int id_commande) {
        this.id_commande = id_commande;
    }

    // Constructeur avec deux paramètres
    public Commande(int id_commande, int id_personne) {
        this.id_commande = id_commande;
        this.id_personne = id_personne;
    }

    // Constructeur avec trois paramètres
    public Commande(int id_commande, int id_personne, Date date_commande) {
        this.id_commande = id_commande;
        this.id_personne = id_personne;
        this.date_commande = date_commande;
    }

    // Constructeur avec quatre paramètres
    public Commande(int id_commande, int id_personne, Date date_commande, float montant_total) {
        this.id_commande = id_commande;
        this.id_personne = id_personne;
        this.date_commande = date_commande;
        this.montant_total = montant_total;
    }

    // Getters et Setters
    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_personne() {
        return id_personne;
    }

    public void setId_personne(int id_personne) {
        this.id_personne = id_personne;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public float getMontant_total() {
        return montant_total;
    }

    public void setMontant_total(float montant_total) {
        this.montant_total = montant_total;
    }

    // Getter pour le nom du client
    public String getClient() {
        // Implémentez la logique pour récupérer le nom du client
        return "Nom du client";
    }

    // Getter pour la quantité
    public int getQuantite() {
        // Implémentez la logique pour récupérer la quantité
        return 0;
    }

    // Getter pour le total
    public float getTotal() {
        // Implémentez la logique pour récupérer le total
        return 0.0f;
    }

    // Getter pour le nom du produit
    public String getProduit() {
        if (produit != null) {
            return produit.getNom();
        } else {
            return "Produit non spécifié";
        }
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id_commande=" + id_commande +
                ", id_personne=" + id_personne +
                ", date_commande=" + date_commande +
                ", montant_total=" + montant_total +
                '}';
    }
}

