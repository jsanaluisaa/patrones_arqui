
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de inquilinos</title>
        <script src="jq/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="js/inquilinos.js" type="text/javascript"></script>
    </head>
    <body>
        <a href="index.html">Regresar</a><br/>
        <c:if test="${message != null}">
            <div>${message}</div>
        </c:if>
        <table border="1">
            <thead>
                <tr>
                    <th>
                        <a href="#" onclick="inquilinosIns();">
                            Agregar                            
                        </a>
                    </th>
                    <th>ID</th>
                    <th>DNI</th>
                    <th>NOMBRES</th>
                    <th>PATERNO</th>
                    <th>MATERNO</th>
                    <th>TELEFONO</th>
                    <th>FECHA DE INGRESO</th>
                    <th>CORREO</th>
                    <th>DEUDAS</th>
                    <th>
                        <a href="#" onclick="inquilinosUpd();">
                            Actualizar                            
                        </a>
                    </th>
                    <th>
                        <a href="#" onclick="inquilinosDel();">
                            Eliminar                         
                        </a>
                    </th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="f" items="${list}">                
                <tr>
                    <td colspan="2">${f.idinquilinos}</td>
                    <td>${f.dni}</td>
                    <td>${f.nombres}</td>
                    <td>${f.paterno}</td>
                    <td>${f.materno}</td>
                    <td>${f.telefono}</td>
                    <td>${f.fecha_ingreso}</td>
                    <td>${f.correo}</td>
                    <td>${f.deuda}</td>
                    <th>
                        <input type="radio" name="id_upd"
                               value="${f.idinquilinos}"/>
                    </th>
                    <th>
                        <input type="checkbox" name="id_del" 
                               value="${f.idinquilinos}"/>
                    </th>
                </tr>
            </c:forEach> 
        </tbody>
    </table>
</body>
</html>