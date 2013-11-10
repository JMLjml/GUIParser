package guibuilder;

public class Number extends Terminal{
	private final int value;

	public Number(Token t, int v) {
		super(t);
		this.value = v;		
	}
	
	public boolean equals(Number n) {
		if(super.equals(n)) {
			return(this.value == n.value);
		} else {
			return(false);
		}
	}
	
	public String toString() {
		return(new Integer(this.value).toString());
	}
}
