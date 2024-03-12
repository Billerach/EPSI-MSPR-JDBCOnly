package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Client;

import java.util.List;

public interface ClientDAO {
    Client create(Client client);
    List<Client> findAllClients();
    Client findById(int id_personne);
    void update(Client client);
    void archiveById(Client client);
}
