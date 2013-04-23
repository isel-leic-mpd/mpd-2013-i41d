package tohtml;

import java.util.Map;

import tohtml.elements.HtmlBody;
import tohtml.elements.HtmlHead;
import tohtml.elements.HtmlRoot;
import tohtml.elements.HtmlTextElement;
import utils.Iters;
import utils.Predicate;
import binder.Binder;

/**
 * @author        mcarvalho
 * @uml.dependency   supplier="tohtml.HtmlSingleElement"
 * @uml.dependency   supplier="tohtml.HtmlComposedElement"
 * @uml.dependency   supplier="tohtml.elements.HtmlTextElement"
 */
public class HtmlTransformer {
	
	final HtmlFormatter<?>[] formatters;
	
	public HtmlTransformer(HtmlFormatter<?>...formatters){
		this.formatters = formatters;
	}

	public HtmlElement getRoot(Object src){
		HtmlRoot root = new HtmlRoot();		
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
			final Object val = p.getValue();
			HtmlFormatter f = Iters.find(formatters, new Predicate<HtmlFormatter<?>>() {
				@Override
				public boolean eval(HtmlFormatter<?> e) {
					// TODO TPC...
					return false;
				}
			});
			b.addElement(
					f.format(p.getKey(), 
					val));	
		}
		root.addElement(b);
		return root;
	}
}
