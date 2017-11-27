<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.superenalotto.model.Utente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="css/scheda_utente.css">
<title>Insert title here</title>

<script type="text/javascript">
	
	function validaForm(form){		
		if(parseFloat(form.saldo.value)<=0){
			alert("sei un poraccio");
			return false;
		}
		else if(form.uno.value==""){
			alert("inserisci tutti i numeri");
			form.uno.focus();
			return false;
		}
		if(form.due.value==""){
			alert("inserisci tutti i numeri");
			form.due.focus();
			return false;
		}
		if(form.tre.value==""){
			alert("inserisci tutti i numeri");
			form.tre.focus();
			return false;
		}
		if(form.qua.value==""){
			alert("inserisci tutti i numeri");
			form.qua.focus();
			return false;
		}
		if(form.cin.value==""){
			alert("inserisci tutti i numeri");
			form.cin.focus();
			return false;
		}
		if(form.sei.value==""){
			alert("inserisci tutti i numeri");
			form.sei.focus();
			return false;
		}
		
		var a = parseInt(form.uno.value, 10);
		var b = parseInt(form.due.value, 10);
		var c = parseInt(form.tre.value, 10);
		var d = parseInt(form.qua.value, 10);
		var e = parseInt(form.cin.value, 10);
		var f = parseInt(form.sei.value, 10);
		
		if(a<=0 || a>=91){ 
			alert("i numeri devono essere compresi tra 1 e 90");
		    form.uno.focus();
			return false;
		}
		if(b<=0 || b>=91){ 
			alert("i numeri devono essere compresi tra 1 e 90");
		    form.due.focus();
			return false;
		}
		if(c<=0 || c>=91){ 
			alert("i numeri devono essere compresi tra 1 e 90");
		    form.tre.focus();
			return false;
		}
		if(d<=0 || d>=91){ 
			alert("i numeri devono essere compresi tra 1 e 90");
		    form.qua.focus();
			return false;
		}
		if(e<=0 || e>=91){ 
			alert("i numeri devono essere compresi tra 1 e 90");
		    form.cin.focus();
			return false;
		}
		if(f<=0 || f>=91){ 
			alert("i numeri devono essere compresi tra 1 e 90");
		    form.sei.focus();
			return false;
		}
		var x = [a, b, c, d, e, f];
		x.sort();
		var i=0;
		for(i=0; i<5; i++){
			if(x[i]==x[i+1]){
				alert("i numeri devono essere tutti diversi");
				return false;
			}
		}
		return true;
	}


</script>

</head>
<body>
	<form action="NuovaServlet" method="post" onSubmit="return validaForm(this);">
	<input type="hidden" name="controllo" value="4">
	<table width="100%" height="100%" border="1">
		<center>
		<tr>
			<td>id  e  user<br>
				
				<% Utente ut = (Utente) request.getAttribute("utente");
					out.println(ut.getId());
					out.println(ut.getUser());
					out.println("<input type='hidden' name='id_utente' value='"+ut.getId()+"'>");
				%>
			
			</td>
			<td> <input name="uno" type="number">
				 <input name="due" type="number">
				 <input name="tre" type="number">
				 <input name="qua" type="number">
				 <input name="cin" type="number">
				 <input name="sei" type="number"> </td>
				 <input type="hidden" name="saldo" value="<%out.print(ut.getSaldo());%>">
			<td> saldo<br>
			<%
				out.println(ut.getSaldo());
			%>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><center><input type="submit" value="gioca"></center></td></form>
			<td><form action="NuovaServlet" method="post">
				<input type="submit" value="ricarica">
				<input type="hidden" name="controllo" value="3">
				<input type='hidden' name="id" value="<%Utente u = (Utente) request.getAttribute("utente");
					out.print(u.getId());%>">
			</form></td>
		</tr>
		<tr>
			<td>ultime utente</td>
			<td></td>
			<td>ultime giocate</td>
		</tr>
		</center>
	</table>
	
		<img alt="" src="img/footer.jpg" align="bottom" style="margin-top: 40%; margin-left: 0%" width="100%">
	
</body>
</html>