<%-- 
    Document   : error
    Created on : 23 mar 2026, 2:40:33 p.m.
    Author     : fer_g
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Murach´s Java Servlets and JSP - Error Page</title>
        
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
        
        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col">
                    
                    <h1>Error</h1>
                    <p>${mensaje}</p>
                    <div class="alert alert-danger mt-2" role="alert">
                        ${error}
                    </div>
                    
                    <p>To enter another email address, click on the Back<br>
                        button  in your browser or the Return button show below.</p>
                    
                    <form action="" method="get">
                        <input type="hidden" name="action" value="join">
                        <input type="submit" class="btn btn-primary" value="Return">
                    </form>
                    
                </div>
            </div>
        </div>
                   
    </body>
</html>
