package tohtml;

import java.lang.reflect.Field;
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

	public HtmlElement getRoot(Object src) throws IllegalArgumentException, IllegalAccessException, InstantiationException{
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
		for (Field field : src.getClass().getDeclaredFields()) {
			final Object val = field.get(src);
			Format fAnnot = field.getAnnotation(Format.class);
			HtmlFormatter f = null;
			if(fAnnot != null){
				f = fAnnot.value().newInstance();
			}
			else{
				f = Iters.find(formatters, new Predicate<HtmlFormatter<?>>() {
					@Override
					public boolean eval(HtmlFormatter<?> e) {
						return e.supportedType().isInstance(val);
						// return e instanceof DefaultFormatter; => Solução errada!!!!
					}
				});
			}
			b.addElement(
					f.format(field.getName(), 
					val));	
		}
		root.addElement(b);
		return root;
	}
}
