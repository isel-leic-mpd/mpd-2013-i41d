package orm.northwind;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;
import orm.AbstractJdbcDataMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdTemplate;
import orm.JdbcConverter;
import orm.JdbcExecutor;

public class JdbcEmployeesMapper extends AbstractJdbcDataMapper<Integer, Employee> implements JdbcConverter<Employee>{

	final static String sql = "SELECT EmployeeId, Title, FirstName, LastName, BirthDate FROM Employees";
	
	final JdbcCmd<Employee> cmdLoadAll = new JdbcCmdTemplate<Employee>(
			sql, 
			this);
	
	final JdbcCmd<Employee> cmdLoadById = new JdbcCmdTemplate<Employee>(
			sql + " WHERE EmployeeId = ?", 
			this,
			JdbcBinder.IntBinder);	


	public JdbcEmployeesMapper(JdbcExecutor exec) {
		super(exec);
	}

	@Override
	protected JdbcCmd<Employee> cmdGetAll() {
		return cmdLoadAll;
	}

	@Override
	protected JdbcCmd<Employee> cmdGetById() {
		return cmdLoadById;
	}
	
	@Override
	public Employee convert(ResultSet rs) throws SQLException{
		return new Employee(
				rs.getInt(1),
				rs.getString(2), 
				rs.getString(3), 
				rs.getString(4), 
				rs.getDate(5));
	}

	@Override
	protected JdbcCmd<Employee> cmdUpdate() {
		return null;
	}		
}
