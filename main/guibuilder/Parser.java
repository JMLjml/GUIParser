/**
 * File: Parser.java
 * Author: John M. Lasheski
 * Date: November 15, 2013
 * Platform: Windows 8, Eclipse Juno 
 * 
 * Class: UMUC CMSC 330, Section 7981
 * Project: 1
 * 
 * The Parser class is works in conjunction with the Lexer class to construct parser and then construct the GUI display dictated by the input file.
 * If the syntax of the input is incorrect construction of the GUI Display will terminate and an Error will be thrown reporting the details of the line
 * within the input where the error occurred and a message providing some detail as to the nature of the syntax error.
 * 
 * The parser class uses recursive calls where necessary to create the components of the GUI.
 */

package guibuilder;

import java.awt.Button;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import javax.swing.*;

public class Parser {
	
	private Lexer lex;
	private Terminal terminal;
	private JFrame frame;
	
	public Parser(Lexer lex) {
		this.lex = lex;
	}
	
	
	
	/** Match is a helper method used often to see if current terminal stored in the parser class matched the supplied parameter terminal t, 
	 * before calling the lexer to return the next terminal.
	 * 
	 * It was inspired by a similar method found in Aho Compilers 2006.
	 * 
	 * The method retuns a string to the calling function of the terminal.toString()
	 * 
	 * Match will throw an Error if the current terminal doesn't match the supplied parameter t
	 * 
	 * @param Terminal t
	 * @return string
	 * @throws Error
	 */
	private String match(Terminal t) throws Error {
		if(terminal.equals(t)) {
			String str = terminal.toString();
			terminal = lex.getNextToken();
			return(str);
		} else {
			throw new Error("Syntax Error at line : " + lex.getLineNumber() + ". Expected to find token " + t.toString() + ", but received " + terminal.toString());
		}
	}
	
	
	/** Method called by GUIBuilder for starting construction of the GUI Display*/
	protected boolean Parse(){
		return(gui());
	}
	
