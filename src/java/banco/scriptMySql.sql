-- NOME DO BANCO DE DADOS:  shirtCents
-- NOME DO USUARIO: shirt
-- SENHA DO BANCO DE DADOS: cents
-- -------------------------------------

-- -----------------------------------------------------
-- Table `shirtCents`.`usuario`
-- -----------------------------------------------------

CREATE TABLE USUARIO (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45),
  email VARCHAR(45),
  login VARCHAR(30),
  senha VARCHAR(30),
  profile VARCHAR(10),
  PRIMARY KEY (id)
);



-- -----------------------------------------------------
-- Table `shirtCents`.`Endereco`
-- -----------------------------------------------------

CREATE TABLE ENDERECO (
  idEndereco INT NOT NULL AUTO_INCREMENT,
  logradouro VARCHAR(45),
  numero VARCHAR(45),
  complemento VARCHAR(45),
  cidade VARCHAR(45),
  estado VARCHAR(45),
  pais VARCHAR(45),
  cep VARCHAR(45),
  id_usuario INT NOT NULL,
  PRIMARY KEY (idEndereco),
  FOREIGN KEY (id_usuario) REFERENCES USUARIO (id)
  );

-- -----------------------------------------------------
-- Table `shirtCents`.`Categoria`
-- -----------------------------------------------------

CREATE TABLE CATEGORIA (
  id_categoria INT NOT NULL AUTO_INCREMENT,
  categoria VARCHAR(45),
  PRIMARY KEY (id_categoria));

-- -----------------------------------------------------
-- Table `shirtCents`.`Produto`
-- -----------------------------------------------------

  CREATE TABLE PRODUTO (
  id_produto INT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(45),
  tamanho VARCHAR(10),
  preco_custo DOUBLE,
  preco_venda DOUBLE,
  quantidade INT,
  SEXO VARCHAR(10),
  id_categoria INT NOT NULL,
  foto blob,
  PRIMARY KEY (id_produto),
  FOREIGN KEY (id_categoria) REFERENCES CATEGORIA (id_categoria)
   );

-- -----------------------------------------------------
-- Table `shirtCents`.`FormaPagamento`
-- -----------------------------------------------------

CREATE TABLE FORMAPAGAMENTO (
  id INT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(45),
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table `shirtCents`.`Pedido`
-- -----------------------------------------------------

CREATE TABLE PEDIDO (
  id INT NOT NULL AUTO_INCREMENT,
  status_pedido VARCHAR(50),
  criado_pedido DATE,
  id_usuario INT NOT NULL,
  total DOUBLE,
  data_ped date,
  FormaPagamento_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_usuario) REFERENCES USUARIO (id),
  FOREIGN KEY (FormaPagamento_id) REFERENCES FORMAPAGAMENTO (id)
 );

-- -----------------------------------------------------
-- Table `shirtCents`.`Itens`
-- -----------------------------------------------------

CREATE TABLE Itens (
   id INT NOT NULL AUTO_INCREMENT,
   id_produto INT NOT NULL,
   Pedido_id INT NOT NULL,
   quantidade int,
   valor DOUBLE,
   PRIMARY KEY (id),
   FOREIGN KEY (id_produto) REFERENCES PRODUTO (id_produto),
   FOREIGN KEY (Pedido_id) REFERENCES PEDIDO (id)
 );



