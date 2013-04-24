package wcs.core;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Call {

	private final static String SEP = "\0";
	private final static String SEP2 = SEP + SEP;

	private Map<String, String> map = new HashMap<String, String>();
	private String name = "";

	/**
	 * Create a call with name and parameters
	 * 
	 * @param name
	 * @param args
	 */
	public Call(String name, Arg... args) {
		this.name = name;
		if (args.length > 0)
			for (Arg arg : args)
				addArg(arg.name, arg.value);
	}

	public String getName() {
		return name;
	}

	/**
	 * Return the value of a parameter, then delete it (to be used only once)
	 * 
	 * @param key
	 * @return
	 */
	public String getOnce(String key) {
		String val = map.get(key);
		map.remove(key);
		return val;
	}
	
	/**
	 * Get the value of a parameter.
	 */
	public String get(String key) {
		return map.get(key);
	}

	/**
	 * Add an argument
	 */
	public void addArg(String name, String value) {
		if (name != null && value != null)
			map.put(name, value);
	}

	private String[] voidArStr = new String[0];

	/**
	 * Keys left
	 */
	public String[] keysLeft() {
		return (String[]) map.keySet().toArray(voidArStr);
	}

	/**
	 * Decode a call encoded as a string
	 * 
	 * @param encoded
	 * @return
	 */
	public static Call decode(String encoded) {
		StringTokenizer st = new StringTokenizer(encoded, SEP);

		String name = st.nextToken();
		Call call = new Call(name);

		while (st.hasMoreTokens()) {
			try {
				String k = st.nextToken();
				String v = st.nextToken();
				// out.println(">>>" + k + "=" + v);
				call.map.put(k, v);
			} catch (Exception ex) {
			}
		}
		return call;
	}

	/**
	 * Encode a call as a string
	 * 
	 * @param name
	 * @param args
	 * @return
	 */
	public static String encode(String name, Arg... args) {
		StringBuilder sb = new StringBuilder();
		sb.append(SEP2).append(name).append(SEP);
		for (Arg arg : args) {
			if (arg.value != null)
				sb.append(arg.name).append(SEP).append(arg.value).append(SEP);
		}
		sb.append(SEP2);
		return sb.toString();
	}

	/**
	 * Encode the call as a string
	 */
	public String encode() {
		StringBuilder sb = new StringBuilder();
		sb.append(SEP2).append(name).append(SEP);
		for (Map.Entry<String, String> entry : map.entrySet())
			sb.append(entry.getKey()).append(SEP).append(entry.getValue())
					.append(SEP);
		sb.append(SEP2);
		return sb.toString();
	}

	/**
	 * Encode a call as a string
	 * 
	 * @param name
	 * @param args
	 * @return
	 */
	public static String encode(String name, List<Arg> args) {
		StringBuilder sb = new StringBuilder();
		// elements to call have the site name as a prefix
		sb.append(SEP2).append(name).append(SEP);
		for (Arg arg : args) {
			if (arg.value != null)
				sb.append(arg.name).append(SEP).append(arg.value).append(SEP);
		}
		sb.append(SEP2);
		return sb.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<").append(name.replace(':', '-')).append(" ");
		String[] keys = keysLeft();
		Arrays.sort(keys);
		for (String k : keys) {
			sb.append(k).append("=\"").append(map.get(k)).append("\" ");
		}
		if (sb.toString().endsWith(" "))
			sb.setLength(sb.length() - 1);
		sb.append("/>");
		return sb.toString();
	}
}
