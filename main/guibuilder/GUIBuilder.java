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
			
		if(args.length != 1){
			System.out.println("Usage: Supply file name of GUI input file to be parsed as argument.");
			return;
		}
		
		Scanner input = new Scanner(new File(args[0]));
		System.out.println(input.nextLine());
		input.close();
				
		
		//This works for setting up the scanner to read the args file when the file
		//is saved in the bin folder
		/*
		Scanner input = new Scanner(new File(args[0]));
		System.out.println(input.nextLine());
		*/
		
		//This works for reading from the keyboard
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println(sc.nextLine());
		sc.close();
		*/
		//StringBuilder nodes = new StringBuilder();
	}

}
