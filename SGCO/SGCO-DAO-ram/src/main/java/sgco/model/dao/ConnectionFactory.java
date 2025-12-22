package sgco.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection(){
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "root", "Sgco1234");
		} catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
			return null;
		}
    }
}
