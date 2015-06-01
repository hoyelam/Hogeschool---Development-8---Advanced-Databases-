-- Database: "Week1"

--DROP DATABASE "Week1"

drop schema public cascade;
create schema public;

CREATE DATABASE "Week1"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Dutch_Netherlands.1252'
       LC_CTYPE = 'Dutch_Netherlands.1252'
       CONNECTION LIMIT = -1;


CREATE TABLE Student(
	  Studentnummer   varchar(7)   NOT NULL  PRIMARY KEY CHECK (length(Studentnummer) = 7)
	, Voornaam        varchar(45)  NOT NULL
	, Achternaam      varchar(45)  NOT NULL
	, Tussenvoegsel   varchar(45),
	, Geboortedatum   Date         NOT NULL  CHECK (Geboortedatum <= current_date) -- geboortedatum kan niet in de toekomst liggen
	, Geslacht        varchar(45)            CHECK (Geslacht IN ('man', 'vrouw', 'onbekend') 
	, Straat          varchar(45)  NOT NULL
	, Huisnummer      int          NOT NULL
	, Toevoeging      varchar(1),
	, Woonplaats      varchar(45)  NOT NULL
	, Postcode        varchar(6)   NOT NULL
	, Telefoonnummer  varchar(45)  NOT NULL  CHECK (Telefoonnummer~'^[0-9\." "()-]*$') -- laat getallen, punten, spaties, haakjes, en streepjes toe dmv regex
);

CREATE TABLE Docent(
	Medewerkerscode varchar(7) NOT NULL PRIMARY KEY CHECK (Medewerkerscode~'^[0-9A-Z]{7}$'),
	Voornaam varchar(45) NOT NULL,
	Achternaam varchar(45) NOT NULL,
	Tussenvoegsel varchar(45),
	Geboortedatum Date NOT NULL CHECK (Geboortedatum <= current_date),
	Geslacht varchar(45) NOT NULL CHECK (Geslacht = 'man' OR Geslacht = 'Vrouw' OR Geslacht = 'Onbepaald' OR Geslacht = 'Onbekend'),
	Straat varchar(45) NOT NULL,
	Huisnummer int NOT NULL,
	Toevoeging varchar(45),
	Woonplaats varchar(255) NOT NULL,
	Postcode varchar(6) NOT NULL,	
	Telefoonnummer varchar(255) NOT NULL CHECK (Telefoonnummer~'^[0-9\." "()-]*$')
);

CREATE TABLE Groep(
	groepnaam varchar(45) NOT NULL PRIMARY KEY,
	startdatum DATE NOT NULL,
	einddatum DATE NOT NULL CHECK (einddatum >= startdatum)
);

CREATE TABLE Groep_has_Student(
	Groep_groepnaam varchar(45) NOT NULL references Groep(groepnaam),
	Student_studentnummer varchar(7) NOT NULL references Student(studentnummer)
);

CREATE TABLE Cursussen(
	Cursuscode varchar(25) NOT NULL PRIMARY KEY CHECK(Cursuscode = UPPER(Cursuscode)),
	Cursusbeheerder varchar(45) NOT NULL,
	Omschrijving varchar(255) NOT NULL,
	invoerdatum DATE NOT NULL,
	Einddatum DATE NOT NULL CHECK (Einddatum > invoerdatum)
);

CREATE TABLE Docent_has_Cursussen(
	Docent_medewerkerscode varchar(7) NOT NULL references Docent(Medewerkerscode),
	Cursussen_Cursuscode varchar(25) NOT NULL references Cursussen(Cursuscode)
);

CREATE TABLE Rooster(
	groepnaam varchar(45) NOT NULL references Groep(groepnaam),
	Lokaal varchar(45) NOT NULL,
	medewerkerscode varchar(7) NOT NULL references Docent(medewerkerscode),
	Docent_Cursussen_Cursuscode varchar(25) NOT NULL references Cursussen(Cursuscode),
	startles TIMESTAMP NOT NULL,
	eindles TIMESTAMP NOT NULL CHECK (eindles > startles) 
);