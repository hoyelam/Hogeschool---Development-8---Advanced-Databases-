import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Applicatie {
	// Connectie variables
	public static String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
	public static String username = "student_user";
	public static String password = "1234";

	// SQL Variables
	public static Statement st1;
	public static String studentnummer;
	public static String wachtwoord;
	public static String klas;
	public static String ingeschreven;
	public static String naam;
	public static String opties;
	public static String opties_gebruiker_ingelogd;
	public static String wachtwoord_oud;
	public static String wachtwoord_nieuw;

	// Applicatie variables
	public static Scanner inputReader = new Scanner(System.in);
	public static Console console = System.console();

	public static void main(String[] args) {
		// Connect met Database
		try {
			connect();
		} catch (Exception e) {
			System.out.println("Database Offline");
		}

		// kies een optie
		opties();
	}

	// Opties voor wat de gebruiker wilt doen
	public static void opties() {
		System.out
				.println("Type 'login' voor inloggen, 'klas' voor klas informatie of 'stop' om de applicatie te stoppen");

		opties = inputReader.nextLine();

		if (opties.equals("login")) {
			login();
		}
		if (opties.equals("klas")) {
			dataKlas();
		}
		if (opties.equals("stop")) {
			System.exit(0);
		} else {
			System.out.println("Verkeerde input, probeer het nog is");
			opties();
		}
		
	}

	public static void opties_gebruiker_ingelogd() {
		System.out
				.println("Type 'gegevens' voor gegevens, 'wachtwoord' om wachtwoord te wijzigen of 'home' om terug naar het begin te gaan");

		opties_gebruiker_ingelogd = inputReader.nextLine();

		if (opties_gebruiker_ingelogd.equals("gegevens")) {
			getGegevens_gebruiker();
		}
		if (opties_gebruiker_ingelogd.equals("wachtwoord")) {
			wachtwoord_wijzigen();
		}
		if (opties_gebruiker_ingelogd.equals("home")) {
			opties();
		} else {
			System.out.println("Verkeerde input, probeer het nog is");
			opties();
		}
	}

	public static void wachtwoord_wijzigen() {
		System.out.println("Voer uw huidige wachtwoord in : ");
		wachtwoord_oud = inputReader.nextLine();
		try {
			if (wachtwoord_oud.equals(wachtwoord)) {
				System.out.println("Voer uw nieuwe wachtwoord in : ");
				wachtwoord_nieuw = inputReader.nextLine();
				String wachtwoord_wijzigen = ("UPDATE student SET wachtwoord = '"
						+ wachtwoord_nieuw
						+ "' WHERE studentnummer = '"
						+ studentnummer + "';");
				System.out.println(wachtwoord_wijzigen);
				st1.executeUpdate(wachtwoord_wijzigen);
				System.out.println("Succes! Uw wachtwoord is gewijzigd! ");
				opties_gebruiker_ingelogd();
			}
			else{
				System.out.println("Onjuist wachtwoord probeer het opnieuw : ");
				wachtwoord_wijzigen();
			}
		} catch (Exception e) {
			System.out
					.println("Er was iets fouts gegaan probeer het opnieuw : ");
			opties_gebruiker_ingelogd();
		}
	}

	public static void getGegevens_gebruiker() {
		String test = ("SELECT * FROM student WHERE studentnummer = '"
				+ studentnummer + "' AND wachtwoord = '" + wachtwoord + "';");
		ResultSet z;
		System.out.println(test);

		try {
			z = st1.executeQuery(test);

			if (z.next()) {
				studentnummer = z.getString("studentnummer");
				naam = z.getString("naam");
				klas = z.getString("klas");
				ingeschreven = z.getString("ingeschreven");
			}

			System.out.println("Student : " + studentnummer);
			System.out.println("Naam : " + naam);
			System.out.println("In Klas : " + klas);

			if (ingeschreven.equals("f")) {
				System.out.println("is nog niet ingeschreven!");
			} else {
				System.out.println("is ingeschreven!");
				System.out.println("");
			}
			opties_gebruiker_ingelogd();

		} catch (Exception e) {
			System.out.println("Er was iets fouts gegaan probeer het nog is");
			opties();
		}
	}

	// Login
	public static void login() {
		Connection connection = connect();

		System.out.println("Voer uw studentnummer in: ");
		studentnummer = inputReader.nextLine();

		System.out.println("Voer uw wachtwoord in: ");
		wachtwoord = inputReader.nextLine();

		try {
			st1 = (Statement) connection.createStatement();
			ResultSet z;
			String login_query = ("SELECT * FROM student WHERE studentnummer = '"
					+ studentnummer + "' AND wachtwoord = '" + wachtwoord + "';");

			System.out.println(login_query);
			z = st1.executeQuery(login_query);

			if (z.next()) {
				studentnummer = z.getString("studentnummer");
				naam = z.getString("naam");
				klas = z.getString("klas");
				ingeschreven = z.getString("ingeschreven");
				opties_gebruiker_ingelogd();
			}
			else{
				System.out
						.println("Geen gebruiker gevonden, probeer het nog is");
				login();				
			}
				

			// terug naar opties
			opties();

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Er was iets fout gegaan, probeer het nog is");
			opties();
		}
	}

	// Haal data binnen een klas
	public static void dataKlas() {
		Connection connection = connect();
		
		studentnummer = null; 
		naam = null; 
		klas = null; 
		ingeschreven = null; 
		
		System.out.println("Voer klas in: ");
		klas = inputReader.nextLine();

		try {

			st1 = (Statement) connection.createStatement();
			ResultSet z;
			
			String getKlas = ("SELECT * FROM student WHERE klas = '" + klas
					+ "' AND ingeschreven = 'true';");
			System.out.println(getKlas);
			z = st1.executeQuery(getKlas);

			if (z.next()) {
				studentnummer = z.getString("studentnummer");
				naam = z.getString("naam");
				klas = z.getString("klas");
				ingeschreven = z.getString("ingeschreven");
			}

			if (naam != null) {
				while (z.next()) {
					studentnummer = z.getString("studentnummer");
					naam = z.getString("naam");
					klas = z.getString("klas");
					ingeschreven = z.getString("ingeschreven");

					System.out.println("Student : " + studentnummer);
					System.out.println("Naam : " + naam);
					System.out.println("In Klas : " + klas);
					System.out.println (ingeschreven.equals("f") ? "is nog niet ingeschreven!" : "is ingeschreven!");
					System.out.println("");
				}
			} else {
				System.out.println("Geen klas gevonden, probeer het nog is");
			}
			opties();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Connect functie
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

		} catch (Exception e) {

		}
		return connection;
	}
}
