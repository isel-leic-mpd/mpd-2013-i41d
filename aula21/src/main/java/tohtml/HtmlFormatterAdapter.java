package tohtml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HtmlFormatterAdapter implements HtmlFormatter {

	public HtmlFormatterAdapter(Format fAnnot, Object val, Object src) {
		try {
			supportedClass = src.getClass();
			target = src.getClass().getDeclaredMethod(fAnnot.formatter(), String.class, val.getClass());
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	final Method target;
	final Class supportedClass;
	
	public HtmlElement format(String name, Object val) {
		try {
			target.setAccessible(true);
			return (HtmlElement) target.invoke(null, name, val);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	public Class supportedType() {return supportedClass;}
}
