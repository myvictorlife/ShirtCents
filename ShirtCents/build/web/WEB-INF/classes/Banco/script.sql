
CREATE TABLE USUARIO (

    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    NOME VARCHAR(45) NOT NULL,
    EMAIL VARCHAR(45),
    LOGIN VARCHAR(30) NOT NULL,
    SENHA VARCHAR(30) NOT NULL,
    PROFILE VARCHAR(10) NOT NULL,
    PRIMARY KEY (ID)
);

INSERT INTO USUARIO (NOME, EMAIL, LOGIN, SENHA, PROFILE)
    VALUES ('Victor Cesar', 'victorcmggg@gmail.com', 'victor', '123', 'Admin');