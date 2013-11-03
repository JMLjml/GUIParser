package guibuilder;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class GUIBuilderTest {

	@Test
	public void testMain() {
		
		
		String[] args ={};
		
		try {
			GUIBuilder.main(args);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fail("Not yet implemented");
		}
		
		
	}

}
