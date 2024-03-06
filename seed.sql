-- Suppression des tables existantes
DROP TABLE IF EXISTS contenu_commande;
DROP TABLE IF EXISTS commandes;
DROP TABLE IF EXISTS employes;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS personnes;
DROP TABLE IF EXISTS produits;

-- Création des tables
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
                                         `id_personne` bigint NOT NULL AUTO_INCREMENT,
                                         `prenom` varchar(50) DEFAULT NULL,
                                         `nom` varchar(50) DEFAULT NULL,
                                         `numero_rue` varchar(10) DEFAULT NULL,
                                         `rue` varchar(255) DEFAULT NULL,
                                         `ville` varchar(255) DEFAULT NULL,
                                         `code_postal` mediumint(10) DEFAULT NULL,
                                         `email` varchar(255) DEFAULT NULL,
                                         `telephone` varchar(20) DEFAULT NULL,
                                         `type_personne` varchar(20) DEFAULT NULL,
                                         PRIMARY KEY (`id_personne`)
);

CREATE TABLE IF NOT EXISTS clients (
                                       `id_personne` bigint NOT NULL,
                                       `type_personne` varchar(20) DEFAULT NULL,
                                       PRIMARY KEY (`id_personne`),
                                       FOREIGN KEY (`id_personne`) REFERENCES personnes(`id_personne`)
);

CREATE TABLE IF NOT EXISTS employes (
                                        `login` VARCHAR(50) NOT NULL UNIQUE,
                                        `mot_de_passe` VARCHAR(255) NOT NULL,
                                        `id_personne` bigint NOT NULL,
                                        `type_personne` varchar(20) DEFAULT NULL,
                                        PRIMARY KEY (`login`),
                                        FOREIGN KEY (`id_personne`) REFERENCES personnes(`id_personne`)
);

CREATE TABLE IF NOT EXISTS commandes (
                                         `id_commande` bigint NOT NULL AUTO_INCREMENT,
                                         `id_client` bigint NOT NULL,
                                         `date_commande` date DEFAULT NULL,
                                         `montant_total` decimal(10, 2) DEFAULT NULL,
                                         PRIMARY KEY (`id_commande`),
                                         FOREIGN KEY (`id_client`) REFERENCES clients(`id_personne`)
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

-- Données de test pour la table des personnes
INSERT INTO personnes (prenom, nom, numero_rue, rue, ville, code_postal, email, telephone)
VALUES
    ('Alice', 'Dupont', '123', 'Rue de la République', 'Paris', '75001', 'alice@email.com', '0123456789'),
    ('Bob', 'Martin', '456', 'Avenue des Fleurs', 'Lyon', '69002', 'bob@email.com', '0987654321'),
    ('Claire', 'Leroux', '789', 'Rue de la Liberté', 'Marseille', '13003', 'claire@email.com', '0123456789'),
    ('Timothée', 'Thibault', '46', 'Avenue Millies Lacroix', 'DZAOUDZI', '97610', 'TimotheeThibault@gustr.com', '02.83.06.29.55'),
    ('Cheney', 'Bernard', '31', 'rue du Général Ailleret', 'LE TAMPON', '97430', 'CheneyBernard@rhyta.com', '02.58.34.19.11'),
    ('Damiane', 'Thivierge', '30', 'Place de la Gare', 'COLMAR', '68000', 'DamianeThivierge@jourrapide.com', '03.72.27.10.08'),
    ('Michèle', 'Favreau', '14', 'quai Saint-Nicolas', 'TOULOUSE', '31500', 'MicheleFavreau@rhyta.com', '05.79.45.52.03'),
    ('Manville', 'Brunelle', '27', 'cours Jean Jaures', 'BORDEAUX', '33100', 'ManvilleBrunelle@einrot.com', '05.27.50.25.11'),
    ('Amélie', 'Brisebois', '76', 'Rue Bonnet', 'YERRES', '91330', 'AmelieBrisebois@gustr.com', '01.54.31.99.22');

-- Ajout de données de test pour la table des clients
INSERT INTO clients (id_personne, type_personne)
VALUES
    (1, 'Client'),
    (2, 'Client'),
    (3, 'Client');

-- Ajout de données de test pour la table des employés
INSERT INTO employes (login, mot_de_passe, id_personne, type_personne)
VALUES
    ('timothee', 'motdepasseTimothee', 4, 'Employe'),
    ('cheney', 'motdepasseCheney', 5, 'Employe'),
    ('damiane', 'motdepasseDamiane', 6, 'Employe'),
    ('michele', 'motdepasseMichele', 7, 'Employe'),
    ('manville', 'motdepasseManville', 8, 'Employe'),
    ('amelie', 'motdepasseAmelie', 9, 'Employe');