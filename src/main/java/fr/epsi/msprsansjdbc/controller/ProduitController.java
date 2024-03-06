package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.Client;
import fr.epsi.msprsansjdbc.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produits")
public class ProduitController {

    // Service responsable de la logique métier liée aux produits.
    private final ProduitService service;

    // Injection du service via l'annotation @Autowired lors de la création du contrôleur.
    @Autowired
    public ProduitController(ProduitService service) {
        this.produitService = service;
    }

    @GetMapping()
    public String afficherListeProduits(Model model) {

        //On charge la liste des CLIENTS pour affichage dans la vue
        List<Produit> mesProduits = service.findAll();
        //On envoie la liste à la vue à travers le modèle du MVC
        model.addAttribute("produits", mesProduits);
        return "view-produit-list";
    }

    @GetMapping("/creer")
    public String creerProduit(Model model) {
        //On envoie à la vue l'objet vide à remplir depuis le formulaire
        model.addAttribute("produit", new Produit());
        return "view-produit-form-creation";
    }
    @PostMapping("/creer")
    public String creerProduit(@ModelAttribute Produit produit) {
        service.create(produit);
        return "redirect:/produits";
    }

    @GetMapping("/{id}/edition")
    public String modifierCLient(@PathVariable int id, Model model) {
        //Ici, on récupère l'id dans l'URL et on l'injecte dans la variable id de type "int"
        //On envoie ensuite à la vue l'objet client dont l'id est passé à modifier depuis le formulaire
        model.addAttribute("client", service.findById(id));
        return "view-client-form-edition";
    }
    @PostMapping("/{id}/edition")
    public String modifierCLient(@PathVariable int id, @ModelAttribute Client client) {
        //Comme sur la validation du formulaire de création, ici on fait à peu près la même chose
        client.setId(id);
        service.update(client);
        return "redirect:/clients";
    }

}
