package model;

public class Logger {
	
	public <K> void log(Entity<K> e){
		e.accept(this);
	}
	
	public <T> void visit(Iterable<T> e){
		for (T t : e) {
			System.out.println(t);
		}
	}
	
	public void visit(Object e){
		System.out.println(e);
	}
	
	
}
