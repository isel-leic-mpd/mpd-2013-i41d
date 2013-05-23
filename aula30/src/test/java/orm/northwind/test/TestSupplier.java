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
import orm.northwind.JdbcCategoryMapper;
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
		JdbcProductsMapper prodsMapper = new JdbcProductsMapper(exec, new JdbcCategoryMapper(exec));
		mapper = new JdbcSuppliersMapper(exec, prodsMapper);
		prodsMapper.setSupplier(mapper);
	}
	
	@After
	public void tearDown() throws Exception{
		exec.close();
	}
	
	@Test
	public void test_supplier_load_by_all() throws SQLException{
		Iterable<Supplier> res = mapper.getAll();
		int size = 0;
		for(Supplier e:res){size++;}
		Assert.assertEquals(29, size);
	}
	
	@Test
	public void test_supplier_load_by_id() throws SQLException{
		Supplier s = mapper.getById(4);
		Assert.assertEquals("Tokyo Traders", s.getCompanyName());
		Assert.assertEquals("Tokyo", s.getCity());
		Assert.assertEquals("Yoshi Nagase", s.getContactName());
		
		int[]expected = {9, 74};
		int i = 0;
		for (Product p : s.getProducts()) {
			Assert.assertEquals(expected[i++], p.getId());
		}

		s = mapper.getById(3);
		Assert.assertEquals("Grandma Kelly's Homestead", s.getCompanyName());
		Assert.assertEquals("Ann Arbor", s.getCity());
		Assert.assertEquals("Regina Murphy", s.getContactName());
		
		expected = new int[]{6, 7, 8};
		i = 0;
		for (Product p : s.getProducts()) {
			Assert.assertEquals(expected[i++], p.getId());
		}
		
	}
	
}
