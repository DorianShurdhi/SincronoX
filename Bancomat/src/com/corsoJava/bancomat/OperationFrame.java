package com.corsoJava.bancomat;

import java.awt.*;
import java.awt.event.*;

class OperationFrame extends Frame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button bAzzera = null;
	private Button bDeposito  = null;
	private Button bPrelievo  = null;
	private Button bChiudi = null;
	private TextField tfDeposito = null;
	private TextField tfPrelievo = null;
	private Label lNomeBanca = null;
	private Label lSaldoAttuale = null;
	private Label lErrore = null;
	private Font fontNome = null;
	private Font fontSaldo = null;
	private Font fontErrore = null;
	private ContoCorrente cc = new ContoCorrente("C.C. di Dorian Shurdhi");
	
	

	public OperationFrame(String titolo){
		super(titolo);
			
		this.setVisible(true);
		this.setSize(450,550);
		this.setLocation(300,200);
		this.setResizable(true);
		this.setBackground(Color.YELLOW);
		this.setLocationRelativeTo(null);
		this.setForeground(Color.BLUE);
	
		
		//PULSANTE AZZERA
		bAzzera = new Button ("AZZERA");
		bAzzera.setSize (100,50);
		bAzzera.setLocation (50,320);
		bAzzera.addActionListener(this);
		bAzzera.setFont(new Font("Arial", Font.CENTER_BASELINE+Font.ITALIC, 20));
		bAzzera.setBackground(Color.WHITE);
		bAzzera.setForeground(Color.BLUE);
		
		//PULSANTE DEPOSITO
		bDeposito = new Button ("DEPOSITO");
		bDeposito.setSize (120,50);
		bDeposito.setLocation (50,150);
		bDeposito.addActionListener(this);
		bDeposito.setFont(new Font("Arial", Font.CENTER_BASELINE+Font.ITALIC, 20));
		bDeposito.setBackground(Color.green);
		bDeposito.setForeground(Color.WHITE);
		
		//PULSANTE PRELIEVO
		bPrelievo = new Button ("PRELIEVO");
		bPrelievo.setSize (120,50);
		bPrelievo.setLocation (50,220);
		bPrelievo.addActionListener(this);
		bPrelievo.setFont(new Font("Arial", Font.CENTER_BASELINE+Font.ITALIC, 20));
		bPrelievo.setBackground(Color.green);
		bPrelievo.setForeground(Color.WHITE);

		//PULSANTE CHIUDI
		bChiudi = new Button ("CHIUDI");
		bChiudi.setSize (100,50);
		bChiudi.setLocation (250,320);
		bChiudi.addActionListener(this);
		bChiudi.setFont(new Font("Arial", Font.CENTER_BASELINE+Font.ITALIC, 20));
		bChiudi.setBackground(Color.red);
		bChiudi.setForeground(Color.WHITE);

		//TEXTFIELD DEPOSITO
		tfDeposito = new TextField ();
		tfDeposito.setSize (200,50);
		tfDeposito.setLocation (200,150);
		tfDeposito.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 30));

		//TEXTFIELD PRELIEVO
		tfPrelievo = new TextField ();
		tfPrelievo.setSize (200,50);
		tfPrelievo.setLocation (200,220);
		tfPrelievo.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 30));
		
		//LABEL NOME BANCA
		lNomeBanca = new Label (cc.getEnte());
		fontNome = new Font ("Comic Sans", Font.BOLD, 18);
		lNomeBanca.setFont(fontNome);
		lNomeBanca.setSize (300,20);
		lNomeBanca.setLocation (90,50);

		//LABEL SALDO ATTUALE
		lSaldoAttuale = new Label ("SALDO ATTUALE = "+cc.getSaldo()+ "  Euro");
		fontSaldo = new Font ("Comic Sans", Font.ITALIC, 16);
		lSaldoAttuale.setFont(fontSaldo);
		lSaldoAttuale.setSize (350,20);
		lSaldoAttuale.setLocation (50,80);

		//LABEL ERRORE
		lErrore = new Label (null);
		fontErrore = new Font ("Comic Sans", Font.BOLD, 16);
		lErrore.setForeground(Color.red);
		lErrore.setFont(fontErrore);
		lErrore.setSize (50,20);
		lErrore.setLocation (50,80);

		add(lNomeBanca);
		add(lSaldoAttuale);
		add(bAzzera);
		add(bPrelievo);
		add(bDeposito);
		add(tfDeposito);
		add(tfPrelievo);
		add(bChiudi);
		add(lErrore);
	}

	private String aggiorna(){
		String info = "IL SALDO ATTUALE E' DI  "+ cc.getSaldo() + " EURO ";
		lSaldoAttuale.setText(info);
		return info ;
	}

	public void actionPerformed(ActionEvent e){	
		String text;
		double somma;

		if (e.getSource()==bAzzera){
			tfDeposito.setText(" ");	
			tfPrelievo.setText(" ");
			lErrore.setText(" ");
			tfDeposito.requestFocus();
		}
		if (e.getSource()==bDeposito){	
			text = tfDeposito.getText();
			text=text.trim();
			if( !(text.equals(""))){
				somma=Double.parseDouble(text);
				cc.deposito(somma);
				aggiorna();
				tfDeposito.setText(" ");
			}
			
			lErrore.setText(" ");
		}

		if (e.getSource()==bPrelievo){	
			text = tfPrelievo.getText();
			text=text.trim();
			tfPrelievo.setText(" ");
			if( text.equals("")){
				somma=0;
				
			}
			else{
				somma=Double.parseDouble(text);	
				}			
		
			if(somma!=0){
					aggiorna();
			if (cc.prelievo(somma)){
				lErrore.setText("Prelievo effettuato .");
				aggiorna();
			}
			else {
				lErrore.setText("Saldo insufficiente !");
				}
			}

		}
	
	if (e.getSource()==bChiudi){
			System.exit(0);
		}	
	}
}


