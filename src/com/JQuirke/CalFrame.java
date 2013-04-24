//#############################################################
// Java Calculator 
//@Author John Quirke
//@Email John@JohnQuirke.com
//Date April 2013
//Version 1.0
//@Class CalFrame 
//#############################################################


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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class CalFrame extends JFrame  {
	

	private JPanel panel = new JPanel(new BorderLayout());
	private JPanel textFieldTop = new JPanel(new FlowLayout());
	private JLabel screen = new JLabel(" ");
	private JLabel screenTop = new JLabel("Calculator ");
	private Font fsize = new Font("Dialog", Font.PLAIN, 15);
	private JPanel panelButtons = new JPanel(new GridLayout(4,3,2,4));
	private JMenuBar menuBar;
	private JMenu file, help, ver;
	private JMenuItem quit, instructions, about;
	private String previousOp  = "=";
	private JButton  dec, clear, equals;
	private CalcLogic logic = new CalcLogic();
	private boolean startNumber = true;
	
	// Internal Classes
	private ActionListener numListener = new NumListener();
	private ActionListener opListener = new OpListener();
	
	// Design Borders around the buttons
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	Border raisedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	Border compound;
	Border redline = BorderFactory.createLineBorder(Color.red);

	// I'm using colours to know which panels I'm using
	Color color = new Color(255,255,0);
/**
 * Constructor that will create the Calculator when the 
 * object is created
 */
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
        menuBar.add(file);
        menuBar.add(help);
       
        // New menu items added to the menubar
        quit = new JMenuItem("Quit"); 
        quit.addActionListener(numListener);
        
        instructions = new JMenuItem("Instructions");
        instructions.addActionListener(numListener);
        about = new JMenuItem("About");
        about.addActionListener(numListener);
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
	        JPanel functions = new JPanel(new GridLayout(4,1,2,4));
	        functions.setPreferredSize(new Dimension(80, 100));	   
			
	        String[] func = {"Sqrt", "1/x", "%", "CE"};
		        for (int i = 0; i < func.length; i++) {
		            JButton b = new JButton(func[i]);
		            b.addActionListener(opListener);
		            b.setFont(fsize);
		            b.setBorder(compound);
		            eastSide.add(b);
		        }
	     
	        // Grid to add the function buttons	        
	       
	            
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
	        clear.addActionListener(numListener);
	        panelButtons.add(clear);
	        
			}
	
	
	public JLabel getScreenTop(){
		
		return screenTop;
	}
	
public JLabel getScreen(){
		
		return screen;
	}

	public void clearScreen(){
		
		screenTop.setText("Clear");       
        screen.setText(" ");       
        logic.setTotal("0");
       
	}

	
/**
 * Make the frame visible
 *
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
		                    } else if (previousOp.equals("Sqrt")) {		                    	
		                        logic.sqRoot(displayText);	
		                    }else if (previousOp.equals("1/x")) {		                    	
		                        logic.reciprocalFunction(displayText);	
		                    }else if (previousOp.equals("CE")) {		                    	
		                    	logic.clearExisting(displayText);   
		                    }else if (previousOp.equals("%")) {		                    	
		                        logic.percent(displayText);	
		                   
		                    }
		                  
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
 
 class memListner implements ActionListener {
		       
	 public void actionPerformed(ActionEvent e) {
		                
		 startNumber = true;  
		 previousOp = e.getActionCommand();
		 screenTop.setText(e.getActionCommand());  
		 String total = screen.getText();
		 
		                    if (previousOp.equals("M+")) {
		                    	System.out.println("M+ pressed");
		                    	
		                    
		                    	
		                    	 
		                    	
		                    } else if (previousOp.equals("MR")) {		                    	
		                    	System.out.println("MR pressed");
		                    } else if (previousOp.equals("M-")) {		                    	
		                    	System.out.println("M- pressed");
		                    } else if (previousOp.equals("MC")) {		                    	
		                    	System.out.println("MC pressed");
		                    } 
		                    
		                    
		             	                
		              
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
		    		
		    		if(e.getSource() == quit ){

		    			System.exit(1);

		    		}
		    		
		    		if(e.getSource() == instructions ){

		    			JOptionPane instr = new JOptionPane("Instructions");
		    			instr.showMessageDialog(null, "Press Lots of buttons");
		    			clearScreen();

		    		}
		    		
		    		if(e.getSource() == about ){

		    			JOptionPane instr = new JOptionPane("About");
		    			instr.showMessageDialog(null, "Java Calculator \n" +
		    					"John Quirke \n" +
		    					"Version 1.0");

		    		}
		    	}


		    }// end action Listener


	

	
	
			
}// end code