package fr.epsi.poec24.mspr.epsimspr;

import fr.epsi.poec24.mspr.epsimspr.dao.ProduitDAO;
import fr.epsi.poec24.mspr.epsimspr.entities.Produit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private ProduitDAO produitDAO;

    @Test
    void contextLoads() {
        // Test sur le chargement du contexte
    }

    @Test
    public void testGetAllProduits() {
        // Test sur la récupération de tous les produits depuis la base de données
        List<Produit> produits = produitDAO.getAllProduits();
        assertNotNull(produits, "La liste des produits ne doit pas être nulle.");
        assertFalse(produits.isEmpty(), "La liste des produits ne doit pas être vide.");
    }

    @Test
    public void testAddAndDeleteProduit() {
        // Test sur l'ajout et la suppression d'un produit
        Produit nouveauProduit = new Produit("NouveauNom", "NouveauDépartement", "NouveauLait");

        Produit produitAjoute = produitDAO.addProduit(nouveauProduit);
        assertNotNull(produitAjoute, "Le produit ajouté ne doit pas être nul.");

        List<Produit> produits = produitDAO.getAllProduits();
        assertFalse(produits.contains(produitAjoute), "Le produit ajouté doit être présent dans la liste.");

        produitDAO.deleteProduit(produitAjoute);

        produits = produitDAO.getAllProduits();
        assertFalse(produits.contains(produitAjoute), "Le produit supprimé ne doit plus être dans la liste.");
    }
}
