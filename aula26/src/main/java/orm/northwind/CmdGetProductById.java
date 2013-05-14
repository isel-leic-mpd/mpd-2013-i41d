package orm.northwind;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;
import orm.AbstractJdbcCmd;
import orm.JdbcCmd;
import orm.JdbcConverter;

public class CmdGetProductById extends AbstractJdbcCmd<Product>{

	private static final String s = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products WHERE ProductId = ?";
	
	public CmdGetProductById() {
		super(s, 1, ProductConverter.SINGLETON);
	}

	@Override
	public void doBind(PreparedStatement stmt, Object[] args) throws SQLException {
		stmt.setInt(1, (Integer) args[0]);
	}

}
