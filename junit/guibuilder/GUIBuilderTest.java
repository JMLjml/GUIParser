/**
 * File: GUIBuilderTest.java
 * Author: John M. Lasheski
 * Date: November 15, 2013
 * Platform: Windows 8, Eclipse Juno 
 * 
 * Class: UMUC CMSC 330, Section 7981
 * Project: 1
 * 
 * JUnit test for main method call in GUIBuilder.java.
 */

package guibuilder;

import java.io.IOException;

import org.junit.Test;

public class GUIBuilderTest {

	@Test
	public void testMain() {
		
		String[] args ={"testdata.txt", "gui2.txt", "AnimalTest.txt"};
		
		try {			
			GUIBuilder.main(args);
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}

}
