package servizi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionFactory {
		
		public static final String URL = "jdbc:postgresql://localhost:5432/multedb?currentSchema=multe";
		public static final String USER = "postgres";
		public static final String PASS = "postgres";
		private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);
		
		public static Connection getConnection() {
			Connection conn = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASS);
				System.out.println("connessione stabilita");
			}
			catch(SQLException ex){
				System.out.println("connessione NON stabilita");
				ex.printStackTrace();
			}
			return conn;
		}

	
}
