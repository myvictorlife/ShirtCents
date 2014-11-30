--INSERT INTO TABLE () VALUES ();

-- -----------------------------------------------------
-- Table `shirtCents`.`usuario`
-- -----------------------------------------------------

INSERT INTO USUARIO (NOME, EMAIL, LOGIN, SENHA, PROFILE)
    VALUES ('Victor Cesar', 'victorcmggg@gmail.com', 'victor', '123', 'Admin');

INSERT INTO USUARIO (NOME, EMAIL, LOGIN, SENHA, PROFILE)
    VALUES ('Gilberto Olimpio', 'gilberto.olimpio@gmail.com', 'gilbert', '321', 'Admin');

INSERT INTO USUARIO (NOME, EMAIL, LOGIN, SENHA, PROFILE)
    VALUES ('Pablo Ribeiro', 'pableeenho@gmail.com', 'pablo', '123', 'Leitor');

-- -----------------------------------------------------
-- Table `shirtCents`.`Endereco`
-- -----------------------------------------------------

INSERT INTO Endereco (logradouro, numero, complemento, cidade, estado, pais, cep, id_usuario) 
   VALUES ('Avenida Amazonas', '1330', 'apart 222', 'Uberlandia', 'MG', 'Brasil', '38777-440', 1);

INSERT INTO Endereco (logradouro, numero, complemento, cidade, estado, pais, cep, id_usuario) 
   VALUES ('Rua Abadia', '330', '', 'Araguari', 'MG', 'Brasil', '38444-440', 2);


-- -----------------------------------------------------
-- Table `shirtCents`.`Categoria`
-- -----------------------------------------------------

INSERT INTO categoria (categoria) VALUES ('CAMISA');
INSERT INTO categoria (categoria) VALUES ('PULSEIRA');

-- -----------------------------------------------------
-- Table `shirtCents`.`Produto`
-- -----------------------------------------------------

INSERT INTO Produto (descricao, tamanho, preco_custo,PRECO_VENDA, quantidade, sexo, id_categoria) 
    VALUES ('Camisa Polo', 'G', 22.90,44.80, 20, 'Feminino' ,1);

INSERT INTO Produto (descricao, tamanho, preco_custo,PRECO_VENDA, quantidade, sexo ,id_categoria) 
    VALUES ('Pandora', 'A', 12.90,34.90, 12, 'Masculino' ,2);

-- -----------------------------------------------------
-- Table `shirtCents`.`FormaPagamento`
-- -----------------------------------------------------

INSERT INTO FormaPagamento (descricao) VALUES('Cartao');
INSERT INTO FormaPagamento (descricao) VALUES('Dinheiro');
INSERT INTO FormaPagamento (descricao) VALUES('Cheque');

-- -----------------------------------------------------
-- Table `shirtCents`.`Pedido`
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `shirtCents`.`Itens`
-- -----------------------------------------------------






