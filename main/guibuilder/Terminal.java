/**
 * 
 */
package guibuilder;

/**
 * @author John
 *
 */
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



