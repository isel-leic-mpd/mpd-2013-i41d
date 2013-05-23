package orm.northwind;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import model.Category;
import model.Product;
import model.Supplier;
import orm.DataMapper;
import orm.JdbcAbstractMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdTemplate;
import orm.JdbcConverter;
import orm.JdbcExecutor;
import orm.ValueHolder;

public class JdbcProductsMapper extends JdbcAbstractMapper<Integer, Product>{

	/*=================================================================
	 * ----------------------- CONSTANTS ------------------------------
	 *=================================================================*/
	
	final static String sqlQuery = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock, SupplierId, CategoryId FROM Products";
	final static String sqlUpdate = "UPDATE Products SET ProductName = ?, UnitPrice = ?, UnitsInStock = ? WHERE ProductID = ?";
	final static String sqlInsert = "INSERT INTO Products (ProductName, UnitPrice, UnitsInStock) VALUES (?, ?, ?)";
	final static String sqlDelete = "DELETE FROM Products WHERE ProductID = ?";
	
	final JdbcConverter<Product> converter = new JdbcConverter<Product>() {
		@Override
		public Product convert(ResultSet rs) throws SQLException {
			int prodId = rs.getInt(1);
			Product p = checkId(prodId);
			if(p != null) return p;
			final int supId = rs.getInt(5);
			final int catId = rs.getInt(6);
			p = new Product(
					prodId, 
					rs.getString(2), 
					rs.getDouble(3), 
					rs.getInt(4),
					new Supplier(){
						Supplier cache;
						private Supplier get(){
							if(cache == null){
								cache = supMapper.getById(supId );
							}
							return cache;
						}
						public String getAddress() {return get().getAddress();}
						public Integer getId() {return get().getId();}
						public String getCompanyName() { return get().getCompanyName(); }
						public String getContactTitle() { return get().getContactTitle(); }
						public String getCity() {return get().getCity(); }
						public Iterable<Product> getProducts() { return get().getProducts(); }
					},
					new ValueHolder<Category>() {
						Category cache;
						@Override
						public Category value() {
							if(cache == null){
								cache = catMapper.getById(catId);
							}
							return cache;
						}
					});
			add(prodId, p);
			return p;
		}
	};

	final static JdbcBinder<Product> binderUpdate = new JdbcBinder<Product>() {
		@Override
		public void bind(PreparedStatement stmt, int idx, Product arg) throws SQLException {
			binderInsert.bind(stmt, idx, arg);
			stmt.setInt(4, arg.getId());
		}
	};
	
	final static JdbcBinder<Product> binderInsert= new JdbcBinder<Product>() {
		@Override
		public void bind(PreparedStatement stmt, int idx, Product arg) throws SQLException {
			stmt.setString(1, arg.getProductName());
			stmt.setDouble(2, arg.getUnitPrice());
			stmt.setInt(3, arg.getUnitsInStock());
		}
	};
	
	final static JdbcBinder<Product> binderDelete = new JdbcBinder<Product>() {
		@Override
		public void bind(PreparedStatement stmt, int idx, Product arg) throws SQLException {
			stmt.setInt(1, arg.getId());
		}
	};
	
	final static JdbcConverter<Integer> genKey = new JdbcConverter<Integer>() {		
		@Override
		public Integer convert(ResultSet rs) throws SQLException {
			return rs.getInt(1);
		}
	};
	
	final JdbcCmd<Product> getById = new JdbcCmdTemplate<>(sqlQuery + " WHERE ProductId = ?", converter, JdbcBinder.BindInt);
	final JdbcCmd<Product> getAll = new JdbcCmdTemplate<>(sqlQuery, converter);
	final JdbcCmd<Product> updateCmd = new JdbcCmdTemplate<>(sqlUpdate, null, binderUpdate);
	final JdbcCmd<Integer> insertCmd = new JdbcCmdTemplate<>(sqlInsert, genKey, binderInsert);
	final JdbcCmd<Product> deleteCmd = new JdbcCmdTemplate<>(sqlDelete, null, binderDelete);
	
	/*=================================================================
	 *=================================================================*/
	
	private DataMapper<Integer, Supplier> supMapper;
	private DataMapper<Integer, Category> catMapper;
	
	@Inject
	public JdbcProductsMapper(JdbcExecutor exec, DataMapper<Integer, Supplier> supMapper, DataMapper<Integer, Category> catMapper) {
		super(exec);
		this.supMapper = supMapper;
		this.catMapper = catMapper;
	}
	
	@Override
	protected JdbcCmd<Product> cmdGetAll() {
		return getAll;
	}

	@Override
	protected JdbcCmd<Product> cmdGetById() {
		return getById;
	}

	@Override
	protected JdbcCmd<Product> cmdUpdate() {
		return updateCmd;
	}

	@Override
	protected void updateKeyOnValue(Product value, Integer key) {
		value.setId(key);
	}

	@Override
	protected JdbcCmd<Integer> cmdInsert() {
		return insertCmd;
	}

	@Override
	protected JdbcCmd<Product> cmdDelete() {
		return deleteCmd;
	}

}
