package orm;

import java.sql.SQLException;

public interface JdbcExecutor extends AutoCloseable{

	public <T> Iterable<T> executeQuery(JdbcCmd<T> cmd, Object...args) throws SQLException ;

	public <T> void executeUpdate(JdbcCmd<T> cmd, Object...args) throws SQLException ;
}
