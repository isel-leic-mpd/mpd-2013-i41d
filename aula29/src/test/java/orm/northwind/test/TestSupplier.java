package orm.northwind.test;

import java.sql.SQLException;

import junit.framework.Assert;

import model.Product;
import model.Supplier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import orm.DataMapper;
import orm.JdbcBinder;
import orm.JdbcExecutor;
import orm.JdbcExecutorSingleConnection;
import orm.northwind.JdbcProductsMapper;
import orm.northwind.JdbcSuppliersMapper;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class TestSupplier {

	private DataMapper<Integer, Supplier> mapper; 
	private JdbcExecutor exec;
	@Before
	public void setUp(){
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser("myAppUser");
		ds.setPassword("fcp");
		exec = new JdbcExecutorSingleConnection(ds, false);
		mapper = new JdbcSuppliersMapper(exec, new JdbcProductsMapper(exec));
	}
	
	@After
	public void tearDown() throws Exception{
		exec.close();
	}
	
	@Test
	public void test_product_load_by_all() throws SQLException{
		Iterable<Supplier> res = mapper.getAll();
		int size = 0;
		for(Supplier e:res){size++;}
		Assert.assertEquals(29, size);
	}
	
	@Test
	public void test_product_load_by_id() throws SQLException{
		Supplier s = mapper.getById(4);
		Assert.assertEquals("Tokyo Traders", s.getCompanyName());
		Assert.assertEquals("Tokyo", s.getCity());
		Assert.assertEquals("Yoshi Nagase", s.getContactName());
		
		int[]expected = {9, 74};
		int i = 0;
		for (Product p : s.getProducts()) {
			Assert.assertEquals(expected[i++], p.getId());
		}

	}
	
}
