import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataLezen {
	public static void main(String[] args) {
		// Maak en start thread 1
		new Thread(new Runnable() {
			@Override
			public void run() {
				Connection connection = DataInvoegen.connect();
				// Decladeer variables
				long begintijd;
				long eindtijd;
				long duurInms;
				List<Long> totale_duur_thread = new ArrayList<Long>();
				Statement st1;

				// Begin 600 iteraties van student en klas toevoegen
				for (int i = 0; i < 600; i++) {

					// SELECT ALLE STUDENTEN

					try {
						st1 = (Statement) connection.createStatement();
						ResultSet z;
						z = st1.executeQuery("SELECT voornaam,achternaam FROM Student ORDER BY RANDOM() LIMIT 1;");

						String voornaam = null;
						String achternaam = null;
						String groepnaam = null;
						String cursusnaam = null;

						if (z.next()) {
							voornaam = z.getString("voornaam");
							achternaam = z.getString("achternaam");
						}

						// sla begin tijd op
						begintijd = System.currentTimeMillis();

						// transactie
						ResultSet groep_ophalen;
						groep_ophalen = st1
								.executeQuery("SELECT groep_groepnaam FROM Student INNER JOIN Groep_has_Student ON Student.studentnummer = Groep_has_Student.student_studentnummer WHERE voornaam = '"
										+ voornaam
										+ "' AND achternaam = '"
										+ achternaam + "';");

						if (groep_ophalen.next()) {
							groepnaam = groep_ophalen
									.getString("groep_groepnaam");
						}

						ResultSet cursus_ophalen;
						cursus_ophalen = st1
								.executeQuery("SELECT * FROM Cursussen_has_Groep WHERE groep_groepnaam = '"
										+ groepnaam + "';");

						while (cursus_ophalen.next()) {
							cursusnaam = cursus_ophalen
									.getString("cursussen_cursuscode");
							System.out.println("A: " + voornaam + " "
									+ achternaam + " in " + groepnaam
									+ " volgt " + cursusnaam);
						}

						// sla eind tijd op
						eindtijd = System.currentTimeMillis();

						// Bereken duur
						duurInms = eindtijd - begintijd;
						System.out.println("Gebruiker 1 : " + duurInms);
						totale_duur_thread.add(duurInms);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				int i;
				float totale_duur = 0;
				for (i = 1; i < totale_duur_thread.size(); i++) {
					totale_duur += totale_duur_thread.get(i);
				}
				System.out.println("thread 1 deed : " + totale_duur);
				System.out.println("thread 1 gemiddeld: " + (totale_duur / totale_duur_thread.size()));

				try {
					connection.commit();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, "Thread 1").start();

		// Maak en start thread 2
		new Thread(new Runnable() {
			@Override
			public void run() {
				Connection connection = DataInvoegen.connect();
				
				// Decladeer variables
				long begintijd;
				long eindtijd;
				long duurInms;
				List<Long> totale_duur_thread = new ArrayList<Long>();
				Statement st1;

				// Begin 600 iteraties van student en klas toevoegen
				for (int i = 0; i < 600; i++) {

					// SELECT ALLE STUDENTEN

					try {
						st1 = (Statement) connection.createStatement();
						ResultSet z;
						z = st1.executeQuery("SELECT voornaam,achternaam FROM Student ORDER BY RANDOM() LIMIT 1;");

						String voornaam = null;
						String achternaam = null;
						String groepnaam = null;
						String cursusnaam = null;

						if (z.next()) {
							voornaam = z.getString("voornaam");
							achternaam = z.getString("achternaam");
						}

						// sla begin tijd op
						begintijd = System.currentTimeMillis();

						// transactie
						ResultSet groep_ophalen;
						groep_ophalen = st1
								.executeQuery("SELECT groep_groepnaam FROM Student INNER JOIN Groep_has_Student ON Student.studentnummer = Groep_has_Student.student_studentnummer WHERE voornaam = '"
										+ voornaam
										+ "' AND achternaam = '"
										+ achternaam + "';");

						if (groep_ophalen.next()) {
							groepnaam = groep_ophalen
									.getString("groep_groepnaam");
						}

						ResultSet cursus_ophalen;
						cursus_ophalen = st1
								.executeQuery("SELECT * FROM Cursussen_has_Groep WHERE groep_groepnaam = '"
										+ groepnaam + "';");

						while (cursus_ophalen.next()) {
							cursusnaam = cursus_ophalen
									.getString("cursussen_cursuscode");
							System.out.println("B: " + voornaam + " "
									+ achternaam + " in " + groepnaam
									+ " volgt " + cursusnaam);
						}

						// sla eind tijd op
						eindtijd = System.currentTimeMillis();

						// Bereken duur
						duurInms = eindtijd - begintijd;
						System.out.println("Gebruiker 2 : " + duurInms);
						totale_duur_thread.add(duurInms);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				int i;
				float totale_duur = 0;
				for (i = 1; i < totale_duur_thread.size(); i++) {
					totale_duur += totale_duur_thread.get(i);
				}
				System.out.println("thread 2 totale duur : " + totale_duur);
				System.out.println("thread 2 gemiddeld: " + (totale_duur / totale_duur_thread.size()));

				try {
					connection.commit();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, "Thread 2").start();
	}
}
