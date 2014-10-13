package dcs.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

//import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 * create a db_conn - this is just an example of one way to do this.
 * NOTE: on class "abstract" - you have to create subclasses from this class to use this class in other classes. 
 * Delete abstract if you want to use this class directly.
 * @author branflake2267
 *
 */
public abstract class DB_Conn {

	
	public static String error = "";
	/**
	 * Constructor
	 */
	public DB_Conn() {


	}


	protected static Connection getConn() {

		Connection conn	= null;

		try {

			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection(
					   "jdbc:postgresql://192.168.56.101:5432/dcstracking","postgres", "8theman8");
		} catch (Exception e) {
			error = error + "\n" + e.toString();
			System.out.println("db init: " + e.toString());
			System.exit(-1);
		}

		if (conn == null)  {
			System.out.println("~~~~~~~~~~ can't get a Mysql connection");
		}
		
		return conn;
	}
	protected static Connection getConnKeyDB() {

		Connection conn	= null;

		try {

			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection(
					   "jdbc:postgresql://192.168.56.101:5432/key_db","postgres", "8theman8");
		} catch (Exception e) {
			error = error + "\n" + e.toString();
			System.out.println("db init: " + e.toString());
			System.exit(-1);
		}

		if (conn == null)  {
			System.out.println("~~~~~~~~~~ can't get a Mysql connection");
		}
		
		return conn;
	}

	


}