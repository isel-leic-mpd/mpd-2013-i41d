package binder;

public class SetterValidation implements Setter {

	final IValidation valid;
	final Setter setter;
	
	
	public SetterValidation(Validator v, Setter setter) {
		this.setter = setter;
		try {
			valid = v.value().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Object target, Object value) {
		if(valid.validate(value))
			setter.update(target, value);
	}

}
