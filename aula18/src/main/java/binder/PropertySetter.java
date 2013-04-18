package binder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PropertySetter implements Setter {

	final Method prop;
	
	public PropertySetter(Method prop) {
		this.prop = prop;
	}

	@Override
	public void update(Object target, Object value) {
		try {
			prop.invoke(target, value);
		} catch (IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
