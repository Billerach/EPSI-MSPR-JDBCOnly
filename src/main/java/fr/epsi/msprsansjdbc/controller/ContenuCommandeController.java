package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.service.ContenuCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ContenuCommandeController {

    @Autowired
    private ContenuCommandeService contenuCommandeService;

    public void creerContenuCommande(int commande, int produit, int quantite) {
        // Ici vous pouvez implémenter la logique pour créer les ContenuCommande en fonction de la commande reçue
        // Par exemple :
//        for (Produit produit : commande.getProduits()) {
//            ContenuCommande contenuCommande = new ContenuCommande();
//            contenuCommande.setCommande(commande);
//            contenuCommande.setProduit(produit);
//            contenuCommande.setQuantite(1); // Par exemple, vous pouvez ajuster la quantité selon vos besoins
//            contenuCommandeService.create(contenuCommande);
//        }
    }
}
