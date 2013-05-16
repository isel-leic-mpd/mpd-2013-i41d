package orm;

public interface DataMapper<K, T>{
	Iterable<T> getAll();
	T getById(K key);
	/*
	void update(T value);
	void insert(T value);
	void delete(T value);
	*/
}
