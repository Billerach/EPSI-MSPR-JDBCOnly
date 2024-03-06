package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Statistique;

import java.util.List;

public interface StatistiqueDAO {
    List<Statistique> getClientsAvecNombreProduitsAchetes();
    List<Statistique> getProduitsAvecNombreClientsAchetes();
    List<Statistique> getProduitsAvecNombreClientsAchetesAsc();
    List<Statistique> getClientsAvecNombreCommandes();
    List<Statistique> getProduitsAvecNombreCommandes();
    List<Statistique> getProduitsAvecNombreCommandesAsc();
    List<Statistique> getClientsAvecMontantTotalDepense();
    List<Statistique> getProduitsAvecChiffreAffairesTotal();
    List<Statistique> getProduitsAvecChiffreAffairesTotalAsc();
    List<Statistique> getClientsAvecMontantTotalDepenseAsc();
    List<Statistique> getProduitsAvecTypeLaitEtChiffreAffairesTotal();
    List<Statistique> getClientsAvecMontantMoyenDepenseParCommande();
    List<Statistique> getProduitsAvecNombreMoyenCommandes();
    List<Statistique> getProduitsAvecTypeLaitEtNombreCommandes();
    List<Statistique> getProduitsAvecDepartementEtNombreCommandes();
    List<Statistique> getProduitsPlusVendus();
    List<Statistique> getProduitsMoinsVendus();
}

