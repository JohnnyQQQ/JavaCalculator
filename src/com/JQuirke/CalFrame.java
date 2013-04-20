package com.JQuirke;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class CalFrame extends JFrame  {
	
	
	private static final String COLOR = null;
	JMenuBar menubar;
	JMenu makeMenuItem;
	JPanel panel = new JPanel(new BorderLayout());
	JPanel textFieldTop = new JPanel(new FlowLayout());
	JLabel screen = new JLabel("0");
	JLabel screenTop = new JLabel("Calculator ");
	Font fsize = new Font("Dialog", Font.PLAIN, 15);
	JPanel panelButtons = new JPanel(new GridLayout(4,3,2,4));
	JMenuBar menuBar;
	JMenu file, help, ver;
	JMenuItem quit, instructions, about;
	String keysPressed = "";
	String previousOp  = "=";
	
	JButton[] buttons;
	JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, dec, clear;
	JButton divide, plus, minus, multiply, equals;
	ArrayList<String> topNumbers = new ArrayList<String>();
	String input;
	CalcLogic logic = new CalcLogic();
	boolean   startNumber = true;
	ActionListener numListener = new NumListener();
	ActionListener opListener = new OpListener();
	
	
	
	// Borders around the buttons
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	Border raisedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	Border compound;
	Border redline = BorderFactory.createLineBorder(Color.red);
	
	ArrayList<Integer> calculations = new ArrayList<Integer>();
	public ArrayList<String> list;
	
	
	// I'm using coolors to know which panels I'm using
	Color color = new Color(255,255,0);
	
	public CalFrame(){
		
		super("John Quirke Calculator V 1.0");
		makeFrame();
		makePanels();
		makeScreen();
		makeNumbersGrid();
		makeVisable();
	 
	   	 
	}
	
	/**
	 * Method that will make the frame of the calculator
	 * and include the menuBar with options
	 */
	
	public void makeFrame(){
		
		// Create the menubar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);// add to frame
        // Menu Items
        file = new JMenu("File");
        help = new JMenu("Help");
        ver =  new JMenu("Version");
        menuBar.add(file);
        menuBar.add(help);
        menuBar.add(ver);
        // New menu items added to the menubar
        quit = new JMenuItem("Quit");    
        instructions = new JMenuItem("Instructions");
        about = new JMenuItem("About");
        // add items to the menu
        file.add(quit);
        help.add(instructions);
        help.add(about);        
	    // Add JMenu bar
	    setSize(500, 400);
	    setLocation(200, 200);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
	    }
	
		
	/**
	 * Method that makes the panels of the calculator
	 * and adds colour to them.	
	 */
		public void makePanels(){
			
	        // Grid to add the  M Buttons
	        JPanel joinPanels = new JPanel(new BorderLayout());      
	        
	        JPanel eastSide = new JPanel(new GridLayout(4,1,2,4));
	        eastSide.setPreferredSize(new Dimension(60, 100));
	        compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);   
	      
			 String[] func = {"%", "MRC", "M+", "M-"};
		        for (int i = 0; i < func.length; i++) {
		            JButton b = new JButton(func[i]);
		            b.addActionListener(opListener);
		            b.setFont(fsize);
		            b.setBorder(compound);
		            eastSide.add(b);
		        }
	     
	        // Grid to add the function buttons	        
	        JPanel functions = new JPanel(new GridLayout(4,1,2,4));
	        functions.setPreferredSize(new Dimension(80, 100));	       
		    String[] opOrder = {"+", "-", "*", "/"};
	        for (int i = 0; i < opOrder.length; i++) {
	            JButton b = new JButton(opOrder[i]);
	            b.addActionListener(opListener);
	            b.setFont(fsize);
	            b.setBorder(compound);
	            functions.add(b);
	        }
	        
	        
	        
	        // South Panel equals
	        JPanel south = new JPanel(new FlowLayout());	        
	        equals = new JButton("=");
	        equals.setFont(fsize);	        
	        equals.setBorder(BorderFactory.createMatteBorder(1, 8, 1, 1, Color.red));
	        equals.setToolTipText("Click here for answer");
	        equals.setPreferredSize(new Dimension(155, 45));
	        equals.addActionListener(opListener);	        
	        south.add(equals);
	        
	        // The left side of the display panel
	        joinPanels.add(eastSide, BorderLayout.CENTER);
	        joinPanels.add(functions, BorderLayout.EAST);
	        joinPanels.add(south, BorderLayout.SOUTH);
			
	        	        
	        // Join the panels
			panel.add(textFieldTop, BorderLayout.NORTH);
			panel.add(panelButtons, BorderLayout.CENTER);
			panel.add(joinPanels, BorderLayout.EAST);
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			add(panel);
			
			
		}
		
		
		/**
		 * Make the 2 Text screens
		 * 
		 */
		public void makeScreen(){
			
		// Two text fields on one column
		JPanel textScreen = new JPanel(new GridLayout(2,1,0,0));	
		panel.setBackground(color); 
		textFieldTop.setBackground(Color.BLUE);
					
		 // Top screen
        screenTop.setHorizontalAlignment(JTextField.RIGHT);		
		screenTop.setBackground(Color.WHITE);
		screenTop.setOpaque(true);
		screenTop.setFont( new Font( "Dialog", Font.PLAIN, 25 ) );
		screenTop.setEnabled( true );
               
		// Bottom screen 
		screen.setHorizontalAlignment(JTextField.RIGHT);		
		screen.setFont( new Font( "Dialog", Font.BOLD, 25 ) );
		screen.setBackground(Color.WHITE);
		screen.setOpaque(true);
		screen.setEnabled(true);
		
        // add 2 text screens to panel
        textScreen.add(screenTop);
        textScreen.add(screen);
        
        // add to root border north
		add(textScreen, BorderLayout.NORTH);
		
		}
		
		
		/**
		 * Method that will make the numbers for the
		 * grid
		 */
	public void makeNumbersGrid(){
			
			panelButtons.setBackground(Color.YELLOW);
			panelButtons.setLayout(new GridLayout(4, 3, 6, 6));
			Font fsize = new Font("Dialog", Font.PLAIN, 20);
		
//		    in a loop from the chars in a string.
	        String buttonOrder = "7894561230";
	      
	        for (int i = 0; i < buttonOrder.length(); i++) {
	            String keyTop = buttonOrder.substring(i, i+1);
	            JButton b = new JButton(keyTop);
	            if (keyTop.equals(" ")) {
	                //... Put a dummy button in this position.
	                b.setEnabled(false);
	            } else {
	                //... Put a digit button in the interface.
	                b.addActionListener(numListener);
	                b.setBorder(raisedBorder);
	                
	            }
	            panelButtons.add(b);
	        }
	        
		
	        dec= new JButton(".");
	        dec.setBorder(raisedBorder);
	        dec.addActionListener(numListener);
	        dec.setFont(fsize);
	        
	        panelButtons.add(dec);
	        
	        clear = new JButton("Clear");
	        clear.setBorder(raisedBorder);
	        clear.setToolTipText("Clear all entries");
	        clear.setFont(fsize);
	        panelButtons.add(clear);
	        
			}
	
	
	
	public void clearScreen(){
		
		screenTop.setText("Clear");       
        screen.setText("0");       
        logic.setTotal("0");
       
	}

	
