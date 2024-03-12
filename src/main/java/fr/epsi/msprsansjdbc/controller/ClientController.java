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
        service.create(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id_personne}/edition")
    public String modifierClient(@PathVariable int id_personne, Model model) {
        model.addAttribute("client", service.findById(id_personne));
        return "view-client-form-edition";
    }
    @PostMapping("/{id_personne}/edition")
    public String modifierClient(@PathVariable int id_personne, @ModelAttribute Client client) {
        client.setId_personne(id_personne);
        service.update(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id_personne}/suppression")
    public String Client(@PathVariable int id_personne, Model model) {
        Client clientAArchiver = service.findById(id_personne);
        model.addAttribute("client", clientAArchiver);

        if (clientAArchiver.isEstClient()){
            service.archiveById(clientAArchiver);
            System.out.println("Fonction archiveById appelée");
        }
        else {
            System.out.println("Erreur : la personne désignée n'est pas un client.");
        }
        return "redirect:/clients";
    }
}
