package fr.epsi.msprsansjdbc;

import fr.epsi.msprsansjdbc.controller.ClientController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Scanner;

@SpringBootApplication
public class EpsiMsprApplication implements CommandLineRunner {

    private final ClientController controller;

    // Injection des dépendances JdbcTemplate et NamedParameterJdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate npjt;

    // Scanner pour lire l'entrée de l'utilisateur
    private final Scanner scanner = new Scanner(System.in);

    // Constructeur pour l'injection de dépendance du contrôleur
    @Autowired
    public EpsiMsprApplication(ClientController controller) {
        this.controller = controller;
    }

    // Point d'entrée de l'application
    public static void main(String[] args) {
        // Lance l'application Spring Boot
        SpringApplication.run(EpsiMsprApplication.class, args);
    }

    // Méthode exécutée après le démarrage de l'application
    @Override
    public void run(String... args) throws Exception {
        // Affiche un message indiquant que l'application est démarrée
        System.out.println("L'application est démarrée.");
    }
}

