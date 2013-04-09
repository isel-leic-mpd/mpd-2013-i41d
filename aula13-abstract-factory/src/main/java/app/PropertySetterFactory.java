package app;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class PropertySetterFactory implements SetterFactory{

	@Override
	public Setter makeSetter(Member m) {
		return new PropertySetter((Method) m);
	}

}
