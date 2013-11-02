package gui.builder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GUIBuilderTest.class, LexerTest.class, ParserTest.class,
		TokenTest.class })
public class AllTests {

}
