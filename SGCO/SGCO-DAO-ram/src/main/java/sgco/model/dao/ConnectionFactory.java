package sgco.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL não encontrado", e);
        }

        return DriverManager.getConnection(
        	    "jdbc:mysql://localhost:3306/banco"
        	    + "?useSSL=false"
        	    + "&serverTimezone=UTC"
        	    + "&allowPublicKeyRetrieval=true"
        	    + "&useUnicode=true"
        	    + "&characterEncoding=UTF-8",
        	    "sgco_user",
        	    "Sgco1234"
        	);
    }
}
