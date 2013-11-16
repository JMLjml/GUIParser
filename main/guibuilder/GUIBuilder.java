/**
 * File: GUIBuilder.java
 * Author: John M. Lasheski
 * Date: November 15, 2013
 * Platform: Windows 8, Eclipse Juno 
 * 
 * Class: UMUC CMSC 330, Section 7981
 * Project: 1
 * 
 * The GUIBuilder class contains the main method and creates and calls the Lexer and Parser classes to create the GUI Display
 * dictated by the text input file supplied as the arguments. GUIBuilder can take multiple arguments and display multiple GUIs and 
 * will print a usage statement to the console if no args are supplied.
 */

package guibuilder;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GUIBuilder {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		if(!(args.length >= 1)){
			System.out.println("Usage: Supply file name of GUI input file to be parsed as argument.");
			return;
		}
		
		for(int i = 0; i < args.length; i++) {
			Scanner input = new Scanner(new File(args[i])); //the input Scanner will be used by Lexer for reading tokens
			Lexer lex = new Lexer(input); //the lexer will be called by Parser to return tokens/terminals
			Parser parser = new Parser(lex);//The parser analyzes the syntax of the input file
		
			buildGUI(parser);//analyze the tokens and construct the GUI display		
		
			input.close();
		}			
	}
	
	static boolean buildGUI(Parser parser) {		
		return(parser.Parse());
	}
}
