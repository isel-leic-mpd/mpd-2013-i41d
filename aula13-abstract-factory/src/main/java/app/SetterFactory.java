package app;

import java.lang.reflect.Member;

/**
 * @uml.dependency   supplier="app.Setter"
 */
public interface SetterFactory {
	public Setter makeSetter(Member m);
}
