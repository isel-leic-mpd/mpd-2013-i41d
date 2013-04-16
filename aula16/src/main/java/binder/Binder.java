package binder;

import static java.util.Arrays.asList;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import utils.Iters;
import utils.Predicate;

public class Binder<E>{

	public static <T> Map<String, Object> getFields(T p) {
		Map<String, Object> values = new HashMap<String, Object>();
		Field [] fs = p.getClass().getDeclaredFields();
		for (Field f : fs) {
			try {
				values.put(f.getName(), f.get(p));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);			
			}
		}
		return values;
	}

	public E bindTo(Class<E> klass, Iterable<Entry<String, Object>> pairs, SetterFinder...finders) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		E res = klass.newInstance();
		Method [] ms = klass.getMethods();
		Field[] fs = klass.getFields();
		Iterable<Member> members = Iters.merge(Member.class, asList(fs), asList(ms));
		for (Entry<String, Object> e : pairs) {
			for (SetterFinder f : finders) {
				Setter s = f.get(members, e.getKey());
				if(s != null){
					s.update(res, e.getValue());
					break;
				}
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
