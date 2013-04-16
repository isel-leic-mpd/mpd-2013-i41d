package tohtml;

import java.text.SimpleDateFormat;
import java.util.Date;

import tohtml.elements.HtmlTextElement;

public class DefaultHtmlFormatter implements HtmlFormatter{

	
	final static SimpleDateFormat dtformat = new SimpleDateFormat("dd-MMM-yyyy");
	
	
	@Override
	public HtmlElement format(String name, Object val) {
		if(val instanceof Iterable){
			// listar por bullets
			return new HtmlTextElement("p", name);
		}
		if(val instanceof Date){
			return new HtmlTextElement("p", name + ": " + dtformat.format(val));
		}else 
			return new HtmlTextElement("p", name + ": " + val);	

	}

}
