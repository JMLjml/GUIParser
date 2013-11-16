/**
 * File: AllTests.java
 * Author: John M. Lasheski
 * Date: November 15, 2013
 * Platform: Windows 8, Eclipse Juno 
 * 
 * Class: UMUC CMSC 330, Section 7981
 * Project: 1
 * 
 * JUnit test suite.
 */

package guibuilder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GUIBuilderTest.class, LexerTest.class, ParserTest.class,
		TerminalTest.class })
public class AllTests {

}
