package orm.northwind.test;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import junit.framework.Assert;
import model.Employee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import orm.JdbcExecutor;
import orm.JdbcExecutorSingleConnection;
import orm.northwind.JdbcEmployeesMapper;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class TestEmployees {

	private JdbcEmployeesMapper mapper; 
	private JdbcExecutor exec;

	@Before
	public void setUp(){
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser("myAppUser");
		ds.setPassword("fcp");
		exec = new JdbcExecutorSingleConnection(ds, false);
		mapper = new JdbcEmployeesMapper(exec);
	}

	@After
	public void tearDown() throws Exception{
		exec.close();
	}

	@Test
	public void test_update() throws Exception{
		Employee e = mapper.getById(7);
		Assert.assertEquals("King", e.getLastName());
		Assert.assertEquals("Robert", e.getFirstName());
		Assert.assertEquals("Sales Representative", e.getTitle());
		//
		// Update
		//
		e.setFirstName("Jose");
		e.setLastName("Manel");
		e.setTitle("Engenheiro");
		mapper.update(e);
		//
		// Assert
		//
		e = mapper.getById(7);
		Assert.assertEquals("Jose", e.getFirstName());
		Assert.assertEquals("Manel", e.getLastName());
		Assert.assertEquals("Engenheiro", e.getTitle());
	}

	@Test
	public void test_load_all_employees() throws SQLException{		
		Iterable<Employee> res = mapper.getAll();
		int size = 0;
		for(Employee e:res){size++;}
		Assert.assertEquals(9, size);
	}
	@Test
	public void test_load_byid_employees() throws SQLException{
		Employee e = mapper.getById(7);
		Assert.assertEquals("King", e.getLastName());
		Assert.assertEquals("Robert", e.getFirstName());
		Assert.assertEquals("Sales Representative", e.getTitle());
	}


	final static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

	@Test
	public void test_insert_and_delete_employee() throws Exception{
		Employee e = new Employee("Eng", "Jose", "Manuel", format.parse("5-12-1960"));
		//
		// ACT
		//
		mapper.insert(e);
		Employee newE = mapper.getById(e.getId());
		Assert.assertTrue(e != newE);
		//
		// ASSERT
		//
		Assert.assertEquals("Manuel", newE.getLastName());
		Assert.assertEquals("Jose", newE.getFirstName());
		Assert.assertEquals("Eng", newE.getTitle());			
		Assert.assertEquals(format.parse("5-12-1960"), newE.getBirthDate());
		//
		// ACT
		//
		mapper.delete(e);
		//
		// ASSERT
		//
		Assert.assertNull(mapper.getById(e.getId()));
	}
}
