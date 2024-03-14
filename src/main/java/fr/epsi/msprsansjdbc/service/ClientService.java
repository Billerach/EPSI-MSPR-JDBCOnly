package fr.epsi.msprsansjdbc.service;

import fr.epsi.msprsansjdbc.entities.Client;
import fr.epsi.msprsansjdbc.dao.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service pour gérer les opérations liées aux clients
@Service
public class ClientService {

    private final ClientDAO dao; // Référence vers le DAO pour interagir avec la base de données

    // Injection de dépendance du DAO via le constructeur
    @Autowired
    public ClientService(ClientDAO dao) {
        this.dao = dao;
    }

    // Méthode pour trouver tous les clients
    public List<Client> findAllClients() {
        return dao.findAllClients();
    }

    // Méthode pour créer un nouveau client
    public Client create(Client client) {
        return dao.create(client);
    }

    // Méthode pour archiver un client par son ID
    public void archiveById(Client client){ dao.archiveById(client); }

    // Méthode pour trouver un client par son ID
    public Client findById(int id_personne) { return dao.findById(id_personne); }

    // Méthode pour mettre à jour les informations d'un client
    public void update(Client client) { dao.update(client); }
}
