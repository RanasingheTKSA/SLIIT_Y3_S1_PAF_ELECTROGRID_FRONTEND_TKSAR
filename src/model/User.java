package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

	//Database connection
    private Connection connect(){

        Connection con = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/electro_grid", "root", "root");
            //For testing
            System.out.print("Successfully connected");

        }catch (Exception e) {
            e.printStackTrace(); }

        return con;
    }
    
    // insert users/connection request
    public String insertUser(String user_name , String user_nic ,String user_address ,	String user_zip_code , String user_contact_number, String user_email ) {
    	
        String output = "";

        try {

            Connection con = connect();

            if(con == null) {
                return "error while connecting database"; 
            }
            
            String sql = "INSERT INTO `electro_grid`.`user` (`id`,`name`, `nic`, `address`, `zipcode`, `contact number`, `email`)" 
            		+ "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement state = con.prepareStatement(sql);

            //binding values
            state.setInt(1, 0);
            state.setString(2, user_name);
            state.setString(3, user_nic);
            state.setString(4, user_address);
            state.setString(5, user_zip_code);
            state.setString(6, user_contact_number);
            state.setString(7, user_email);

            //execute the statement
            state.execute();
            con.close();

            output = "Inserted successfully";
            
            String newConnRequests = readConnRequest();
			output = "{\"status\":\"success\", \"data\": \"" + newConnRequests + "\"}";

        }catch(Exception e) {
        	output = "{\"status\":\"error\", \"data\": \"Error while inserting the connection request.\"}";
			System.err.println(e.getMessage());
        }
        return output;
    }
    
  //View connection request
    public String readConnRequest(){

        String output = "";

        try {
            Connection con = connect();
            
            if (con == null){
            	return "Error while connecting to the database for reading.";
            } 
            
         // Prepare the html table to be displayed
            output = "<table border='1'>"
            		+ "<tr>"
            			+ "<th>User Name</th>" 
            			+ "<th>NIC</th>" 
            			+ "<th>Address</th>" 
            			+ "<th>ZipCode</th>"  
            			+ "<th>ContactNo</th>"
            			+ "<th> Email </th>"                    
            			+ "<th>Remove</th>"
            		+ "</tr>";

            String sql = "select * from user";
            Statement state = con.createStatement();
            ResultSet rs = state.executeQuery(sql);

            // Iterate through the raws in the result set
            while(rs.next()) {

                String id = Integer.toString(rs.getInt("id"));
                String user_name = rs.getString("name");
                String user_nic = rs.getString("nic");
                String user_address = rs.getString("address");
                String user_zip_code = rs.getString("zipcode");
                String user_contact_number = rs.getString("contact number");
                String user_email = rs.getString("email");

                
                // Add into the html table
				output += "<tr>"
							+ "<td>"
								+ "<input id= 'hidConnReqIdUpdate' name = 'hidConnReqIdUpdate' type='hidden' value= '" + id + "'>" + user_name 
							+ "</td>";
				output += "<td>" + user_nic + "</td>";
				output += "<td>" + user_address + "</td>";
				output += "<td>" + user_zip_code + "</td>";
				output += "<td>" + user_contact_number + "</td>";
				output += "<td>" + user_email + "</td>";

				
				// buttons
				output += "<td>"
							+ "<input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-connectionRequest_id='" + id + "'>" 
						+ "</td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"	+ id + "'>" + "</td></tr>";
			}
			con.close();
			
			// Complete the html table
			output += "</table>";
			
		} catch(Exception e) {
			output = "Error while reading the connection requests";
			System.err.println(e.getMessage());
			}

        return output;
    }

  //update user
    public String updateUser(String id,String user_name , String user_nic ,String user_address ,String user_zip_code , String user_contact_number, String user_email ) {

        String output = "";

        try {

            Connection con = connect();
            if(con == null) {

                return "error while connecting database";
            }

            //create a prepared statement
//            String sql = "UPDATE connection_req SET nicNo = ? , firstName = ? , lastName = ? , address = ? , tpNo = ?"
//                    + "WHERE connReqId = ?";
            String sql = "UPDATE `electro_grid`.`user` SET `name` = '?'"
            		+ "`nic` = '?', `address` = '?', `zipcode` = '?', `contact number` = '?', `email` = '?'"
            		+ " WHERE (`id` = '?')";

            PreparedStatement state = con.prepareStatement(sql);

            //binding values
            state.setString(1, user_name);
            state.setString(2, user_nic);
            state.setString(3, user_address);
            state.setString(4, user_zip_code);
            state.setString(5, user_contact_number);
            state.setString(6, user_email);
            state.setInt(7, Integer.parseInt(id));

            state.execute();
            con.close();

            output = "Update Successfully";
            
            String newConnRequests = readConnRequest();
			output = "{\"status\":\"success\", \"data\": \"" + newConnRequests + "\"}";

        }catch(Exception e) {
        	output = "{\"status\":\"error\", \"data\": \"Error while updating the connection request.\"}";
			System.err.println(e.getMessage());
			}

        return output;
    }
    
    
  //Delete a connection request
    public String deleteUser(String id) {

        String output = "";

        try {

            Connection con = connect();
            if(con == null) {
                return "error while deleting connection request";
            }

            // Create a prepared statement
            String sql = "delete from connection_req where connReqId = ?";

            PreparedStatement state = con.prepareStatement(sql);

        	// binding values
            state.setInt(1 , Integer.parseInt(id));

            // execute the statement
            state.execute();
            con.close();

            output = "Deleted Successfully";
            
            String newConnRequests = readConnRequest();
			output = "{\"status\":\"success\", \"data\": \"" + newConnRequests + "\"}";

        }catch(Exception e) {

        	output = "{\"status\":\"error\", \"data\": \"Error while deleting the connection request.\"}";
			System.err.println(e.getMessage());

        }

        return output;
    }



}
