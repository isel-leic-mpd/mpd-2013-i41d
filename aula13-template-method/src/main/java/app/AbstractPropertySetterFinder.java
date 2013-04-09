package app;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

public abstract class AbstractPropertySetterFinder extends AbstractSetterFinder{

	@Override
	protected final boolean evalMember(Member e, String memberName) {
		return (e instanceof Method) && (e.getName().equalsIgnoreCase("set" + memberName));
	}
	
}
