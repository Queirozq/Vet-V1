INSERT INTO tb_user (name, email, password) VALUES ('Matheus Queiroz','matheus@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Silva Queiroz', 'silva@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_CLIENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);



INSERT INTO tb_animal (name, animal, gender, race, owner_id, photo) VALUES ('Trovoada','Cachorro','M','Puddle',1, 'https://www.hypeness.com.br/1/2021/04/3ae08b7c-berkay-gumustekin-ngqyo2ayyne-unsplash-1.jpg');
INSERT INTO tb_animal (name, animal, gender, race, owner_id, photo) VALUES ('Scri','Gato','F','Ragdoll', 2, 'https://saude.abril.com.br/wp-content/uploads/2020/04/gato-coronavc3adrus.jpg');

