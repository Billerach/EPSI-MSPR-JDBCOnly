package fr.epsi.msprsansjdbc.entities;

import java.math.BigDecimal;

public class Statistique {

    private String nomProduit;
    private Integer nombreVentes;
    private String mois;
    private Integer annee;
    private BigDecimal chiffreAffaire;


    // Constructeurs, getters et setters

    public Statistique() {
        // Constructeur par défaut
    }

    public Statistique(String nomProduit, Integer nombreVentes) {
        this.nomProduit = nomProduit;
        this.nombreVentes = nombreVentes;
    }

    public Statistique(String mois, BigDecimal chiffreAffaire) {
        this.mois = mois;
        this.chiffreAffaire = chiffreAffaire;
    }

    public Statistique(Integer annee, BigDecimal chiffreAffaire) {
        this.annee = annee;
        this.chiffreAffaire = chiffreAffaire;
    }

    // Getters et setters

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public Integer getNombreVentes() {
        return nombreVentes;
    }

    public void setNombreVentes(Integer nombreVentes) {
        this.nombreVentes = nombreVentes;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public BigDecimal getChiffreAffaire() {
        return chiffreAffaire;
    }

    public void setChiffreAffaire(BigDecimal chiffreAffaire) {
        this.chiffreAffaire = chiffreAffaire;
    }
}