/**
 * Make the frame visible
 */
		  public void makeVisable(){
				 
				 setVisible(true);
			
		 }

		  
		    /** Listener for all op buttons. */
		    class OpListener implements ActionListener {
		        public void actionPerformed(ActionEvent e) {
		        	
		        		            	
		                startNumber = true;  
		                
		                try {
		                  
		                    String displayText = screen.getText();
		                                     
		                    if (previousOp.equals("=")) {
		                        logic.setTotal(displayText);		                        
		                    } else if (previousOp.equals("+")) {		                    	
		                        logic.add(displayText);
		                    } else if (previousOp.equals("-")) {		                    	
		                        logic.subtract(displayText);
		                    } else if (previousOp.equals("*")) {		                    	
		                        logic.multiply(displayText);
		                    } else if (previousOp.equals("/")) {		                    	
		                        logic.divide(displayText);		                        
		                    } else if (previousOp.equals("%")) {		                    
		                        logic.percent(displayText);
		                        System.out.println("percent");
		                    }
		                    
		                    
		                    printNums();
		                    screen.setText("" + logic.getTotalString());
		                    
		                    
		                } catch (NumberFormatException ex) {
		                	clearScreen();
		                    screen.setText("Error");
		                }
		                
		                //... set _previousOp for the next operator.
		                previousOp = e.getActionCommand();
		                screenTop.setText(e.getActionCommand());
		            }
		        }
		
		    
		    public void printNums(){
	        	
	        	for(String s : topNumbers){
	        		
	        		screenTop.setText(s);
	        	}
	        }
		    
		 /**
		  * Action event numbers for the 
		  */
		  
		    class NumListener implements ActionListener {
		        public void actionPerformed(ActionEvent e) {
		            String digit = e.getActionCommand(); // Get text from button
		            if (startNumber) {
		                //... This is the first digit, clear field and set
		                screen.setText(digit);
		                startNumber = false;
		            } else {
		                //... Add this digit to the end of the display field
		                screen.setText(screen.getText() + digit);
		                
            }
		            
		            if(e.getSource() == clear ){
		            	
		            	clearScreen();
		            	
		            }
		        }
		   
			    
		  }// end action Listener
		  
		  
	
	
			
}// end code