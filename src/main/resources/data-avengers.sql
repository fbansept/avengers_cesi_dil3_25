INSERT INTO type_organisation (designation) VALUES
       ('Planete'),
       ('Pays'),
       ('Entreprise');

INSERT INTO utilisateur (nom, prenom, email, password, admin) VALUES
      ('Bansept', 'Franck', 'a@a', '$2a$10$kKjcgJunyoP0VfTOIVis2u/ZcUqJTt2eZB.GsYiBbW5MSPZwqJHOC', 1),
      ('Doe', 'John', 'b@b', '$2a$10$kKjcgJunyoP0VfTOIVis2u/ZcUqJTt2eZB.GsYiBbW5MSPZwqJHOC', 0),
      ('Sawyer', 'Tom', 'c@c', '$2a$10$kKjcgJunyoP0VfTOIVis2u/ZcUqJTt2eZB.GsYiBbW5MSPZwqJHOC', 0);


INSERT INTO organisation (nom, type_id, createur_id) VALUES
       ('Terre', 1 , 1),
       ('France', 2, 2),
       ('Allemagne', 2 , 3),
       ('CESI', 3, 1);


INSERT INTO organisation_utilisateur (utilisateur_id, organisation_id) VALUES
      (1, 1),
      (1, 2),
      (1, 3),
      (2, 1),
      (3, 1),
      (3, 2);