package orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class JdbcAbstractMapper<K, T> implements DataMapper<K, T>{
	
	private final JdbcExecutor exec;
	private final Map<K, T> identityMap = new HashMap<>();
	
	
	public JdbcAbstractMapper(JdbcExecutor exec) {
		this.exec = exec;
	}

	protected T checkId(K key){
		return identityMap.get(key);
	}
	

	protected void add(K key, T value){
		identityMap.put(key, value);
	}

	
	@Override
	public final T getById(K key) {
		try {
			Iterator<T> iter = exec.executeQuery(cmdGetById(), key).iterator();
			return iter.hasNext()? iter.next(): null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	@Override
	public final Iterable<T> getAll() {
		try {
			return exec.executeQuery(cmdGetAll());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * One cache per DataMapper. The same clause turns into the same command.
	 */
	private final Map<String, JdbcCmd<T>> whereCache = new HashMap<String, JdbcCmd<T>>();
	
	@Override
	public final Iterable<T> where(final String clause, final JdbcBinder binder, final Object arg) {
		JdbcCmd<T> cmdWhere = whereCache.get(clause);
		if(cmdWhere == null){
			final JdbcCmd<T> cmdAll = cmdGetAll();
			cmdWhere = new JdbcCmd<T>(){
				public String getSql() {
					return cmdAll.getSql() + " WHERE " + clause;
				}
				public void bind(PreparedStatement stmt, Object[] args) throws SQLException {
					binder.bind(stmt, 1, args[0]);
				}
				public T convert(ResultSet rs) throws SQLException {
					return cmdAll.convert(rs);
				}
			};
			whereCache.put(clause, cmdWhere);
		}
		try {
			return exec.executeQuery(cmdWhere, arg);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public final void update(T value) {
		try {
			exec.executeUpdate(cmdUpdate(), value);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public final void insert(T value) {
		try {
			K key = exec.executeInsert(cmdInsert(), value);
			updateKeyOnValue(value, key);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void delete(T value) {
		try {
			exec.executeUpdate(cmdDelete(), value);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected abstract void updateKeyOnValue(T value, K key);

	protected abstract JdbcCmd<K> cmdInsert();

	protected abstract JdbcCmd<T> cmdGetAll();
	
	protected abstract JdbcCmd<T> cmdGetById();
	
	protected abstract JdbcCmd<T> cmdUpdate();
	
	protected abstract JdbcCmd<T> cmdDelete();


}
