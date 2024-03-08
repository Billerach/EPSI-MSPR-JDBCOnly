package fr.epsi.msprsansjdbc.service;

import fr.epsi.msprsansjdbc.dao.CommandeDAO;
import fr.epsi.msprsansjdbc.entities.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

    private final CommandeDAO dao;

    @Autowired
    public CommandeService(CommandeDAO dao) {
        this.dao = dao;
    }

    public List<Commande> findAll() {
        return dao.findAll();
    }

    public String getNomProduit(int idCommande) {
        return dao.getNomProduit(idCommande);
    }

    public Commande create(Commande commande) {
        return dao.create(commande);
    }

    public Object findById(int id) {
        return dao.findById(id);
    }

    public void update(Commande commande) {
        dao.update(commande);
    }
}
