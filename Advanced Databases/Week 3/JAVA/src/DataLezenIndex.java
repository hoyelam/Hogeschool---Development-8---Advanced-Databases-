import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataLezenIndex {
	public static void main(String[] args) {
		// Maak en start thread 1
		new Thread(new Runnable() {
			@Override
			public void run() {
				Connection connection = DataInvoegen.connect();
				// Decladeer variables
				long begintijd_zonder_idx;
				long eindtijd_zonder_idx;
				long begintijd_met_idx;
				long eindtijd_met_idx;
				long duurInms_zonder_idx;
				long duurInms_met_idx;
				
				Statement st1;
				String voornaam = null;
				
				try {
					st1 = (Statement) connection.createStatement();
					
					//Zonder IDX duur
					st1.executeUpdate("DROP INDEX Student_idx;");
					
					// sla begin tijd op
					begintijd_zonder_idx = System.currentTimeMillis();
					ResultSet z;
					z = st1.executeQuery("SELECT voornaam FROM Student WHERE voornaam LIKE 'a%';");
					
					if (z.next()) {
						voornaam = z.getString("voornaam");
					}
					
					
					eindtijd_zonder_idx = System.currentTimeMillis();
					
					duurInms_zonder_idx = eindtijd_zonder_idx - begintijd_zonder_idx;
					
					System.out.println("Zonder Index Duurt : " + duurInms_zonder_idx);
					
					//Met IDX duur
					st1.executeUpdate("CREATE UNIQUE INDEX Student_idx ON Student (voornaam);");
					
					// sla begin tijd op
					begintijd_met_idx = System.currentTimeMillis();
					ResultSet b;
					b = st1.executeQuery("SELECT voornaam FROM Student WHERE voornaam LIKE 'a%';");
					
					if (b.next()) {
						voornaam = b.getString("voornaam");
					}
					
					
					eindtijd_met_idx = System.currentTimeMillis();
					
					duurInms_met_idx = eindtijd_met_idx - begintijd_met_idx;
					
					System.out.println("Met Index Duurt : " + duurInms_met_idx);
					
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, "Thread 1").start();
	}
}
