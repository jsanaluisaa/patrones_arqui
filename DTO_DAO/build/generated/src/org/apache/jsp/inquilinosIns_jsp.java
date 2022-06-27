package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class inquilinosIns_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Agregar inquilino</h1>\n");
      out.write("        <form action=\"Inquilinos\" method=\"post\" style=\"margin: auto; display: table\"> \n");
      out.write("            <input type=\"hidden\" name=\"accion\" value=\"INS\"/>\n");
      out.write("            <fieldset>\n");
      out.write("                <legend>Agregar datos</legend>\n");
      out.write("                <br/>\n");
      out.write("                <input type=\"text\" name=\"dni\" style=\"width: 400px\"\n");
      out.write("                       placeholder=\"Ingrese el DNI\"/>\n");
      out.write("                <br/>\n");
      out.write("                <input type=\"text\" name=\"nombres\" style=\"width: 400px\"\n");
      out.write("                       placeholder=\"Ingrese el nombre\"/>\n");
      out.write("                <br/>\n");
      out.write("                <input type=\"text\" name=\"paterno\" style=\"width: 400px\"\n");
      out.write("                       placeholder=\"Ingrese el apellido paterno\"/>\n");
      out.write("                <br/>\n");
      out.write("                <input type=\"text\" name=\"materno\" style=\"width: 400px\"\n");
      out.write("                       placeholder=\"Ingrese el apellido materno\"/>\n");
      out.write("                <br/>\n");
      out.write("                <input type=\"text\" name=\"telefono\" style=\"width: 400px\"\n");
      out.write("                       placeholder=\"Ingrese el telefono\"/>\n");
      out.write("                <br/>\n");
      out.write("                <label>Ingrese la fecha de ingreso</label> \n");
      out.write("                <input type=\"date\" name=\"fecha_ingreso\" style=\"width: 400px\"/>\n");
      out.write("                <br/>\n");
      out.write("                <label>Ingrese la deuda</label> \n");
      out.write("                <input type=\"number\" name=\"deuda\" style=\"width: 400px\"/>\n");
      out.write("                <br/>\n");
      out.write("                <input type=\"email\" name=\"correo\" style=\"width: 400px\"\n");
      out.write("                       placeholder=\"Ingrese el correo\"/>\n");
      out.write("                <br/>    \n");
      out.write("                <br/><p style=\"text-align: center;\">\n");
      out.write("                    <button type=\"submit\">Agregar</button>\n");
      out.write("                </p>               \n");
      out.write("            </fieldset>\n");
      out.write("        </form>\n");
      out.write("        <a href=\"index.html\">Regresar</a><br/>\n");
      out.write("        <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${message != null}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("            <div>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${message}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</div>\n");
      out.write("        </c:if>\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
