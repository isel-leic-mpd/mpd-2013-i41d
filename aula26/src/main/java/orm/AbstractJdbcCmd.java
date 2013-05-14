package orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractJdbcCmd<T> implements JdbcCmd<T>{
	
	private final String sql;
	private int nrArgs;
	private final JdbcConverter<T> converter;
	
	public AbstractJdbcCmd(String sql, int nrArgs, JdbcConverter<T> converter) {
		super();
		this.sql = sql;
		this.nrArgs = nrArgs;
		this.converter = converter;
	}
		
	@Override
	public final String getSql() {
		return sql;
	}
	
	@Override
	public final void bind(PreparedStatement stmt, Object[] args) throws SQLException {
		if(args.length != nrArgs) throw new IllegalArgumentException("Wring number of arguments!");
		doBind(stmt, args);
	}

	protected abstract void doBind(PreparedStatement stmt, Object[] args) throws SQLException;
	
	@Override
	public final T convert(ResultSet rs) throws SQLException {
		return converter.convert(rs);
	}
}
