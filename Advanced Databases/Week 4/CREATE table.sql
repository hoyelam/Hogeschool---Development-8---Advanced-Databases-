-- Database: postgres

-- DROP DATABASE postgres;

DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE DATABASE postgres
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;

COMMENT ON DATABASE postgres
  IS 'default administrative connection database';

CREATE TABLE Student(
	  studentnummer                VARCHAR(7)   NOT NULL  PRIMARY KEY  CHECK (length(studentnummer) = 7)
	, wachtwoord                   VARCHAR(45)  NOT NULL
	, naam			       VARCHAR(45)  NOT NULL
	, klas		               VARCHAR(45)
	, ingeschreven		       BOOLEAN      NOT NULL 
);

