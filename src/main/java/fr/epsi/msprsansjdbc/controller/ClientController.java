package fr.epsi.msprsansjdbc.controller;

import fr.epsi.msprsansjdbc.entities.Client;
import fr.epsi.msprsansjdbc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Indique à Spring que cette classe est un contrôleur
@RequestMapping("/clients") // Définit le préfixe d'URL pour toutes les méthodes de ce contrôleur
public class ClientController {

    // Le contrôleur s'appuie sur un service pour effectuer ses opérations
    private final ClientService service;

    // Injection du service via l'annotation @Autowired lors de la création du contrôleur
    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    // Méthode pour afficher la liste des clients
    @GetMapping()
    public String afficherListeClients(Model model) {
        List<Client> mesClients = service.findAllClients();
        model.addAttribute("clients", mesClients);
        return "view-client-list"; // Retourne le nom de la vue à afficher
    }

    // Méthode pour afficher le formulaire de création d'un client
    @GetMapping("/creer")
    public String creerClient(Model model) {
        model.addAttribute("client", new Client()); // Envoie un nouvel objet Client vide à la vue
        return "view-client-form-creation"; // Retourne le nom de la vue à afficher
    }

    // Méthode pour traiter la création d'un client
    @PostMapping("/creer")
    public String creerClient(@ModelAttribute Client client) {
        service.create(client); // Appel du service pour créer le client
        return "redirect:/clients"; // Redirection vers la liste des clients
    }

    // Méthode pour afficher le formulaire de modification d'un client
    @GetMapping("/{id_personne}/edition")
    public String modifierClient(@PathVariable int id_personne, Model model) {
        model.addAttribute("client", service.findById(id_personne)); // Envoie le client à modifier à la vue
        return "view-client-form-edition"; // Retourne le nom de la vue à afficher
    }

    // Méthode pour traiter la modification d'un client
    @PostMapping("/{id_personne}/edition")
    public String modifierClient(@PathVariable int id_personne, @ModelAttribute Client client) {
        client.setId_personne(id_personne);
        service.update(client); // Appel du service pour mettre à jour le client
        return "redirect:/clients"; // Redirection vers la liste des clients
    }

    // Méthode pour traiter la suppression d'un client
    @GetMapping("/{id_personne}/suppression")
    public String Client(@PathVariable int id_personne, Model model) {
        Client clientAArchiver = service.findById(id_personne);
        model.addAttribute("client", clientAArchiver);

        // Vérifie si le client à supprimer est également un client dans le système
        if (clientAArchiver.isEstClient()){
            service.archiveById(clientAArchiver); // Appel du service pour archiver le client
            System.out.println("Fonction archiveById appelée");
        }
        else {
            System.out.println("Erreur : la personne désignée n'est pas un client.");
        }
        return "redirect:/clients"; // Redirection vers la liste des clients
    }
}
