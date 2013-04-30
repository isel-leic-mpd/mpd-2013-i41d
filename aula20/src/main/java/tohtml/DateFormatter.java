package tohtml;

import java.text.SimpleDateFormat;
import java.util.Date;

import tohtml.elements.HtmlDiv;
import tohtml.elements.HtmlTextElement;
import tohtml.elements.HtmlUl;

public class DateFormatter implements HtmlFormatter<Date>{

	
	final static SimpleDateFormat dtformat = new SimpleDateFormat("dd-MMM-yyyy");
	
	
	@Override
	public HtmlElement format(String name, Date val) {
			return new HtmlTextElement("p", name + ": " + dtformat.format(val));
	}


	@Override
	public Class<Date> supportedType() {
		return Date.class;
	}

}
