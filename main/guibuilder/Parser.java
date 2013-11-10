package guibuilder;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.*;

public class Parser {
	
	private Lexer lex;
	private Terminal terminal;
	
	private JFrame frame;
	private LayoutManager layout;
	
	

	public Parser(Lexer lex) {
		this.lex = lex;
	}
	
		
	private String match(Terminal t) throws Error {
		if(terminal.equals(t)) {
			String str = terminal.toString();
			terminal = lex.getNextToken();
			return(str);
		} else {
			throw new Error("Syntax Error at line : " + lex.getLineNumber());
		}
	}
	
	
	protected boolean Parse(){
		return(gui());
	}
	
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
		System.out.println("Build the window here with title " + windowName + ", width: " + width + " height: " + height);
		buildWindow(windowName,width,height);
		
		//if(!layout(Window window)) return(false);
		
		layout();
		this.frame.setLayout(layout);
		
		
		widgets();
			
		return(true);
		
	}
	
	
	
	private void layout() {
		match(new Terminal(Token.Layout));
		layout_type();
		match(new Terminal(Token.Colon));
		return;
	}
	
	
	
	
	
	private void layout_type() {
		
		//Check to see if it is a Flow layout or a Grid layout
		switch(terminal.toString()) {
		case("Flow"): 
			System.out.println("Build a flow layout here");
			this.layout = new FlowLayout();			
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
			
			//check for option gap assignments and build grid layout
			switch(terminal.toString()) {
			case("Coma"):
				terminal = lex.getNextToken();
				hGap = new Integer(match(new Terminal(Token.NUMBER)));
				match(new Terminal(Token.Coma));
				vGap = new Integer(match(new Terminal(Token.NUMBER)));
				match(new Terminal(Token.CloseParen));
				
				System.out.println("build a grid layout here. rows: " + rows + " columns: " + columns + " hgap: " + hGap + " vgap: " + vGap);
				layout = new GridLayout(rows, columns,hGap,vGap);
				
				return;
			
			//Optional gap assignments were missing, build the grid layout
			case("Close_Paren"):
				System.out.println("build a grid layout here. rows: " + rows + " columns: " + columns);
				layout = new GridLayout(rows, columns);
				terminal = lex.getNextToken();
				return;
			
			//syntax error
			default: throw new Error("Syntax Error at line: " + lex.getLineNumber() + " Expected Grid Layout statement.");
		}		
		
			//syntax error
		default: throw new Error("Syntax Error at line: " + lex.getLineNumber() + " Expected Flow or Grid layout statement.");	
		}
	}
	
	
	//this needs recursive ability
	private void widgets() {
		switch(terminal.toString()) {
		case("End"):
			return;
		
		default: 
			widget(); 
			widgets();
			return;
		}
	}
	
	private void widget() {
		//optional data fields
		String name;
		Integer width;
		
		switch(terminal.toString()) {
		
		case("Panel"):
			terminal = lex.getNextToken();
			layout();
			System.out.println("build a new panel here");
			widgets();
			match(new Terminal(Token.End));
			match(new Terminal(Token.SemiColon));
			return;
		
		case("Button"):
			terminal = lex.getNextToken();
			name = match(new Terminal(Token.STRING));
			match(new Terminal(Token.SemiColon));
			System.out.println("build a new button here with the name: " + name);
			return;
		
		case("Group"): 
			terminal = lex.getNextToken();
			radio_buttons();
			match(new Terminal(Token.End));
			match(new Terminal(Token.SemiColon));
			return;
				
		case("Label"): 
			terminal = lex.getNextToken();
			name = match(new Terminal(Token.STRING));
			match(new Terminal(Token.SemiColon));
			System.out.println("build a new label here with the name: " + name);
			return;
		
		case("TextField"):
			terminal = lex.getNextToken();
			width = new Integer(match(new Terminal(Token.NUMBER)));
		   	match(new Terminal(Token.SemiColon));
		    System.out.println("build a new Text Field here with the width of: " + width);
		    return;
				
		default:
			throw new Error("Syntax Error at line: " + lex.getLineNumber() + " Expected widget statement.");
		
		}
	}
	
	//this needs recursion
	private void radio_buttons() {
		switch(terminal.toString()) {
		case("Radio"): 
			radio_button(); 
			radio_buttons();
			return;	
		
		case("End"):
			return;
		
		default:
			throw new Error("Syntax Error at line: " + lex.getLineNumber() + " Expected Radio statement.");
		}
	}
	
	private void radio_button() {
		terminal = lex.getNextToken();
		String rString = match(new Terminal(Token.STRING));
		match(new Terminal(Token.SemiColon));
		System.out.println("Build a radio button here with the name of " + rString);
		return;
	}
	
	
	private void buildWindow(String title, int width, int height){
		this.frame = new JFrame(title);
		this.frame.setSize(width, height);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}
	
	
}
