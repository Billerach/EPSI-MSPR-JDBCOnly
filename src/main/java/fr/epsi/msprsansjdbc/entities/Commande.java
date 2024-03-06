package fr.epsi.msprsansjdbc.entities;

import java.util.Date;

public class Commande {

    // Attributs
    private int id_commande;
    private int id_client;
    private Date date_commande;
    private float montant_total;

    // Constructeur par défaut

    public Commande() {
    }

    // Constructeur avec un paramètre
    public Commande(int id_commande) {
        this.id_commande = id_commande;
    }

    // Constructeur avec deux paramètres
    public Commande(int id_commande, int id_client) {
        this.id_commande = id_commande;
        this.id_client = id_client;
    }

    // Constructeur avec trois paramètres
    public Commande(int id_commande, int id_client, Date date_commande) {
        this.id_commande = id_commande;
        this.id_client = id_client;
        this.date_commande = date_commande;
    }

    // Constructeur avec quatre paramètres
    public Commande(int id_commande, int id_client, Date date_commande, float montant_total) {
        this.id_commande = id_commande;
        this.id_client = id_client;
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

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
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
}
