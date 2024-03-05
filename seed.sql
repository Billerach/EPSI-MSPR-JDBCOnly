-- Table des produits
CREATE TABLE IF NOT EXISTS produits (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `département` varchar(50) DEFAULT NULL,
  `lait` varchar(50) DEFAULT NULL,
  `date_de_fin` date DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Table des utilisateurs (clients et employés)
CREATE TABLE IF NOT EXISTS utilisateurs (
    `id` INT NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(50) NOT NULL UNIQUE,
    `mot_de_passe` VARCHAR(255) NOT NULL,
    `est_client` BOOLEAN DEFAULT FALSE,
    `est_employe` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (`id`)
);

-- Table des clients
CREATE TABLE IF NOT EXISTS clients (
  `id` int NOT NULL AUTO_INCREMENT,
  `Prenom` varchar(50) DEFAULT NULL,
  `Nom` varchar(50) DEFAULT NULL,
  `Numero_rue` varchar(10) DEFAULT NULL,
  `Rue` varchar(255) DEFAULT NULL,
  `Ville` varchar(50) DEFAULT NULL,
  `CodePostal` varchar(10) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Telephone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES utilisateurs(`id`)
);

-- Table des employés
CREATE TABLE IF NOT EXISTS liste_employes (
  `id` int NOT NULL AUTO_INCREMENT,
  `Prenom` varchar(50) DEFAULT NULL,
  `Nom` varchar(50) DEFAULT NULL,
  `Numero_rue` varchar(10) DEFAULT NULL,
  `Rue` varchar(255) DEFAULT NULL,
  `Ville` varchar(50) DEFAULT NULL,
  `CodePostal` varchar(10) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Telephone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES utilisateurs(`id`)
);

-- Table des commandes
CREATE TABLE IF NOT EXISTS commandes (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_client` int NOT NULL,
  `date_commande` date DEFAULT NULL,
  `montant_total` decimal(10, 2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_client`) REFERENCES clients(`id`)
);

-- Table du contenu des commandes
CREATE TABLE IF NOT EXISTS contenu_commande (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_commande` int NOT NULL,
  `id_produit` int NOT NULL,
  `quantite` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_commande`) REFERENCES commandes(`id`),
  FOREIGN KEY (`id_produit`) REFERENCES produits(`id`)
);

-- -- Table de l'historique des commandes
-- CREATE TABLE IF NOT EXISTS historique_commandes (
--   `id` int NOT NULL AUTO_INCREMENT,
--   `id_client` int NOT NULL,
--   `date_commande` date DEFAULT NULL,
--   `montant_total` decimal(10, 2) DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   FOREIGN KEY (`id_client`) REFERENCES liste_employes(`id`)
-- );
--
-- -- Table de l'historique des produits
-- CREATE TABLE IF NOT EXISTS historique_produits (
--   `id` int NOT NULL AUTO_INCREMENT,
--   `id_commande` int NOT NULL,
--   `id_produit` int NOT NULL,
--   `quantite` int DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   FOREIGN KEY (`id_commande`) REFERENCES historique_commandes(`id`),
--   FOREIGN KEY (`id_produit`) REFERENCES produits(`id`)
-- );

-- Création des données tests
-- Données de test pour la table des produits
INSERT INTO produits (nom, département, lait, date_de_fin)
VALUES
  ('Santranges-sancerre', 'Cher', 'Chèvre', '2024-02-22'),
  ('Truffe de Ventadour', 'Corrèze', 'Chèvre', '2024-02-23'),
  ('Pétafine', 'Drôme', 'Chèvre', '2024-02-24'),
  ('Bleu du Dévoluy', 'Hautes-Alpes', 'Vache', '2024-02-25'),
  ('Tome de Cambrai', 'Nord', 'Vache', '2024-02-26'),
  ('Vieux Boulogne', 'Pas-de-Calais', 'Vache', '2024-02-27'),
  ('Piton des Neiges', 'Réunion', 'Vache', '2024-02-28'),
  ('Bleu de Sainte-Foy', 'Savoie', 'Vache', '2024-02-29'),
  ('Neufchâtel', 'Oise', 'Vache', '2024-03-01'),
  ('Ardi-Gasna', 'Pyrénées-Atlantiques', 'Brebis', '2024-03-02');


