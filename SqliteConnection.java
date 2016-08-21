package mridul_personal;
import java.sql.*;
import javax.swing.*;

public class SqliteConnection {
	
	Connection conn = null;
	
	public static Connection dbconnector() {
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:G:\\Learning Java\\New Java GUI Projects\\Mridul_Personal\\DataBase\\EmployeeInformation\\Mishu_And_Brothers.sqlite");
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
