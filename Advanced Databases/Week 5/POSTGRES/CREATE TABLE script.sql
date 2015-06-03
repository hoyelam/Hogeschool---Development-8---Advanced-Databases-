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

CREATE TABLE gebruiker(
	voornaam	VARCHAR(45)	NOT NULL	
,	achternaam	VARCHAR(45)	NOT NULL
,	email		VARCHAR(45)	NOT NULL	CHECK (email ~*'^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$')	
,	wachtwoord	VARCHAR(45)	NOT NULL
);

CREATE TABLE betalingsGegevens(
	eigenaarNaam	VARCHAR(45)	NOT NULL
,	nummer		VARCHAR(12)	NOT NULL	CHECK (nummer~'^[0-9\." "()-]*$')
);

CREATE TABLE iDeal(
	vervalMaand	INT		NOT NULL
,	vervalJaar	INT		NOT NULL
);

CREATE TABLE creditCard(
	banknaam	VARCHAR(45)	NOT NULL
);

CREATE TABLE advertentie(
	naam		VARCHAR(255)	NOT NULL
,	beschrijving	TEXT		NOT NULL
,	startPrijs	INT		NOT NULL
,	actief		BOOLEAN		NOT NULL
,	startDatum	DATE		NOT NULL	CHECK (startDatum <= current_DATE)
);

CREATE TABLE advertentieReactie(
	tekts		VARCHAR(255)	NOT NULL
,	datum		DATE		NOT NULL
);

CREATE TABLE category(
	naam		VARCHAR(45)	NOT NULL
);

CREATE TABLE bod(
	prijs		INT		NOT NULL
,	datum		DATE		NOT NULL	CHECK (datum = current_DATE)
);