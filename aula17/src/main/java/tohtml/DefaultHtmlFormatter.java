package tohtml;

import java.text.SimpleDateFormat;

import tohtml.elements.HtmlDiv;
import tohtml.elements.HtmlTextElement;
import tohtml.elements.HtmlUl;

public class DefaultHtmlFormatter extends AbstractHtmlFormatter{

	
	final static SimpleDateFormat dtformat = new SimpleDateFormat("dd-MMM-yyyy");
	
	@Override
	protected HtmlElement formatIterable(String name, Object val) {
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
	protected HtmlElement formatDate(String name, Object val) {
		return new HtmlTextElement("p", name + ": " + dtformat.format(val));
	}

	@Override
	protected HtmlElement formatObject(String name, Object val) {
		return new HtmlTextElement("p", name + ": " + val);	
	}

}
