package $site;format="normalize"$.test;

import $site;format="normalize"$.element.Wrapper;
import wcs.java.util.TestElement;
import org.junit.Before;
import org.junit.Test;
import wcs.java.util.AddIndex;

// this test must be run by the test runner
@AddIndex("$site;format="normalize"$/tests.txt")
public class WrapperTest extends TestElement {

	Wrapper it;

	@Before
	public void setUp() {
		it = new Wrapper();
	}

	@Test
	public void test() {
		parse(it.apply(env()));
		assertAttr("render-callelement", "error", "Asset not found");
	}

}