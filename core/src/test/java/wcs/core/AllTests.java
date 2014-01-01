package wcs.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AssemblerTest.class, ModelTest.class, SequencerTest.class, PickerTest.class})
public class AllTests {

}
