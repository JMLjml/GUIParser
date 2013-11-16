/**
 * File: Lexer.java
 * Author: John M. Lasheski
 * Date: November 15, 2013
 * Platform: Windows 8, Eclipse Juno 
 * 
 * Class: UMUC CMSC 330, Section 7981
 * Project: 1
 * 
 * The Lexer class uses a Scanner input to read characters from the supplied input one at a time. The Lexer takes these characters and creates 
 * terminals that will be returned to the Parser class. The Lexer call with throw a syntax error if an unexpected character is encountered.
 */

package guibuilder;

import java.io.IOException;
import java.util.Scanner;


public class Lexer {
	private int line = 1; //used for reporting line position of a syntax error
	private char peek;
	private Scanner input;
		
	

	/**Public constructor
	 * 
	 * @param Scanner input
	 * @throws IOException
	 */
	public Lexer(Scanner input) throws IOException {
		
		if(input != null) {
			this.input = input;			
			input.useDelimiter("");//this sets the scanner input to give one character at at time
						
			if(input.hasNext()) this.peek = input.next().charAt(0);//load the first char into peek
						
		} else {
			throw new IOException("Invalid Scanner Argument supplied to Lexer constructor.");
		}
	}
	
	
	/**return the current line number
	 * used for error reporting
	 * @return
	 */
	protected int getLineNumber() {
		return(this.line);
	}
		
	
	/**Used to create special case NUMBER Tokens
	 * 
	 * @return Number
	 * 
	 * Basic idea found in Aho Compilers 2006.
	 */
	private Number createNumberToken() {
		int v = 0;
		do {
			v = 10 * v + Character.digit(peek, 10);
			peek = input.next().charAt(0);
		} while(Character.isDigit(peek));
		
		return(new Number(Token.NUMBER,v));		
	}
	
	
	/**Used to create special case STRING Token
	 * 
	 * @return Word
	 */
	private Word createStringToken() {

		StringBuilder sb = new StringBuilder();
		String lexeme;
		
		if(input.hasNext()) peek = input.next().charAt(0);
		
		//special case where the string is ""
		if(peek == '"') {
			lexeme = "";
		} else {
			do {
				sb.append(peek);
				peek = input.next().charAt(0);
			} while(peek != '"');
			
			lexeme = sb.toString();
		}
		
		//discard the second '"', and get the next look ahead character for future calls
		if(input.hasNext()) peek = input.next().charAt(0);
		
		Word w = new Word(Token.STRING, lexeme);
		return w;		
	}
	

	/**Help method used for creating a lexeme that will be used
	 * to create a standard token
	 * @return String lexeme
	 */
	private String createLexeme() {
		StringBuilder sb = new StringBuilder();	
		
		while(input.hasNext() && Character.isLetterOrDigit(peek)) {
			sb.append(peek);
			peek = input.next().charAt(0);
		}
		
		return(sb.toString());		
	}
	
	
	/** Used to create punctuation tokens
	 * 
	 * @return Terminal
	 */
	private Terminal createPunctuationToken() {
		switch(peek) {
		case '(': if(input.hasNext()) { 
		      peek = input.next().charAt(0);
		}
		return new Terminal(Token.OpenParen);
				  
		case ')': if(input.hasNext()) { 
		      peek = input.next().charAt(0);
		  }
		  return new Terminal(Token.CloseParen);
		
		case ',': if(input.hasNext()) { 
					  peek = input.next().charAt(0);
				  }
				  return new Terminal(Token.Coma);
		
		case '.': if(input.hasNext()) { 
			  peek = input.next().charAt(0);
		  }
		  return new Terminal(Token.Period);
		
		case ';': if(input.hasNext()) { 
			  peek = input.next().charAt(0);
		  }
		  return new Terminal(Token.SemiColon);
		
		case ':': if(input.hasNext()) { 
			  peek = input.next().charAt(0);
		  }
		  return new Terminal(Token.Colon);
		  
		default : throw new Error("Sytnax Error, unexpected Punctuation Symbol at line " + this.getLineNumber());
		}
	}
	
	
	/** returns the next token from the input
	 * call the appropriate helper methods for handling each type of
	 * token scenario
	 * @return Terminal
	 * @throws IOException
	 */
	protected Terminal getNextToken() {		
		
		//skip and discard whitespace while counting new lines
		while(input.hasNext()) {
			if(peek == ' ' || peek == '\t' || peek == '\r') peek = input.next().charAt(0);
			else if(peek == '\n') {
				peek = input.next().charAt(0);
				line++; 
			}
			else break;	
		}
		
				
		//special case - we have a Number Token
		if(Character.isDigit(peek)) {			
			return(createNumberToken());			
		}
		
		//special case - we have a STRING - create a Word Terminal
		if(peek == '"') {
			return(createStringToken());
		}
			
		//Regular case terminals
		if(Character.isLetter(peek)) {				
		
				switch(createLexeme()) {
				case "Window": return new Terminal(Token.Window);
				case "End": return new Terminal(Token.End);
				case "Layout": return new Terminal(Token.Layout);
				case "Flow": return new Terminal(Token.Flow);
				case "Grid": return new Terminal(Token.Grid);
				case "Button": return new Terminal(Token.Button);
				case "Group": return new Terminal(Token.Group);
				case "Label": return new Terminal(Token.Label);
				case "Panel": return new Terminal(Token.Panel);
				case "Textfield": return new Terminal(Token.Textfield);
				case "Radio": return new Terminal(Token.Radio);
				}
					
		} else {//It must be a punctuation token
			return(createPunctuationToken());
		}
				
		//Throw an exception here
		return(new Word(Token.STRING,"Error - unrecognized token"));
	}
}
