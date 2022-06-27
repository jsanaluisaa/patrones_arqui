<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Actualizar inquilino</h1>
        <form action="Inquilinos" method="post" style="margin: auto; display: table"> 
            <input type="hidden" name="accion" value="UPD"/>
            <input type="hidden" name="idinquilinos" value="${inquilino.idinquilinos}"/>
            <fieldset>
                <legend>Actualizar datos</legend>
                <br/>
                <input type="text" name="dni" style="width: 400px"
                       placeholder="Ingrese el DNI" 
                       value="${inquilino.dni}"/>
                <br/>
                <input type="text" name="nombres" style="width: 400px"
                       placeholder="Ingrese el nombre" 
                       value="${inquilino.nombres}"/>
                <br/>
                <input type="text" name="paterno" style="width: 400px"
                       placeholder="Ingrese el apellido paterno" 
                       value="${inquilino.paterno}"/>
                <br/>
                <input type="text" name="materno" style="width: 400px"
                       placeholder="Ingrese el apellido materno" 
                       value="${inquilino.materno}"/>
                <br/>
                <input type="text" name="telefono" style="width: 400px"
                       placeholder="Ingrese el telefono" 
                       value="${inquilino.telefono}"/>
                <br/>
                <label>Ingrese la fecha de ingreso</label> 
                <input type="date" name="fecha_ingreso" style="width: 400px" 
                       value="${inquilino.fecha_ingreso}"/>
                <br/>
                <label>Ingrese la deuda</label> 
                <input type="number" name="deuda" style="width: 400px" 
                       value="${inquilino.deuda}"/>
                <br/>
                <input type="email" name="correo" style="width: 400px"
                       placeholder="Ingrese el correo" 
                       value="${inquilino.correo}"/>
                <br/>    
                <br/><p style="text-align: center;">
                    <button type="submit">Actualizar</button>
                </p>               
            </fieldset>
        </form>
        <a href="index.html">Regresar</a><br/>
        <c:if test="${message != null}">
            <div>${message}</div>
        </c:if>
    </body>
</html>