package orm.northwind;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;
import orm.JdbcConverter;

public class ProductConverter implements JdbcConverter<Product>{

	public static final JdbcConverter<Product> SINGLETON = new ProductConverter();

	@Override
	public Product convert(ResultSet rs) throws SQLException {
		return new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
	}

}
