INSERT INTO tbl_laboratorio (id, nome)
VALUES (1, 'Lab 1'), (2, 'Lab 2'), (3, 'Lab 3')
ON CONFLICT (id) DO NOTHING;

INSERT INTO tbl_propriedade (id, nome)
VALUES (1, 'Propriedade 1'), (2, 'Propriedade 2'), (3, 'Propriedade 3')
ON CONFLICT (id) DO NOTHING;
