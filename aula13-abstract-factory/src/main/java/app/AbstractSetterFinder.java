package app;

import java.lang.reflect.Member;

import utils.Iters;
import utils.Predicate;

/**
 * @author  mcarvalho
 */
public abstract class AbstractSetterFinder implements SetterFinder{
		
	/** 
	 * @uml.property name="factory"
	 * @uml.associationEnd aggregation="shared"
	 */
	final SetterFactory factory;
	
	public AbstractSetterFinder(SetterFactory factory) {
		this.factory = factory;
	}

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
			return factory.makeSetter(m);
		return null;
	}
	
	protected abstract boolean evalMember(Member e, String memberName) ;
}
