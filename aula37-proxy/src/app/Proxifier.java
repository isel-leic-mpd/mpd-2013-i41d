package app;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Proxifier {

	@SuppressWarnings("unchecked")
	public static <T> T of(Class<T> k, final Object target, String method) throws NoSuchMethodException, SecurityException {
		
		Class<?>[] paramTypes = k.getDeclaredMethods()[0].getParameterTypes();
		final Method m = target.getClass().getDeclaredMethod(method, paramTypes);
		
		return (T) Proxy.newProxyInstance(k.getClassLoader(),
                new Class[] { k },
                new InvocationHandler(){

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						return m.invoke(target, args);
					}
			
		});
		
	}

}
