package fr.epsi.msprsansjdbc.service;

import fr.epsi.msprsansjdbc.dao.CommandeDAO;
import fr.epsi.msprsansjdbc.entities.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service pour gérer les opérations liées aux commandes
@Service
public class CommandeService {

    private final CommandeDAO dao; // Référence vers le DAO pour interagir avec la base de données

    // Injection de dépendance du DAO via le constructeur
    @Autowired
    public CommandeService(CommandeDAO dao) {
        this.dao = dao;
    }

    // Méthode pour récupérer toutes les commandes
    public List<Commande> getAllCommandes() {
        return dao.getAllCommandes();
    }

    // Méthode pour créer une nouvelle commande
    public Commande create(Commande commande) {
        return dao.create(commande);
    }

    // Méthode pour trouver une commande par son ID
    public Commande findById(int id_commande) {
        return dao.findById(id_commande);
    }

    // Méthode pour mettre à jour une commande
    public void update(Commande commande) {
        dao.update(commande);
    }

    // Méthode pour supprimmer une commande
    public void deleteCommmande(int id_commande) {
        dao.deleteCommmande(id_commande);
    }
}
