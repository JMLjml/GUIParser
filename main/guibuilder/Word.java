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
		return(super.toString() + " " + this.lexeme);
	}

}
