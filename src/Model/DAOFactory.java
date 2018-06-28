package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
	public static Connection getConnection() throws SQLException{
		String url = "jdbc:mysql://localhost:3306/agenda_contato";
		String user = "root";
		String password = "";
		return DriverManager.getConnection(url, user, password);
	}
	
	
	
	
}
