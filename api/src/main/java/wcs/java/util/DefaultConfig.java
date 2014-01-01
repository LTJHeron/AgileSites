package wcs.java.util;

import wcs.api.Log;
import wcs.java.Config;

/**
 * Default configuration
 * 
 * It maps types to attribute type looking at the type name
 * 
 * It type is a Page, then typeAttribute is PageAttribute
 * 
 * If it contains an "_", remove the suffix and add _A
 * 
 * E.g. MySite_Category maps to MySite_A
 * 
 * @author msciab
 * 
 */
public class DefaultConfig extends Config implements wcs.api.Config {

	final static Log log = Log.getLog(DefaultConfig.class);

	private String site;

	public DefaultConfig(String site) {
		this.site = site;
	}

	@Override
	public String getAttributeType(String type) {
		if (type == null)
			return null;

		// simple configuration for Pages
		if (type.equals("Page"))
			return "PageAttribute";

		int pos = type.indexOf("_");
		if (pos != -1)
			return type.substring(0, pos) + "A";

		log.warn("cannot map attribute type for %s", type);
		return null;

	}

	@Override
	public String getSite() {
		return site;
	}

}
