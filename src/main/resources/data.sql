-- a senha é 123456
INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '$2a$10$tKhsRSzpey9HCnYguqzAH.bW/n8hATydzkrTnAfn3ndL1ir5G4w3K');
INSERT INTO USUARIO(nome, email, senha) VALUES('Moderador', 'moderador@email.com', '$2a$10$tKhsRSzpey9HCnYguqzAH.bW/n8hATydzkrTnAfn3ndL1ir5G4w3K');
INSERT INTO USUARIO(nome, email, senha) VALUES('Administrador', 'admin@email.com', '$2a$10$tKhsRSzpey9HCnYguqzAH.bW/n8hATydzkrTnAfn3ndL1ir5G4w3K');

INSERT INTO PERFIL(id, nome) values (1, 'ROLE_ALUNO');
INSERT INTO PERFIL(id, nome) values (2, 'ROLE_MODERADOR');
INSERT INTO PERFIL(id, nome) values (3, 'ROLE_ADMINISTRADOR');

INSERT INTO USUARIO_PERFIS(usuario_id,perfis_id) values (1,1);
INSERT INTO USUARIO_PERFIS(usuario_id,perfis_id) values (2,2);
INSERT INTO USUARIO_PERFIS(usuario_id,perfis_id) values (3,3);


INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);