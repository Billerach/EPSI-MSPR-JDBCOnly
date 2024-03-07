package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Client;

import java.util.List;

public interface ClientDAO {

    //C -> CRUD
    Client create(Client client);
    //R -> CRUD
    List<Client> findAllClients();
    //R -> CRUD
    Client findById(int id_personne);

    //U -> CRUD
    Client update(Client client);

    //D -> Crud
    void deleteById(int id_personne);

}
