import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.sql.DataSource;

public class ProductDataAccessor{

	public Iterable<Product> getProductById(DataSource ds, int id) throws SQLException{
		LinkedList<Product> res = null;
		final String SQL = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products WHERE ProductId = ?";
		try (Connection con = ds.getConnection("myAppUser", "fcp"); // Establish the connection.
			 PreparedStatement stmt = con.prepareStatement(SQL)	
		) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			res = new LinkedList<Product>();
			/*
			 * Iterate through the data in the result set and display it.
			 */
			while (rs.next()) {
				Product p = new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
				res.add(p);
			}
			return res;
		}		
	}
	
	public Iterable<Product> getProducts(DataSource ds) throws SQLException{
		LinkedList<Product> res = null;
		try (Connection con = ds.getConnection("myAppUser", "fcp"); // Establish the connection.
			 Statement stmt = con.createStatement();	
		) {
			/*
			 * Execute an SQL statement that returns some data.
			 */
			String SQL = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products";
			ResultSet rs = stmt.executeQuery(SQL);
			res = new LinkedList<Product>();
			/*
			 * Iterate through the data in the result set and display it.
			 */
			while (rs.next()) {
				Product p = new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
				res.add(p);
			}
			return res;
		}		
	}
}
