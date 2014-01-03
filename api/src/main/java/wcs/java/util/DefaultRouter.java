package wcs.java.util;

import static wcs.Api.*;

import java.util.List;
import java.util.StringTokenizer;

import wcs.api.Arg;
import wcs.api.Call;
import wcs.api.Env;
import wcs.api.Id;
import wcs.api.Log;
import wcs.api.URL;

/**
 * Default router
 * 
 * It routes strings in the form /Something as page with name Something
 * 
 * It routes strings in the form /Type/name as assets of type Site_Type with
 * name Something'
 * 
 * Hence it assumes that all the type names starts with the site name.
 * 
 * @author msciab
 * 
 */
public class DefaultRouter extends wcs.java.Router {

	final static Log log = Log.getLog(DefaultRouter.class);

	private String site;

	public DefaultRouter(String site) {
		init(site);
	}

	public DefaultRouter() {
	}

	public void init(String site) {
		this.site = site;
	}

	@Override
	public Call route(Env e, URL url) {
		if (log.debug())
			log.debug("url=%s", url);

		// split the token
		String c = null;
		String name = null;

		StringTokenizer st = url.getPathTokens();
		switch (st.countTokens()) {
		case 0: // example: http://yoursite.com
			// look for the home page
			c = "Page";
			name = "Home";
			break;

		case 1: // example: http://yoursite.com/Welcome
			// look for a named page
			c = "Page";
			name = st.nextToken();
			break;

		case 2: // example: http://yoursite/Article/About
			// the following assume all the asset types
			// have the same prefix as the site name
			c = site + "_" + st.nextToken();
			name = st.nextToken();
			break;

		// unknown path
		default: // example: http://yoursite/service/action/parameter"
			c = null;
			break;
		}

		// path not split in pieces
		if (c == null || name == null) {
			if (log.debug())
				log.debug("path not found");
			return call("Wrapper",
					arg("error", "Path not found: " + url.getPath()));
		}

		// resolve the name to an id
		List<Id> list = e.find(c, arg("name", name));
		if (list.size() > 0) {
			// found
			if (log.debug())
				log.debug("calling Wrapper c=%s cid=%s", list.get(0).c,
						list.get(0).cid.toString());
			return call("Wrapper", //
					arg("c", list.get(0).c), //
					arg("cid", list.get(0).cid.toString()));
		} else {
			// not found
			String error = "Asset not found: type:" + c + " name:" + name;
			return call("Wrapper", arg("error", error));
		}
	}

	/**
	 * Create a link with just the page name
	 * 
	 * Special case: the home page, normalized to just the void string
	 */
	@Override
	public String link(Env e, Id id, Arg... args) {
		String name = e.getAsset(id).getName();
		if (id.c.equals("Page"))
			// home page
			if (name.equals("Home"))
				return "";
			else
				return "/" + name;
		else
			// assuming all the types starts with Site name _, remove the prefix
			return "/" + id.c.substring(site.length() + 1) + "/" + name;
	}

}
