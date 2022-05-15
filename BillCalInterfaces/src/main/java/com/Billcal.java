package com;

import java.sql.*;

public class Billcal {

	// DB Connection 
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bill", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	// Insert Bill Method
	
	public String insertBill(String accNumber, String name, String units, String date) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			// create a prepared statement
			String query = " insert into billinfo (id, accNumber, name, units , date)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, accNumber);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, units);
			preparedStmt.setString(5, date);
			preparedStmt.execute();
			con.close();
			String newItems = readBillInfo();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	

	// Retrieve Bill Method

	public String readBillInfo() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Acc Number</th><th>Acc Name</th>" + "<th>Units</th>"
					+ "<th>Date</th>"+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from billinfo";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String accNumber = rs.getString("accNumber");
				String name = rs.getString("name");
				String units = rs.getString("units");
				String date = rs.getString("date");
				// Add into the html table
				
				output += "<td>" + accNumber + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + units + "</td>";
				output += "<td>" + date + "</td>";
				
				// Update And Delete buttons
				
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-itemid='" + id + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-itemid='" + id + "'></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Billcal.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	// Update Bill Info Method
	
	public String updateBillInfo(String id, String accNumber, String name, String units, String date) {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE billinfo SET accNumber=?,name=?,units=?,date=?, total=? WHERE id=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, accNumber);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, units);
			preparedStmt.setString(4, date);
			preparedStmt.setInt(6, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readBillInfo();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	

	// Delete Bill Info Method
	
	public String deletBillInfo(String id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from billinfo where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readBillInfo();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Bill Info.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}


}
