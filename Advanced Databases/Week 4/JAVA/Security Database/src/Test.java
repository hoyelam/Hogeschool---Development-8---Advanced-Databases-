import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class Test {
	public static void main(String[] args) {
		
		Connection connection = Applicatie.connect();

		try {
			Statement st1 = (Statement) connection.createStatement();
			st1.executeUpdate("UPDATE student SET wachtwoord = '123456' WHERE studentnummer = '1876814'; DROP TABLE student;");
			
			System.out.println("done!");
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
