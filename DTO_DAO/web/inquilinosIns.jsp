<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        <h1>Agregar inquilino</h1>
        <form action="Inquilinos" method="post" style="margin: auto; display: table"> 
            <input type="hidden" name="accion" value="INS"/>
            <fieldset>
                <legend>Agregar datos</legend>
                <br/>
                <input type="text" name="dni" style="width: 400px"
                       placeholder="Ingrese el DNI"/>
                <br/>
                <input type="text" name="nombres" style="width: 400px"
                       placeholder="Ingrese el nombre"/>
                <br/>
                <input type="text" name="paterno" style="width: 400px"
                       placeholder="Ingrese el apellido paterno"/>
                <br/>
                <input type="text" name="materno" style="width: 400px"
                       placeholder="Ingrese el apellido materno"/>
                <br/>
                <input type="text" name="telefono" style="width: 400px"
                       placeholder="Ingrese el telefono"/>
                <br/>
                <label>Ingrese la fecha de ingreso</label> 
                <input type="date" name="fecha_ingreso" style="width: 400px"/>
                <br/>
                <label>Ingrese la deuda</label> 
                <input type="number" name="deuda" style="width: 400px"/>
                <br/>
                <input type="email" name="correo" style="width: 400px"
                       placeholder="Ingrese el correo"/>
                <br/>    
                <br/><p style="text-align: center;">
                    <button type="submit">Agregar</button>
                </p>               
            </fieldset>
        </form>
        <a href="index.html">Regresar</a><br/>
        <c:if test="${message != null}">
            <div>${message}</div>
        </c:if>
    </body>
</html>