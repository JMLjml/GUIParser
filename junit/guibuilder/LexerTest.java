package guibuilder;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class LexerTest {

	@Test
	public void testgetNextToken() throws IOException, FileNotFoundException {
		Scanner input = new Scanner(new File("testdata.txt"));
		Lexer lex = new Lexer(input);
		
		Terminal t;
		
		String[] lexemes = {"Window", "Calculator", "OpenParen", "200", "Coma", "200", "CloseParen",
				"Layout", "Flow", "Colon", "TextField", "20", "SemiColon", "Panel", "Layout", "Grid", "OpenParen",
				"4", "Coma", "3", "Coma", "5", "Coma", "5", "CloseParen", "Colon", "Button", "7",
				"SemiColon", "Button", "8", "SemiColon", "Button", "9", "SemiColon", "Button", "4", "SemiColon",
				"Button", "5", "SemiColon", "Button", "6", "SemiColon", "Button", "1", "SemiColon", "Button",
				"2", "SemiColon", "Button", "3", "SemiColon", "Label", "", "SemiColon", "Button", "0",
				"SemiColon", "End", "SemiColon", "End"};
			
		int i = 0;
		
		while(input.hasNext()) {
			
			t = lex.getNextToken();
			
			//assert that token we get back matches the expected result, print the conflicting strings if not
			assertTrue(lexemes[i] + " didn't match " + t.toString(), t.toString().equals(lexemes[i]));
			i++;
		}
	}
}
