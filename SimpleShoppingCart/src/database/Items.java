package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

public class Items {
	
	private Connection conn;
	private List<String> names;
	private List<String> descriptions;
	private List<String> images;
	
	public Items(Connection conn) {
		this.conn = conn;
	}
	
	public void initLoad() throws ServletException, SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		names = new ArrayList<String>();
		descriptions = new ArrayList<String>();
		images = new ArrayList<String>();
		int counter = 0;
		
		String sql = "SELECT * FROM items";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				names.add(rs.getString("name"));
				descriptions.add(rs.getString("description"));
				images.add(rs.getString("imageurl"));
				counter++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException();
		}
		
	}

	public List<String> getNames() {
		return names;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public List<String> getImages() {
		return images;
	}

	
}
