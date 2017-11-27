<html>
<head>
<title></title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript">
 	
	 	function  validaForm(form) {	 			
			if(form.username.value=="" || form.password.value==""){
				alert("inserire username e password");
			    form.username.focus();
			    return false;
			}
			else{
				return true;
			}
	 	}
	 	
 	</script>
</head>
<body>
<body>
	<div id="wrap">
		<div id="regbar">
			<div id="navthing">
				<h2>
					<a href="#" id="loginform">Login</a> | <a
						href="view/registrazione.jsp">Registrati</a>
				</h2>
				<div class="login">
					<div class="arrow-up"></div>
					<div class="formholder">
						<div class="randompad">
							<fieldset>
								<form action="NuovaServlet" method="post"
									onSubmit="return validaForm(this);">
									<label name="user">Username</label> <input type="username"
										name="username" /> <label name="password">Password</label> <input
										type="password" name="password" /> <input type="submit"
										value="Login" /> <input type="hidden" name="controllo"
										value="1">
								</form>
							</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script src="js/index.js"></script>
	
	<img alt="" src="img/footer.jpg" align="bottom" style="margin-top: 40%; margin-left: 0%" width="100%">
	
</body>
</html>
