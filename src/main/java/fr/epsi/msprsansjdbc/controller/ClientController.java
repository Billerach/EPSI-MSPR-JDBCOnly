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

    // Injection du service via l'annotation @Autowired lors de la création du contrôleur.
    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping()
    public String afficherListeClients(Model model) {
        List<Client> mesClients = service.findAllClients();
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
        //À la validation du formulaire sur l'action "/clients/creer" c'est cette méthode qui est activée !
        //Elle permet grâce, là encore, à l'injection de récupérer l'objet inutilisé dans le formulaire "view-client-form-creation"
        //Il ne reste plus qu'à essayer de sauvegarder cet objet en s'appuyant sur le service Client
        service.create(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id_personne}/edition")
    public String modifierClient(@PathVariable int id_personne, Model model) {
        //Ici, on récupère l'id dans l'URL et on l'injecte dans la variable id de type "int"
        //On envoie ensuite à la vue l'objet client dont l'id est passé à modifier depuis le formulaire
        model.addAttribute("client", service.findById(id_personne));
        return "view-client-form-edition";
    }
    @PostMapping("/{id_personne}/edition")
    public String modifierClient(@PathVariable int id_personne, @ModelAttribute Client client) {
        //Comme sur la validation du formulaire de création, ici, on fait à peu près la même chose
        client.setId_personne(id_personne);
        service.update(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id_personne}/suppression")
    public String Client(@PathVariable int id_personne, Model model) {
        Client clientAArchiver = service.findById(id_personne);
        model.addAttribute("client", clientAArchiver);

//        if (clientAArchiver.isEstClient()){
//            service.archiveById(clientAArchiver);
//            System.out.println("trkfhrk:hc: !!");
//        }
//        else {
//            System.out.println("SURPRISE MOTHERFUCKER !!");
//        }
//        return "redirect:/clients";
        return "view-client-test";
    }
}
