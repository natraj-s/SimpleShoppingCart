package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class UserAccount {
	
	private Connection conn;
	private ArrayList<String> names;
	private ArrayList<String> descriptions;
	private ArrayList<String> images;
	
	public UserAccount() throws ServletException {
		
		DataSource ds;
		
		try {
			InitialContext initContext = new InitialContext();
			
			Context env = (Context) initContext.lookup("java:comp/env");
			
			ds = (DataSource) env.lookup("jdbc/webshop");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			throw new ServletException();
		}
		
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UserAccount(Connection conn) {
		this.conn = conn;
	}
	
	public String addItems(String email, String items) throws ServletException {
		Statement stmt = null;
		PreparedStatement prepstmt = null;
		ResultSet rs = null;
		
		String newItemsString = items;
		String combinedItemsString = "";
		String userItemsString = "";
		List<String> userItemsList = new ArrayList<String>();
		List<String> newItemsList = new ArrayList<String>();
		List<Integer> allItemsList = new ArrayList<Integer>();
		
		if(newItemsString.indexOf(",") > -1) {
			newItemsList = Arrays.asList(newItemsString.split(","));
		}
		else {
			newItemsList.add(newItemsString);
		}
		
		String sql = "SELECT items FROM myaccount WHERE email=\"" + email +"\"";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				userItemsString = rs.getString("items");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException();
		}
		
		if(userItemsString != null) {
			userItemsList = Arrays.asList(userItemsString.split(","));
		}
		
		
		if(userItemsList.isEmpty()) {
			allItemsList.addAll(newItemsList.stream().map(Integer::valueOf).collect(Collectors.toList()));
			//allItemsList.addAll(newItemsList);
		}
		else {
			//allItemsList.addAll(userItemsList);
			allItemsList.addAll(userItemsList.stream().map(Integer::valueOf).collect(Collectors.toList()));
			for(String oneitem : newItemsList) {
				if(!userItemsList.contains(oneitem)) {
					allItemsList.add(Integer.parseInt(oneitem));
				}
			}
		}
		
		Collections.sort(allItemsList);
		
		for(Integer item: allItemsList) {
			combinedItemsString = combinedItemsString + String.valueOf(item);
			
			if(allItemsList.indexOf(item) != allItemsList.size() - 1) {
				combinedItemsString = combinedItemsString + ",";
			}
		}
		
		
		sql = "UPDATE myaccount SET items = \'" + combinedItemsString + "\' WHERE email = \'" + email + "\'";
		System.out.println("SQL statement: " + sql);
		
		try {
			prepstmt = conn.prepareStatement(sql);
			prepstmt.executeUpdate();
			
			prepstmt.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return combinedItemsString;
	}
	
	public void removeItems(String email, String items) throws ServletException {
		Statement stmt = null;
		PreparedStatement prepstmt = null;
		ResultSet rs = null;
		
		if(items != null || items != "") {
			String updatedString = "";
			String userItemsString = getItems(email);
			List<String> userItemsList = new ArrayList<String>(Arrays.asList(userItemsString.split(",")));
			
			List<String> removeList = new ArrayList<String>(Arrays.asList(items.split(",")));			
			
			userItemsList.removeAll(removeList);
			
			for(String one : userItemsList) {
				updatedString = updatedString + one;
				
				if(userItemsList.indexOf(one) != userItemsList.size() - 1) {
					updatedString = updatedString + ",";
				}
			}
			
			String sql = "UPDATE myaccount SET items =\'" + updatedString + "\' WHERE email = \'" + email + "\'";
			
			try {
				prepstmt = conn.prepareStatement(sql);
				prepstmt.executeUpdate();
				
				prepstmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public String getCombinedString(String email) throws ServletException {
		String combinedString = "";
		String itemsString = "";
		String namesString = "";
		String descriptionString = "";
		
		itemsString = getItems(email);
		namesString = getNames(itemsString);
		descriptionString = getDescriptions(itemsString);
		
		combinedString = itemsString + ";" + namesString + ";" + descriptionString;
		
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException();
		}
		
		return combinedString;
	}
	
	public String getItems(String email) throws ServletException {
		Statement stmt = null;
		ResultSet rs = null;
		
		String userItemsString = "";
		
		String sql = "SELECT items FROM myaccount WHERE email=\"" + email +"\"";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				userItemsString = rs.getString("items");
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException();
		}
		
		return userItemsString;
	}
	
	public String getNames(String items) throws ServletException {
		Statement stmt = null;
		ResultSet rs = null;
		
		String nameString = "";
		List<String> itemsList = new ArrayList<String>();
		if(items != null) {
			itemsList = Arrays.asList(items.split(","));
		
			try {
				stmt = conn.createStatement();
				
				for(String item: itemsList) {
					String sql = "SELECT name FROM items WHERE id = \"" + item + "\"";
					rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
						nameString = nameString + rs.getString("name");
					}
					
					if(itemsList.iterator().hasNext()) {
						nameString = nameString + ",";
					}
				}
				
				stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new ServletException();
			}
		}
		
		return nameString;
	}
	
	public String getDescriptions(String items) throws ServletException {
		Statement stmt = null;
		ResultSet rs = null;
		
		String descriptionString = "";
		List<String> itemsList = new ArrayList<String>();
		if(items != null) {
			itemsList = Arrays.asList(items.split(","));
		
			try {
				stmt = conn.createStatement();
				
				for(String item: itemsList) {
					String sql = "SELECT description FROM items WHERE id = \"" + item + "\"";
					rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
						descriptionString = descriptionString + rs.getString("description");
					}
					
					if(itemsList.iterator().hasNext()) {
						descriptionString = descriptionString + ":::";
					}
				}
				
				stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new ServletException();
			}
		}
		
		return descriptionString;
	}
	
	public void closeConnection() throws SQLException {
		conn.close();
	}
	

}
