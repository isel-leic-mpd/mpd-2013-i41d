package orm;

public interface DataMapper<K, T>{
	
	T getById(K key);
	
	Iterable<T> getAll();
	
	Iterable<T> where(String clause, JdbcBinder<?> binder, Object arg);
	
	void update(T value);
	
	void insert(T value);
	
	void delete(T value);
	
}
