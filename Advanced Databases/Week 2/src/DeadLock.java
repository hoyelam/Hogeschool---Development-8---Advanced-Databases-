import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class DeadLock {
public static void main(String[] args) {
		
		// Maak en start thread 1
		new Thread(new Runnable() {
			@Override
			public void run() {
				// maak verbinding met de driver
				try {
					System.out.println("A: Loading driver...");
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("A: Driver loaded!");
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(
							"Cannot find the driver in the classpath!", e);
				}
				
				// decladerdeer variabele
				String url = "jdbc:mysql://localhost:3306/test";
				String username = "hoye";
				String password = "hoye";
				Random r = new Random();
				
				//maak verbinding met de database
				Connection connection = null;
				try {
					System.out.println("A: Connecting database...");
					connection = DriverManager.getConnection(url, username,
							password);
					System.out.println("A: Database connected!");
					connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
					
					// set autocommit off
					connection.setAutoCommit(false);
					
					//Gebruiker A begint met een handeling
					
					//RETRIEVE AANTAL
					Statement st = (Statement) connection.createStatement();
					ResultSet x;
					
					x = st.executeQuery("SELECT aantal FROM voorraad WHERE product_idProduct = 1");
					int oud = 0;
					if(x.next()){
						oud = x.getInt("aantal");
						System.out.println("A: Leest Aantal: " + oud);
					}
					//SLEEP 4 SEC
					try {
						Thread.sleep(r.nextInt(5000-3000) + 3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//UPDATE AANTAL
					
					int nieuw =  75;
					String QueryUpdate = ("UPDATE voorraad SET aantal = " + nieuw + " WHERE product_idProduct = 1");
					st.executeUpdate(QueryUpdate);
					System.out.println("A: Schrijft Aantal: " + nieuw);
					
					
				} catch (SQLException e) {
					throw new RuntimeException("Cannot connect the database!",
							e);
				}
			}
		}, "Thread 1").start();
		// Maak en start thread 2. Deze draait tegelijkertijd met thread 1
		new Thread(new Runnable() {
			@Override
			public void run() {

				// maak verbinding met de driver
				try {
					System.out.println("B: Loading driver...");
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("B: Driver loaded!");
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(
							"Cannot find the driver in the classpath!", e);
				}
				
				// decladerdeer variabele
				String url = "jdbc:mysql://localhost:3306/test";
				String username = "hoye";
				String password = "hoye";
				Random r = new Random();
				
				//maak verbinding met de database
				Connection connection = null;
				try {

					
					
					System.out.println("B: Connecting database...");
					connection = DriverManager.getConnection(url, username,
							password);
					System.out.println("B: Database connected!");
					
					// set autocommit off
					connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
					connection.setAutoCommit(false);
					
					
					//Gebruiker B begint met een handeling
					Statement st = (Statement) connection.createStatement();
					
					//SLEEP 2 SEC
					try {
						Thread.sleep(r.nextInt(3000-1000) + 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					//RETRIEVE AANTAL
					ResultSet x;
					
					x = st.executeQuery("SELECT aantal FROM voorraad WHERE product_idProduct = 1");
					int oud = 0;
					if(x.next()){
						oud = x.getInt("aantal");
						System.out.println("B: Leest Aantal: " + oud);
					}
					
					//SLEEP 6
					try {
						Thread.sleep(r.nextInt(7000-5000) + 5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//UPDATE AANTAL
					
					int nieuw =  200;
					String QueryUpdate = ("UPDATE voorraad SET aantal = " + nieuw + " WHERE product_idProduct = 1");
					st.executeUpdate(QueryUpdate);
					System.out.println("B: Schrijft Aantal: " + nieuw);
			
					
				} catch (SQLException e) {
					System.out.println(e);
					throw new RuntimeException("Cannot connect the database!",
							e);
				}
			}
		}, "Thread 2").start();
	}
}
