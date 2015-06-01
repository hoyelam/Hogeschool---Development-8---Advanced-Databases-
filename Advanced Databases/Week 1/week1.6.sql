-- Test inserts

INSERT INTO Student VALUES ('0882447','Rinesh','Ramadhin','','19930926','man','Reling',211,'','Barendrecht','2993DR','0643643698');
INSERT INTO Student VALUES ('08824457','Joost','Ruiter','de','19950916','man','Wijnhaven',107,'','Rotterdam','1345DA','06484839933'); -- studentnummer te lang
INSERT INTO Student VALUES ('0123445','Jan','Bakker','de','19970916','Man','Wijnhaven',107,'','Rotterdam','1345DA','06484839933'); -- Geslacht verkeerd.
INSERT INTO Student VALUES ('0832445','hoye','lam','','19950916','man','Wijnhaven',107,'','Rotterdam','1345DA','gmail.com'); -- studentnummer te lang

INSERT INTO Docent VALUES ('TJANG','dfgh','dfgh','ddgvf','19970926','man','Wijnhaven',123,'','Barendrecht','2993DR','78907');
INSERT INTO Docent VALUES ('887979','dfgh','dfgh','ddgvf','19970926','man','Wijnhaven',123,'','Barendrecht','2993DR','78907');
INSERT INTO Docent VALUES ('344 556','dfgh','dfwwsgh','','19971026','man','Wijnhaven',123,'','Barendrecht','2993DR','78907'); -- fout medewerkerscode
INSERT INTO Docent VALUES ('47979','fdghfgh','dfdwgh','dcvf','20180926','man','Wijnhaven',123,'','Barendrecht','2993DR','78907'); -- geboortedatum
INSERT INTO Docent VALUES ('887979','dffhgh','dfgfgrfh','fff','19970926','gijian','Wijnhaven',123,'','Barendrecht','2993DR','78907'); -- foutive geslacht
INSERT INTO Docent VALUES ('887979','dfgh','dfgh','dvf','19970926','man','Wijnhaven',123,'','Barendrecht','2993DR','06094a509'); -- fout telefoonnummer

INSERT INTO Groep VALUES ('INF1D','2013-01-01','2013-10-10');
INSERT INTO Groep VALUES ('INF2D','2013-01-01','2013-10-10');
INSERT INTO Groep VALUES ('Development','2013-10-01','2013-01-10'); -- einddatum na invoerdatum

INSERT INTO Cursussen VALUES ('INFONZ01-1','TJANG','bablalbla','2013-01-01','2013-10-10');
INSERT INTO Cursussen VALUES ('INFONZ02-1','YOURI','bablalfbla','2013-10-01','2013-01-10'); -- einddatum na invoerdatum
INSERT INTO Cursussen VALUES ('infanl01-1','STELL','badblalbla','2013-01-01','2013-10-10'); -- kleine letters bij cussuscode

INSERT INTO Rooster VALUES ('INF2D','H.4.002','TJANG','INFONZ01-1',TIMESTAMP '2011-05-16 15:36:38',TIMESTAMP '2012-05-16 15:36:38');
INSERT INTO Rooster VALUES ('INF1D','H.4.002','TJANG','INFONZ01-1',TIMESTAMP '2012-05-16 15:36:38',TIMESTAMP '2011-05-16 15:36:38'); -- eindles na startles





