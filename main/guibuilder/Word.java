/**
 * File: Word.java
 * Author: John M. Lasheski
 * Date: November 15, 2013
 * Platform: Windows 8, Eclipse Juno 
 * 
 * Class: UMUC CMSC 330, Section 7981
 * Project: 1
 * 
 * The Word class is a special case and extends the regular Terminal class. It stores a Token.STRING as well as containing an
 * extra string lexeme value for storing the string itself.
 */


package guibuilder;

public class Word extends Terminal {
	final String lexeme;
	
	public Word(Token t, String s) {
		super(t);
		this.lexeme = new String(s);
	}
	
	public boolean equals(Word w) {
		if(super.equals(w)) {
			return(this.lexeme.equals(w.lexeme));
		} else {
			return(false);
		}
	}
	
	public String toString() {
		return(this.lexeme);
	}
}
