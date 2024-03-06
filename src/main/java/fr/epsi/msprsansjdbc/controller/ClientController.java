package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.Client;
import fr.epsi.msprsansjdbc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    //Dans le développement multi-couche et notamment le MVC, le controller s'appuie sur d'autres briques
    // pour réaliser ces traitements... c'est la raison pour laquelle, nous avons besoin ici d'un objet service
    // dont la création est déléguée à Spring Core
    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/clients")
    @GetMapping()
    public String afficherListeClients(Model model) {

        //On charge la liste des CLIENTS pour affichage dans la vue
        List<Client> mesClients = service.findAll();
        //On envoie la liste à la vue à travers le modèle du MVC
        model.addAttribute("clients", mesClients);
        return "view-client-list";
    }

    @GetMapping("/creer")
    public String creerClient(Model model) {
        //On envoie à la vue l'objet vide à remplir depuis le formulaire
        model.addAttribute("client", new Client());
        return "view-client-form-creation";
    }
    @PostMapping("/creer")
    public String creerClient(@ModelAttribute Client client) {
        //A la validation du formulaire sur l'action "/formateurs/creer" c'est cette méthode qui est activée!
        //Elle permet grâce, là encore, à l'injection de récupérer l'objet inutilisé dans le formulaire "view-client-form-creation"
        //Il ne reste plus qu'à essayer de sauvegarder cet objet en s'appuyant sur le service Client
        service.create(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edition")
    public String modifierClient(@PathVariable int id, Model model) {
        //Ici, on récupère l'id dans l'URL et on l'injecte dans la variable id de type "int"
        //On envoie ensuite à la vue l'objet client dont l'id est passé à modifier depuis le formulaire
        model.addAttribute("client", service.findById(id));
        return "view-client-form-edition";
    }
    @PostMapping("/{id}/edition")
    public String modifierClient(@PathVariable int id, @ModelAttribute Client client) {
        //Comme sur la validation du formulaire de création, ici on fait à peu près la même chose
        client.setId(id);
        service.update(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/suppression")
    public String Client(@PathVariable int id) {
        //TODO il faut faire toutes les vérifications nécessaires ici
        service.deleteById(id);
        return "redirect:/clients";
    }
}
