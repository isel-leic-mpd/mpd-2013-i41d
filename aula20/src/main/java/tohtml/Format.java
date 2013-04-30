package tohtml;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Format {
	Class<? extends HtmlFormatter<?>> value();
}
