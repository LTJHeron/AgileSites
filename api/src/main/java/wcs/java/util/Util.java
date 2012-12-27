package wcs.java.util;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fatwire.assetapi.data.AssetData;
import com.fatwire.assetapi.data.AttributeDataImpl;
import com.fatwire.assetapi.data.BlobObject;
import com.fatwire.assetapi.data.BlobObjectImpl;
import com.fatwire.assetapi.def.AttributeTypeEnum;
import com.openmarket.xcelerate.asset.AttributeDefImpl;

/**
 * Utility and support data
 * 
 * @author msciab
 * 
 */
public class Util {

	// private static Log log = new Log(Util.class);

	/**
	 * Qualified Id class
	 * 
	 * @author msciab
	 * 
	 */
	public static class Id {
		public Id(String type, Long id) {
			this.type = type;
			this.id = id;
		}

		public Long id;
		public String type;

		public String toString() {
			return type + ":" + id;
		}
	}

	/**
	 * Arg class
	 * 
	 * @author msciab
	 * 
	 */
	public static class Arg {
		public Arg(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String name;
		public String value;
	}

	/**
	 * Shortcut to create an id, to be used with a static import
	 * 
	 * @param type
	 * @param id
	 * @return
	 */
	public static Id id(String type, long id) {
		return new Id(type, id);
	}

	/**
	 * Shortcut to create an arg, to be used with a static import
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public static Arg arg(String name, String value) {
		return new Arg(name, value);
	}

	/**
	 * Create a list from multiple arguments
	 * 
	 * @param objs
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List list(Object... objs) {
		List result = new ArrayList();
		for (Object obj : objs)
			result.add(obj);
		// System.out.println(result);
		return result;
	}

	/**
	 * Create a list from multiple arguments
	 * 
	 * @param objs
	 * @return
	 */
	public static List<String> listString(String... objs) {
		List<String> result = new ArrayList<String>();
		for (String obj : objs)
			result.add(obj);
		// System.out.println(result);
		return result;
	}

	/**
	 * Create a list from multiple arguments
	 * 
	 * @param objs
	 * @return
	 */
	public static List<AssetData> listData(AssetData... objs) {
		List<AssetData> result = new ArrayList<AssetData>();
		for (AssetData obj : objs)
			result.add(obj);
		// System.out.println(result);
		return result;
	}

	/**
	 * from an array to a list
	 * 
	 * @param array
	 * @return
	 */
	public static List<Object> array2list(Object[] array) {
		List<Object> ls = new ArrayList<Object>();
		for (Object s : array)
			ls.add(s);
		return ls;
	}

	/**
	 * from an array to a list
	 * 
	 * @param array
	 * @return
	 */
	public static List<String> array2listString(String[] array) {
		List<String> ls = new ArrayList<String>();
		for (String s : array)
			ls.add(s);
		return ls;
	}

	/*
	 * public static String adump(Object o) { return o.toString(); }
	 * 
	 * public static String adump(AttributeData data) { Object d =
	 * data.getData(); if (d == null) return "null"; else if(d instanceof List)
	 * { StringBuffer sb = new StringBuffer("["); for(Object o: (List)d) {
	 * sb.append(adump(o)).append(","); } sb.setLength(sb.length()-1);
	 * sb.append("]"); } return d.toString(); }
	 * 
	 * / ** Dump Asset data.. / public static String dump(AssetData data) {
	 * StringBuffer sb = new StringBuffer("\n-----\n"); for (String attr :
	 * data.getAttributeNames()) { sb.append(attr); Object o =
	 * data.getAttributeData(attr); //
	 * sb.append("(").append(o.getClass()).append(")"); sb.append("="); String v
	 * = "???";// o.toString(); if (o != null && o instanceof AttributeData) {
	 * AttributeData a = (AttributeData) o; Object oo = a.getData(); if (oo
	 * instanceof com.fatwire.assetapi.data.BlobObjectImpl) { BlobObject b =
	 * (BlobObject) oo; if (b != null && b.getFilename() != null) v = "BLOB(" +
	 * b.getFilename() + ")"; else v = "BLOB(null)"; } else if (a != null && a
	 * instanceof AttributeData) { v = adump(a); } else if (a != null && oo !=
	 * null) { v = oo.toString(); } else v = "null"; sb.append(v).append("\n");
	 * } else sb.append("BOH!").append("\n"); } sb.append("------\n"); return
	 * sb.toString(); }
	 */

	public static String dump(AssetData data) {
		String s = new com.thoughtworks.xstream.XStream().toXML(data);
		try {
			FileWriter fw = new FileWriter("/tmp/out.xml", true);
			fw.write(s);
			fw.close();
		} catch (Exception ex) {
		}

		return s;
	}

	private static AttributeDefImpl def(String name, AttributeTypeEnum type) {
		return new AttributeDefImpl(name, type);
	}

	/**
	 * String attribute
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public static AttributeDataImpl attrString(String name, String value) {
		AttributeTypeEnum type = AttributeTypeEnum.STRING;
		return new AttributeDataImpl(def(name, type), name, type, value);
	}

	/**
	 * Blob Attribute
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public static AttributeDataImpl attrBlob(String name, BlobObject value) {
		AttributeTypeEnum type = AttributeTypeEnum.URL;
		return new AttributeDataImpl(def(name, type), name, type, value);
	}

	/**
	 * Structure default arguments
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static AttributeDataImpl attrStructKV(String name, String value) {
		HashMap map = new HashMap<String, Object>();
		map.put("name", attrString("name", name));
		map.put("value", attrString("value", value));
		return attrStruct("Structure defaultarguments", map);
	}

	/**
	 * Struct (map) attribute
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public static AttributeDataImpl attrStruct(String name,
			Map<String, Object> value) {
		AttributeTypeEnum type = AttributeTypeEnum.STRUCT;
		return new AttributeDataImpl(def(name, type), name, type, value);
	}

	/**
	 * Array (list) attribute
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public static AttributeDataImpl attrArray(String name, Object... value) {
		AttributeTypeEnum type = AttributeTypeEnum.ARRAY;
		System.out.println("#####" + array2list(value));
		return new AttributeDataImpl(def(name, type), name, type,
				(Object) array2list(value));

	}

	/**
	 * Blob Attribute
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public static AttributeDataImpl attrBlob(String name, String folder,
			String filename, String value) {
		AttributeTypeEnum type = AttributeTypeEnum.URL;
		return new AttributeDataImpl(def(name, type), name, type,
				new BlobObjectImpl(filename, folder, value.getBytes()));

	}

	public static String tmpVar() {
		return "_TMP_" + System.currentTimeMillis() + "_VAR_";
	}
	

}