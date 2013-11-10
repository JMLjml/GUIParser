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
