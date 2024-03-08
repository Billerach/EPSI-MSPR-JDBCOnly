package fr.epsi.msprsansjdbc.dao;

import fr.epsi.msprsansjdbc.entities.Client;
import fr.epsi.msprsansjdbc.entities.Commande;
import fr.epsi.msprsansjdbc.entities.ContenuCommande;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CommandeDAOImpl implements CommandeDAO {

    private static final Logger logger = LoggerFactory.getLogger(CommandeDAOImpl.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public CommandeDAOImpl(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("commandes")
                .usingGeneratedKeyColumns("id_commande");
    }

    private static final String FIND_ALL_QUERY = "SELECT ID_PERSONNE, NOM, PRENOM FROM personnes WHERE EST_CLIENT = TRUE";

    @Override
    public List<Commande> getAllCommandesWithPersonne() {
//        String sql = "SELECT c.id_commande, c.id_personne, c.date_commande, c.montant_total, p.nom " +
//                "FROM commandes c JOIN personnes p ON c.id_personne = p.id_personne";

        return jdbcTemplate.query("SELECT * FROM commandes", new BeanPropertyRowMapper<>(Commande.class));
    }

    @Override
    public List<Commande> findAll() {
        String sql = "SELECT Commandes.id_commande, produits.nom AS nom_produit, personnes.nom AS nom_client, " +
                "personnes.prenom AS prenom_client, Contenu_commande.quantite, Commandes.montant_total " +
                "FROM Commandes JOIN Contenu_commande ON Commandes.id_commande = Contenu_commande.id_commande " +
                "JOIN produits ON Contenu_commande.id_produit = produits.id_produit " +
                "JOIN personnes ON Commandes.id_personne = personnes.id_personne";

        logger.info("Récupération de toutes les commandes depuis la base de données.");
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Commande.class));
    }


    @Override
    public Commande create(Commande commande) {
        // Exécution de l'insertion de la commande
        BeanPropertySqlParameterSource parameterSourceCommande = new BeanPropertySqlParameterSource(commande);
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameterSourceCommande);
        commande.setId_commande(newId.intValue());

        // Vérifier si l'ID de personne est différent de 0
        if (commande.getId_personne() != 0) {
            // Recherche du client
            List<Client> clients = jdbcTemplate.query(
                    "SELECT id_personne, est_client FROM personnes WHERE id_personne = :id_personne AND est_client = true",
                    new MapSqlParameterSource("id_personne", commande.getId_personne()),
                    new BeanPropertyRowMapper<>(Client.class)
            );

            if (!clients.isEmpty()) {
                Client client = clients.get(0);
                logger.info("ID_personne récupéré pour la commande : {}", client.getId_personne());

                // Vérification de l'existence de la personne pour chaque client
                String checkPersonneSql = "SELECT COUNT(*) FROM personnes WHERE id_personne = :id_personne";
                MapSqlParameterSource checkPersonneParams = new MapSqlParameterSource();
                checkPersonneParams.addValue("id_personne", client.getId_personne());
                int count = jdbcTemplate.queryForObject(checkPersonneSql, checkPersonneParams, Integer.class);

                // Insertion du contenu de la commande pour chaque client
                String contenuCommandeSql = "INSERT INTO crm_sansjpa.contenu_commande (id_commande, id_produit, quantite) VALUES (:id_commande, :id_produit, :quantite)";
                MapSqlParameterSource contenuCommandeParams = new MapSqlParameterSource();
                contenuCommandeParams.addValue("id_commande", commande.getId_commande());

                // Modification pour utiliser les valeurs réelles de ContenuCommande
                ContenuCommande contenuCommande = commande.getContenuCommande();
                contenuCommandeParams.addValue("id_produit", contenuCommande.getId_produit());
                contenuCommandeParams.addValue("quantite", contenuCommande.getQuantite());

                jdbcTemplate.update(contenuCommandeSql, contenuCommandeParams);
            } else {
                // Gérer le cas où aucun client n'est trouvé
                logger.error("Client not found for id_personne: {}", commande.getId_personne());
                throw new IllegalArgumentException("Client not found for id_personne: " + commande.getId_personne());
            }
        } else {
            // Gérer le cas où l'ID de personne est invalide (0)
            logger.error("Invalid ID_personne (0) for creating a command.");
            throw new IllegalArgumentException("Invalid ID_personne (0) for creating a command.");
        }

        return commande;
    }



    @Override
    public Commande findById(int id_commande) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_commande", id_commande);
        return jdbcTemplate.queryForObject("SELECT id_commande, id_personne, date_commande, montant_total FROM commandes WHERE id_commande = :id_commande", parameterSource, new BeanPropertyRowMapper<>(Commande.class));
    }

    @Override
    public Commande update(Commande commande) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(commande);
        jdbcTemplate.update("UPDATE commandes SET id_personne = :id_personne, date_commande = :date_commande, montant_total = :montant_total WHERE id_commande = :id_commande", parameterSource);

        return commande;
    }

    @Override
    public void deleteById(int id_commande) {
        // Implémentez la suppression en fonction de vos besoins.
    }

    @Override
    public String getNomProduit(int id_commande) {
        String sql = "SELECT produits.nom FROM crm_sansjpa.produits " +
                "JOIN crm_sansjpa.contenu_commande ON crm_sansjpa.contenu_commande.id_produit = crm_sansjpa.produits.id_produit " +
                "WHERE crm_sansjpa.contenu_commande.id_commande = :id_commande";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id_commande", id_commande);
        return jdbcTemplate.queryForObject(sql, parameterSource, String.class);
    }

    @Override
    public String getClient(int id_personne) {
        String sql = "SELECT nom FROM personnes WHERE est_client = true AND id_personne = :id_personne";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id_personne", id_personne);
        return jdbcTemplate.queryForObject(sql, parameterSource, String.class);
    }

    @Override
    public int getQuantite(int id_commande) {
        String sql = "SELECT quantite FROM crm_sansjpa.contenu_commande WHERE id_commande = :id_commande";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id_commande", id_commande);
        return jdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
    }

    @Override
    public float getTotal(int id_commande) {
        String sql = "SELECT montant_total FROM commandes WHERE id_commande = :id_commande";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id_commande", id_commande);
        return jdbcTemplate.queryForObject(sql, parameterSource, Float.class);
    }
}