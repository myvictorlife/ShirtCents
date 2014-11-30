
--------- CRIANDO BANCO DE DADOS ------
-- NOME DO BANCO DE DADOS:  shirtCents
-- NOME DO USUARIO: shirt
-- SENHA DO BANCO DE DADOS: cents
-- -------------------------------------

-- -----------------------------------------------------
-- Table `shirtCents`.`usuario`
-- -----------------------------------------------------

CREATE TABLE usuario (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
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

CREATE TABLE Endereco (
  idEndereco INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  logradouro VARCHAR(45),
  numero VARCHAR(45),
  complemento VARCHAR(45),
  cidade VARCHAR(45),
  estado VARCHAR(45),
  pais VARCHAR(45),
  cep VARCHAR(45),
  id_usuario INT NOT NULL,
  PRIMARY KEY (idEndereco),
  FOREIGN KEY (id_usuario) REFERENCES Usuario (id)
  );

-- -----------------------------------------------------
-- Table `shirtCents`.`Categoria`
-- -----------------------------------------------------

CREATE TABLE Categoria (
  id_categoria INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  categoria VARCHAR(45),
  PRIMARY KEY (id_categoria));

-- -----------------------------------------------------
-- Table `shirtCents`.`Produto`
-- -----------------------------------------------------

  CREATE TABLE Produto (
  id_produto INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  descricao VARCHAR(45),
  tamanho VARCHAR(10),
  preco_custo DOUBLE,
  preco_venda DOUBLE,
  quantidade INT,
  SEXO VARCHAR(10),
  id_categoria INT NOT NULL,
  foto blob,
  PRIMARY KEY (id_produto),
  FOREIGN KEY (id_categoria) REFERENCES Categoria (id_categoria)
   );

-- -----------------------------------------------------
-- Table `shirtCents`.`FormaPagamento`
-- -----------------------------------------------------

CREATE TABLE FormaPagamento (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  descricao VARCHAR(45),
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table `shirtCents`.`Pedido`
-- -----------------------------------------------------

CREATE TABLE Pedido (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  pagamento_pedido DOUBLE,
  status_pedido VARCHAR(50),
  criado_pedido DATE,
  modificado_pedido DATE,
  id_usuario INT NOT NULL,
  total DOUBLE,
  data_ped date,
  FormaPagamento_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_usuario) REFERENCES Usuario (id),
  FOREIGN KEY (FormaPagamento_id) REFERENCES FormaPagamento (id)
 );

-- -----------------------------------------------------
-- Table `shirtCents`.`Itens`
-- -----------------------------------------------------

CREATE TABLE Itens (
   quantidade int,
   valor_unitario DOUBLE,
   id_produto INT NOT NULL,
   Pedido_id INT NOT NULL,
   valor DOUBLE,
   PRIMARY KEY (id_produto, Pedido_id),
   FOREIGN KEY (id_produto) REFERENCES Produto (id_produto),
   FOREIGN KEY (Pedido_id) REFERENCES Pedido (id)
 );


