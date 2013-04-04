package app;

import static utils.Iters.find;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

import utils.Predicate;

public class FieldSetterWithValidationFinder implements SetterFinder{

	@Override
	public Setter get(Iterable<? extends Member> members, final String memberName) {
		Field f = (Field) find(members, new Predicate<Member>() {
			@Override
			public boolean eval(Member e) {
				if((e instanceof Field) && (e.getName().equalsIgnoreCase(memberName))) 
					return true;
				return false;
			}
		});
		if(f != null){
			Validator v = f.getAnnotation(Validator.class);
			final Setter setter = new FieldSetter(f); 
			if(v != null){
				return new SetterValidation(v, setter);
		} else
			return setter;
		}
		return null;
	}
}
