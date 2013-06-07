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

public class TestProducts {

	private DataMapper<Integer, Product> mapper; 
	private JdbcExecutor exec;
	@Before
	public void setUp(){
		Injector inj = Guice.createInjector(new AbstractModule() {
			protected void configure() {
				SQLServerDataSource ds = new SQLServerDataSource();
				ds.setUser("myAppUser");
				ds.setPassword("fcp");
				bind(DataSource.class).toInstance(ds);
				bind(JdbcExecutor.class).to(JdbcExecutorSingleConnection.class).in(Scopes.SINGLETON);
				bind(new TypeLiteral<DataMapper<Integer, Product>>(){}).to(JdbcProductsMapper.class).in(Scopes.SINGLETON);
				bind(new TypeLiteral<DataMapper<Integer, Supplier>>(){}).to(JdbcSuppliersMapper.class).in(Scopes.SINGLETON);
				bind(new TypeLiteral<DataMapper<Integer, Category>>(){}).to(JdbcCategoryMapper.class).in(Scopes.SINGLETON);
			}
		});
		
		exec = inj.getInstance(JdbcExecutor.class);
		mapper = inj.getInstance(new Key<DataMapper<Integer, Product>>(){});		
	}
	
	@After
	public void tearDown() throws Exception{
		exec.close();
	}
	
	@Test
	public void test_product_load_by_all() throws SQLException{
		Iterable<model.Product> res = mapper.getAll();
		int size = 0;
		for(Product e:res){size++;}
		Assert.assertEquals(77, size);
	}
	
	@Test
	public void test_product_where() throws SQLException{
		Iterable<model.Product> res = mapper.where("UnitPrice < ?", JdbcBinder.BindInt, 7);
		int[]expected = {13, 24, 33};
		int i = 0;
		for (Product p : res) {
			Assert.assertEquals(expected[i++], p.getId());
		}
	}
	
	@Test
	public void test_product_load_by_id() throws SQLException{
		Product p = mapper.getById(9);
		
		Assert.assertEquals("Mishi Kobe Niku", p.getProductName());
		Assert.assertEquals(97.0, p.getUnitPrice());
		Assert.assertEquals(29, p.getUnitsInStock());
		
		Product p2 = mapper.getById(9);
		
		Assert.assertSame(p2, p);
		
	}
	
	@Test
	public void test_update_product() throws SQLException{
		Product p = mapper.getById(9);
		Assert.assertEquals("Mishi Kobe Niku", p.getProductName());
		
		p.setProductName("Casa de Cafe Bastos");
		mapper.update(p);
		
		p = mapper.getById(9);
		Assert.assertEquals("Casa de Cafe Bastos", p.getProductName());		
	}
	
	@Test
	public void test_insert_product() throws SQLException{
		Product p1 = new Product("Cafe da Alcobia", 100.0, 5550, null, null);
		mapper.insert(p1);
		
		Product p2  = mapper.getById(p1.getId());
		Assert.assertNotSame(p1, p2);
		
		Assert.assertEquals(p1.getProductName(), p2.getProductName());
		Assert.assertEquals(p1.getUnitPrice(), p2.getUnitPrice());
		Assert.assertEquals(p1.getUnitsInStock(), p2.getUnitsInStock());		
	}
	
	@Test
	public void test_delete_product() throws SQLException{
		Product p1 = new Product("Cafe da Alcobia", 100.0, 5550, null, null);
		mapper.insert(p1);
		
		Product p2  = mapper.getById(p1.getId());
		Assert.assertNotSame(p1, p2);
		
		
		mapper.delete(p1);
		
		Assert.assertNull(mapper.getById(p1.getId()));
	}
}
