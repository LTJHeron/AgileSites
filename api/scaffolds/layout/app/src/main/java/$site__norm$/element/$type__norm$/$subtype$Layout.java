package $site;format="normalize"$.element.$type;format="normalize"$;

import wcs.java.Env;
import wcs.java.Element;
import wcs.java.Template;
import wcs.java.Asset;
import wcs.java.AssetSetup;
import wcs.java.Picker;

public class $subtype$Layout extends Element {

	public static AssetSetup setup() {
		
		return new Template("$type$", "$prefix$$subtype$Layout", 
			Template.LAYOUT, // change template type here
			"$subtype$", $site;format="normalize"$.element.$type;format="normalize"$.$subtype$Layout.class) //
			.cache("false", "false") // change caching here
			.description("Layout for type $type$ $subtype$");
	}

	@Override
	public String apply(Env e) {
		Asset a = e.getAsset();
		Picker html = Picker.load("/$site;format="normalize"$/simple.html" , "#content");		
		html.replace("#title", a.getName());
		html.replace("#subtitle", a.getDescription());
		return html/*.dump(log)*/.html();
	}

}
