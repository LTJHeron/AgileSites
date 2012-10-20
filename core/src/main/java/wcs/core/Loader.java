package wcs.core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Load a jar using a networked classloader. It caches the classloader and check
 * if the underlying jar file changes then reload it.
 * 
 * @author msciab
 * 
 */
public class Loader {

	java.util.logging.Logger log = java.util.logging.Logger
			.getLogger(Loader.class.getCanonicalName());

	private long jarTimestamp = 0;
	private URLClassLoader ucl;
	private ClassLoader mycl = getClass().getClassLoader();
	private File jar;

	/**
	 * Build a loader
	 * 
	 * @param file
	 */
	public Loader(File file) {
		jar = file;
	}

	/**
	 * Load jar if modified
	 * 
	 * @param jar
	 * @return
	 * @throws MalformedURLException
	 */
	public ClassLoader loadJar() throws MalformedURLException {

		if (jar == null) {

			WCS.debug("[Loader]: no jar specified");
			return mycl;
		}

		if (!jar.exists()) {
			WCS.debug("[Loader]: jar not found!!!");
			return mycl;
		}

		// System.out.println("curTimestamp=" + curTimestamp);
		// System.out.println("jarTimestamp=" + jarTimestamp);

		// reloading jar if modified
		long curTimestamp = jar.lastModified();
		if (curTimestamp > jarTimestamp) {
			URL url = jar.toURI().toURL();

			WCS.debug("[Loader] reloading " + url);

			jarTimestamp = curTimestamp;
			ucl = new URLClassLoader(new URL[] { url }, mycl);

		}
		return ucl;

	}

}
