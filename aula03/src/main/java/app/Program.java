package app;

import java.util.Map;

import model.PersonDto;

public class Program {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		PersonDto p = new PersonDto("Ze Manel", "2348236", 23);
		Map<String, Object> fields = Binder.getFields(p);
		assert p.name.equals(fields.get("name"));
		assert p.id.equals(fields.get("id"));
		assert p.age == ((Integer) fields.get("age")).intValue();
	}

}
