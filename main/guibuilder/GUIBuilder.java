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
		// TODO Auto-generated method stub
		
		System.out.println("Inside of main from GUIBulder.java");
		//uncomment when time to run for real
		/*
		if(args.length != 1){
			System.out.println("Usage: Supply file name of GUI input file to be parsed as argument.");
			return;
		}
		
		Scanner input = new Scanner(new File(args[0]));
		*/
		
		Scanner input = new Scanner(new File("testdata.txt"));
		
		Lexer lex = new Lexer(input);
		Parser parser = new Parser(lex);
		
		boolean success = buildGUI(parser);		
		
		input.close();
			
	}
	
	static boolean buildGUI(Parser parser) {
		
		return(parser.Parse());
	}

}
