package fr.epsi.msprsansjdbc.service;

import fr.epsi.msprsansjdbc.dao.StatistiqueDAO;
import fr.epsi.msprsansjdbc.entities.Statistique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatistiqueService {

    private final StatistiqueDAO dao;

    @Autowired
    public StatistiqueService(StatistiqueDAO dao) {
        this.dao = dao;
    }

    public List<Statistique> getClientsAvecNombreProduitsAchetes() {
        return dao.getClientsAvecNombreProduitsAchetes();
    }

    public List<Statistique> getProduitsAvecNombreClientsAchetes() {
        return dao.getProduitsAvecNombreClientsAchetes();
    }

    public List<Statistique> getProduitsAvecNombreClientsAchetesAsc() {
        return dao.getProduitsAvecNombreClientsAchetesAsc();
    }

    public List<Statistique> getClientsAvecNombreCommandes() {
        return dao.getClientsAvecNombreCommandes();
    }

    public List<Statistique> getProduitsAvecNombreCommandes() {
        return dao.getProduitsAvecNombreCommandes();
    }

    public List<Statistique> getProduitsAvecNombreCommandesAsc() {
        return dao.getProduitsAvecNombreCommandesAsc();
    }

    public List<Statistique> getClientsAvecMontantTotalDepense() {
        return dao.getClientsAvecMontantTotalDepense();
    }

    public List<Statistique> getProduitsAvecChiffreAffairesTotal() {
        return dao.getProduitsAvecChiffreAffairesTotal();
    }

    public List<Statistique> getProduitsAvecChiffreAffairesTotalAsc() {
        return dao.getProduitsAvecChiffreAffairesTotalAsc();
    }

    public List<Statistique> getClientsAvecMontantTotalDepenseAsc() {
        return dao.getClientsAvecMontantTotalDepenseAsc();
    }

    public List<Statistique> getProduitsAvecTypeLaitEtChiffreAffairesTotal() {
        return dao.getProduitsAvecTypeLaitEtChiffreAffairesTotal();
    }

    public List<Statistique> getClientsAvecMontantMoyenDepenseParCommande() {
        return dao.getClientsAvecMontantMoyenDepenseParCommande();
    }

    public List<Statistique> getProduitsAvecNombreMoyenCommandes() {
        return dao.getProduitsAvecNombreMoyenCommandes();
    }

    public List<Statistique> getProduitsAvecTypeLaitEtNombreCommandes() {
        return dao.getProduitsAvecTypeLaitEtNombreCommandes();
    }

    public List<Statistique> getProduitsAvecDepartementEtNombreCommandes() {
        return dao.getProduitsAvecDepartementEtNombreCommandes();
    }

    public List<Statistique> getProduitsPlusVendus() {
        return dao.getProduitsPlusVendus();
    }

    public List<Statistique> getProduitsMoinsVendus() {
        return dao.getProduitsMoinsVendus();
    }
}