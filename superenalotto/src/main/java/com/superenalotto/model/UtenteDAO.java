package com.superenalotto.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtenteDAO {
	
	Connection conn=null;
	
	public UtenteDAO() {
		super();
		conn=DAO.getConnection();
	}
	
	public void addUtente(String user, String pass) throws SQLException{
        if(conn==null) conn=DAO.getConnection();
        Statement st = conn.createStatement();
        
        
        st.executeUpdate("insert into utente(user, password, saldo) values('"+ user +"','"+ pass +"','10');");
	}
	
	public int getId(String user, String pass) throws SQLException{
        if(conn==null) conn=DAO.getConnection();
        Statement st = conn.createStatement();
        int id=0;
        
        ResultSet rs = st.executeQuery("select id from utente where user='"+user+"' and password='"+pass+"'");
        
        if(!rs.first()) {
        	return -1;
        }
        id=Integer.parseInt(rs.getString("id"));
        return id;
    }
	
	public String getUser(int id) throws SQLException{
        if(conn==null) conn=DAO.getConnection();
        Statement st = conn.createStatement();
        String user = "";
        
        ResultSet rs = st.executeQuery("select user from utente where id="+id);
        try {
        	rs.first();
        	user=rs.getString("user");
        }catch(SQLException e) {
        	return "";
        }
        return user;
    }
	public String getPass(int id) throws SQLException{
        if(conn==null) conn=DAO.getConnection();
        Statement st = conn.createStatement();
        String pass = "";
        
        ResultSet rs = st.executeQuery("select password from utente where id="+id);
        try {
        	rs.first();
        	pass=rs.getString("password");
        }catch(SQLException e) {
        	return "";
        }
        return pass;
    }
	
	public double getSaldo(int id) throws SQLException{
        if(conn==null) conn=DAO.getConnection();
        Statement st = conn.createStatement();
        double saldo=0.0;
        
        ResultSet rs = st.executeQuery("select saldo from utente where id="+id);
        try {
        	rs.first();
        	saldo=Double.parseDouble(rs.getString("saldo"));
        }catch(SQLException e) {
        	return -1;
        }
        return saldo;
    }
	
	public void setSaldo(double addsaldo, int id) throws SQLException {
		if(conn==null) conn=DAO.getConnection();
	    Statement st = conn.createStatement();
	    double old = this.getSaldo(id);
	    old += addsaldo;
		st.executeUpdate("UPDATE superenalotto.utente SET saldo = "+old+" where id="+id);
	}
    
}
