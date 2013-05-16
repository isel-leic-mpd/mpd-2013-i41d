package orm;

import java.sql.SQLException;

public abstract class AbstractJdbcDataMapper<K, T> implements DataMapper<K, T>{

	private final JdbcExecutor exec;
	
	public AbstractJdbcDataMapper(JdbcExecutor exec) {
		this.exec = exec;
	}

	@Override
	public final Iterable<T> getAll() {
		try {
			return exec.executeQuery(cmdGetAll());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public final T getById(K key) {
		try {
			return exec.executeQuery(cmdGetById(), key).iterator().next();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract JdbcCmd<T> cmdGetAll();

	protected abstract JdbcCmd<T> cmdGetById();

}
