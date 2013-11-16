/**
 * File: Terminal.java
 * Author: John M. Lasheski
 * Date: November 15, 2013
 * Platform: Windows 8, Eclipse Juno 
 * 
 * Class: UMUC CMSC 330, Section 7981
 * Project: 1
 * 
 * The Terminal class provides the extra functionality to the Token enumeration and serves as the primary terminal/token
 * mechanism to the Lexer and Parser classes.
 */

package guibuilder;

public class Terminal {
	private Token token;
	
	public Terminal(Token t) {
		this.token = t;
	}
	
	public boolean equals(Terminal t) {
		return(this.token == t.token);
	}
	
	public String toString() {
		return(this.token.toString());
	}
}



