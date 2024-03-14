DROP TABLE IF EXISTS contenu_commande;
DROP TABLE IF EXISTS commandes;
DROP TABLE IF EXISTS personnes;
DROP TABLE IF EXISTS produits;
DROP TABLE IF EXISTS dbuser;

-- Création des tables
CREATE TABLE IF NOT EXISTS produits
(
    `id_produit`  int NOT NULL AUTO_INCREMENT,
    `nom`         varchar(100) DEFAULT NULL,
    `departement` varchar(50)  DEFAULT NULL,
    `lait`        varchar(50)  DEFAULT NULL,
    `prix`        float(4, 2)  DEFAULT NULL,
    `date_de_fin` date         DEFAULT NULL,
    `actif`       BOOLEAN      DEFAULT TRUE,
    PRIMARY KEY (`id_produit`)
);

CREATE TABLE IF NOT EXISTS personnes
(
    `id_personne`  INT NOT NULL AUTO_INCREMENT,
    `login`        VARCHAR(50) UNIQUE,
    `mot_de_passe` VARCHAR(255),
    `nom`          varchar(50)  DEFAULT NULL,
    `prenom`       varchar(50)  DEFAULT NULL,
    `numero_voie`  varchar(10)  DEFAULT NULL,
    `type_voie`    varchar(255) DEFAULT NULL,
    `libelle_voie` varchar(255) DEFAULT NULL,
    `commune`      varchar(50)  DEFAULT NULL,
    `code_postal`  varchar(10)  DEFAULT NULL,
    `email`        varchar(255) DEFAULT NULL,
    `telephone`    varchar(30)  DEFAULT NULL,
    `est_client`   BOOLEAN      DEFAULT FALSE,
    `est_employe`  BOOLEAN      DEFAULT FALSE,
    `est_archive`  BOOLEAN      DEFAULT FALSE,
    PRIMARY KEY (`id_personne`)
);

CREATE TABLE IF NOT EXISTS commandes
(
    `id_commande`   int NOT NULL AUTO_INCREMENT,
    `id_personne`   int NOT NULL,
    `date_commande` date           DEFAULT NULL,
    `montant_total` decimal(10, 2) DEFAULT NULL,
    PRIMARY KEY (`id_commande`),
    FOREIGN KEY (`id_personne`) REFERENCES personnes (`id_personne`)
);

CREATE TABLE IF NOT EXISTS contenu_commande
(
    `id_contenu_commande` int NOT NULL AUTO_INCREMENT,
    `id_commande`         int NOT NULL,
    `id_produit`          int NOT NULL,
    `quantite`            int DEFAULT NULL,
    PRIMARY KEY (`id_contenu_commande`),
    FOREIGN KEY (`id_commande`) REFERENCES commandes (`id_commande`),
    FOREIGN KEY (`id_produit`) REFERENCES produits (`id_produit`)
);

CREATE TABLE IF NOT EXISTS dbuser
(
    `id`       int AUTO_INCREMENT,
    `username` varchar(250) NOT NULL,
    `password` varchar(250) NOT NULL,
    `role`     varchar(250) NOT NULL,
    PRIMARY KEY (`id`)
);

