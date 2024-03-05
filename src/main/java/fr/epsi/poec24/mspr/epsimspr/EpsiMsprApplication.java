package fr.epsi.poec24.mspr.epsimspr;

import fr.epsi.poec24.mspr.epsimspr.controller.ClientController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Scanner;

@SpringBootApplication
public class EpsiMsprApplication  implements CommandLineRunner {
    private ClientController controller;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate npjt;

    Scanner sc = new Scanner(System.in);

    @Autowired
    public EpsiMsprApplication(ClientController controller) {
        this.controller = controller;
    }


    public static void main(String[] args) {
        SpringApplication.run(EpsiMsprApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
