import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class PhantomRead {
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
					connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
					
					// set autocommit off
					connection.setAutoCommit(false);
					
					//Gebruiker A begint met een handeling

					// lees tabel
					String query = "SELECT * FROM voorraad";
					Statement st = connection.createStatement();
					ResultSet rs = st.executeQuery(query);
					
					while(rs.next())
					{
						int product_id = rs.getInt("product_idProduct");
						int aantalintabel = rs.getInt("aantal");
						
						System.out.format("%s, %s\n", product_id, aantalintabel);
					}					
					
					// sleep 4sec
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//lees tabel
					ResultSet rs2 = st.executeQuery(query);
					
					while(rs2.next())
					{
						int product_id = rs2.getInt("product_idProduct");
						int aantalintabel = rs2.getInt("aantal");
						
						System.out.format("%s, %s\n", product_id, aantalintabel);
					}	
					// sleep 4sec
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// commit
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
				
				// decladerdeer variabeleS
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
					Statement st = (Statement) connection.createStatement();

					// sleep 2sec
					try {
						Thread.sleep(r.nextInt(3000-1000) + 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					
					// insert nieuwe regel in tabel
					st.executeUpdate("INSERT INTO product " + "VALUES (11, 'Samsung S6', 'Telefoon')");
					st.executeUpdate("INSERT INTO voorraad " + "VALUES (11, 50)");
					
					// commit
					connection.commit();
					System.out.println("B: Commit");	
					//sleep 8sec
					try {
						Thread.sleep(r.nextInt(2000) + 9000);
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
