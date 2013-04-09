package app;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

public class FieldSetterFinder extends AbstractSetterFinder{

	@Override
	protected Setter makeSetter(Member m) {
		return new FieldSetter((Field) m);
	}

	@Override
	protected boolean evalMember(Member e, String memberName) {
		return (e instanceof Field) && (e.getName().equalsIgnoreCase(memberName));
	}

}
