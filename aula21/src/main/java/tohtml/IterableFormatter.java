package tohtml;

import java.text.SimpleDateFormat;
import java.util.Date;

import tohtml.elements.HtmlDiv;
import tohtml.elements.HtmlTextElement;
import tohtml.elements.HtmlUl;

public class IterableFormatter implements HtmlFormatter<Iterable>{
	
	final static SimpleDateFormat dtformat = new SimpleDateFormat("dd-MMM-yyyy");
	
	@Override
	public HtmlElement format(String name, Iterable val) {
			HtmlDiv div = new HtmlDiv();
			div.addElement(new HtmlTextElement("p", name + ":"));
			HtmlUl ul = new HtmlUl();
			div.addElement(ul);
			for (Object elem : (Iterable) val) {
				ul.addElement(new HtmlTextElement("li", elem.toString()));
			}
			return div;
	}


	@Override
	public Class<Iterable> supportedType() {
		return Iterable.class;
	}

}
