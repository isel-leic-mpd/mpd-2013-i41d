package tohtml;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import tohtml.elements.HtmlBody;
import tohtml.elements.HtmlHead;
import tohtml.elements.HtmlRoot;
import tohtml.elements.HtmlTextElement;
import binder.Binder;

/**
 * @author        mcarvalho
 * @uml.dependency   supplier="tohtml.HtmlSingleElement"
 * @uml.dependency   supplier="tohtml.HtmlComposedElement"
 * @uml.dependency   supplier="tohtml.elements.HtmlTextElement"
 */
public class HtmlTransformer {
	
	/**
	 * @uml.property  name="root"
	 * @uml.associationEnd  
	 */
	final HtmlElement root;
	final HtmlFormatter formatter;
	
	public HtmlTransformer(Object src, HtmlFormatter formatter){
		root = new HtmlRoot();
		this.formatter = formatter;
		
		/*
		 * Head
		 */
		HtmlHead head = new HtmlHead();
		head.addElement(new HtmlTextElement("title", src.getClass().getSimpleName()));
		root.addElement(head);
		/*
		 * Body
		 */
		HtmlBody b = new HtmlBody();
		HtmlElement h1 = new HtmlTextElement("h1", src.getClass().getSimpleName());
		b.addElement(h1);
		b.addElement(new HtmlSingleElement("hr"));
		for (Map.Entry<String, Object> p : Binder.getFields(src).entrySet()) {
			b.addElement(formatter.format(p.getKey(), p.getValue()));	
		}
		root.addElement(b);
	}

	/**
	 * @return
	 * @uml.property  name="root"
	 */
	public HtmlElement getRoot(){
		return root;
	}
}
