package util;

public interface Predicate<T> {
	boolean invoke(T elem);
}
