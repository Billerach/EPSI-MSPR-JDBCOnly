package fr.epsi.msprsansjdbc.entities;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Date;

public class Commande {

    // Attributs
    private int id_commande;
    private int id_personne;
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

    public void setId_personne(int id_personne) {
        this.id_personne = id_personne;
    }
    public int getId_personne() {
        return id_personne;
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
