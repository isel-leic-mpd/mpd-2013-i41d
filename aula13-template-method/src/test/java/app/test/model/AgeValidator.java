package app.test.model;

import app.IValidation;

public class AgeValidator implements IValidation<Integer>{

	@Override
	public boolean validate(Integer elem) {
		return elem.intValue() > 10 && elem.intValue() < 80;
	}

}
