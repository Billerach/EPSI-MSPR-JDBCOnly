package fr.epsi.msprsansjdbc.service;

import fr.epsi.msprsansjdbc.dao.ProduitDAO;
import fr.epsi.msprsansjdbc.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    private final ProduitDAO dao;

    @Autowired
    public ProduitService(ProduitDAO dao) {
        this.dao = dao;
    }

    public List<Produit> findAll() {
        return dao.findAll();
    }

    public Produit create(Produit Produit) {
        return dao.create(Produit);
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }

    public Object findById(int id) {
        return dao.findById(id);
    }

    public void update(Produit Produit) {
        dao.update(Produit);
    }
}