-- Ajout de données de test pour la table des utilisateurs
INSERT INTO utilisateurs (login, mot_de_passe, est_client, est_employe)
VALUES
  ('alice', 'motdepasseAlice', TRUE, FALSE),
  ('bob', 'motdepasseBob', TRUE, FALSE),
  ('claire', 'motdepasseClaire', TRUE, FALSE),
  ('timothee', 'motdepasseTimothee', FALSE, TRUE),
  ('cheney', 'motdepasseCheney', FALSE, TRUE),
  ('damiane', 'motdepasseDamiane', FALSE, TRUE),
  ('michele', 'motdepasseMichele', FALSE, TRUE),
  ('manville', 'motdepasseManville', FALSE, TRUE),
  ('amelie', 'motdepasseAmelie', FALSE, TRUE);

-- Ajout de données de test pour la table des clients
INSERT INTO clients (id, Prenom, Nom, Numero_rue, Rue, Ville, CodePostal, Email, Telephone)
VALUES
  (1, 'Alice', 'Dupont', '123', 'Rue de la République', 'Paris', '75001', 'alice@email.com', '0123456789'),
  (2, 'Bob', 'Martin', '456', 'Avenue des Fleurs', 'Lyon', '69002', 'bob@email.com', '0987654321'),
  (3, 'Claire', 'Leroux', '789', 'Rue de la Liberté', 'Marseille', '13003', 'claire@email.com', '0123456789')
ON DUPLICATE KEY UPDATE
  Prenom = VALUES(Prenom),
  Nom = VALUES(Nom),
  Numero_rue = VALUES(Numero_rue),
  Rue = VALUES(Rue),
  Ville = VALUES(Ville),
  CodePostal = VALUES(CodePostal),
  Email = VALUES(Email),
  Telephone = VALUES(Telephone);

-- Ajout de données de test pour la table des employés
INSERT INTO liste_employes (id, Prenom, Nom, Numero_rue, Rue, Ville, CodePostal, Email, Telephone)
VALUES
  (4, 'Timothée', 'Thibault', '46', 'Avenue Millies Lacroix', 'DZAOUDZI', '97610', 'TimotheeThibault@gustr.com', '02.83.06.29.55'),
  (5, 'Cheney', 'Bernard', '31', 'rue du Général Ailleret', 'LE TAMPON', '97430', 'CheneyBernard@rhyta.com', '02.58.34.19.11'),
  (6, 'Damiane', 'Thivierge', '30', 'Place de la Gare', 'COLMAR', '68000', 'DamianeThivierge@jourrapide.com', '03.72.27.10.08'),
  (7, 'Michèle', 'Favreau', '14', 'quai Saint-Nicolas', 'TOULOUSE', '31500', 'MicheleFavreau@rhyta.com', '05.79.45.52.03'),
  (8, 'Manville', 'Brunelle', '27', 'cours Jean Jaures', 'BORDEAUX', '33100', 'ManvilleBrunelle@einrot.com', '05.27.50.25.11'),
  (9, 'Amélie', 'Brisebois', '76', 'Rue Bonnet', 'YERRES', '91330', 'AmelieBrisebois@gustr.com', '01.54.31.99.22')
ON DUPLICATE KEY UPDATE
  Prenom = VALUES(Prenom),
  Nom = VALUES(Nom),
  Numero_rue = VALUES(Numero_rue),
  Rue = VALUES(Rue),
  Ville = VALUES(Ville),
  CodePostal = VALUES(CodePostal),
  Email = VALUES(Email),
  Telephone = VALUES(Telephone);

-- Ajout de données de test pour la table des commandes
INSERT INTO commandes (id, id_client, date_commande, montant_total)
VALUES
  (1, 1, '2024-02-22', 150.00),
  (2, 2, '2024-02-23', 75.50),
  (3, 3, '2024-02-24', 200.00)
ON DUPLICATE KEY UPDATE
  id_client = VALUES(id_client),
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