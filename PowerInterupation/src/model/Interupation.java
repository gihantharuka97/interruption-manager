package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Interupation {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/eletrogrid?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	public String insertInterupation(String province, String time, String no_of_crew, String vehicle_no, String phone_no) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into interupationdb(`intID`,`province`,`time`,`no_of_crew`,`vehicle_no`,`phone_no`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, province);
			preparedStmt.setString(3, time);
			preparedStmt.setString(4, no_of_crew);
			preparedStmt.setString(5, vehicle_no);
			preparedStmt.setString(6, phone_no);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the interupation.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readInterupation() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ID</th><th>Province</th><th>Time</th><th>No of Crew Members</th><th>Vehicle No</th><th>Phone No</th></tr>";
			String query = "select * from interupationdb";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String intID = Integer.toString(rs.getInt("intID"));
				String province = rs.getString("province");
				String time = rs.getString("time");
				String no_of_crew = rs.getString("no_of_crew");
				String vehicle_no = rs.getString("vehicle_no");
				String phone_no = rs.getString("phone_no");

				// Add into the html table
				output += "<tr><td>" + intID + "</td>";
				output += "<td>" + province + "</td>";
				output += "<td>" + time + "</td>";
				output += "<td>" + no_of_crew + "</td>";
				output += "<td>" + vehicle_no + "</td>";
				output += "<td>" + phone_no + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the interupation.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateInterupation(String intID, String province, String time, String no_of_crew, String vehicle_no, String phone_no) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE interupationdb SET province=?,time=?,no_of_crew=?,vehicle_no=?,phone_no=?" + "WHERE intID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, province);
			preparedStmt.setString(2, time);
			preparedStmt.setString(3, no_of_crew);
			preparedStmt.setString(4, vehicle_no);
			preparedStmt.setString(5, phone_no);
			preparedStmt.setInt(6, Integer.parseInt(intID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the interupation.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteInterupation(String intID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from interupationdb where intID =?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(intID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the interupation.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
