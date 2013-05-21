package orm.northwind;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;
import model.Supplier;
import orm.DataMapper;
import orm.JdbcAbstractMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdTemplate;
import orm.JdbcConverter;
import orm.JdbcExecutor;

public class JdbcSuppliersMapper extends JdbcAbstractMapper<Integer, Supplier>{

	private final static String  sqlAll = "SELECT SupplierId, CompanyName,ContactName,ContactTitle, Address, City FROM Suppliers";
	private final static String  sqlById = sqlAll + " WHERE SupplierId = ?";  
			
	private final JdbcConverter<Supplier> conv= new JdbcConverter<Supplier>() {
		@Override
		public Supplier convert(ResultSet rs) throws SQLException {
			int supplierID = rs.getInt(1);
			return new Supplier(
					supplierID, 
					rs.getString(2), 
					rs.getString(3), 
					rs.getString(4), 
					rs.getString(5), 
					rs.getString(6), 
					prodsMapper.where("SupplierId = ?", JdbcBinder.BindInt, supplierID));
		}
	};
	
	private final DataMapper<Integer, Product> prodsMapper;
	
	private final JdbcCmd<Supplier> cmdAll = new JdbcCmdTemplate<>(sqlAll, conv); 
	private final JdbcCmd<Supplier> cmdById = new JdbcCmdTemplate<>(sqlById, conv, JdbcBinder.BindInt);
	
	public JdbcSuppliersMapper(JdbcExecutor exec, DataMapper<Integer, Product> prodsMapper) {
		super(exec);
		this.prodsMapper = prodsMapper;
	}

	@Override
	protected void updateKeyOnValue(Supplier value, Integer key) {
		// TODO Auto-generated method stub
	}

	@Override
	protected JdbcCmd<Integer> cmdInsert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JdbcCmd<Supplier> cmdGetAll() {
		return cmdAll;
	}

	@Override
	protected JdbcCmd<Supplier> cmdGetById() {
		return cmdById;
	}

	@Override
	protected JdbcCmd<Supplier> cmdUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JdbcCmd<Supplier> cmdDelete() {
		// TODO Auto-generated method stub
		return null;
	}

}
