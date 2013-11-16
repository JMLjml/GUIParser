/**
 * File: ParserTest.java
 * Author: John M. Lasheski
 * Date: November 15, 2013
 * Platform: Windows 8, Eclipse Juno 
 * 
 * Class: UMUC CMSC 330, Section 7981
 * Project: 1
 * 
 * JUnit test asserts that the Parser Method finished and returns true.
 */

package guibuilder;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testParse() throws IOException {
		Scanner input = new Scanner(new File("testdata.txt"));
		Lexer lex = new Lexer(input);
		Parser parser = new Parser(lex);
		
		assertTrue(parser.Parse());
	}

}
