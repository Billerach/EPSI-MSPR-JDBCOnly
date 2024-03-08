package fr.epsi.msprsansjdbc.entities;

import java.util.Date;

public class Commande {

    private int id_commande;
    private int id_personne;
    private Date date_commande;
    private float montant_total;

    private ContenuCommande contenuCommande;

    public Commande() {
    }

    public Commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public Commande(int id_commande, int id_personne) {
        this.id_commande = id_commande;
        this.id_personne = id_personne;
    }

    public Commande(int id_commande, int id_personne, Date date_commande) {
        this.id_commande = id_commande;
        this.id_personne = id_personne;
        this.date_commande = date_commande;
    }

    public Commande(int id_commande, int id_personne, Date date_commande, float montant_total) {
        this.id_commande = id_commande;
        this.id_personne = id_personne;
        this.date_commande = date_commande;
        this.montant_total = montant_total;
    }

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

    public ContenuCommande getContenuCommande() {
        return contenuCommande;
    }

    public void setContenuCommande(ContenuCommande contenuCommande) {
        this.contenuCommande = contenuCommande;
    }
}
