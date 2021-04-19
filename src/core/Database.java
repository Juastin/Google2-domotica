package src.core;

import java.sql.*;
import java.util.ArrayList;

// BASED ON: https://www.youtube.com/watch?v=2i4t-SL1VsU

// EXAMPLE USAGE
// ArrayList<ArrayList<String>> result = Database.query("select * from users");
// System.out.println(result.toString());

public class Database {
    static String host = "jdbc:mysql://localhost:3306/test";
    static String username = "root";
    static String password = "";

    public static ArrayList<ArrayList<String>> query(String query) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = DriverManager.getConnection(Database.host, Database.username, Database.password);
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(query);
			ResultSetMetaData rsmd = myRs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			while (myRs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					ArrayList<String> result_part = new ArrayList<String>();
					result_part.add(rsmd.getColumnName(i));
					result_part.add(myRs.getString(i));
					result.add(result_part);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
		}

		try {
			if (myRs != null) {myRs.close();}
			if (myStmt != null) {myStmt.close();}
			if (myConn != null) {myConn.close();}
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return result;
	}
}