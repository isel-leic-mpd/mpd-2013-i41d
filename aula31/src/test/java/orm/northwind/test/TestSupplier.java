package orm.northwind.test;

import java.sql.SQLException;

import javax.inject.Singleton;
import javax.sql.DataSource;

import junit.framework.Assert;

import model.Category;
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

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class TestSupplier {

	private DataMapper<Integer, Supplier> mapper; 
	private DataMapper<Integer, Product> prodMapper;
	private JdbcExecutor exec;
	
	@Before
	public void setUp(){
		Injector inj = Guice.createInjector(new AbstractModule() {
			protected void configure() {
				SQLServerDataSource ds = new SQLServerDataSource();
				ds.setUser("myAppUser");
				ds.setPassword("fcp");
				bind(DataSource.class).toInstance(ds);
				bind(JdbcExecutor.class).to(JdbcExecutorSingleConnection.class);
				bind(new TypeLiteral<DataMapper<Integer, Product>>(){}).to(JdbcProductsMapper.class).in(Singleton.class);
				bind(new TypeLiteral<DataMapper<Integer, Supplier>>(){}).to(JdbcSuppliersMapper.class).in(Singleton.class);
				bind(new TypeLiteral<DataMapper<Integer, Category>>(){}).to(JdbcCategoryMapper.class).in(Singleton.class);
			}
		});
		
		exec = inj.getInstance(JdbcExecutor.class);
		mapper = inj.getInstance(new Key<DataMapper<Integer, Supplier>>(){});
		prodMapper = inj.getInstance(new Key<DataMapper<Integer, Product>>(){});
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
			Assert.assertEquals(expected[i], p.getId());
			/*
			 * Here we want to check that the prodMapper will not load a new Product object
			 * and it will use the same Product that is already stored in the internal identity map.
			 * Besides that, we are also testing the singleton behavior defined in the Guice 
			 * configuration module for all DataMappers. If we remove the Singleton scope from the
			 * JdbcProductsMapper definition (see above) then this test will fail.      
			 */
			Assert.assertSame(prodMapper.getById(expected[i]), p);
			
			i++;
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