	/**
	 * Represents the first expected terminal in the GUI Display grammar. It calls
	 * all other necessary methods for the completion of construction. 
	 */
	private boolean gui() {
		
		//data fields for holding the information gathered from parsing
		String windowName;
		Integer width, height;
		
		//get the first token for comparison
		terminal = lex.getNextToken();
		
		//Get the window name
		match(new Terminal(Token.Window));		
		windowName = match(new Terminal(Token.STRING));
		
		//Get the window width
		match(new Terminal(Token.OpenParen));
		width = new Integer(match(new Terminal(Token.NUMBER)));
		
		//Get the window height
		match(new Terminal(Token.Coma));
		height = new Integer(match(new Terminal(Token.NUMBER)));		
		match(new Terminal(Token.CloseParen));
	
		//If the program makes it here, it is time to start building the window
		buildWindow(windowName,width,height);
		
		//call the layout method to set the layout for the GUI window	
		layout(frame);
		
		//call the widgets method to construct the widgets that will go into the window
		widgets(frame);
		
		//Tokens found at the end of the input file
		match(new Terminal(Token.End));
		match(new Terminal(Token.Period));
		
		//Refresh the GUI so that the new components are displayed correctly
		frame.revalidate();
				
		return(true);		
	}
	
	
	/**
	 * Layout sets the layout type for the the conatiner supplied as a paramenter.
	 * it calls layout_type for help
	 * @param container
	 */
	private void layout(Container container) {
		match(new Terminal(Token.Layout));
		layout_type(container);
		match(new Terminal(Token.Colon));
		return;
	}
	
	
	
	
	/**
	 * Setup either a flow layout or a grid layout to the supplied container
	 * 
	 * @param container
	 */
	private void layout_type(Container container) {
		
		//Check to see if it is a Flow layout or a Grid layout
		switch(terminal.toString()) {
		case("Flow"): 
			container.setLayout(new FlowLayout());
			terminal = lex.getNextToken();// need to manually advance the token here
			return;
				
		case("Grid"):
			//data for the Grid layout
			Integer rows, columns, hGap, vGap;
		
			terminal = lex.getNextToken();
			
			//Get the rows of the grid
			match(new Terminal(Token.OpenParen));
			rows = new Integer(match(new Terminal(Token.NUMBER)));
			
			//Get the columns of the grid
			match(new Terminal(Token.Coma));
			columns = new Integer(match(new Terminal(Token.NUMBER)));
			
			//check for optional gap assignments and build grid layout
			switch(terminal.toString()) {
			case("Coma"):
				terminal = lex.getNextToken();
				hGap = new Integer(match(new Terminal(Token.NUMBER)));
				match(new Terminal(Token.Coma));
				vGap = new Integer(match(new Terminal(Token.NUMBER)));
				match(new Terminal(Token.CloseParen));
				
				//build the grid layout here with the optional gap assignments
				container.setLayout(new GridLayout(rows, columns,hGap,vGap));
				
				return;
			
			//Optional gap assignments were not supplied, build the regular grid layout
			case("CloseParen"):
				container.setLayout(new GridLayout(rows, columns));;
				terminal = lex.getNextToken();
				return;
			
			//syntax error
			default: throw new Error("Syntax Error at line: " + lex.getLineNumber() + " Expected Grid Layout statement, but received " + terminal.toString());
		}		
		
		//syntax error
		default: throw new Error("Syntax Error at line: " + lex.getLineNumber() + " Expected Flow or Grid layout statement, but received " + terminal.toString());	
		}
	}
	
	
	/**
	 * Recursive method for adding a single widget or multiple widgets to the supplied container
	 * @param container
	 */
	private void widgets(Container container) {
		switch(terminal.toString()) {
		case("End"):
			return;
		
		default: 
			widget(container); 
			widgets(container);
			return;
		}
	}
	
	
	/**
	 * Calls a switch on the current terminal to determine the widget that should be added to the supplied container
	 * 
	 * @param container
	 */
	private void widget(Container container) {
		//optional data fields
		String name;
		Integer width;
		
		switch(terminal.toString()) {
		
		case("Panel"):
			terminal = lex.getNextToken();
			//Build a new Panel container here and then set the Panel's Layout
			Panel panel = new Panel();
			panel.setVisible(true);
			layout(panel);
			
			widgets(panel);//add widgets to said panel widgets(panel)
			match(new Terminal(Token.End));
			match(new Terminal(Token.SemiColon));
			container.add(panel);// add the new Panel to the supplied container
			return;
		
		case("Button"):
			terminal = lex.getNextToken();
			name = match(new Terminal(Token.STRING));
			match(new Terminal(Token.SemiColon));

			//create a new Button here and add it to the container
			Button button = new Button(name);
			button.setVisible(true);
			container.add(button);
			return;
		
		case("Group"): 
			terminal = lex.getNextToken();
			radio_buttons(container);
			match(new Terminal(Token.End));
			match(new Terminal(Token.SemiColon));
			return;
				
		case("Label"): 
			terminal = lex.getNextToken();
			name = match(new Terminal(Token.STRING));
			match(new Terminal(Token.SemiColon));
			//Create a new Label and add it to the container
			Label label = new Label(name);
			container.add(label);			
			return;
		
		case("Textfield"):
			terminal = lex.getNextToken();
			width = new Integer(match(new Terminal(Token.NUMBER)));
		   	match(new Terminal(Token.SemiColon));
		    //Create a new Textfield and add it to the container
		    TextField tf = new TextField(width);
		    container.add(tf);		    
		    return;
				
		default:
			throw new Error("Syntax Error at line: " + lex.getLineNumber() + " Expected widget statement, but received " + terminal.toString());
		}
	}
	

	/**
	 * Recursive method for adding a single radio button or multiple radio buttons to the supplied container
	 * @param container
	 */
	private void radio_buttons(Container container) {
		switch(terminal.toString()) {
		case("Radio"): 
			radio_button(container); 
			radio_buttons(container);
			return;	
		
		case("End"):
			return;
		
		default:
			throw new Error("Syntax Error at line: " + lex.getLineNumber() + " Expected Radio statement, but received " + terminal.toString());
		}
	}
	
	
	/**
	 * Method for creating and adding radio buttons to the supplied container
	 * @param container
	 */
	private void radio_button(Container container) {
		terminal = lex.getNextToken();
		String name = match(new Terminal(Token.STRING));
		match(new Terminal(Token.SemiColon));
		//Create the radio button and add it to the container
		JRadioButton radio = new JRadioButton(name);
		container.add(radio);
		return;
	}
	
	/**Set the initial variables for the overall frame container*/
	private void buildWindow(String title, int width, int height){		
		this.frame = new JFrame(title);
		this.frame.setSize(width, height);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);		
	}	
}
