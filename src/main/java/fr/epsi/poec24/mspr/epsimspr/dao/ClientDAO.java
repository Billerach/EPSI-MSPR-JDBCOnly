package fr.epsi.poec24.mspr.epsimspr.dao;

import fr.epsi.poec24.mspr.epsimspr.entities.Client;

import java.util.List;

public interface ClientDAO {

    //C -> CRUD
    Client create(Client client);
    //R -> CRUD
    List<Client> findAll();
    //R -> CRUD
    Client findById(int id);

    //U -> CRUD
    Client update(Client client);

    //D -> Crud
    void deleteById(int id);
}
