package fr.epsi.msprsansjdbc.service;

import fr.epsi.msprsansjdbc.dao.ClientDAO;
import fr.epsi.msprsansjdbc.dao.ContenuCommandeDAO;
import fr.epsi.msprsansjdbc.entities.Client;
import fr.epsi.msprsansjdbc.entities.ContenuCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenuCommandeService {

        private final ContenuCommandeDAO dao;

        @Autowired
        public ContenuCommandeService(ContenuCommandeDAO dao) {
            this.dao = dao;
        }
        public ContenuCommande create(ContenuCommande contenuCommande) {
            return dao.create(contenuCommande);
        }

        public List<ContenuCommande> getContenuCommandeList(int id_commande) {
            return dao.getContenuCommandeList(id_commande);
        }
}
