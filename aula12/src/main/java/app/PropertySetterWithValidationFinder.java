package app;

import static utils.Iters.find;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import utils.Predicate;

public class PropertySetterWithValidationFinder implements SetterFinder{

	@Override
	public Setter get(Iterable<? extends Member> members, final String memberName) {
		final Method prop = (Method) find(members, new Predicate<Member>() {
			@Override
			public boolean eval(Member e) {
				if((e instanceof Method) && (e.getName().equalsIgnoreCase("set" + memberName))) 
					return true;
				return false;
			}
		});
		Validator v = prop.getAnnotation(Validator.class);
		final PropertySetter setter = new PropertySetter(prop);
		if(v != null){
			return new SetterValidation(v, setter);
		}else {
			return setter;
		}
				
	}

}
