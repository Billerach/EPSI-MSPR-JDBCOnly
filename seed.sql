DROP TABLE IF EXISTS contenu_commande;
DROP TABLE IF EXISTS produits;
DROP TABLE IF EXISTS commandes;
DROP TABLE IF EXISTS personnes;

CREATE TABLE IF NOT EXISTS produits (
                                        `id_produit` bigint NOT NULL AUTO_INCREMENT,
                                        `nom` varchar(100) DEFAULT NULL,
                                        `departement` varchar(50) DEFAULT NULL,
                                        `lait` varchar(50) DEFAULT NULL,
                                        `prix` float(4,2) DEFAULT NULL,
                                        `date_de_fin` date DEFAULT NULL,
                                        PRIMARY KEY (`id_produit`)
);

CREATE TABLE IF NOT EXISTS personnes (
    `id` INT NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(50) NOT NULL UNIQUE,
    `mot_de_passe` VARCHAR(255) NOT NULL,
    `nom` varchar(50) DEFAULT NULL,
    `prenom` varchar(50) DEFAULT NULL,
    `numero_voie` varchar(10) DEFAULT NULL,
    `type_voie` varchar(255) DEFAULT NULL,
    `libelle_voie` varchar(255) DEFAULT NULL,
    `commune` varchar(50) DEFAULT NULL,
    `code_postal` varchar(10) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `telephone` varchar(30) DEFAULT NULL,
    `est_client` BOOLEAN DEFAULT FALSE,
    `est_employe` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (`id`)
);

-- Table des commandes
CREATE TABLE IF NOT EXISTS commandes (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_personne` int NOT NULL,
  `date_commande` date DEFAULT NULL,
  `montant_total` decimal(10, 2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_personne`) REFERENCES personnes(`id`)
);

CREATE TABLE IF NOT EXISTS contenu_commande (
                                                `id_contenu_commande` bigint NOT NULL AUTO_INCREMENT,
                                                `id_commande` bigint NOT NULL,
                                                `id_produit` bigint NOT NULL,
                                                `quantite` int DEFAULT NULL,
                                                PRIMARY KEY (`id_contenu_commande`),
                                                FOREIGN KEY (`id_commande`) REFERENCES commandes(`id_commande`),
                                                FOREIGN KEY (`id_produit`) REFERENCES produits(`id_produit`)
);

-- Ajout de données de test
-- Données de test pour la table des produits
INSERT INTO produits (nom, departement, lait, prix, date_de_fin)
VALUES
    ('Santranges-sancerre', 'Cher', 'Chèvre', 10.50, '2024-02-22'),
    ('Truffe de Ventadour', 'Corrèze', 'Chèvre', 8.75, '2024-02-23'),
    ('Pétafine', 'Drôme', 'Chèvre', 12.25, '2024-02-24'),
    ('Bleu du Dévoluy', 'Hautes-Alpes', 'Vache', 15.80, '2024-02-25'),
    ('Tome de Cambrai', 'Nord', 'Vache', 13.45, '2024-02-26'),
    ('Vieux Boulogne', 'Pas-de-Calais', 'Vache', 18.20, '2024-02-27'),
    ('Piton des Neiges', 'Réunion', 'Vache', 20.30, '2024-02-28'),
    ('Bleu de Sainte-Foy', 'Savoie', 'Vache', 17.90, '2024-02-29'),
    ('Neufchâtel', 'Oise', 'Vache', 9.99, '2024-03-01'),
    ('Ardi-Gasna', 'Pyrénées-Atlantiques', 'Brebis', 14.50, '2024-03-02');

-- Ajout de données de test pour la table des utilisateurs
INSERT INTO personnes (
  id,
  login,
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
  est_employe
  )
  
