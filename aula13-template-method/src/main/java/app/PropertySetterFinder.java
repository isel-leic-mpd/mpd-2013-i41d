package app;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class PropertySetterFinder extends AbstractPropertySetterFinder{

	@Override
	protected Setter makeSetter(Member m) {
		return new PropertySetter((Method) m);
	}

}
