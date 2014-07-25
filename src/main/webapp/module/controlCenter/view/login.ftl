
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>登录控制中心</title>
    
    <base href="${request_context.basePath}module/" />

    
    <@res resid="bootstrap" include=".*\\.css" />
    <@res resid="jquery" />
    
    <style>
    	body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}

.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  height: auto;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
    </style>
    
    
  </head>

  <body>

    <div class="container">
    
    <#if loginLoad_errorMessage?? > 
    	<div style="height: 200px; text-align: center; font-size: 20px;" >
    		${loginLoad_errorMessage }
    	</div>
    <#else>

      <form class="form-signin" role="form" action="controlCenter/dologin.do" >
        <h2 class="form-signin-heading">Please sign in</h2>
        <input name="username" type="username" value="${username!""}" class="form-control" placeholder="Username" required autofocus>
        <input name="password" type="password" value="${password!""}" class="form-control" placeholder="Password" required>
        <div>${errorMessage!"" }</div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
      
    </#if>

    </div> <!-- /container -->



    <@res resid="bootstrap" include=".*\\.js" />
    
  </body>
</html>
