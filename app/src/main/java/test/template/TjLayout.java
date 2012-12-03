package test.template;

import wcs.java.Element;
import wcs.java.Env;
import wcs.java.Picker;

public class TjLayout extends Element {

	@Override
	public String apply(Env env) {
		Picker p = new Picker("/index.html");
		p.select("head").attr("link", "href", "/cs/css/default.css");
		return p.html();
	}
}