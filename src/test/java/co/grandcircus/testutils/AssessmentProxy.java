package co.grandcircus.testutils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import co.grandcircus.Assessment;

public class AssessmentProxy {

	private static Class<?> target = Assessment.class;

	@SuppressWarnings("unchecked")
	private static <T> T invoke(Class<T> returnType, String name, Class<?>[] params, Object... args) {
		try {
			Method method = target.getDeclaredMethod(name, params);
			method.setAccessible(true); // Even if private, we can call the method.
			assertTrue(Modifier.isStatic(method.getModifiers()), "Method is static");
			assertEquals(returnType, method.getReturnType());

			return (T) method.invoke(null, args);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public static double calculateSubtotal(int burgers, int sodas) {
		return invoke(double.class, "calculateSubtotal", new Class<?>[] { int.class, int.class }, burgers, sodas);
	}

}