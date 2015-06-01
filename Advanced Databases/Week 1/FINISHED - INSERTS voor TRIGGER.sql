-- benodigde inserts om te testen

INSERT INTO Docent VALUES ('1234567','Youri','Tjang','','1990-01-01','man','wijnhaven',99,'','Rotterdam','1234AB','0100000');
INSERT INTO Docent VALUES ('7654321','Marco','Noord','van','1995-01-01','man','wijnhaven',107,'','Rotterdam','1234BA','0101000');

INSERT INTO Groep VALUES ('INF2D','2014-09-01','2015-08-01');
INSERT INTO Groep VALUES ('INF1D','2013-09-01','2014-08-01');

INSERT INTO Lokaal VALUES ('H.4.318');
INSERT INTO Lokaal VALUES ('H.4.208');

INSERT INTO Cursussen VALUES ('ANALYSE','YouriTjang','analyse vak','2000-01-01','2020-01-01');
INSERT INTO Cursussen VALUES ('PROJECT','MarcovanNoord','Project Emerging Technologies','2010-01-01','2020-01-01');

INSERT INTO Lestijden VALUES ('1','08:30','09:20');
INSERT INTO Lestijden VALUES ('2','09:20','10:10');
INSERT INTO Lestijden VALUES ('3','10:30','11:20');
INSERT INTO Lestijden VALUES ('4','11:20','12:10');
INSERT INTO Lestijden VALUES ('5','12:10','13:00');
INSERT INTO Lestijden VALUES ('6','13:00','13:50');
INSERT INTO Lestijden VALUES ('7','13:50','14:40');
INSERT INTO Lestijden VALUES ('8','15:00','15:50');

-- test trigger

INSERT INTO Rooster VALUES ('INF2D','H.4.318','1234567','ANALYSE','1','2015-01-01');
INSERT INTO Rooster VALUES ('INF2D','H.4.208','7654321','PROJECT','1','2015-01-01'); -- klas heeft al les op dat uur
INSERT INTO Rooster VALUES ('INF1D','H.4.208','1234567','ANALYSE','1','2015-01-01'); -- docent geeft al les op dat uur
INSERT INTO Rooster VALUES ('INF1D','H.4.318','7654321','PROJECT','1','2015-01-01'); -- lokaal is al bezet













