package model;

public interface Entity<K>{
	
	public K getId();

	public void setId(K val);
	
	public void accept(Logger l);
}
