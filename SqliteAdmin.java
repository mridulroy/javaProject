package mridul_personal;
import java.sql.*;
import javax.swing.*;

public class SqliteAdmin {
	
	Connection conn = null;
	
	public static Connection dbconnector() {
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:G:\\Learning Java\\New Java GUI Projects\\Mridul_Personal\\DataBase\\AdminPanel\\Admin.sqlite");
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
