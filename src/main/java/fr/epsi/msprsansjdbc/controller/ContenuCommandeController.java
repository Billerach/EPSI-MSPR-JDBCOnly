package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.ContenuCommande;
import fr.epsi.msprsansjdbc.service.ContenuCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ContenuCommandeController {

    @Autowired
    private ContenuCommandeService service;

    public void creerContenuCommande(int commande, int produit, int quantite) {
        ContenuCommande contenuCommande = new ContenuCommande(commande,produit,quantite);
        service.create(contenuCommande);
    }

    public List<ContenuCommande> getContenuCommandeList(int id_commande) {
        return service.getContenuCommandeList(id_commande);
    }
}
