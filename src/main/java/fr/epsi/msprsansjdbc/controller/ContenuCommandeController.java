package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.ContenuCommande;
import fr.epsi.msprsansjdbc.service.ContenuCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ContenuCommandeController {

    @Autowired
    private ContenuCommandeService contenuCommandeService;

    public void creerContenuCommande(int commande, int produit, int quantite) {
            ContenuCommande contenuCommande = new ContenuCommande(commande,produit,quantite);
            contenuCommandeService.create(contenuCommande);
    }
}
