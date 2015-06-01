import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataInvoegen {
	//Functie Variables
	public static Random rand = new Random();
	
	public static void main(String[] args) {
		// Maak en start thread 1
		new Thread(new Runnable() {
			@Override
			public void run() {
				Connection connection = Applicatie.connect();


				// Begin 600 iteraties van student en klas toevoegen
				for (int i = 0; i < 600; i++) {

					// random string
					char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
					StringBuilder sb = new StringBuilder();
					Random random = new Random();
					for (int nu = 0; nu < 6; nu++) {
						char c = chars[random.nextInt(chars.length)];
						sb.append(c);
					}

					// transactie
					try {
						Statement st = (Statement) connection.createStatement();

						int studentnummer = 1876814 + i;
						String klas = "INF" + rand.nextInt(5) + "D";
						String naam = sb.toString();
					
						int inge = rand.nextInt(2)+1;
						if (inge == 1){
						st.executeUpdate("INSERT INTO student " + "VALUES ('"
								+ studentnummer + "','1234','" + naam + "','"
								+ klas + "',true);");
						}
						else{
							st.executeUpdate("INSERT INTO student " + "VALUES ('"
									+ studentnummer + "','1234','" + naam + "','"
									+ klas + "',false)");
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				try {
					System.out.println("Done!");
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}, "Thread 1").start();
	}
}