-- Ajout de données de test pour la table des produits
INSERT INTO produits (nom, departement, lait, prix, date_de_fin, actif)
VALUES ('Santranges-sancerre', '18 - Cher', 'Chèvre', 10.50, '2024-02-22', TRUE),
       ('Pouligny-Saint-Pierre', '36 - Indre', 'Chèvre', 12.75, '2024-02-23', TRUE),
       ('Sainte-Maure-de-Touraine', '37 - Indre-et-Loire', 'Chèvre', 14.25, '2024-02-24', TRUE),
       ('Valençay', '36 - Indre', 'Chèvre', 11.50, '2024-02-25', TRUE),
       ('Crottin de Chavignol', '18 - Cher', 'Chèvre', 9.75, '2024-02-26', TRUE),
       ('Bûche de Chèvre', '18 - Cher', 'Chèvre', 8.50, '2024-02-27', TRUE),
       ('Brie de Meaux', '77 - Seine-et-Marne', 'Vache', 15.50, '2024-02-28', TRUE),
       ('Brie de Melun', '77 - Seine-et-Marne', 'Vache', 16.75, '2024-02-29', TRUE),
       ('Coulommiers', '77 - Seine-et-Marne', 'Vache', 14.25, '2024-03-01', FALSE),
       ('Chaource', '10 - Aube', 'Vache', 13.50, '2024-03-02', TRUE),
       ('Langres', '52 - Haute-Marne', 'Vache', 12.75, '2024-03-03', TRUE),
       ('Epoisses', '21 - Côte-d''Or', 'Vache', 17.25, '2024-03-04', TRUE),
       ('Munster', '68 - Haut-Rhin', 'Vache', 16.50, '2024-03-05', TRUE),
       ('Maroilles', '59 - Nord', 'Vache', 15.75, '2024-03-06', TRUE),
       ('Pont-l''Evêque', '14 - Calvados', 'Vache', 14.25, '2024-03-07', TRUE),
       ('Livarot', '14 - Calvados', 'Vache', 13.50, '2024-03-08', FALSE),
       ('Camembert', '61 - Orne', 'Vache', 12.75, '2024-03-09', TRUE),
       ('Neufchâtel', '76 - Seine-Maritime', 'Vache', 11.50, '2024-03-10', TRUE),
       ('Roquefort', '12 - Aveyron', 'Brebis', 18.50, '2024-03-11', TRUE),
       ('Bleu des Causses', '12 - Aveyron', 'Brebis', 17.75, '2024-03-12', TRUE),
       ('Bleu d''Auvergne', '63 - Puy-de-Dôme', 'Vache', 17.00, '2024-03-13', TRUE),
       ('Fourme d''Ambert', '63 - Puy-de-Dôme', 'Vache', 16.25, '2024-03-14', TRUE),
       ('Saint-Nectaire', '63 - Puy-de-Dôme', 'Vache', 15.50, '2024-03-15', FALSE),
       ('Cantal', '15 - Cantal', 'Vache', 14.75, '2024-03-16', TRUE),
       ('Salers', '15 - Cantal', 'Vache', 14.00, '2024-03-17', TRUE),
       ('Laguiole', '12 - Aveyron', 'Vache', 13.25, '2024-03-18', TRUE),
       ('Pélardon', '30 - Gard', 'Chèvre', 12.50, '2024-03-19', TRUE),
       ('Picodon', '26 - Drôme', 'Chèvre', 11.75, '2024-03-20', FALSE),
       ('Banon', '04 - Alpes-de-Haute-Provence', 'Chèvre', 11.00, '2024-03-21', TRUE),
       ('Brocciu', '2A - Corse', 'Brebis', 10.25, '2024-03-22', TRUE),
       ('Fleur du Maquis', '2A - Corse', 'Brebis', 9.50, '2024-03-23', TRUE),
       ('Tomme de Savoie', '73 - Savoie', 'Vache', 8.75, '2024-03-24', TRUE),
       ('Truffe de Ventadour', '19 - Corrèze', 'Chèvre', 8.75, '2024-02-23', FALSE),
       ('Pétafine', '26 - Drôme', 'Chèvre', 12.25, '2024-02-24', TRUE),
       ('Bleu du Dévoluy', '05 - Hautes-Alpes', 'Vache', 15.80, '2024-02-25', TRUE),
       ('Tome de Cambrai', '59 - Nord', 'Vache', 13.45, '2024-02-26', TRUE),
       ('Vieux Boulogne', '62 - Pas-de-Calais', 'Vache', 18.20, '2024-02-27', TRUE),
       ('Piton des Neiges', '974 - Réunion', 'Vache', 20.30, '2024-02-28', TRUE),
       ('Bleu de Sainte-Foy', '73 - Savoie', 'Vache', 17.90, '2024-02-29', TRUE),
       ('Neufchâtel', '60 - Oise', 'Vache', 9.99, '2024-03-01', TRUE),
       ('Ardi-Gasna', '64 - Pyrénées-Atlantiques', 'Brebis', 14.50, '2024-03-02', TRUE);

-- Ajout de données de test pour la table des utilisateurs
INSERT INTO personnes (login,
                       mot_de_passe,
                       nom, prenom,
                       numero_voie,
                       type_voie,
                       libelle_voie,
                       commune,
                       code_postal,
                       email,
                       telephone,
                       est_client,
                       est_employe,
                       est_archive)
VALUES (null, null, 'Dupont', 'Alice', '123', 'rue', 'de la République', 'Paris', '75001', 'alice@email.com',
        '01.23.45.67.89', TRUE, FALSE, FALSE),
       (null, null, 'Martin', 'Bob', '456', 'avenue', 'des Fleurs', 'Lyon', '69002', 'bob@email.com', '09.87.65.43.21',
        TRUE, FALSE, FALSE),
       (null, null, 'Leroux', 'Claire', '789', 'rue', 'de la Liberté', 'Marseille', '13003', 'claire@email.com',
        '01.23.45.67.89', TRUE, FALSE, FALSE),
       (null, null, 'Durand', 'Marie', '10', 'rue', 'de la Liberté', 'Paris', '75002', 'marie@email.com',
        '01.23.45.67.89', TRUE, FALSE, FALSE),
       (null, null, 'Leclerc', 'Jean', '20', 'avenue', 'Victor Hugo', 'Lyon', '69003', 'jean@email.com',
        '06.54.32.10.98', TRUE, FALSE, FALSE),
       (null, null, 'Dupuis', 'Pierre', '30', 'boulevard', 'Pasteur', 'Marseille', '13004', 'pierre@email.com',
        '02.11.22.33.44', TRUE, FALSE, FALSE),

