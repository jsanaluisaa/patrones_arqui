package web.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.validator.InquilinosValidator;

@WebServlet(name = "InquilinosServlet", urlPatterns = {"/Inquilinos"})
public class InquilinosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        accion = (accion == null) ? "" : accion;
        String result;
        String target = "inquilinosSel.jsp";
        InquilinosValidator validator = new InquilinosValidator(request);
        switch (accion) {
            case "SEL":
                result = validator.inquilinosSel();
                break;
            case "INS":
                result = validator.inquilinosInsUpd(false);
                target = result == null ? "inquilinos.jsp" : "inquilinosIns.jsp";
                break;
            case "DEL":
                result = validator.inquilinosDel();
                target = "inquilinos.jsp";
                break;
            case "GET":
                result = validator.inquilinosGet();
                target = "inquilinosUpd.jsp";
                break;
            case "UPD":
                result = validator.inquilinosInsUpd(true);
                target = result == null ? "inquilinos.jsp" : "inquilinosUpd.jsp";
                break;
            case "":
                result = "Solicitud requerida";
                break;
            default:
                result = "Solicitud no reconocida";
        }
        if (result != null) {
            request.setAttribute("message", result);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
