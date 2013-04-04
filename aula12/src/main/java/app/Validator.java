package app;

public @interface Validator {
	Class<? extends IValidation> value();
}
