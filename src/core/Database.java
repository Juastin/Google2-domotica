package src.core;

import java.sql.*;
import java.util.ArrayList;

/* Based on: https://www.youtube.com/watch?v=2i4t-SL1VsU

EXAMPLE:
ArrayList<ArrayList<ArrayList<String>>> result = Database.query("select * from Persoon");
System.out.println(result.toString());

Returns data in the following format:
[
  [                   // row1
    [key, value],
    [key, value],
    [key, value]
  ],
  [                   // row2
    [key, value],
    [key, value],
    [key, value]
  ]
]
*/

public class Database {
    static String host = "jdbc:mysql://40.113.153.48/Google2";
    static String username = "google2";
    static String password = "windesheim123?";

    public static ArrayList<ArrayList<ArrayList<String>>> query(String query) {
		ArrayList<ArrayList<ArrayList<String>>> result = new ArrayList<ArrayList<ArrayList<String>>>();
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
				ArrayList<ArrayList<String>> result_part = new ArrayList<ArrayList<String>>();
				for (int i = 1; i <= columnsNumber; i++) {
					ArrayList<String> result_part_part = new ArrayList<String>();
					result_part_part.add(rsmd.getColumnName(i));
					result_part_part.add(myRs.getString(i));
					result_part.add(result_part_part);
				}
				result.add(result_part);
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