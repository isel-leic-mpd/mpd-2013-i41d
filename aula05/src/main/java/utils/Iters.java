package utils;

public class Iters {
	public static <T> T find(T[] elems, Predicate<T> p){
		for (T e : elems) {
			if(p.eval(e))
				return e;
		}
		return null;
	}
}
