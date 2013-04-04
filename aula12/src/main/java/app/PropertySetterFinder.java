package app;

import static utils.Iters.find;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

import utils.Predicate;

public class PropertySetterFinder implements SetterFinder{

	@Override
	public Setter get(Iterable<? extends Member> members, final String memberName) {
		Method prop = (Method) find(members, new Predicate<Member>() {
			@Override
			public boolean eval(Member e) {
				if((e instanceof Method) && (e.getName().equalsIgnoreCase("set" + memberName))) 
					return true;
				return false;
			}
		});
		if(prop != null) 
			return new PropertySetter(prop);
		return null;
	}

}
