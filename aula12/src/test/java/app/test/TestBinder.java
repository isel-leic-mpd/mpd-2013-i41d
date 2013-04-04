package app.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;

import app.Binder;
import app.FieldSetterFinder;
import app.PropertySetterFinder;
import app.test.model.Account;
import app.test.model.Person;
import app.test.model.PersonDto;

public class TestBinder extends TestCase{

	public void test_bind_to_person_fields_and_properties() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		/*
		 * Arrange
		 */
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("name", "Laurinda");
		fields.put("age", 67);
		fields.put("nationality", "portuguese");
		fields.put("id", "63576315745123");
		
		/*
		 * Act
		 */
		Person p = new Binder<Person>().bindTo(Person.class, fields.entrySet(), new PropertySetterFinder(), new FieldSetterFinder());
		/*
		 * Assert
		 */
		Assert.assertEquals(fields.get("name"), p.getName());
		Assert.assertEquals(fields.get("age"), p.getAge());
		Assert.assertEquals(fields.get("id"), p.id);
	}
	
	public void test_bind_to_person_fields() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		/*
		 * Arrange
		 */
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("name", "Laurinda");
		fields.put("age", 67);
		fields.put("id", "63576315745123");
		/*
		 * Act
		 */
		PersonDto p = new Binder<PersonDto>().bindTo(PersonDto.class, fields.entrySet(), new FieldSetterFinder());
		/*
		 * Assert
		 */
		Assert.assertEquals(fields.get("name"), p.name);
		Assert.assertEquals(fields.get("age"), p.age);
		Assert.assertEquals(fields.get("id"), p.id);
	}
	
	public void test_binder_with_person() throws IllegalArgumentException, IllegalAccessException{
		PersonDto p = new PersonDto("Ze Manel", "2348236", 23);
		Map<String, Object> fields = Binder.getFields(p);
		Assert.assertEquals(p.name, fields.get("name"));
		Assert.assertEquals(p.id, fields.get("id"));
		Assert.assertEquals(p.age, ((Integer)fields.get("age")).intValue());
	}
	
	public void test_binder_with_account() throws IllegalArgumentException, IllegalAccessException{
		Account acc = new Account(2343, 3231, "Laurita", "2346234");
		Map<String, Object> fields = Binder.getFields(acc);
		Assert.assertEquals(acc.balance, fields.get("balance"));
		Assert.assertEquals(acc.nr, fields.get("nr"));
	}
	
	
}
