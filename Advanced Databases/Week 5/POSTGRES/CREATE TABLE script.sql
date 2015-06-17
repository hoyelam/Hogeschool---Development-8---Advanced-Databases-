-- Database: postgres

-- DROP DATABASE postgres;

CREATE DATABASE postgres
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;
GRANT CONNECT, TEMPORARY ON DATABASE postgres TO public;
GRANT ALL ON DATABASE postgres TO postgres;
GRANT CONNECT ON DATABASE postgres TO student_role;

COMMENT ON DATABASE postgres
  IS 'default administrative connection database';
  
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE Gebruiker(
	idGebruiker	INT		NOT NULL	PRIMARY KEY 	
,	voornaam	VARCHAR(45)	NOT NULL	
,	achternaam	VARCHAR(45)	NOT NULL
,	email		VARCHAR(45)	NOT NULL	UNIQUE		CHECK (email ~*'^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$')	
,	wachtwoord	VARCHAR(45)	NOT NULL	
);

CREATE TABLE BetalingsGegevens(
	idGebruiker	INT		NOT NULL	REFERENCES Gebruiker(idGebruiker)
,	eigenaarNaam	VARCHAR(45)	NOT NULL
,	bankNummer	VARCHAR(12)	NOT NULL	PRIMARY KEY	CHECK (bankNummer~'^[0-9\." "()-]*$')
);

CREATE TABLE IDeal(
	banknaam	VARCHAR(45)	NOT NULL
,	bankNummer	VARCHAR(12)	NOT NULL	REFERENCES BetalingsGegevens(bankNummer)
,	idGebruiker	INT		NOT NULL	REFERENCES Gebruiker(idGebruiker)
);

CREATE TABLE CreditCard(
	vervalMaand	INT		NOT NULL
,	vervalJaar	INT		NOT NULL
,	bankNummer	VARCHAR(12)	NOT NULL	REFERENCES BetalingsGegevens(bankNummer)
,	idGebruiker	INT		NOT NULL	REFERENCES Gebruiker(idGebruiker)
);

CREATE TABLE Categorie(
	categorieNaam	VARCHAR(45)	NOT NULL	PRIMARY KEY
);

CREATE TABLE Advertentie(
	idAdvertentie	INT		NOT NULL	PRIMARY KEY
,	idGebruiker	INT		NOT NULL	REFERENCES Gebruiker(idGebruiker)
,	naam		VARCHAR(255)	NOT NULL
,	beschrijving	TEXT		NOT NULL
,	startPrijs	INT		NOT NULL
,	actief		BOOLEAN		NOT NULL
,	startDatum	DATE		NOT NULL	CHECK (startDatum <= current_DATE)
,	categorieNaam	VARCHAR(45)	NOT NULL	REFERENCES Categorie(categorieNaam)
);

CREATE TABLE AdvertentieReactie(
	idAdvertentieReactie	INT	NOT NULL 	PRIMARY KEY	
,	tekts		VARCHAR(255)	NOT NULL
,	datum		DATE		NOT NULL
,	idAdvertentie	INT		NOT NULL	REFERENCES Advertentie(idAdvertentie)
,	idGebruiker	INT		NOT NULL	REFERENCES Gebruiker(idGebruiker)
);



CREATE TABLE Bod(
	idBod		INT		NOT NULL	PRIMARY KEY
,	prijs		INT		NOT NULL
,	datum		DATE		NOT NULL	CHECK (datum = current_DATE)
,	idAvertentie	INT		NOT NULL	REFERENCES Advertentie(idAdvertentie)
,	idGebruiker	INT		NOT NULL	REFERENCES Gebruiker(idGebruiker)
);