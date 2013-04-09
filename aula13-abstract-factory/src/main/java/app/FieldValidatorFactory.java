package app;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

public class FieldValidatorFactory implements SetterFactory{

	@Override
	public Setter makeSetter(Member m) {
		Field f = (Field) m;
		Validator v = f.getAnnotation(Validator.class);
		final Setter setter = new FieldSetter(f); 
		if(v != null){
			return new SetterValidation(v, setter);
		} else
			return setter;
	}

}
