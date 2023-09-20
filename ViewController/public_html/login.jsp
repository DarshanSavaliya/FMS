<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Login</title>
        <style>
                html {
                    visibility: hidden;
                }
                .center {
                  margin: auto;
                  width: 60%;
                  border: 3px solid cyan;
                  padding: 10px;
                }
            </style>
        <script>
          if (self == top) {
              document.documentElement.style.visibility = 'visible';
          }
          else {
              top.location = self.location;
          }
        </script>
        
    </head>
    <% response.addHeader("X-FRAME-OPTIONS", "SAMEORIGIN"); %>
    <body>
    <div>
        <h1> <a href="/FlightManagementSystem-ViewController-context-root/faces/home">SkyJet Airlines</a> </h1> 
        <div style="justify-content:right; display: flex;">
        </div>
    </div>
            
        <% if(request.getMethod().equals( "POST")) {
        out.print("Invalid username or password!");
        }
        %>
         <div class="center">
            
            <form style="justify-content:center; display: flex;" method="POST" action="j_security_check" autocomplete="off">
            <div>
            <table cellspacing="2" cellpadding="3">
                <tr>
                    <th>Username:</th>
                    <td>
                        <input type="text" name="j_username" autocomplete="off"/>
                    </td>
                </tr>
                 
                <tr>
                    <th>Password:</th>
                    <td>
                        <input type="password" name="j_password" autocomplete="off"/>
                    </td>
                </tr>
            </table>
            </div>
            <br> <br> <br>
            <div>
            <p>
                <input type="submit" name="submit" value="Submit"/>
            </p>
            </div>
            
        </form>
        </div>
    </body>
</html>