package orm.northwind;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Product;
import orm.AbstractJdbcDataMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdTemplate;
import orm.JdbcExecutor;

public class JdbcProductsMapper extends AbstractJdbcDataMapper<Integer, Product> implements JdbcBinder<Product>{

	final static private String sqlGetAll = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products";
	final static private String sqlUpdate = "UPDATE Products SET ProductName = ?, UnitPrice = ?, UnitsInStock = ? WHERE ProductID = ?";

	final static private JdbcCmd<Product> cmdGetAll = new JdbcCmdTemplate<Product>(
			sqlGetAll, 
			ProductConverter.SINGLETON);

	final static private JdbcCmd<Product> cmdGetById = new JdbcCmdTemplate<Product>(
			sqlGetAll + " WHERE ProductId = ?", 
			ProductConverter.SINGLETON, 
			JdbcBinder.IntBinder);

	final private JdbcCmd<Product> cmdUpdate = new JdbcCmdTemplate<Product>(
			sqlUpdate, 
			null, 
			this);

	
	public JdbcProductsMapper(JdbcExecutor exec) {
		super(exec);
	}


	@Override
	protected JdbcCmd<Product> cmdGetAll() {
		return cmdGetAll;
	}


	@Override
	protected JdbcCmd<Product> cmdGetById() {
		return cmdGetById;
	}

	@Override
	protected JdbcCmd<Product> cmdUpdate() {
		return cmdUpdate;
	}

	@Override
	public void bind(PreparedStatement stmt, int idx, Product p) throws SQLException {
		stmt.setString(1, p.getProductName());
		stmt.setDouble(2, p.getUnitPrice());
		stmt.setInt(3, p.getUnitsInStock());
		stmt.setInt(4, p.getId());
	}

}
