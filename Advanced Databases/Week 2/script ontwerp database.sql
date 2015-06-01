SET GLOBAL TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

DROP SCHEMA test;
CREATE SCHEMA test;

CREATE TABLE product(
        idProduct       INT           NOT NULL       PRIMARY KEY      AUTO_INCREMENT
    ,   naam            VARCHAR(45)   NOT NULL       
    ,   beschrijving    VARCHAR(255)  NOT NULL
);

CREATE TABLE voorraad(
        product_idProduct INT         NOT NULL       REFERENCES product(idProduct)
    ,   aantal            INT         NOT NULL       
);

CREATE TABLE magazijn_veranderingen(
        idVeranderingVoorraad       INT     NOT NULL    PRIMARY KEY   AUTO_INCREMENT
	,	voorraad_product_idProduct	INT		NOT NULL	REFERENCES voorraad(product_idProduct)
    ,   verandering                 INT     NOT NULL    
    ,   reden               VARCHAR(45)     NOT NULL       
);

DELIMITER //
CREATE TRIGGER before_magazijn_veranderingen_insert BEFORE INSERT ON magazijn_veranderingen
        FOR EACH ROW BEGIN
            UPDATE voorraad
            SET aantal = aantal + NEW.verandering
            WHERE product_idProduct = NEW.voorraad_product_idProduct;
            END;//
DELIMITER ;
