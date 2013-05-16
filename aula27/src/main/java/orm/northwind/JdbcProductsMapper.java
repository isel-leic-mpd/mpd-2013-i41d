package orm.northwind;

import java.sql.SQLException;

import model.Product;
import orm.AbstractJdbcDataMapper;
import orm.DataMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdTemplate;
import orm.JdbcExecutor;

public class JdbcProductsMapper extends AbstractJdbcDataMapper<Integer, Product>{

	final static private String sqlGetAll = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products";

	final static private JdbcCmd<Product> cmdGetAll = new JdbcCmdTemplate<Product>(
			sqlGetAll, 
			ProductConverter.SINGLETON);

	final static private JdbcCmd<Product> cmdGetById = new JdbcCmdTemplate<Product>(
			sqlGetAll + " WHERE ProductId = ?", 
			ProductConverter.SINGLETON, 
			JdbcBinder.IntBinder);


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

}
