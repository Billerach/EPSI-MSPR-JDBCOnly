package fr.epsi.msprsansjdbc.entities;

public class ContenuCommande {

    private int id_contenu_commande;
    private int id_commande;
    private int id_produit;
    private int quantite;

    public ContenuCommande(int id_commande, int id_produit, int quantite) {
        this.id_contenu_commande = id_contenu_commande;
        this.id_commande = id_commande;
        this.id_produit = id_produit;
        this.quantite = quantite;
    }

    public int getId_contenu_commande() { return id_contenu_commande; }
    public void setId_contenu_commande(int id_contenu_commande) {
        this.id_contenu_commande = id_contenu_commande;
    }
    public int getId_produit() {
        return id_produit;
    }
    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public int getId_commande() {
        return id_commande;
    }
    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }
}
