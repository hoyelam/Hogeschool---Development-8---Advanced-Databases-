SELECT groep_groepnaam FROM Student 
INNER JOIN Groep_has_Student 
ON Student.studentnummer = Groep_has_Student.student_studentnummer 
WHERE voornaam = 'hahpei';

SELECT * FROM Cursussen_has_Groep WHERE groep_groepnaam = 'INF1C';

EXPLAIN SELECT voornaam FROM Student WHERE voornaam = ' ndchap';