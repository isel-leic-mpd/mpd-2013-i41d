package orm;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface JdbcBinder<T>{

	public void bind(PreparedStatement stmt, int idx, T arg) throws SQLException;
	
	public final static JdbcBinder<Integer> IntBinder = new JdbcBinder<Integer>() {
		@Override
		public void bind(PreparedStatement stmt, int idx, Integer arg) throws SQLException {
			stmt.setInt(idx, arg);
		}
	};  
	
	public final static JdbcBinder<Double> DoubleBinder = new JdbcBinder<Double>() {
		@Override
		public void bind(PreparedStatement stmt, int idx, Double arg) throws SQLException {
			stmt.setDouble(idx, arg);
		}
	};
	
}
