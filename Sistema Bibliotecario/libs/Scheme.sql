CREATE OR REPLACE FUNCTION DEBITO(data_devo DATE) RETURNS REAL AS
	$body$
	DECLARE
	dias integer;
	dia_atual date;
	dia_devo date;
	debito real;
	BEGIN
		SELECT NOW() INTO dia_atual;
		dia_devo := $1;
		dias := dia_atual - dia_devo;
		
		IF(dias > 0) THEN
			debito := dias * 0.30;
		ELSE
			debito := 0.0;
		END IF;
		RETURN debito;
	END;
	$body$
	LANGUAGE 'plpgsql';
--DROP FUNCTION DEBITO;

CREATE TABLE usuario(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    senha VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    data_nasc DATE NOT NULL,
    rua VARCHAR(30) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    cidade VARCHAR(30) NOT NULL,
    estado VARCHAR(30) NOT NULL
);

CREATE TABLE telefone(
    id_usr INTEGER NOT NULL,
	ddd CHAR(2) NOT NULL,
    telefone VARCHAR(9) NOT NULL,
    FOREIGN KEY(id_usr) REFERENCES usuario(id)
);

CREATE TABLE servidor(
    siape INTEGER PRIMARY KEY,
    id_usr INTEGER NOT NULL,
    nivel_acc INTEGER NOT NULL,
    FOREIGN KEY(id_usr) REFERENCES usuario(id)
);

CREATE TABLE aluno(
    matricula INTEGER PRIMARY KEY,
    id_usr INTEGER NOT NULL,
    curso VARCHAR(30) NOT NULL,
    FOREIGN KEY(id_usr) REFERENCES usuario(id)
);

CREATE TABLE livro(
    num_acv INTEGER PRIMARY KEY,
    titulo VARCHAR(30) NOT NULL,
    edicao INTEGER NOT NULL,
    ano_lan VARCHAR(4) NOT NULL,
    qtd INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE unidade(
    num_reg INTEGER PRIMARY KEY,
    num_acv INTEGER NOT NULL,
    FOREIGN KEY(num_acv) REFERENCES livro(num_acv)
);

CREATE TABLE emprestimo(
	num_reg INTEGER NOT NULL PRIMARY KEY,
	matricula INTEGER NOT NULL,
    data_emp DATE NOT NULL,
    data_devo DATE NOT NULL,
    qtd_reno INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY(matricula) REFERENCES aluno(matricula),
    FOREIGN KEY(num_reg) REFERENCES unidade(num_reg)
);

CREATE TABLE reserva(
    matricula INTEGER NOT NULL,
    num_acv INTEGER NOT NULL,
    data_rsv DATE NOT NULL,
    FOREIGN KEY(matricula) REFERENCES aluno(matricula),
    FOREIGN KEY(num_acv) REFERENCES livro(num_acv),
    PRIMARY KEY(matricula, num_acv, data_rsv)
);

CREATE OR REPLACE VIEW debitos AS
SELECT num_reg, matricula, data_emp, data_devo, qtd_reno, DEBITO(data_devo) FROM emprestimo;
--DROP VIEW debitos;

CREATE OR REPLACE FUNCTION insertQtdAcv() RETURNS TRIGGER AS
	$body$
	DECLARE
	qtdAcv integer;
	BEGIN
		SELECT COUNT(new.num_acv) INTO qtdAcv 
		FROM unidade 
		WHERE num_acv = new.num_acv;
		
		UPDATE livro SET qtd = qtdAcv WHERE num_acv = new.num_acv;
		RETURN new;
	END;
	$body$
	LANGUAGE 'plpgsql';
--DROP FUNCTION insertQtdAcv;

CREATE TRIGGER trig_insQtdAcv
AFTER INSERT ON unidade
FOR EACH ROW EXECUTE PROCEDURE insertQtdAcv();
--DROP TRIGGER trig_insQtdAcv ON unidade;

CREATE OR REPLACE FUNCTION deleteQtdAcv() RETURNS TRIGGER AS
	$body$
	DECLARE
	qtdAcv integer;
	BEGIN
		SELECT COUNT(old.num_acv) INTO qtdAcv 
		FROM unidade 
		WHERE num_acv = old.num_acv;
		
		UPDATE livro SET qtd = qtdAcv WHERE num_acv = old.num_acv;
		RETURN old;
	END;
	$body$
	LANGUAGE 'plpgsql';
--DROP FUNCTION deleteQtdAcv;

CREATE TRIGGER trig_delQtdAcv
AFTER DELETE ON unidade
FOR EACH ROW EXECUTE PROCEDURE deleteQtdAcv();
--DROP TRIGGER trig_delQtdAcv ON unidade;