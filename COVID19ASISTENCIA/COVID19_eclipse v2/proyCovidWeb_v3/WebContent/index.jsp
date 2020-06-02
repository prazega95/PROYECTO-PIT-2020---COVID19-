<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PROYECTO COVID19</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.slim.js" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous">
<link rel="stylesheet" href="resources/main/css/diseñologin.css">


</head>
<body>


<div class="container-fluid">
  <div class="row no-gutter">
    <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
    <div class="col-md-8 col-lg-6">
      <div class="login d-flex align-items-center py-5">
        <div class="container">
          <div class="row">

            <div class="col-md-9 col-lg-8 mx-auto">
            
              
              <h3 class="login-heading mb-4">SISTEMA DE ADMINISTRACION</h3>
              
      	
            
               <form class="form-sign" action="Validar" method="POST">
             
                <div class="form-label-group">
                  <input type="text" id="inputEmail" name="txtuser" class="form-control" placeholder="Email address" required autofocus>
                  <label for="inputEmail">Usuario</label>
                </div>

                <div class="form-label-group">
                  <input type="password" id="inputPassword" name="txtpass" class="form-control" placeholder="Password" required>
                  <label for="inputPassword">Password</label>
                </div>

            
               
            
            
            

    
                <div class="custom-control custom-checkbox mb-3">
                  <input type="checkbox" class="custom-control-input" id="customCheck1">
                  <label class="custom-control-label" for="customCheck1">Recordar</label>
                </div>
               

                
              <input type="submit" name="accion" value="Ingresar" class="btn btn-primary btn-block">                
                
                
                
 
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>




</body>
</html>