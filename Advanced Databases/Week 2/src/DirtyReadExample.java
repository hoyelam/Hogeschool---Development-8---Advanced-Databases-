import java.sql.*;
import java.util.Date;
import java.util.Random;

public class DirtyReadExample {
	
	private static final int TIMERANGE = 2000;
	
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
				
				// declareer variabele
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
					connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
					
					// set autocommit off
					connection.setAutoCommit(false);
					
					//Gebruiker A begint met een handeling
						
					//sleep 4 sec
					try {
						Thread.sleep(r.nextInt(TIMERANGE) + 3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					//retrieve aantal
					Statement st = (Statement) connection.createStatement();
					ResultSet x;
					
					x = st.executeQuery("SELECT aantal FROM voorraad WHERE product_idProduct = 1");
					int oud = 0;
					if(x.next()){
						oud = x.getInt("aantal");
						System.out.println("A: Leest Aantal: " + oud);
					}
					
					//sleep 4 sec
					try {
						Thread.sleep(r.nextInt(TIMERANGE) + 3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//update aantal (aantal+50)
					int nieuw = oud + 50;
					String QueryUpdate = ("UPDATE voorraad SET aantal = " + nieuw + " WHERE product_idProduct = 1");
					st.executeUpdate(QueryUpdate);
					System.out.println("A: Schrijft Aantal: " + nieuw);

					
					//commit
					connection.commit();		
					System.out.println("A: Commit");

					//Sluit verbinding.
					connection.close();
					
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
					connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
					connection.setAutoCommit(false);
					
					//Gebruiker B begint met een handeling

					
					
					//UPDATE aantal
					Statement st = (Statement) connection.createStatement();
					
					
					////
					ResultSet y;
					y = st.executeQuery("SELECT aantal FROM voorraad WHERE product_idProduct = 1");
					int oudy = 0;
					if(y.next()){
						oudy = y.getInt("aantal");
						System.out.println("B: templees = "+ oudy);
					}
					
					////
					
					
					int nieuw =  125;
					String QueryUpdate = ("UPDATE voorraad SET aantal = " + nieuw + " WHERE product_idProduct = 1");
					st.executeUpdate(QueryUpdate);
					System.out.println("B: Schrijft Aantal: " + nieuw);
					
					
					////
					ResultSet z;
					z = st.executeQuery("SELECT aantal FROM voorraad WHERE product_idProduct = 1");
					int oudz = 0;
					if(z.next()){
						oudz = z.getInt("aantal");
						System.out.println("B: templees = "+ oudz);
					}
					
					////
					
					//Sleep 6
					try {
						Thread.sleep(r.nextInt(TIMERANGE) + 5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//ROLLBACK
					connection.rollback();
					
					//retrieve
					ResultSet x;
					
					x = st.executeQuery("SELECT aantal FROM voorraad WHERE product_idProduct = 1");
					int oud = 0;
					if(x.next()){
						oud = x.getInt("aantal");
						System.out.println("B: Rollback" + " Aantal = "+ oud);
					}
					
					
					//SLEEP 4
					try {
						Thread.sleep(r.nextInt(TIMERANGE) + 3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//Sluit verbinding.
					connection.close();
					
				} catch (SQLException e) {
					throw new RuntimeException("Cannot connect the database!",
							e);
				}
			}
		}, "Thread 2").start();
	}
}