package keyboard.works.search.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import keyboard.works.search.SearchOperation;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Searchable {

	String[] field();
	
	SearchOperation operation();
	
	boolean nullable() default false;
	
}
