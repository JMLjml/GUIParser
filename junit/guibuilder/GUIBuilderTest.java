package guibuilder;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class GUIBuilderTest {

	@Test
	public void testMain() {
		
		String[] args ={"testdata.txt"};
		
		try {			
			GUIBuilder.main(args);
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}

}
