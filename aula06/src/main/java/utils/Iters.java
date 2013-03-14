package utils;

public class Iters {
	//public static <T extends S, S> T find(T[] elems, Predicate<S> p){
	public static <T> T find(T[] elems, Predicate<? super T> p){
		for (T e : elems) {
			if(p.eval(e))
				return e;
		}
		return null;
	}
}
