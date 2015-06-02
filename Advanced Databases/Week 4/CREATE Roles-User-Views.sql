DROP OWNED BY student_role;
REVOKE ALL ON student FROM student_role;
DROP USER student_user;
DROP ROLE student_role;

CREATE ROLE student_role;
GRANT CONNECT ON DATABASE postgres TO student_role;
GRANT USAGE ON SCHEMA public TO student_role;
GRANT SELECT ON student TO student_role;
GRANT SELECT ON INF0D_view TO student_role;
GRANT SELECT ON INF1D_view TO student_role;
GRANT SELECT ON INF2D_view TO student_role;
GRANT SELECT ON INF3D_view TO student_role;
GRANT SELECT ON INF4D_view TO student_role;

CREATE USER student_user WITH PASSWORD '1234';
GRANT student_role to student_user;

DROP VIEW INF0D_view;
DROP VIEW INF1D_view;
DROP VIEW INF2D_view;
DROP VIEW INF3D_view;
DROP VIEW INF4D_view;

CREATE OR REPLACE VIEW INF0D_view AS
SELECT studentnummer, naam, klas, ingeschreven FROM student WHERE klas = 'INF0D' AND ingeschreven = 'true';

CREATE OR REPLACE VIEW INF1D_view AS
SELECT studentnummer, naam, klas, ingeschreven FROM student WHERE klas = 'INF1D' AND ingeschreven = 'true';

CREATE OR REPLACE VIEW INF2D_view AS
SELECT studentnummer, naam, klas, ingeschreven FROM student WHERE klas = 'INF2D' AND ingeschreven = 'true';


CREATE OR REPLACE VIEW INF3D_view AS
SELECT studentnummer, naam, klas, ingeschreven FROM student WHERE klas = 'INF3D' AND ingeschreven = 'true';

CREATE OR REPLACE VIEW INF4D_view AS
SELECT studentnummer, naam, klas, ingeschreven FROM student WHERE klas = 'INF4D' AND ingeschreven = 'true';

