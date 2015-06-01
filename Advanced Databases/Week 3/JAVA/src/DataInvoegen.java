import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataInvoegen {
	// decladerdeer variabele
	//Connectie Variables	
	public static String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
	public static String username = "postgres";
	public static String password = "hoye";
	
	
	public static Random rand = new Random();
	
	public static void main(String[] args) {
		// Maak en start thread 1
		new Thread(new Runnable() {
			@Override
			public void run() {				
				Connection connection = connect();
				// Decladeer Thread 1 variables
				List<String> groep = new ArrayList<String>();
				List<String> module = new ArrayList<String>();
				List<Long> totale_duur_thread1 = new ArrayList<Long>();

				long begintijd;
				long eindtijd;
				long duurInms;
				
				// Onze eerste klas en module
				Statement st1;
				try {

					st1 = (Statement) connection.createStatement();
					st1.executeUpdate("INSERT INTO Groep "
							+ "VALUES ('INF1D', '2015-05-05', '2016-01-01')");
					groep.add("INF1D");

					st1.executeUpdate("INSERT INTO Cursussen "
							+ "VALUES ('INFANL-01','TJANG','analyse','2015-05-05', '2016-01-01')");
					module.add("INFANL-01");

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				// Begin 600 iteraties van student en klas toevoegen
				for (int i = 0; i < 600; i++) {

					// sla begin tijd op
					begintijd = System.currentTimeMillis();

					// random string
					char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
					StringBuilder sb = new StringBuilder();
					for (int nu = 0; nu < 6; nu++) {
						char c = chars[rand.nextInt(chars.length)];
						sb.append(c);
					}

					// transactie
					try {
						Statement st = (Statement) connection.createStatement();

						int studentnummer = 1876814 + i;
						int modulenummer = i + 10;
						String klas = "INF" + i + "D";
						String naam = sb.toString();
						String modulenaam = "INFANL-" + modulenummer;

						st.executeUpdate("INSERT INTO Student "
								+ "VALUES ('"
								+ studentnummer
								+ "','"
								+ naam
								+ "' ,'Lam' ,'' , '1996-02-28' ,'man' ,'wijnhaven',106 ,'','Rotterdam','2222AA','0639387945')");

						int n = rand.nextInt(30) + 1;
						if (n == 20) {
							groep.add(klas);
							st.executeUpdate("INSERT INTO Groep " + "VALUES ('"
									+ klas + "', '2015-05-05', '2016-01-01')");
						}

						// student aan willekeurige klas
						st.executeUpdate("INSERT INTO Groep_has_Student "
								+ "VALUES ('"
								+ groep.get(rand.nextInt(groep.size()))
								+ "' ,'" + studentnummer + "')");

						int z = rand.nextInt(30) + 1;
						if (z == 20) {
							module.add(modulenaam);
							st.executeUpdate("INSERT INTO Cursussen "
									+ "VALUES ('"
									+ modulenaam
									+ "', 'TJANG','analyse','2015-05-05', '2016-01-01')");
							
							int a = rand.nextInt(100) + 1;
							if (a <= 15) {
								for (int b = 0; b < groep.size(); b++) {

									st.executeUpdate("INSERT INTO Cursussen_has_Groep "
											+ "VALUES ('"
											+ groep.get(b)
											+ "','" + modulenaam + "')");
								}
							}

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// sla eind tijd op
					eindtijd = System.currentTimeMillis();

					// Bereken duur
					duurInms = eindtijd - begintijd;
					System.out.println("Gebruiker 1 : " + duurInms);

					totale_duur_thread1.add(duurInms);
				}

				int i;
				float totale_duur = 0;
				for (i = 1; i < totale_duur_thread1.size(); i++) {
					totale_duur += totale_duur_thread1.get(i);
				}
				System.out.println("thread 1 totale duur : " + totale_duur);
				System.out.println("thread 1 gemiddeld: " + (totale_duur / totale_duur_thread1.size()));

				try {
					connection.commit();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					Thread.sleep((long) (Math.random() * 1000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "Thread 1").start();

		// Maak en start thread 2
		new Thread(new Runnable() {
			@Override
			public void run() {
				Connection connection = connect();
				// Decladeer Thread 2 variables
				List<String> groep = new ArrayList<String>();
				List<String> module = new ArrayList<String>();
				List<Long> totale_duur_thread2 = new ArrayList<Long>();

				// Decladeer variables
				long begintijd;
				long eindtijd;
				long duurInms;

				// Onze eerste klas en module
				Statement st1;
				try {
					st1 = (Statement) connection.createStatement();
					st1.executeUpdate("INSERT INTO Groep "
							+ "VALUES ('INF1B', '2015-05-05', '2016-01-01')");
					groep.add("INF1B");

					st1.executeUpdate("INSERT INTO Cursussen "
							+ "VALUES ('INFDEV-01','TJANG','analyse','2015-05-05', '2016-01-01')");
					module.add("INFDEV-01");

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				// Begin 600 iteraties van student en klas toevoegen
				for (int i = 0; i < 600; i++) {

					// sla begin tijd op
					begintijd = System.currentTimeMillis();

					// random string
					char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
					StringBuilder sb = new StringBuilder();
					for (int nu = 0; nu < 6; nu++) {
						char c = chars[rand.nextInt(chars.length)];
						sb.append(c);
					}

					// transactie
					try {
						Statement st = (Statement) connection.createStatement();

						int studentnummer = 2876814 + i;
						int modulenummer = i + 10;
						String klas = "INF" + i + "B";
						String naam = sb.toString();
						String modulenaam = "INFDEV-" + modulenummer;

						st.executeUpdate("INSERT INTO Student "
								+ "VALUES ('"
								+ studentnummer
								+ "','"
								+ naam
								+ "' ,'Lam' ,'' , '1996-02-28' ,'man' ,'wijnhaven',106 ,'','Rotterdam','2222AA','0639387945')");

						int n = rand.nextInt(30) + 1;
						if (n == 20) {
							groep.add(klas);
							st.executeUpdate("INSERT INTO Groep " + "VALUES ('"
									+ klas + "', '2015-05-05', '2016-01-01')");
						}

						// student aan willekeurige klas
						st.executeUpdate("INSERT INTO Groep_has_Student "
								+ "VALUES ('"
								+ groep.get(rand.nextInt(groep.size()))
								+ "' ,'" + studentnummer + "')");

						int z = rand.nextInt(30) + 1;
						if (z == 20) {
							module.add(modulenaam);
							st.executeUpdate("INSERT INTO Cursussen "
									+ "VALUES ('"
									+ modulenaam
									+ "', 'TJANG','analyse','2015-05-05', '2016-01-01')");
							int a = rand.nextInt(100) + 1;

							if (a <= 15) {
								for (int b = 0; b < groep.size(); b++) {

									st.executeUpdate("INSERT INTO Cursussen_has_Groep "
											+ "VALUES ('"
											+ groep.get(b)
											+ "','" + modulenaam + "')");
								}
							}

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// sla eind tijd op
					eindtijd = System.currentTimeMillis();

					// Bereken duur
					duurInms = eindtijd - begintijd;
					System.out.println("Gebruiker 2 : " + duurInms);

					totale_duur_thread2.add(duurInms);
				}

				int i;
				float totale_duur = 0;
				for (i = 1; i < totale_duur_thread2.size(); i++) {
					totale_duur += totale_duur_thread2.get(i);
				}
				System.out.println("thread 2 totale duur : " + totale_duur);
				System.out.println("thread 2 gemiddeld: " + (totale_duur /  totale_duur_thread2.size()));
				try {
					connection.commit();
					connection.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					Thread.sleep((long) (Math.random() * 1000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "Thread 2").start();

		// Maak en start thread 3
		new Thread(new Runnable() {
			@Override
			public void run() {
				Connection connection = connect();
				List<String> groep = new ArrayList<String>();
				List<String> module = new ArrayList<String>();		

				// Decladeer variables
				long begintijd;
				long eindtijd;
				long duurInms;

				List<Long> totale_duur_thread3 = new ArrayList<Long>();

				// Onze eerste klas en module
				Statement st1;
				try {
					st1 = (Statement) connection.createStatement();
					st1.executeUpdate("INSERT INTO Groep "
							+ "VALUES ('INF1C', '2015-05-05', '2016-01-01')");
					groep.add("INF1C");

					st1.executeUpdate("INSERT INTO Cursussen "
							+ "VALUES ('INFONZ-01','TJANG','analyse','2015-05-05', '2016-01-01')");
					module.add("INFONZ-01");

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				// Begin 600 iteraties van student en klas toevoegen
				for (int i = 0; i < 600; i++) {

					// sla begin tijd op
					begintijd = System.currentTimeMillis();

					// random string
					char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
					StringBuilder sb = new StringBuilder();
					for (int nu = 0; nu < 6; nu++) {
						char c = chars[rand.nextInt(chars.length)];
						sb.append(c);
					}

					// transactie
					try {
						Statement st = (Statement) connection.createStatement();

						int studentnummer = 3876814 + i;
						int modulenummer = i + 10;
						String klas = "INF" + i + "C";
						String naam = sb.toString();
						String modulenaam = "INFONZ-" + modulenummer;

						st.executeUpdate("INSERT INTO Student "
								+ "VALUES ('"
								+ studentnummer
								+ "','"
								+ naam
								+ "' ,'Lam' ,'' , '1996-02-28' ,'man' ,'wijnhaven',106 ,'','Rotterdam','2222AA','0639387945')");

						int n = rand.nextInt(30) + 1;
						if (n == 20) {
							groep.add(klas);
							st.executeUpdate("INSERT INTO Groep " + "VALUES ('"
									+ klas + "', '2015-05-05', '2016-01-01')");
						}

						// student aan willekeurige klas
						st.executeUpdate("INSERT INTO Groep_has_Student "
								+ "VALUES ('"
								+ groep.get(rand.nextInt(groep.size()))
								+ "' ,'" + studentnummer + "')");

						int z = rand.nextInt(30) + 1;
						if (z == 20) {
							module.add(modulenaam);
							st.executeUpdate("INSERT INTO Cursussen "
									+ "VALUES ('"
									+ modulenaam
									+ "', 'TJANG','analyse','2015-05-05', '2016-01-01')");
							int a = rand.nextInt(100) + 1;

							if (a <= 15) {
								for (int b = 0; b < groep.size(); b++) {

									st.executeUpdate("INSERT INTO Cursussen_has_Groep "
											+ "VALUES ('"
											+ groep.get(b)
											+ "','" + modulenaam + "')");
								}
							}

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// sla eind tijd op
					eindtijd = System.currentTimeMillis();

					// Bereken duur
					duurInms = eindtijd - begintijd;
					System.out.println("Gebruiker 3 : " + duurInms);

					totale_duur_thread3.add(duurInms);
				}

				int i;
				float totale_duur = 0;
				for (i = 1; i < totale_duur_thread3.size(); i++) {
					totale_duur += totale_duur_thread3.get(i);
				}
				
				System.out.println("thread 3 totale duur : " + totale_duur);
				System.out.println("thread 3 gemiddeld: " + (totale_duur /  totale_duur_thread3.size()));

				try {
					connection.commit();
					connection.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					Thread.sleep((long) (Math.random() * 1000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "Thread 3").start();
	}
	
	//Connect functie
	public static Connection connect() {
		// maak verbinding met postgres

		// maak verbinding met de driver
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(
					"Cannot find the driver in the classpath!", e);
		}

		// maak verbinding met de database
		Connection connection = null;
		try {
			System.out.println("Connecting database...");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");

			connection.setAutoCommit(false);

		} catch (Exception e) {

		}
		return connection;
	}
	
}
