package fr.epsi.poec24.mspr.epsimspr.service;

import fr.epsi.poec24.mspr.epsimspr.dao.ClientDAO;
import fr.epsi.poec24.mspr.epsimspr.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientDAO dao;

    @Autowired
    public ClientService(ClientDAO dao) {
        this.dao = dao;
    }

    public List<Client> findAll() {
        return dao.findAll();
    }

    public Client create(Client Client) {
        return dao.create(Client);
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }

    public Object findById(int id) {
        return dao.findById(id);
    }

    public void update(Client Client) {
        dao.update(Client);
    }
}
