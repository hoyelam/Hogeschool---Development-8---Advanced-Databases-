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
	, postcode                     VARCHAR(6)   NOT NULL		   CHECK (postcode~'^[1-9][0-9]{3}[A-Z]{2}')
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
	, postcode                     VARCHAR(6)   NOT NULL		   CHECK (postcode~'^[1-9][0-9]{3}[A-Z]{2}')
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

CREATE TABLE Cursussen_has_Groep(
	  groep_groepnaam		VARCHAR(45) NOT NULL  REFERENCES Groep(groepnaam)
	, cursussen_cursuscode		VARCHAR(25) NOT NULL  REFERENCES Cursussen(cursuscode)
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

CREATE FUNCTION Rooster_check() RETURNS trigger AS $Rooster_check$
	BEGIN
		IF ((SELECT COUNT(groepnaam) FROM Rooster WHERE groepnaam = NEW.groepnaam AND datum = NEW.datum AND lestijd = NEW.lestijd) = 1) THEN
		RAISE EXCEPTION '% heeft al les op dat tijdstip', NEW.groepnaam;
		END IF;
		
		IF ((SELECT COUNT(medewerkerscode) FROM Rooster WHERE medewerkerscode = NEW.medewerkerscode AND datum = NEW.datum AND lestijd = NEW.lestijd) = 1) THEN
		RAISE EXCEPTION '% geeft al les op dat tijdstip', NEW.medewerkerscode;
		END IF;
		
		IF ((SELECT COUNT(lokaal) FROM Rooster WHERE lokaal = NEW.lokaal AND datum = NEW.datum AND lestijd = NEW.lestijd) = 1) THEN
		RAISE EXCEPTION '% is al bezet op dat tijdstip', NEW.lokaal;  
		END IF;

		RETURN NEW;
		
	END
$Rooster_check$ LANGUAGE plpgsql;

CREATE TRIGGER before_Rooster_insert
	BEFORE INSERT OR UPDATE
	ON Rooster
	FOR EACH ROW
	EXECUTE PROCEDURE Rooster_check();

CREATE UNIQUE INDEX Student_idx ON Student (voornaam);

	