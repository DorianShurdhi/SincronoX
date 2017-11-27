<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.superenalotto.model.Utente" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

	function validaForm(form) {
		var today = new Date();		
		if(form.carta.value==""){
			alert("selezionare un tipo di carta");
			return false;
		}
		else if(form.intCarta.value == "") {
			alert("Inserire l'intestatario della carta");
		 	form.intCarta.focus();
			return false;
		}
		else if(form.numCarta.value==""){
			alert("inserisci il numero della carta");
			form.numCarta.focus();
			return false;
		}
		else if(today.getFullYear() > form.anno.value){
			alert("carta scaduta");
			form.anno.focus();
			return false;
		}
		else if(today.getFullYear() == form.anno.value && (today.getMonth()+1) > form.mese.value){
			alert("carta scaduta");
			form.mese.focus();
			return false;
		}
		else if(form.mese.value==""){
			alert("inserisci la scadenza della carta");
			form.mese.focus();
			return false;
		}
		else if(form.mese.value>12 || form.mese.value<1){
			alert("inserisci una scadenza della carta valida");
			form.mese.focus();
			return false;
		}
		else if(form.anno.value==""){
			alert("inserisci la scadenza della carta");
			form.anno.focus();
			return false;
		}
		else if(form.newSaldo.value=="" || form.newSaldo.value<1){
			alert("inserisci un importo da ricaricare valido");
			form.newSaldo.focus();
			return false;
		}
		else{
			return true;
		}
	}
	
</script>

</head>
<body background="img/bg-iphone.jpg" style="box-sizing: content-box;">
	<h1><center>Aggiorna qui il tuo saldo</center></h1><br>
	<%
		Utente x = (Utente)request.getAttribute("utente");
		out.println("username: "+x.getUser());
		out.println("<br>saldo attuale: € "+x.getSaldo());
	%>
	<form action="NuovaServlet" method="post" onSubmit="return validaForm(this);">
		<h5>seleziona il tipo di pagamento</h5>
		<input type="radio" name="carta" value="MasterCard"> MasterCard    
	  	<input type="radio" name="carta" value="VISA"> VISA    
	  	<input type="radio" name="carta" value="Paypal"> Paypal    
	  	<input type="radio" name="carta" value="Postepay"> Postepay<br><br>
	  	
	  	<h5>Intestatario carta:</h5>
	  	<input type="text" name="intCarta" placeholder="inserire l'intestatario"><br><br>
	  	
	  	<h5>Numero della carta:</h5>
	  	<input type="number" name="numCarta" placeholder="inserire numero carta"><br><br>
	  	
	  	<h5>Data di scadenza della carta:</h5>
	  	<input type="number" name="mese" placeholder="mese"><input type="number" name="anno" placeholder="anno"><br><br><br>
	  	
	  	
	  	<h5>inserire il saldo da ricaricare</h5>
	  	<input type="number" name="addSaldo" placeholder="inserire importo"><br><br><br>
	  	
	  	<input type="hidden" name="controllo" value="6">
	  	<input type="hidden" name="id_utente" value="<%Utente a = (Utente) request.getAttribute("utente"); out.print(a.getId());%>">
	  	<input type="submit" value="conferma">
	</form>
</body>
</html>