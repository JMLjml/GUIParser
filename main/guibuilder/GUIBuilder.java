package guibuilder;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GUIBuilder {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		System.out.println("Inside of main from GUIBulder.java");
		
		
		if(!(args.length >= 1)){
			System.out.println("Usage: Supply file name of GUI input file to be parsed as argument.");
			return;
		}
		
		for(int i = 0; i < args.length; i++) {
			Scanner input = new Scanner(new File(args[i]));
		
		
				
			Lexer lex = new Lexer(input);
			Parser parser = new Parser(lex);
		
			buildGUI(parser);		
		
			input.close();
		}
			
	}
	
	static boolean buildGUI(Parser parser) {
		
		return(parser.Parse());
	}

}
