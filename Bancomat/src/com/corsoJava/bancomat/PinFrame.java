package com.corsoJava.bancomat;

import java.awt.*;

import java.awt.event.*;

import javax.swing.JOptionPane;

class PinFrame extends Frame implements ActionListener{	
	
	
	private static final long serialVersionUID = 1L;
	
	private int count = 3;
	private final String password = "12345";
	private Button bAzzera = null;
	private Button bInvia  = null;
	private Button bChiudi = null;
	private TextField tfInserisciPin = null; 
	private Label lNomeBanca = null;
	private Label lErrore = null;
	private Font fontNome = null;
	private Font fontErrore = null;


	public PinFrame(String titolo){
		
		super(titolo);
		
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(410,450);
		this.setLocation(300,200);
		this.setResizable(false);
		this.setBackground(Color.YELLOW);
		this.setLocationRelativeTo(null);
		this.setForeground(Color.BLUE);
	
	
		
		//PULSANTE AZZERA
		bAzzera = new Button ("AZZERA");
		bAzzera.setSize (100,50);
		bAzzera.setFont(fontNome);
		bAzzera.setLocation (300,250);
		bAzzera.addActionListener(this);
		bAzzera.setFont(new Font("Arial", Font.CENTER_BASELINE+Font.ITALIC, 20));
		bAzzera.setBackground(Color.WHITE);
		bAzzera.setForeground(Color.BLUE);
		
		//PULSANTE INVIA
		bInvia = new Button ("INVIA");
		bInvia.setSize (110,50);
		bInvia.setLocation (150,250);
		bInvia.addActionListener(this);
		bInvia.setFont(new Font("Arial", Font.CENTER_BASELINE+Font.ITALIC, 20));
		bInvia.setBackground(Color.green);
		bInvia.setForeground(Color.WHITE);
		
		//PULSANTE CHIUDI
		bChiudi = new Button ("CHIUDI");
		bChiudi.setSize (100,50);
		bChiudi.setLocation (10,250);
		bChiudi.addActionListener(this);
		bChiudi.setFont(new Font("Arial", Font.CENTER_BASELINE+Font.ITALIC, 20));
		bChiudi.setBackground(Color.red);
		bChiudi.setForeground(Color.WHITE);

		//TEXTFIELD PIN
		tfInserisciPin = new TextField ("InserisciPin...", 5);
		tfInserisciPin.setSize (250,50);
		tfInserisciPin.setLocation (80,150);
		tfInserisciPin.setFont(new Font("Arial", Font.CENTER_BASELINE+Font.ITALIC, 30));
		tfInserisciPin.requestFocusInWindow();
		tfInserisciPin.requestFocus();
		tfInserisciPin.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        tfInserisciPin.setText("");
		        tfInserisciPin.setEchoChar('*');
		    }
		});
		

		//LABEL NOME BANCA
		lNomeBanca = new Label (" Poste Italiane");
		fontNome = new Font ("Italic", Font.BOLD, 24);
		lNomeBanca.setFont(fontNome);
		lNomeBanca.setSize (300,20);
		lNomeBanca.setLocation (85,50);

		//LABEL ERRORE
		lErrore = new Label (null);
		fontErrore = new Font("Comic Sans", Font.ITALIC, 16);
		lErrore.setFont(fontErrore);
		lErrore.setSize (320,20);   
		lErrore.setLocation(30, 70);

		add(lNomeBanca);
		add(lErrore);
		add(bAzzera);
		add(bInvia);
		add(bChiudi);
		add(tfInserisciPin);
	}
	
	

	public void actionPerformed(ActionEvent e){
		if (e.getSource()==bAzzera){
			tfInserisciPin.setText(" ");	
			tfInserisciPin.requestFocus();
		}
				
		if (e.getSource()==bChiudi){
		   System.exit(0);
		}
	
		if (e.getSource()==bInvia){	
			String text ; 
			//tfInserisciPin.setEchoChar('*');
			text = tfInserisciPin.getText();
			 
			text=text.trim();
			
		    if(text.equals(password))  {				
					this.dispose();
					JOptionPane.showMessageDialog(
	                        PinFrame.this,
	                        "Correct PIN!");
					OperationFrame op = new OperationFrame("Operazioni"); 
		       }
			 else{
				if(count>=1)  {			  
					count--;		
					if ( count == 0) {
						System.exit(0);
					}
					JOptionPane.showMessageDialog(
	                        PinFrame.this,
	                        "Sorry, try again.");
				lErrore.setText("Pin errato, tentativi rimasti : " +count); 		 
				tfInserisciPin.setText(" ");	
				tfInserisciPin.requestFocus();
				}
			}
		}	
	}
}

