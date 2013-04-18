package binder;

import java.lang.reflect.Member;

public interface SetterFinder{ 
	  Setter get(Iterable<? extends Member> members, String memberName); 
} 