package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Statistique;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class StatistiqueDAOImpl implements StatistiqueDAO {
    private JdbcTemplate jdbcTemplate;

    private static final String STATISTIQUE_TABLE = "statistique";

    private Statistique mapStatistique(ResultSet resultSet, int i) throws SQLException {
        Statistique statistique = new Statistique();
        statistique.setId(resultSet.getInt("id"));

        return statistique;
    }

    @Override
    public List<Statistique> getClientsAvecNombreProduitsAchetes() {
        String sql = "SELECT id_personne, nom, nombre_produits_achetes FROM " + STATISTIQUE_TABLE + " WHERE type_personne = 'Client'";
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getProduitsAvecNombreClientsAchetes() {
        String sql = "SELECT id_produit, nom, nombre_clients_achetes FROM " + STATISTIQUE_TABLE + " WHERE type_personne = 'Client'";
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getProduitsAvecNombreClientsAchetesAsc() {
        String sql = "SELECT id_produit, nom, nombre_clients_achetes FROM " + STATISTIQUE_TABLE + " WHERE type_personne = 'Client' ORDER BY nombre_clients_achetes ASC";
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getClientsAvecNombreCommandes() {
        String sql = "SELECT id_personne, nom, nombre_commandes FROM " + STATISTIQUE_TABLE + " WHERE type_personne = 'Client'";
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getProduitsAvecNombreCommandes() {
        String sql = "SELECT id_produit, nom, nombre_commandes FROM " + STATISTIQUE_TABLE;
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getProduitsAvecNombreCommandesAsc() {
        String sql = "SELECT id_produit, nom, nombre_commandes FROM " + STATISTIQUE_TABLE + " ORDER BY nombre_commandes ASC";
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getClientsAvecMontantTotalDepense() {
        String sql = "SELECT id_personne, nom, montant_total_depense FROM " + STATISTIQUE_TABLE + " WHERE type_personne = 'Client'";
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getProduitsAvecChiffreAffairesTotal() {
        String sql = "SELECT id_produit, nom, chiffre_affaires_total FROM " + STATISTIQUE_TABLE;
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getProduitsAvecChiffreAffairesTotalAsc() {
        String sql = "SELECT id_produit, nom, chiffre_affaires_total FROM " + STATISTIQUE_TABLE + " ORDER BY chiffre_affaires_total ASC";
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getClientsAvecMontantTotalDepenseAsc() {
        String sql = "SELECT id_personne, nom, montant_total_depense FROM " + STATISTIQUE_TABLE + " WHERE type_personne = 'Client' ORDER BY montant_total_depense ASC";
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getProduitsAvecTypeLaitEtChiffreAffairesTotal() {
        String sql = "SELECT id_produit, nom, type_lait, chiffre_affaires_total FROM " + STATISTIQUE_TABLE;
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getClientsAvecMontantMoyenDepenseParCommande() {
        String sql = "SELECT id_personne, nom, montant_moyen_depense_par_commande FROM " + STATISTIQUE_TABLE + " WHERE type_personne = 'Client'";
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getProduitsAvecNombreMoyenCommandes() {
        String sql = "SELECT id_produit, nom, nombre_moyen_commandes FROM " + STATISTIQUE_TABLE;
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getProduitsAvecTypeLaitEtNombreCommandes() {
        String sql = "SELECT id_produit, nom, type_lait, nombre_commandes FROM " + STATISTIQUE_TABLE;
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getProduitsAvecDepartementEtNombreCommandes() {
        String sql = "SELECT id_produit, nom, departement, nombre_commandes FROM " + STATISTIQUE_TABLE;
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getProduitsPlusVendus() {
        String sql = "SELECT id_produit, nom, nombre_commandes FROM " + STATISTIQUE_TABLE + " ORDER BY nombre_commandes DESC";
        return jdbcTemplate.query(sql, this::mapStatistique);
    }

    @Override
    public List<Statistique> getProduitsMoinsVendus() {
        String sql = "SELECT id_produit, nom, nombre_commandes FROM " + STATISTIQUE_TABLE + " ORDER BY nombre_commandes ASC";
        return jdbcTemplate.query(sql, this::mapStatistique);
    }
}
