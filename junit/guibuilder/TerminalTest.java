package guibuilder;

import static org.junit.Assert.*;

//import org.junit.Before;
import org.junit.Test;


public class TerminalTest {
	
	//@Before
	

	@Test
	public void testTerminal() {
		fail("Not yet implemented");
	}
	
	
	//Test that the equals function is behaving correctly
	//Also test the special Terminal cases for NUMBER and STRING
	@Test
	public void testequals() {
		Terminal t1 = new Terminal(Token.Button);
		Terminal t2 = new Terminal(Token.Button);
		Terminal t3 = new Terminal(Token.CloseParen);
		
		assertTrue(t1.equals(t2));
		assertFalse(t1.equals(t3));
		
		Number n1 = new Number(Token.NUMBER,9);
		Number n2 = new Number(Token.NUMBER,9);
		Number n3 = new Number(Token.NUMBER,3);
		Number n4 = new Number(Token.Button,9);
				
		assertTrue(n1.equals(n2));
		assertFalse(n1.equals(n3));
		assertFalse(n1.equals(n4));
		
		Word w1 = new Word(Token.STRING, "Awesome");
		Word w2 = new Word(Token.STRING, "Awesome");
		Word w3 = new Word(Token.STRING, "Lame");
		Word w4 = new Word(Token.Button, "Awesome");
		
		assertTrue(w1.equals(w2));
		assertFalse(w1.equals(w3));
		assertFalse(w1.equals(w4));
		
		
		
	}

}
