package app;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

public class FieldSetterFactory implements SetterFactory{

	@Override
	public Setter makeSetter(Member m) {
		Field f = (Field) m;
		return new FieldSetter(f); 
	}

}
