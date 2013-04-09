package app;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class PropertySetterFinder extends AbstractSetterFinder{

	public PropertySetterFinder(SetterFactory factory) {
		super(factory);
	}

	@Override
	protected final boolean evalMember(Member e, String memberName) {
		return (e instanceof Method) && (e.getName().equalsIgnoreCase("set" + memberName));
	}
	
}
