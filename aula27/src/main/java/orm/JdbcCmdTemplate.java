package orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCmdTemplate<T> implements JdbcCmd<T>{
	
	private final String sql;
	private final JdbcConverter<T> converter;
	private final JdbcBinder[] binders;
	
	public JdbcCmdTemplate(String sql, JdbcConverter<T> converter, JdbcBinder...binders) {
		super();
		this.sql = sql;
		this.converter = converter;
		this.binders = binders;
	}
		
	@Override
	public final String getSql() {
		return sql;
	}
	
	@Override
	public final void bind(PreparedStatement stmt, Object[] args) throws SQLException {
		if(args.length != binders.length) 
			throw new IllegalArgumentException("Wring number of arguments!");
		int i = 0;
		for (Object a : args) {
			binders[i].bind(stmt, i + 1, a);
			i++;
		}		
	}
	
	@Override
	public final T convert(ResultSet rs) throws SQLException {
		return converter.convert(rs);
	}
}
