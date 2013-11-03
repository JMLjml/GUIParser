package guibuilder;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;


public class Lexer {
	
	
	private int line = 1; //used for reporting line position of a syntax error
	private char peek = ' ';
	private Hashtable<String, Word> words = new Hashtable<String, Word>();
	
	private Scanner input;
	
	void reserve(Word w) {
		words.put(w.lexeme, w);
	}
	
	

	public Lexer(Scanner input) throws IOException {
		
		if(input != null) {
			this.input = input;
			
			//this sets the scanner input to give me one character at at time
			input.useDelimiter("");
			
			if(input.hasNext()) this.peek = input.next().charAt(0);
			
			
		} else {
			throw new IOException("Invalid Scanner Argument supplied to Lexer constructor.");
		}
			
		// TODO reserve keywords here - this might not be neccesary...
		reserve(new Word(Token.Window,"Window"));		
		reserve(new Word(Token.OpenParen, "("));
	}
	
	
	protected Terminal getNextToken() throws IOException {
		
		//skip and discard whitespace while counting new lines
		while(input.hasNext()) {
			if(peek == ' ' || peek == '\t') peek = input.next().charAt(0);
			else if(peek == '\n') line++;
			else break;	
		}
		
		
		//special case - we have a Number Token
		if(Character.isDigit(peek)) {
			int v = 0;
			do {
				v = 10 * v + Character.digit(peek, 10);
				peek = input.next().charAt(0);
			} while(Character.isDigit(peek));
			
			return(new Number(Token.NUMBER,v));			
		}
		
		//special case - we have a STRING - store it as a Word
		if(peek == '"') {
			StringBuilder sb = new StringBuilder();
			
			peek = input.next().charAt(0);
			do {
				sb.append(peek);
				peek = input.next().charAt(0);
			} while(peek != '"');
			
			//get the next look ahead character for future calls
			if(input.hasNext()) peek = input.next().charAt(0);
			
			String lexeme = sb.toString();
			Word w = words.get(lexeme);
			
			if(w != null) return w;

			w = new Word(Token.STRING, lexeme);
			words.put(lexeme,  w);
			return w;
		}
			
			
		StringBuilder sb = new StringBuilder();	
		
		while(peek != ' ') {
			sb.append(peek);
			peek = input.next().charAt(0);
		}
		
		String lexeme = sb.toString();
		
		switch(lexeme) {
		case "Window": return new Terminal(Token.Window);
		case "(": return new Terminal(Token.OpenParen);
		case ",": return new Terminal(Token.Coma);
		}
			
		
		
		
		
		
		
		
		return(new Terminal(Token.Button));
		
		
	}
	
	
	
	

}
