package app;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

public class FieldSetterFinder extends AbstractSetterFinder{

	public FieldSetterFinder(SetterFactory factory) {
		super(factory);
	}

	@Override
	protected boolean evalMember(Member e, String memberName) {
		return (e instanceof Field) && (e.getName().equalsIgnoreCase(memberName));
	}

}
