package app;

import static java.util.Arrays.asList;
import static utils.Iters.find;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import utils.Iters;
import utils.Predicate;

public class Binder<E>{

	public static <T> Map<String, Object> getFields(T p) {
		return null;
	}

	public E bindTo(Class<E> klass, Iterable<Entry<String, Object>> pairs, SetterFinder...finders) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		E res = klass.newInstance();
		Method [] ms = klass.getMethods();
		Field[] fs = klass.getFields();
		Iterable<Member> members = Iters.merge(Member.class, asList(fs), asList(ms));
		for (Entry<String, Object> e : pairs) {
			for (SetterFinder f : finders) {
				Setter s = f.get(members, e.getKey());
				s.update(res, e.getValue());
			}
		}		
		return res;
	}
	
	private static Predicate<Member> memberNameEqualsTo(final String name){
		return new Predicate<Member>(){
			public boolean eval(Member e) {
				return e.getName().equalsIgnoreCase(name);
			}
		};
	} 
}

class PredicateMemberName implements Predicate<Member>{
	private final String name;
	
	public PredicateMemberName(String name) {
		this.name = name;
	}
	public boolean eval(Member e) {
		return e.getName().equalsIgnoreCase(name);
	}	
}
