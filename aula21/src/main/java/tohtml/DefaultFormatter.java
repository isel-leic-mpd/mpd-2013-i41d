package tohtml;

import java.text.SimpleDateFormat;
import java.util.Date;

import tohtml.elements.HtmlDiv;
import tohtml.elements.HtmlTextElement;
import tohtml.elements.HtmlUl;

public class DefaultFormatter implements HtmlFormatter<Object>{

	
	@Override
	public HtmlElement format(String name, Object val) {
			return new HtmlTextElement("p", name + ": " + val);	

	}
	@Override
	public Class<Object> supportedType() {
		return Object.class;
	}

}