VALUES
  #login  mot_de_passe  nom  prenom  numero_voie  type_voie  libelle_voie  commune  code_postal  email  telephone  est_client  est_employe

  (1, 'alice', 'motdepasseAlice',       'Dupont',    'Alice',    '123', 'rue',    'de la République',    'Paris',    '75001', 'alice@email.com',                 '01.23.45.67.89', TRUE, FALSE),
  (2, 'bob', 'motdepasseBob',           'Martin',    'Bob',      '456', 'avenue', 'des Fleurs',          'Lyon',     '69002', 'bob@email.com',                   '09.87.65.43.21', TRUE, FALSE),
  (3, 'claire', 'motdepasseClaire',     'Leroux',    'Claire',   '789', 'rue',    'de la Liberté',       'Marseille','13003', 'claire@email.com',                '01.23.45.67.89', TRUE, FALSE),
  (4, 'timothee', 'motdepasseTimothee', 'Thibault',  'Timothée', '46',  'avenue', 'Millies Lacroix',     'DZAOUDZI', '97610', 'TimotheeThibault@gustr.com',      '02.83.06.29.55', FALSE, TRUE),
  (5, 'cheney', 'motdepasseCheney',     'Bernard',   'Cheney',   '31',  'rue',    'du Général Ailleret', 'LE TAMPON','97430', 'CheneyBernard@rhyta.com',         '02.58.34.19.11', FALSE, TRUE),
  (6, 'damiane', 'motdepasseDamiane',   'Thivierge', 'Damiane',  '30',  'place',  'de la Gare',          'COLMAR',   '68000', 'DamianeThivierge@jourrapide.com', '03.72.27.10.08', FALSE, TRUE),
  (7, 'michele', 'motdepasseMichele',   'Favreau',   'Michèle',  '14',  'quai',   'Saint-Nicolas',       'TOULOUSE', '31500', 'MicheleFavreau@rhyta.com',        '05.79.45.52.03', FALSE, TRUE),
  (8, 'manville', 'motdepasseManville', 'Brunelle',  'Manville', '27',  'cours',  'Jean Jaures',         'BORDEAUX', '33100', 'ManvilleBrunelle@einrot.com',     '05.27.50.25.11', FALSE, TRUE),
  (9, 'amelie', 'motdepasseAmelie',     'Brisebois', 'Amélie',   '76',  'rue',    'Bonnet',              'YERRES',   '91330', 'AmelieBrisebois@gustr.com',       '01.54.31.99.22', FALSE, TRUE);

-- Ajout de données de test pour la table des commandes
INSERT INTO commandes (id, id_personne, date_commande, montant_total)
VALUES
  (1, 1, '2024-02-22', 150.00),
  (2, 2, '2024-02-23', 75.50),
  (3, 3, '2024-02-24', 200.00)
ON DUPLICATE KEY UPDATE
  id_personne = VALUES(id_personne),
  date_commande = VALUES(date_commande),
  montant_total = VALUES(montant_total);

-- Ajout de données de test pour la table du contenu des commandes
INSERT INTO contenu_commande (id, id_commande, id_produit, quantite)
VALUES
  (1, 1, 1, 2),
  (2, 1, 2, 1),
  (3, 2, 3, 3)
ON DUPLICATE KEY UPDATE
  id_commande = VALUES(id_commande),
  id_produit = VALUES(id_produit),
  quantite = VALUES(quantite);

# -- Ajout de données de test pour la table de l'historique des commandes
# INSERT INTO historique_commandes (id, id_client, date_commande, montant_total)
# VALUES
#   (1, 1, '2023-12-15', 120.00),
#   (2, 2, '2023-12-16', 90.75),
#   (3, 3, '2023-12-17', 180.25)
# ON DUPLICATE KEY UPDATE
#   id_client = VALUES(id_client),
#   date_commande = VALUES(date_commande),
#   montant_total = VALUES(montant_total);
#
# -- Ajout de données de test pour la table de l'historique des produits
# INSERT INTO historique_produits (id, id_commande, id_produit, quantite)
# VALUES
#   (1, 1, 1, 1),
#   (2, 1, 3, 2),
#   (3, 2, 2, 1)
# ON DUPLICATE KEY UPDATE
#   id_commande = VALUES(id_commande),
#   id_produit = VALUES(id_produit),
#   quantite = VALUES(quantite);
