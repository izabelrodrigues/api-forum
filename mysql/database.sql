CREATE TABLE `forum`.`usuario`(`id` int(11) NOT NULL AUTO_INCREMENT,
	`nome` TEXT NOT NULL,
	`email` VARCHAR(255) NOT NULL,
	`senha` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`));
CREATE TABLE `forum`.`perfil`(`id` int(11) NOT NULL AUTO_INCREMENT,
	`nome` TEXT NOT NULL,
	PRIMARY KEY (`id`));
CREATE TABLE `forum`.`usuario_perfis`(`usuario_id` int(11) NOT NULL,
	`perfis_id` int(11) NOT NULL);
CREATE TABLE `forum`.`curso`(`id` int(11) NOT NULL AUTO_INCREMENT,
	`nome` text NOT NULL,
        `categoria` text NOT NULL,
	PRIMARY KEY (`id`));
CREATE TABLE `forum`.`resposta`(`id` int(11) NOT NULL AUTO_INCREMENT,
        `autor_id` int(11) NOT NULL,
	`topico_id` int(11) NOT NULL,
        `mensagem` text NOT NULL,
        `solucao`text NOT NULL,
        `data_criacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        PRIMARY KEY (`id`));
CREATE TABLE `forum`.`topico`(`id` int(11) NOT NULL AUTO_INCREMENT,
	`titulo` text NOT NULL,
	`mensagem` text NOT NULL,
	`data_criacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`status` text NOT NULL,
	`autor_id` int(11) NOT NULL,
	`curso_id` int(11) NOT NULL,
        PRIMARY KEY (`id`));
INSERT INTO usuario(nome, email, senha)VALUES('Aluno', 'aluno@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');
INSERT INTO usuario(nome, email, senha)VALUES('Moderador', 'moderador@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');
INSERT INTO perfil(id, nome)VALUES(1, 'ROLE_ALUNO');
INSERT INTO perfil(id, nome)VALUES(2, 'ROLE_MODERADOR');
INSERT INTO usuario_perfis(usuario_id, perfis_id)VALUES(1, 1);
INSERT INTO usuario_perfis(usuario_id, perfis_id)VALUES(2, 2);
INSERT INTO curso(nome, categoria)VALUES('Spring Boot', 'Programacao');
INSERT INTO curso(nome, categoria)VALUES('HTML 5', 'Front-end');
INSERT INTO topico(titulo, mensagem, data_criacao, status, autor_id, curso_id)VALUES('Duvida 1', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO topico(titulo, mensagem, data_criacao, status, autor_id, curso_id)VALUES('Duvida 2', 'Projeto nao compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO topico(titulo, mensagem, data_criacao, status, autor_id, curso_id)VALUES('Duvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);
GRANT ALL PRIVILEGES ON *.* TO 'forum'@'%' IDENTIFIED BY 'Bk55yc1u0eiqga6e';
FLUSH PRIVILEGES;
