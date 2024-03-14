package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Client;

import java.util.List;

// Interface représentant un contrat pour accéder aux données des clients dans la base de données
public interface ClientDAO {

    // Méthode pour créer un nouveau client dans la base de données
    // Elle prend un objet Client en argument qui contient les informations du nouveau client à créer
    // Elle renvoie également un objet Client qui représente le client créé, éventuellement avec des données supplémentaires attribuées par la base de données (comme l'identifiant)
    Client create(Client client);

    // Méthode pour récupérer la liste de tous les clients de la base de données
    // Elle renvoie une liste d'objets Client contenant les informations de chaque client
    List<Client> findAllClients();

    // Méthode pour récupérer un client spécifique de la base de données en utilisant son identifiant unique (id_personne)
    // Elle prend l'identifiant du client en argument et renvoie l'objet Client correspondant s'il est trouvé, sinon elle renvoie null
    Client findById(int id_personne);

    // Méthode pour mettre à jour les informations d'un client existant dans la base de données
    // Elle prend un objet Client en argument qui contient les nouvelles informations du client à mettre à jour
    void update(Client client);

    // Méthode pour archiver un client spécifique dans la base de données
    // Elle prend un objet Client en argument et archive ce client, généralement en modifiant un attribut dans la base de données
    void archiveById(Client client);
}

