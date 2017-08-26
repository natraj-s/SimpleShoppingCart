package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
	
	private Connection conn;
	
	public Account(Connection conn) {
		this.conn = conn;
	}
	
	public boolean login(String email, String password) throws SQLException {
		int count = 0;
		
		String sql = "SELECT count(*) AS count FROM login WHERE email=? AND password=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, email);
		stmt.setString(2, password);		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			count = rs.getInt("count");
		}
		
		rs.close();
		
		if(count == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void create(String email, String password) throws SQLException {
		String sql = "INSERT INTO login (email, password) VALUES (?,?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, email);
		stmt.setString(2, password);
		
		stmt.executeUpdate();
		
		sql = "INSERT INTO myaccount (email) VALUES (?)";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	public boolean exists(String email) throws SQLException {
		int count = 0;
		
		String sql = "SELECT count(*) AS count FROM login WHERE email=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, email);		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			count = rs.getInt("count");
		}
		
		if(count == 0) {
			return false;
		}
		else {
			return true;
		}
	}
}
