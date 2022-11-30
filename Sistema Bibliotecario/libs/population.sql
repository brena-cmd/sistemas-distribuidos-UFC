--POPULATION LIVRO
INSERT INTO livro VALUES(1234, 'Arquitetura de Computadores', 7, '2001');
INSERT INTO livro VALUES(4567, 'Matematica Discresta', 2, '2000');
INSERT INTO livro VALUES(1357, 'Fundamentos de Programacao', 4, '2003');
INSERT INTO livro VALUES(1245, 'Programacao OO', 2, '2005');
INSERT INTO livro VALUES(1097, 'Banco de Dados', 4, '2008');
INSERT INTO livro VALUES(1567, 'Algoritmos', 1, '2000');
INSERT INTO livro VALUES(1239, 'Programacao WEB', 5, '2002');
INSERT INTO livro VALUES(1029, 'Python', 3, '2009');
INSERT INTO livro VALUES(6286, 'C++', 6, '2006');
INSERT INTO livro VALUES(5982, 'Introducao a C', 4, '2004');

--POPULATION UNIDADE
INSERT INTO unidade VALUES(1926, 1234);
INSERT INTO unidade VALUES(2192, 4567);
INSERT INTO unidade VALUES(2817, 1357);
INSERT INTO unidade VALUES(1209, 1357);
INSERT INTO unidade VALUES(3716, 1234);
INSERT INTO unidade VALUES(1927, 5982);
INSERT INTO unidade VALUES(1096, 4567);
INSERT INTO unidade VALUES(7567, 4567);
INSERT INTO unidade VALUES(7320, 5982);
INSERT INTO unidade VALUES(2362, 1245);
INSERT INTO unidade VALUES(2917, 1029);
INSERT INTO unidade VALUES(1026, 1239);
INSERT INTO unidade VALUES(7277, 6286);
INSERT INTO unidade VALUES(1928, 6286);
INSERT INTO unidade VALUES(7119, 6286);
INSERT INTO unidade VALUES(9283, 1097);
INSERT INTO unidade VALUES(8273, 1029);
INSERT INTO unidade VALUES(8732, 1567);
INSERT INTO unidade VALUES(4823, 1097);
INSERT INTO unidade VALUES(3827, 1239);

--POPULATION USUÁRIOS
INSERT INTO usuario (nome, senha, email, cpf, data_nasc, rua, numero, cidade, estado) VALUES ('Maria Mendonca','1234','maria@gmail.com','12345678910','1987-06-28','Rua Pinto Damasceno','2255','Caninde','Ceara')
INSERT INTO usuario (nome, senha, email, cpf, data_nasc, rua, numero, cidade, estado) VALUES ('Gregorio Neto','1234','gregorio@gmail.com','12345678911','1999-05-17','Avenida Bosques de Cabral','124','Crato','Ceara');
INSERT INTO usuario (nome, senha, email, cpf, data_nasc, rua, numero, cidade, estado) VALUES ('Maria Emilia','1234','emilia@gmail.com','12345678912','2000-04-03','Rua Joao Pinto','36','Reboussas','Ceara');
INSERT INTO usuario (nome, senha, email, cpf, data_nasc, rua, numero, cidade, estado) VALUES ('Ricado Alberto','1234','ricardo@gmail.com','12345678913','2001-05-06','Rua Aroldo Lopez','1258A','Sobral','Ceara');
INSERT INTO usuario (nome, senha, email, cpf, data_nasc, rua, numero, cidade, estado) VALUES ('Roger Maciel','1234','roger@gmail.com','12345678914','1995-07-09','Rua Lucas Rosse','965','Quixada','Ceara');
INSERT INTO usuario (nome, senha, email, cpf, data_nasc, rua, numero, cidade, estado) VALUES ('Gabriel Medeiros','1234','gabriel@gmail.com','12345678915','1976-02-10','Rua Sao Braz','645','Impueiras','Ceara');
INSERT INTO usuario (nome, senha, email, cpf, data_nasc, rua, numero, cidade, estado) VALUES ('Galena Gonsalez','1234','galena@gmail.com','12345678916','1994-03-20','Rua Milho Assado','621','Fortaleza','Ceara');
INSERT INTO usuario (nome, senha, email, cpf, data_nasc, rua, numero, cidade, estado) VALUES ('Constantino Lopes','1234','constantino@gmail.com','12345678917','1993-09-27','Rua Mao de Vaca','879','Caridade','Ceara');

--POPULATION ALUNOS
INSERT INTO aluno (matricula, id_usr, curso) VALUES (1, 2, 'Engenharia de Computacao');
INSERT INTO aluno (matricula, id_usr, curso) VALUES (2, 3, 'Engenharia de Software');
INSERT INTO aluno (matricula, id_usr, curso) VALUES (3, 4, 'Ciencia da Computacao');
INSERT INTO aluno (matricula, id_usr, curso) VALUES (4, 5, 'Design Digital');
INSERT INTO aluno (matricula, id_usr, curso) VALUES (5, 7, 'Redes de Computadores');
INSERT INTO aluno (matricula, id_usr, curso) VALUES (6, 8, 'Sistemas de Informacao');

--POPULATION SERVIDORES
INSERT INTO servidor (siape, id_usr, nivel_acc) VALUES (001, 1, 3);
INSERT INTO servidor (siape, id_usr, nivel_acc) VALUES (002, 6, 3);

--POPULATION EMPRESTIMO
INSERT INTO emprestimo VALUES(3716, 1, '2019-11-04', '2019-12-04');
INSERT INTO emprestimo VALUES(2817, 3, '2019-11-10', '2019-12-10');
INSERT INTO emprestimo VALUES(1928, 2, '2019-11-26', '2019-12-26');
INSERT INTO emprestimo VALUES(7320, 1, '2019-11-26', '2019-12-26');
INSERT INTO emprestimo VALUES(1927, 5, '2019-11-26', '2019-12-26');
INSERT INTO emprestimo VALUES(1209, 6, '2019-11-27', '2019-12-27');
INSERT INTO emprestimo VALUES(3827, 4, '2019-11-29', '2019-12-29');
INSERT INTO emprestimo VALUES(9283, 5, '2019-11-29', '2019-12-29');

--POPULATION RESERVA
INSERT INTO reserva VALUES(4, 1357, '2019-12-03');
INSERT INTO reserva VALUES(1, 1357, '2019-12-05');