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

    public Client create(Client client) {
        return dao.create(client);
    }

    public void archiveById(Client client){ dao.archiveById(client); }

    public Client findById(int id_personne) { return dao.findById(id_personne); }

    public void update(Client client) { dao.update(client); }
}
