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
		
		
		while(input.hasNext()) {
			t = lex.getNextToken();
			System.out.println(t.toString());
		}
		
		/*
		for(int i =0; i < 40; i++) {
			t = lex.getNextToken();
			System.out.println(t.toString());
			
		}*/
	}
}
