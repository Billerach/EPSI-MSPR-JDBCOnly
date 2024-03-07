package fr.epsi.msprsansjdbc.service;

import fr.epsi.msprsansjdbc.entities.Client;
import fr.epsi.msprsansjdbc.dao.ClientDAO;
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

    public List<Client> findAllClients() {
        return dao.findAllClients();
    }

    public Client create(Client Client) {
        return dao.create(Client);
    }

    public void deleteById(int id_personne){
        dao.deleteById(id_personne);
    }

    public Object findById(int id_personne) {
        return dao.findById(id_personne);
    }

    public void update(Client Client) {
        dao.update(Client);
    }
}
