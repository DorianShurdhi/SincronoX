package com.superenalotto.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superenalotto.model.GiocataDAO;
import com.superenalotto.model.Utente;
import com.superenalotto.model.UtenteDAO;

/**
 * Servlet implementation class NuovaServlet
 */
public class NuovaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	private void fromLogin(HttpServletRequest request, HttpServletResponse response, String user, String pass) throws ServletException, IOException {
		UtenteDAO ut = new UtenteDAO();
		Utente x = new Utente();
		
		try {
			int id = ut.getId(user, pass);
			if(id==-1) {
				response.sendRedirect("index.jsp");
				return;
			}
				double saldo = ut.getSaldo(id);
				x.setId(id);
				x.setUser(user);
				x.setPassword(pass);
				x.setSaldo(saldo);
		}
		catch(SQLException e){ 
		}
		
		RequestDispatcher disp=request.getRequestDispatcher("/view/scheda_utente.jsp");
		request.setAttribute("utente", x);
		disp.forward(request,response);
	}
	
	private void fromRegister(HttpServletRequest request, HttpServletResponse response, String user, String pass) throws ServletException, IOException {
		UtenteDAO ut = new UtenteDAO();
		Utente x = new Utente();
		
		try {
			int id = ut.getId(user, pass);
			if(id == -1) {
				ut.addUtente(user, pass);
				id = ut.getId(user, pass);
				double saldo = ut.getSaldo(id);
				x.setId(id);
				x.setUser(user);
				x.setPassword(pass);
				x.setSaldo(saldo);
			}
			else {
				response.sendRedirect("view/registrazione.jsp");
				return;
			}
		}
		catch(SQLException e){ 
		}
		RequestDispatcher disp=request.getRequestDispatcher("/view/scheda_utente.jsp");
		request.setAttribute("utente", x);
		disp.forward(request,response);
	} 
	
	private void toSaldo(HttpServletRequest request, HttpServletResponse response, String id_ut) throws ServletException, IOException {
		Utente ut = new Utente();
		UtenteDAO x = new UtenteDAO();
		int id = Integer.parseInt(id_ut);
		String user="";
		double saldo=0.0;
		String password=""; 
		try {
		 	user = x.getUser(id);
		 	saldo = x.getSaldo(id);
			password = x.getPass(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ut.setUser(user);
		ut.setId(id);
		ut.setSaldo(saldo);
		ut.setPassword(password);
		
		RequestDispatcher disp=request.getRequestDispatcher("/view/ricarica.jsp");
		request.setAttribute("utente", ut);
		disp.forward(request,response);
	}
	private void fromSaldo(HttpServletRequest request, HttpServletResponse response, String addSaldo, String id_ut) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(id_ut);
			double saldo = Double.parseDouble(addSaldo);

			UtenteDAO x = new UtenteDAO();
			Utente ut = new Utente();

			x.setSaldo(saldo, id);
			
			ut.setId(id);
			ut.setUser(x.getUser(id));
			ut.setPassword(x.getPass(id));
			ut.setSaldo(x.getSaldo(id));
				
			RequestDispatcher req = request.getRequestDispatcher("/view/scheda_utente.jsp");
			request.setAttribute("utente", ut);
			req.forward(request, response);
			return ;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

	
	private void toGiocata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i=0;
		String num_estratti = "";
		ArrayList<Integer> arrayEstratti = new ArrayList<Integer>();

		ArrayList<Integer> array = new ArrayList<Integer>();
		for(i=1; i<91; i++) {
			array.add(i);
		}
		Random random = new Random();
		int j = 1;
		int n = 91-j;
		int primo   = random.nextInt(n);
		num_estratti += (array.get(primo)+" - ");
		arrayEstratti.add(array.get(primo));
		array.remove(primo);
		
		n=n-1;
		int secondo = random.nextInt(n);
		num_estratti += (array.get(secondo)+" - ");
		arrayEstratti.add(array.get(secondo));
		array.remove(secondo);

		n=n-1;
		int terzo   = random.nextInt(n);
		num_estratti += (array.get(terzo)+" - ");
		arrayEstratti.add(array.get(terzo));
		array.remove(terzo);

		n=n-1;
		int quarto  = random.nextInt(n);
		num_estratti += (array.get(quarto)+" - ");
		arrayEstratti.add(array.get(quarto));
		array.remove(quarto);

		n=n-1;
		int quinto  = random.nextInt(n);
		num_estratti += (array.get(quinto)+" - ");
		arrayEstratti.add(array.get(quinto));
		array.remove(quinto);

		n=n-1;
		int sesto   = random.nextInt(n);
		num_estratti += (array.get(sesto));
		arrayEstratti.add(array.get(sesto));
		array.remove(sesto);
		
		ArrayList<Integer> arrayGiocati = new ArrayList<Integer>();
		arrayGiocati.add(Integer.parseInt(request.getParameter("uno")));
		arrayGiocati.add(Integer.parseInt(request.getParameter("due")));
		arrayGiocati.add(Integer.parseInt(request.getParameter("tre")));
		arrayGiocati.add(Integer.parseInt(request.getParameter("qua")));
		arrayGiocati.add(Integer.parseInt(request.getParameter("cin")));
		arrayGiocati.add(Integer.parseInt(request.getParameter("sei")));
		
		Collections.sort(arrayEstratti);
		Collections.sort(arrayGiocati);
		boolean vinto=true;
		for(int q=0; q<6; q++) {
			if(arrayEstratti.get(q) != arrayGiocati.get(q)) {
				vinto=false;
			}
		}

		int id = Integer.parseInt(request.getParameter("id_utente"));
		
		String num_giocati="";
		num_giocati += (request.getParameter("uno")+" - ");
		num_giocati += (request.getParameter("due")+" - ");
		num_giocati += (request.getParameter("tre")+" - ");
		num_giocati += (request.getParameter("qua")+" - ");
		num_giocati += (request.getParameter("cin")+" - ");
		num_giocati += request.getParameter("sei");
		
		GiocataDAO g = new GiocataDAO();

		try {
			g.addGiocata(id, num_giocati, num_estratti);
		} catch (SQLException e) { } 
		
		UtenteDAO ut = new UtenteDAO();
		try {
			ut.setSaldo(-1.0, id);
			if(vinto) {
				ut.setSaldo(50000000, id);
				request.setAttribute("esito", true);
			}
			else {
				request.setAttribute("esito", false);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String us="";
		String pa="";
		try {
			us = ut.getUser(id);
			pa = ut.getPass(id);
		} catch (SQLException e) { }
		
		String id_boh = ""+id;

		RequestDispatcher disp=request.getRequestDispatcher("/view/giocata.jsp");
		request.setAttribute("n_giocati", num_giocati);
		request.setAttribute("n_estratti", num_estratti);
		request.setAttribute("id", id_boh);
		request.setAttribute("user", us);
		request.setAttribute("pass", pa);

		disp.forward(request,response);
	}
	private void fromGiocata(HttpServletRequest request, HttpServletResponse response, String id_ut, String user) throws ServletException, IOException {
		
		UtenteDAO ut = new UtenteDAO();
		int id = Integer.parseInt(id_ut);
		String us = null;
		String pa = null;
		double saldo = 0.0;
		try {
			saldo = ut.getSaldo(id);
			us = ut.getUser(id);
			pa = ut.getPass(id);
		} catch (SQLException e) { }
		Utente x = new Utente();
		x.setId(id);
		x.setUser(us);
		x.setPassword(pa);
		x.setSaldo(saldo);
		
		RequestDispatcher disp = request.getRequestDispatcher("/view/scheda_utente.jsp");
		request.setAttribute("utente", x);
		disp.forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String hidden = request.getParameter("controllo"); 
		
		if(hidden.equals("1")) {
			fromLogin(request, response, request.getParameter("username"),request.getParameter("password"));
		}
		if(hidden.equals("2")) {
			fromRegister(request, response, request.getParameter("user"), request.getParameter("pwd1"));
		}
		if(hidden.equals("3")) {
		    toSaldo(request, response, request.getParameter("id"));
		}
		if(hidden.equals("4")) {
			toGiocata(request, response);
		}
		if(hidden.equals("5")) {
			fromGiocata(request, response, request.getParameter("id"), request.getParameter("user"));
		}
		if(hidden.equals("6")) {
			fromSaldo(request, response, request.getParameter("addSaldo"), request.getParameter("id_utente"));
		}
	}

}
