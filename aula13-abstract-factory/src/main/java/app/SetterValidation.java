package app;

/**
 * @author  mcarvalho
 */
public class SetterValidation implements Setter {

	/**
	 * @uml.property  name="valid"
	 * @uml.associationEnd  
	 */
	final IValidation valid;
	/** 
	 * @uml.property name="parser"
	 * @uml.associationEnd aggregation="shared"
	 */
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