#   EMPLOYES
       ('TimThi', 'motdepasseTimothee', 'Thibault', 'Timothée', '46', 'avenue', 'Millies Lacroix', 'DZAOUDZI', '97610',
        'TimotheeThibault@gustr.com', '02.83.06.29.55', FALSE, TRUE, FALSE),
       ('CheBer', 'motdepasseCheney', 'Bernard', 'Cheney', '31', 'rue', 'du Général Ailleret', 'LE TAMPON', '97430',
        'CheneyBernard@rhyta.com', '02.58.34.19.11', FALSE, TRUE, FALSE),
       ('MicFav', 'motdepasseMichele', 'Favreau', 'Michèle', '14', 'quai', 'Saint-Nicolas', 'TOULOUSE', '31500',
        'MicheleFavreau@rhyta.com', '05.79.45.52.03', FALSE, TRUE, FALSE),
       ('ManBru', 'motdepasseManville', 'Brunelle', 'Manville', '27', 'cours', 'Jean Jaures', 'BORDEAUX', '33100',
        'ManvilleBrunelle@einrot.com', '05.27.50.25.11', FALSE, TRUE, FALSE),
       ('AmeBri', 'motdepasseAmelie', 'Brisebois', 'Amélie', '76', 'rue', 'Bonnet', 'YERRES', '91330',
        'AmelieBrisebois@gustr.com', '01.54.31.99.22', FALSE, TRUE, FALSE),

#     ADMIN
       ('DamThi', 'secret', 'Thivierge', 'Damien', '30', 'place', 'de la Gare', 'COLMAR', '68000',
        'DamianeThivierge@jourrapide.com', '03.72.27.10.08', FALSE, TRUE, FALSE);

-- Ajout de données de test pour la table des utilisateurs (utilisation de bcrypt pour encoder les mdp : admin et user)
INSERT INTO dbuser (username, password, role)
VALUES ('DBadmin', '$2y$10$tKX3Mrpnr.NIZgwOmGZvVuW8FwPHPCkI4dvVkSHj5epkmUUo8HtVG', 'ADMIN'),
       ('DBuser', '$2y$10$nX5t/1sGTOqERBZ92A.uBeeSxQAirIRY.Zb0LIEmjSONbQAl3B0Zm', 'USER');

-- Ajout de données de test pour la table des commandes
INSERT INTO commandes (id_commande, id_personne, date_commande, montant_total)
VALUES (1, 1, '2024-01-10', 120.00),
       (2, 2, '2024-01-15', 90.75),
       (3, 3, '2024-02-20', 180.25),
       (4, 1, '2024-02-22', 150.00),
       (5, 2, '2024-02-23', 75.50),
       (6, 3, '2024-02-24', 200.00),
       (7, 1, '2024-03-15', 120.00),
       (8, 2, '2024-03-16', 90.75),
       (9, 3, '2024-03-17', 180.25),
       (10, 1, '2024-03-18', 460.00)
ON DUPLICATE KEY UPDATE id_personne   = VALUES(id_personne),
                        date_commande = VALUES(date_commande),
                        montant_total = VALUES(montant_total);

-- Ajout de données de test pour la table du contenu des commandes
INSERT INTO contenu_commande (id_contenu_commande, id_commande, id_produit, quantite)
VALUES (1, 1, 1, 2),
       (2, 1, 2, 1),
       (3, 2, 3, 3),
       (4, 4, 7, 1),
       (5, 4, 2, 2),
       (6, 5, 6, 1),
       (7, 5, 10, 2),
       (8, 6, 2, 1),
       (9, 6, 9, 3),
       (10, 7, 25, 2),
       (11, 7, 17, 1),
       (12, 8, 8, 3),
       (13, 9, 4, 1),
       (14, 9, 15, 2),
       (15, 9, 6, 1),
       (16, 9, 10, 2),
       (17, 9, 12, 3),
       (18, 10, 7, 10)

ON DUPLICATE KEY UPDATE id_commande = VALUES(id_commande),
                        id_produit  = VALUES(id_produit),
                        quantite    = VALUES(quantite);