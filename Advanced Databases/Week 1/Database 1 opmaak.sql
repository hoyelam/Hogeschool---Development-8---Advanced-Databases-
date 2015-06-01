-- Database: "Week1"

--DROP DATABASE "Week1"

DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE DATABASE "Week1"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Dutch_Netherlands.1252'
       LC_CTYPE = 'Dutch_Netherlands.1252'
       CONNECTION LIMIT = -1;


CREATE TABLE Student(
	  studentnummer                VARCHAR(7)   NOT NULL  PRIMARY KEY  CHECK (length(studentnummer) = 7)
	, voornaam                     VARCHAR(45)  NOT NULL
	, achternaam                   VARCHAR(45)  NOT NULL
	, tussenvoegsel                VARCHAR(45)
	, geboortedatum                DATE         NOT NULL               CHECK (geboortedatum <= current_DATE)
	, geslacht                     VARCHAR(45)                         CHECK (Geslacht = 'man' OR Geslacht = 'Vrouw' OR Geslacht = 'Onbepaald') 
	, straat                       VARCHAR(45)  NOT NULL
	, huisnummer                   INT          NOT NULL
	, toevoeging                   VARCHAR(1)
	, woonplaats                   VARCHAR(45)  NOT NULL
	, postcode                     VARCHAR(6)   NOT NULL
	, telefoonnummer               VARCHAR(45)  NOT NULL               CHECK (telefoonnummer~'^[0-9\." "()-]*$') 
);

CREATE TABLE Docent(
	  medewerkerscode              VARCHAR(7)   NOT NULL  PRIMARY KEY  CHECK (medewerkerscode~'^[0-9A-Z]{7}$')
	, voornaam                     VARCHAR(45)  NOT NULL
	, achternaam                   VARCHAR(45)  NOT NULL
	, tussenvoegsel                VARCHAR(45)
	, geboortedatum                DATE         NOT NULL               CHECK (geboortedatum <= current_DATE)
	, geslacht                     VARCHAR(45)                         CHECK (Geslacht = 'man' OR Geslacht = 'Vrouw' OR Geslacht = 'Onbepaald')
	, straat                       VARCHAR(45)  NOT NULL
	, huisnummer                   INT          NOT NULL
	, toevoeging                   VARCHAR(45)
	, woonplaats                   VARCHAR(255) NOT NULL
	, postcode                     VARCHAR(6)   NOT NULL
	, telefoonnummer               VARCHAR(255) NOT NULL               CHECK (telefoonnummer~'^[0-9\." "()-]*$')
);

CREATE TABLE Groep(
	  groepnaam                    VARCHAR(45)  NOT NULL  PRIMARY KEY
	, startdatum                   DATE         NOT NULL
	, einddatum                    DATE         NOT NULL               CHECK (einddatum >= startdatum)
);

CREATE TABLE Groep_has_Student(
	  groep_groepnaam              VARCHAR(45)  NOT NULL  REFERENCES Groep(groepnaam)
	, student_studentnummer        VARCHAR(7)   NOT NULL  REFERENCES Student(studentnummer)
);

CREATE TABLE Cursussen(
	  cursuscode                   VARCHAR(25)  NOT NULL  PRIMARY KEY  CHECK (cursuscode = UPPER(cursuscode))
	, cursusbeheerder              VARCHAR(45)  NOT NULL
	, omschrijving                 VARCHAR(255) NOT NULL
	, invoerdatum                  DATE         NOT NULL
	, einddatum                    DATE         NOT NULL               CHECK (Einddatum > invoerdatum)
);

CREATE TABLE Docent_has_Cursussen(
	  docent_medewerkerscode       VARCHAR(7)   NOT NULL  REFERENCES Docent(medewerkerscode)
	, cursussen_Cursuscode         VARCHAR(25)  NOT NULL  REFERENCES Cursussen(cursuscode)
);

CREATE TABLE Lokaal(
	  lokaal					   VARCHAR(45)	NOT NULL  PRIMARY KEY
);

CREATE TABLE Lestijden(
	  lestijd					   VARCHAR(45)  NOT NULL  PRIMARY KEY
	, start_tijd				   TIME			NOT NULL
	, eind_tijd					   TIME			NOT NULL			   CHECK (eind_tijd > start_tijd)
);

CREATE TABLE Rooster(
	  groepnaam                    VARCHAR(45)  NOT NULL  REFERENCES Groep(groepnaam)
	, lokaal                       VARCHAR(45)  NOT NULL  REFERENCES Lokaal(lokaal)
	, medewerkerscode              VARCHAR(7)   NOT NULL  REFERENCES Docent(medewerkerscode)
	, Docent_Cursussen_cursuscode  VARCHAR(25)  NOT NULL  REFERENCES Cursussen(cursuscode)
	, lestijd					   VARCHAR(45)	NOT NULL  REFERENCES Lestijden(lestijd)
	, datum						   DATE			NOT NULL

);