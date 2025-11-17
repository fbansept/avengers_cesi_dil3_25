INSERT INTO type_organisation (designation) VALUES
       ('Planete'),
       ('Pays'),
       ('Entreprise');

INSERT INTO organisation (nom, type_id) VALUES
       ('Terre', 1),
       ('France', 2),
       ('Allemagne', 2),
       ('CESI', 3);

INSERT INTO utilisateur (nom, prenom, email, password) VALUES
        ('Bansept', 'Franck', 'a@a', 'root'),
        ('Doe', 'John', 'b@b', 'root'),
        ('Sawyer', 'Tom', 'c@c', 'root');

INSERT INTO organisation_utilisateur (utilisateur_id, organisation_id) VALUES
      (1, 1),
      (1, 2),
      (1, 3),
      (2, 1),
      (3, 1),
      (3, 2);