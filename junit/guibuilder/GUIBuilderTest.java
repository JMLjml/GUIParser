package guibuilder;

import java.io.IOException;

import org.junit.Test;

public class GUIBuilderTest {

	@Test
	public void testMain() {
		
		String[] args ={"testdata.txt", "gui2.txt"};
		
		try {			
			GUIBuilder.main(args);
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}

}
