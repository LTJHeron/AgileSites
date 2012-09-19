package wcs.boot;

import java.io.File;

public class WCS {
	static Dispatcher dispatcher = null;

	public static String dispatch(COM.FutureTense.Interfaces.ICS ics) {

		if (dispatcher == null) {
			File jar = new File(ics.GetProperty("scalawcs.jar"));
			if (jar.exists()) {
				System.err.println("Loading from " + jar);
				dispatcher = new Dispatcher(jar);
			} else
				return "Not found " + jar;
		}
		return dispatcher.dispatch(ics);
	}
}
