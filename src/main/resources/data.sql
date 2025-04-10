INSERT INTO tbl_laboratorio (id, nome)
VALUES (1, 'Lab 1'),
       (2, 'Lab 2'),
       (3, 'Lab 3'),
       (4, 'Lab 4'),
       (5, 'Lab 5')
ON CONFLICT (id) DO NOTHING;

INSERT INTO tbl_propriedade (id, nome)
VALUES (1, 'Propriedade 1'),
       (2, 'Propriedade 2'),
       (3, 'Propriedade 3'),
       (4, 'Propriedade 4'),
       (5, 'Propriedade 5'),
       (6, 'Propriedade 6')
ON CONFLICT (id) DO NOTHING;
