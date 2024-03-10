package fr.epsi.msprsansjdbc;

import fr.epsi.msprsansjdbc.dao.ProduitDAOImpl;
import fr.epsi.msprsansjdbc.entities.Produit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private ProduitDAOImpl produitDAO;

    @Test
    void contextLoads() {
        // Test sur le chargement du contexte
    }

    @Test
    public void testGetAllProduits() {
        // Test sur la récupération de tous les produits depuis la base de données
        List<Produit> produits = produitDAO.findAll();
        assertNotNull(produits, "La liste des produits ne doit pas être nulle.");
        assertFalse(produits.isEmpty(), "La liste des produits ne doit pas être vide.");
    }

//    @Test
//    public void testAddAndDeleteProduit() {
//        // Test sur l'ajout et la suppression d'un produit
////        Produit nouveauProduit = new Produit("NouveauNom", "NouveauDépartement", "NouveauLait");
//
//        // Ajoute une date factice (à remplacer par la logique de votre application)
//        nouveauProduit.setDate_fin(new Date());
//
//        Produit produitAjoute = produitDAO.create(nouveauProduit);
//        assertNotNull(produitAjoute, "Le produit ajouté ne doit pas être nul.");
//
//        List<Produit> produits = produitDAO.findAll();
//        assertTrue(produits.contains(produitAjoute), "Le produit ajouté doit être présent dans la liste.");
//
//        produitDAO.deleteById(produitAjoute.getId_produit());
//
//        produits = produitDAO.findAll();
//        assertFalse(produits.contains(produitAjoute), "Le produit supprimé ne doit plus être dans la liste.");
//    }

}
