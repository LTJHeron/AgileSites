package wcs.core;

import static org.junit.Assert.assertEquals;
import static wcs.Api.*;
import wcs.api.Content;
import wcs.api.Model;
import wcs.api.Picker;

import org.junit.Test;


public class PickerTest {

	Picker none = new Picker("None");
	Picker one1 = new Picker("{{First}} Some");
	Picker one2 = new Picker("Some {{First}}");
	Picker one3 = new Picker("Some {{First}} Else");
	Picker two =  new Picker("Some {{First}} then {{Second}}");
	Picker two2 = new Picker("Some {{First}} then {{Second}} then {{First}}");

	Content c0 = new Model();
	Content c1 = model(arg("First", "[first]"));
	Content c2 = model(arg("Second", "[second]"));
	Content c3 = model(arg("First", "[firstbis]"), arg("Second", "[secondbis]"));

	private String log(String s) {
		s = s.replaceAll("(?s).*<body>((?s).*)</body>(?s).*", "$1").trim();
		// System.out.println(s);
		return s;
	}

	@Test
	public void testReplace() {
		assertEquals(log(none.html(c1)), "None");
		System.out.println("First=" + c1.getString("First"));
		System.out.println("First=" + c1.exists("First"));
		assertEquals(log(one1.html(c1)), "[first] Some");
		assertEquals(log(one2.html(c1)), "Some [first]");
		assertEquals(log(one3.html(c1)), "Some [first] Else");
		assertEquals(log(one1.html(c0)), "{{First}} Some");
		assertEquals(log(one2.html(c0)), "Some {{First}}");
		assertEquals(log(one3.html(c0)), "Some {{First}} Else");
		assertEquals(log(two.html(c0)), "Some {{First}} then {{Second}}");
		assertEquals(log(two.html(c1)), "Some [first] then {{Second}}");
		assertEquals(log(two.html(c0, c1)), "Some [first] then {{Second}}");
		assertEquals(log(two.html(c0, c1, c2)), "Some [first] then [second]");
		assertEquals(log(two.html(c0, c1, c2, c3)),
				"Some [first] then [second]");
		assertEquals(log(two.html(c2, c1, c0)), "Some [first] then [second]");
		assertEquals(log(two.html(c3, c2, c1, c0)),
				"Some [firstbis] then [secondbis]");
		assertEquals(log(two2.html(c0)),
				"Some {{First}} then {{Second}} then {{First}}");
		assertEquals(log(two2.html(c1)),
				"Some [first] then {{Second}} then [first]");
		assertEquals(log(two2.html(c0, c1)),
				"Some [first] then {{Second}} then [first]");
		assertEquals(log(two2.html(c0, c1, c2)),
				"Some [first] then [second] then [first]");
		assertEquals(log(two2.html(c0, c1, c2, c3)),
				"Some [first] then [second] then [first]");
		assertEquals(log(two2.html(c1, c2, c0)),
				"Some [first] then [second] then [first]");
		assertEquals(log(two2.html(c3, c2, c1, c0)),
				"Some [firstbis] then [secondbis] then [firstbis]");
		assertEquals(log(two2.html(c3)),
				"Some [firstbis] then [secondbis] then [firstbis]");
	}
}
