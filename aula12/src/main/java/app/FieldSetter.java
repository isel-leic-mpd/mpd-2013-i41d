package app;

import java.lang.reflect.Field;

public class FieldSetter implements Setter {

	final Field f;
	
	public FieldSetter(Field f) {
		this.f = f;
		f.setAccessible(true);
	}

	@Override
	public void update(Object target, Object value) {
		try {
			f.set(target, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
