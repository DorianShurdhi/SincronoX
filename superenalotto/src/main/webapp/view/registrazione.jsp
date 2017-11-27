<html>
<head>
	<title></title>
 	<link rel="stylesheet" href="../css/style.css">
 	<script type="text/javascript">
 	
	 	function _calculateAge(dateString) {
	 		var today = new Date();
	 	    var birthDate = new Date(dateString);
	 	    var age = today.getFullYear() - birthDate.getFullYear();
	 	    var m = today.getMonth() - birthDate.getMonth();
	 	    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
	 	        age--;
	 	    }
	 	    return age;
	 	}
 	
 		function validaForm(form) {	 			
 			var eta = _calculateAge(form.data.value);
			if(eta<18){
			      alert("Sei troppo piccolo per giocare");
			      return false;
			} 			
			
			else if(form.user.value == "") {
			      alert("Inserire uno username");
			      return false;
			}
			else if (form.pwd1.value != form.pwd2.value){
 				alert("Le password NON coincidono");
 				return false;
			}
			else if (form.pwd1.value == ""){
 				alert("Inserire una password");
 				return false;
			}
			else if(form.data.value==""){
				alert("Inserire una data di nascita");
 				return false;
			}
			else{
 				return true;
 			}
 		}
 	
 		</script>
</head>
<body>

  <div id="wrap">
  <div id="regbar">
    <div id="navthing">
      <h2><a href="../index.jsp">Login</a> | <a href="#" id="registerform">Registrati</a></h2>
    <div class="register">
      <div class="arrow-up"></div>
      <div class="formholder">
        <div class="randompad">
           <fieldset>
			<form action="../NuovaServlet" method="post" onSubmit="return validaForm(this);">
             
             <label name="userA">Username</label>
             <input type="username" name="user"/>
             
             <label name="pwdA">Password</label>
             <input type="password" name="pwd1"/>
             
             <label name="pwdB">Conferma password</label>
             <input type="password"  name="pwd2"/>
             
             <label name="dataN">Data di nascita</label>
             <input type="date" name="data">
             
             <input type="submit" value="Registrati" />
             <input type="hidden" name="controllo" value="2">
             </form>
           </fieldset>
        </div>
      </div>
    </div>
    </div>
  </div>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	
</body>
</html>
