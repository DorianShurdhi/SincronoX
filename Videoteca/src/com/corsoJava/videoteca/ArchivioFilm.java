package com.corsoJava.videoteca;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class ArchivioFilm extends Frame implements ActionListener
{
	
	private Button chiudi=null;
	private Button load= null;
	private Button bAzzera = null;
	private Label intestazione = null;
	private Label cerca1= null;
	private Label cerca2= null;
	private Label cerca3= null;
	private TextArea tabella = null;
	private TextField insAttore = null;
	private TextField insFilm = null;
	private Button vaiAttore = null;
	private Button vaiFilm = null;
	private String[] categorie = {"Scegli categoria : ","Action","Animation","Children","Classics",
									"Comedy","Documentary","Drama","Family","Foreign",
									"Games","Horror","Music","New","Sci-Fi","Sports","Travel"
								};
	private JComboBox<String> tendina = new JComboBox<String>(categorie);

	public ArchivioFilm (String titolo)
	{
		super(titolo);
		setLayout(null);
		setVisible(true);
		setSize(800,800);
		setLocation(250,100);
		setBackground(Color.GRAY);


		chiudi = new Button("CHIUDI");
		chiudi.setSize(70,30);
		chiudi.setLocation(680,730);
		chiudi.setFont(new Font("Courier New", Font.BOLD, 16));
		chiudi.addActionListener(this);
		chiudi.setBackground(Color.red);
		chiudi.setForeground(Color.WHITE);

		load = new Button("LOAD");
		load.setSize(70,30);
		load.setLocation(50,70);
		load.setFont(new Font("Courier New", Font.BOLD, 16));
		load.addActionListener(this);
		load.setBackground(Color.YELLOW);
		load.setForeground(Color.BLUE);

		bAzzera = new Button("AZZERA");
		bAzzera.setSize(70,30);
		bAzzera.setLocation(680,650);
		bAzzera.setFont(new Font("Courier New", Font.BOLD, 16));
		bAzzera.addActionListener(this);
		bAzzera.setBackground(Color.WHITE);
		bAzzera.setForeground(Color.BLUE);

			
		intestazione = new Label("Gli AcchiappaFilm");
		intestazione.setFont(new Font("Courier New", Font.BOLD,30));
		intestazione.setSize(260,60);
		intestazione.setLocation(270,40);	
		intestazione.setForeground(Color.YELLOW);
		//intestazione.setBackground(Color.WHITE);
		
		cerca1 = new Label ("Ricerca un attore");
		cerca1.setSize(124,30);
		cerca1.setLocation(50,620);
		cerca1.setFont(new Font("Courier New",Font.BOLD,15));

		insAttore = new TextField("");
		insAttore.setSize(200,30);
		insAttore.setLocation(50,650);
		insAttore.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        insAttore.setText("");
		        tabella.setText(" ");
				tabella.append(" ");
		    }
		});

		cerca2 = new Label ("Ricerca un film");
		cerca2.setSize(124,30);
		cerca2.setLocation(50,700);
		cerca2.setFont(new Font("Courier New",Font.BOLD,15));
		

		insFilm = new TextField("");
		insFilm.setSize(200,30);
		insFilm.setLocation(50,730);
		insFilm.addActionListener(this);
		insFilm.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        insFilm.setText("");
		        tabella.append(" ");
				tabella.setText(" ");
		    }
		});

		
		vaiAttore = new Button("VAI");
		vaiAttore.setSize(50,30);
		vaiAttore.setLocation(270,650);
		vaiAttore.addActionListener(this);
		vaiAttore.setFont(new Font("Courier New", Font.BOLD, 16));
		vaiAttore.setBackground(Color.green);
		vaiAttore.setForeground(Color.WHITE);
		
		
		vaiFilm = new Button("VAI");
		vaiFilm.setSize(50,30);
		vaiFilm.setLocation(270,730);
		vaiFilm.setFont(new Font("Courier New", Font.BOLD, 16));
		vaiFilm.addActionListener(this);
		vaiFilm.setBackground(Color.green);
		vaiFilm.setForeground(Color.WHITE);
		
		//tendina = new JComboBox<String>(categorie);
		tendina.setSize(200,30);
		tendina.setLocation(410,650);
		tendina.setVisible(true);
		tendina.addActionListener(this);

		cerca3 = new Label ("Seleziona una categoria");
		cerca3.setSize(180,30);
		cerca3.setLocation(420,620);
		cerca3.setFont(new Font("Courier New",Font.BOLD,15));
		
		tabella = new TextArea();
		tabella.setFont(new Font("Courier New",Font.BOLD,20));
		tabella.setSize(700,500);
		tabella.setLocation(50,100);
		tabella.setVisible(true);
	
		
	

		add(chiudi);
		add(intestazione);
		add(tabella);
		add(cerca1);
		add(cerca2);
		add(cerca3);
		add(insAttore);
		add(insFilm);
		add(vaiAttore);
		add(vaiFilm);
		add(tendina);
		add(load);
		add(bAzzera);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==chiudi) {
			System.exit(0);
		}
	

		if (e.getSource()==load) {
			tabella.append(" ");

		try{

		Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
		 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?user=root&password=root");     
		 PreparedStatement pst = conn.prepareStatement("Select * from film");
		ResultSet rs = pst.executeQuery();  
		
		while(rs.next()) {

			String fID = rs.getString("film_id");
			String a= "      ";
			String title = rs.getString("title");
			String b="\n";
			
			tabella.append(fID);
			tabella.append(a);
			tabella.append(title);
			tabella.append(b);



			/* insAttore.setText(" ");
			insAttore.setText("");
			insFilm.setText(" ");
			insFilm.setText("");
			tendina.setSelectedIndex(0); */
		}
		}
		catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
		catch (SQLException ex){
			ex.printStackTrace();
		}
		
	}

	if(e.getSource()==vaiAttore){
		tabella.append(" ");
		
		try{

		Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
		 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?user=root&password=root");     
		 PreparedStatement pst = conn.prepareStatement("Select * from actor where first_name=? or last_name=?");
			
			
			pst.setString(1,insAttore.getText() );
			pst.setString(2,insAttore.getText() );
			
		ResultSet rs = pst.executeQuery();  
		
		while(rs.next()) {

			String fn = rs.getString("first_name");
			String a= "                     ";
			String ln = rs.getString("last_name");
			String b="\n";
			
			tabella.append(fn);
			tabella.append(a);
			tabella.append(ln);
			tabella.append(b);

		}
		}
		catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
		catch (SQLException ex){
			ex.printStackTrace();
		}
	}

		if(e.getSource()==vaiFilm){
			tabella.append(" ");
		
		try{

		Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
		 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?user=root&password=root");     
		 PreparedStatement pst = conn.prepareStatement("Select * from film where title=?");
			
			
			pst.setString(1,insFilm.getText() );
			//pst.setString(2,insAttore.getText() );
			
		ResultSet rs = pst.executeQuery();  
		
		while(rs.next()) {

			String iD = rs.getString("film_id");
			String a= "                     ";
			String t = rs.getString("title");
			String b="\n";
			
			tabella.append(iD);
			tabella.append(a);
			tabella.append(t);
			tabella.append(b);

		}
		}
		catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
		catch (SQLException ex){
			ex.printStackTrace();
		}
		
	}

	if(e.getSource()==tendina) {
			
			tabella.append(" ");
			Object f = tendina.getSelectedItem();
			for (int i=0; i<categorie.length ;i++ )
			{
				if (f==categorie[i] && f!=categorie[0])
				{
					try{

		Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
		 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?user=root&password=root");     
		 PreparedStatement pst = conn.prepareStatement("Select * from category c , film f, film_category fc where c.category_id=fc.category_id and f.film_id=fc.film_id and c.name=?");
			
			
			pst.setString(1,f.toString() );
			//pst.setString(2,insAttore.getText() );
			
		ResultSet rs = pst.executeQuery();  
		
		while(rs.next()) {

			String id = rs.getString("film_id");
			String a= "                     ";
			String n = rs.getString("title");
			String b="\n";
			
			tabella.append(id);
			tabella.append(a);
			tabella.append(n);
			tabella.append(b);

		}
		}
		catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
		catch (SQLException ex){
			ex.printStackTrace();
		}
		}
		}
	
	}

	if(e.getSource()==bAzzera){

		tabella.append(" ");
		tabella.setText(" ");
		insAttore.setText(" ");
		insFilm.setText(" ");

	}

	

	}
}


