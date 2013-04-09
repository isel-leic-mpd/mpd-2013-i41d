package app;

import java.lang.reflect.Member;

import utils.Iters;
import utils.Predicate;

public abstract class AbstractSetterFinder implements SetterFinder{
		
	@Override
	public final Setter get(Iterable<? extends Member> members, final String memberName) {
		Member m = Iters.find(members, new Predicate<Member>() {
			@Override
			public boolean eval(Member e) {
				if(evalMember(e, memberName)) 
					return true;
				return false;
			}
		});
		if(m != null)
			return makeSetter(m);
		return null;
	}

	protected abstract Setter makeSetter(Member m);
	
	protected abstract boolean evalMember(Member e, String memberName) ;
}
