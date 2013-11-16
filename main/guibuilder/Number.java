/**
 * File: Number.java
 * Author: John M. Lasheski
 * Date: November 15, 2013
 * Platform: Windows 8, Eclipse Juno 
 * 
 * Class: UMUC CMSC 330, Section 7981
 * Project: 1
 * 
 * The Number class is a special case and extends the regular Terminal class. It stores a Token.NUMBER as well as containing an
 * extra int value for storing the number itself.
 */



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
