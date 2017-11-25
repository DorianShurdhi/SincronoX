package com.corsoJava.bancomat;

public class ContoCorrente {

	private String ente="Poste Italiane";
	private String nome= null; 
	private String cognome= null; 
	private double saldo =0; 

	public ContoCorrente(String e){
		this.ente=e;
	}

	public ContoCorrente(){
		
	}

	public void setEnte(String e){
			this.ente=e;
		}
	public String getEnte()	{
			return this.ente;
		}

	public void setNome(String n){
			this.nome=n;
		}

	public void setCognome(String c){
			this.cognome=c;
		}

	public String getNome()	{
			return this.nome;
		}
	public String getCognome(){
			return this.cognome;
		}

	public void deposito(double d){
			if(d<0){
				System.out.println("Errore. Il deposito non può essere negativo");
				}
			else{  
		   this.saldo += d;
				}
		}

	public double getSaldo(){
			return this.saldo;
		}

	public boolean prelievo(double p)  {
			if(this.saldo<p) {
				return false;		// "Saldo insufficiente "; 
			}
			else{
				this.saldo -= p;
				return true; 		//"Prelievo effettuato";
			}
		}
}
