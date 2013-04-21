package demo;

import wcs.java.Insite;

public class Config extends wcs.java.Config {

	// Configure
	
	public Insite Title = new Insite();
	//.editor("dojotext").mode("text");

	public Insite Subtitle = new Insite();
	//.editor("fckeditor").mode("html");

	
	// configure sitename and attribute type
	public static final String site = "Demo";
	
	@Override
	public String getSite() {
		return site;
	}

	@Override
	public String getAttributeType(String type) {
		if (type == null)
			return null;

		// simple configuration for Pages
		if (type.equals("Page"))
			return "PageAttribute";

		return null;
	}

}