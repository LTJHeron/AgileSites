package wcs.java.util;

import org.atteo.evo.classindex.IndexAnnotated;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AddIndex {
	String value();
}
