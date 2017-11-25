package com.corsoJava.videoteca;

//filtri sulla categoria: estrazione dei film di una determinata categoria
//all'apertura dell'applicazione si mostrano tutti i film in catalogo
//pin per effettuare l'accesso
//ricerca per film o per attore

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class Accedi extends Frame implements ActionListener 
{

	
	private Button chiudi = null;
	private Button ok = null;
	private Label intestazione = null;
	private TextField psw = null;
	private TextField nome = null;
	private Label tuoNome = null;
	private Label tuaPassword = null;
	private Label lErrore;
	private Font fontErrore;
	
	
	public Accedi(String titolo)
	{
		super(titolo);
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(350,400);
		this.setLocation(400,300);
		this.setBackground(Color.GRAY);
		this.setResizable(false);

		chiudi = new Button("CHIUDI");
		chiudi.setSize(70,30);
		chiudi.setLocation(80,240);
		chiudi.addActionListener(this);
		chiudi.setFont(new Font("Courier New", Font.BOLD, 16));
		chiudi.setBackground(Color.red);
		chiudi.setForeground(Color.WHITE);
		
		ok = new Button("OK");
		ok.setSize(70,30);
		ok.setLocation(220,240);
		ok.addActionListener(this);
		ok.setFont(new Font("Courier New", Font.BOLD, 16));
		ok.setBackground(Color.green);
		ok.setForeground(Color.WHITE);
		
		intestazione = new Label("Gli AcchiappaFilm");
		intestazione.setFont(new Font("Courier New", Font.BOLD, 20));
		intestazione.setSize(400,50);
		intestazione.setLocation(80,40);
		//intestazione.setBackground(Color.YELLOW);
		intestazione.setForeground(Color.YELLOW);

		tuoNome = new Label("Username :");
		tuoNome.setSize(90,30);
		tuoNome.setLocation(50,100);
		tuoNome.setFont(new Font("Courier New",Font.ITALIC+Font.BOLD, 15));
		tuoNome.setBackground(Color.YELLOW);
		tuoNome.setForeground(Color.BLACK);
	
		tuaPassword = new Label("Password :");
		tuaPassword.setSize(90,30);
		tuaPassword.setLocation(50,150);
		tuaPassword.setFont(new Font("Courier New",Font.ITALIC+Font.BOLD, 15));
		tuaPassword.setBackground(Color.YELLOW);
		tuaPassword.setForeground(Color.BLACK);

		
		psw = new TextField("password");
		psw.setSize(100,30);
		psw.setLocation(200,150);
		psw.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
        psw.setText("");
        psw.setEchoChar('*');
		 }
		});

		nome = new TextField("username");
		nome.setSize(100,30);
		nome.setLocation(200,100);
		nome.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
        nome.setText("");
		 }
		});

		lErrore = new Label (null);
		fontErrore = new Font ("Comic Sans", Font.BOLD, 18);
		lErrore.setForeground(Color.red);
		lErrore.setFont(fontErrore);
		lErrore.setSize (200,30);
		lErrore.setLocation (90,200);
		
		
		
		
		add(ok);
		add(chiudi);
		add(intestazione);
		add(tuoNome);
		add(tuaPassword);
		add(psw);
		add(nome);
		add(lErrore);

	}

private boolean validate_login(String nome,String pswd) {

try{           
 Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?user=root&password=root");     
 PreparedStatement pst = conn.prepareStatement("Select * from utenti where nome_utente=? and pass=?");
	pst.setString(1, nome); 
	 pst.setString(2, pswd);
ResultSet rs = pst.executeQuery();                        
if(rs.next())            
	 return true;    
else
     return false;            
 }
catch(Exception e){
	e.printStackTrace();
		return false;
	 }     
}
	
public void actionPerformed (ActionEvent a){

if (a.getSource()==chiudi){
		System.exit(0);
		}

if (a.getSource()==ok){
		String n = nome.getText();
		String b = psw.getText();
			n=n.trim();
			b=b.trim();
			if(validate_login(n,b)){
				this.dispose();
				ArchivioFilm af = new ArchivioFilm("AcchiappaFilm");

			}
		
			else{
				nome.setText("");
				psw.setText("");
				lErrore.setText("Accesso Negato ! ");
				nome.requestFocus();
			}
		}
}
}



	

