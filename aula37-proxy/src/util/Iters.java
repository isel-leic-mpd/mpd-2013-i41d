package util;

public class Iters {
	public static <T> T find(Iterable<T> elems, Predicate<? super T> p){
		for (T elem : elems) {
			if(p.invoke(elem)){
				return elem;
			}
		}
		return null;
	}
	// public static <T extends S, S> T find(T [] elems, Predicate<S> p){
	public static <T> T find(T [] elems, Predicate<? super T> p){
		for (T elem : elems) {
			if(p.invoke(elem)){
				return elem;
			}
		}
		return null;
	}
	public static void foreach(Object [] elems, Action a) {
		for (Object e  : elems) {
			a.doIt(e);
		}	
	}
}
