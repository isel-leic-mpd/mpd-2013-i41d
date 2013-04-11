package tohtml;

import java.util.Map;

import tohtml.elements.HtmlBody;
import tohtml.elements.HtmlHead;
import tohtml.elements.HtmlRoot;
import tohtml.elements.HtmlTextElement;
import binder.Binder;

public class HtmlTransformer {
	
	final HtmlRoot root;
	
	public HtmlTransformer(Object src){
		root = new HtmlRoot();
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
		HtmlTextElement h1 = new HtmlTextElement("h1", src.getClass().getSimpleName());
		b.addElement(h1);
		b.addElement(new HtmlSingleElement("hr"));
		for (Map.Entry<String, Object> p : Binder.getFields(src).entrySet()) {
			b.addElement(new HtmlTextElement("p", p.getKey() + ": " + p.getValue()));	
		}
		root.addElement(b);
	}
	
	public HtmlComposedElement getRoot(){
		return root;
	}
}
