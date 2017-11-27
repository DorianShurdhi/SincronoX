<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/giocata.css">

<title>Insert title here</title>
</head>
<body background="img/bg-iphone.jpg" style="box-sizing: content-box;" >
	<label style="margin-left: 23%; color: dark_gray;" >Numeri giocati:</label><center style="color: gray;">
	<%  String n_gioc = (String) request.getAttribute("n_giocati");
		out.println(n_gioc+"<br><br>"); %>
	</center>
		
	<label style="margin-left: 23%; color: dark gray;">Numeri estratti:</label><center style="color: gray;">
	<%  String n_estr = (String) request.getAttribute("n_estratti");
		out.println(n_estr);
	%></center>
	
	 <form action="NuovaServlet" method="post">
             <input type="hidden" name="id" value="<%  String id = (String) request.getAttribute("id");
             out.print(id);%>" />
			 <input type="hidden" name="user" value="<%  String user = (String) request.getAttribute("user");
				 out.print(user); %>" />
			 <input type="hidden" name="pass" value="<%  String pass = (String) request.getAttribute("pass");
				 out.print(pass); %>" />
             <input type="hidden" name="controllo" value="5">
             <br><center><input type="submit" value="Gioca Ancora" /></center>
 	</form>
 	
 	<img alt="" src="<% 
 		if((Boolean)request.getAttribute("esito")) 
 			out.print("http://24.media.tumblr.com/61c8d368e2a6e53ecf048b8bbc1ef60d/tumblr_mwyo98aufM1t3hbhyo1_r1_500.gif"); 
 		else 
 			out.print("https://media1.tenor.com/images/98dde7adea8c9a1f305b191763f59b67/tenor.gif?itemid=3957166"); 
 	%>" align="bottom" style="margin-top: 5%; margin-right: 0%; margin-left: 5%;" width=36%>
 	
    </fieldset>
    </form>
    		<img alt="" src="img/superenalotto.jpg" align="bottom" style="margin-top: 5%; margin-right: 0%; margin-left: 5%;" width=49%>
</body>
</html>