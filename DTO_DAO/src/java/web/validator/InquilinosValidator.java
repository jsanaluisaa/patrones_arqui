package web.validator;
import biblioteca.DeString;
import dao.DaoInquilinos;
import dao.impl.DaoInquilinosImpl;
import dto.Inquilinos;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
public class InquilinosValidator {
    private final HttpServletRequest request;
    private final DaoInquilinos daoInquilinos;
    public InquilinosValidator(HttpServletRequest request) {
        this.request = request;
        this.daoInquilinos = new DaoInquilinosImpl();
    }
    public String inquilinosSel() {
        String result = null;
        List<Inquilinos> list = daoInquilinos.inquilinosSel();
        if (list != null) {
            request.setAttribute("list", list);
        } else {
            result = daoInquilinos.getMessage();
        }
        return result;
    }
    public String inquilinosGet() {
        String result = null;
        Integer idinquilino = DeString.aInteger(request.getParameter("idinquilino"));
        Inquilinos inquilino = daoInquilinos.inquilinosGet(idinquilino);
        if (inquilino != null) {
            request.setAttribute("inquilino", inquilino);
        } else {
            result = daoInquilinos.getMessage();
        }
        return result;
    }
    public String inquilinosInsUpd(boolean upd) {
        StringBuilder result = new StringBuilder("<ul>");
        Integer idinquilino = DeString.aInteger(request.getParameter("idinquilinos"));
        String dni = request.getParameter("dni");
        String nombres = request.getParameter("nombres");
        String paterno = request.getParameter("paterno");
        String materno = request.getParameter("materno");
        String telefono = request.getParameter("telefono");
        LocalDate fecha_ingreso = LocalDate.parse(request.getParameter("fecha_ingreso"));
        String correo = request.getParameter("correo");
        Double deuda = DeString.aDouble(request.getParameter("deuda"));
        if (upd && idinquilino == null) {
            result.append("<li>Id requerido</li>");
        }
        if (nombres == null || nombres.trim().length() == 0) {
            result.append("<li>Nombre requerido</li>");
        } else if (nombres.trim().length() < 3 || nombres.trim().length() > 50) {
            result.append("<li>La dimensión del nombre debe estar entre")
                    .append("3 a 50 caracteres</li>");
        }
        if (paterno == null || paterno.trim().length() == 0) {
            result.append("<li>Apellido paterno requerido</li>");
        } else if (paterno.trim().length() < 3 || paterno.trim().length() > 50) {
            result.append("<li>La dimensión del apellido paterno debe estar entre")
                    .append("3 a 50 caracteres</li>");
        }
        if (materno == null || materno.trim().length() == 0) {
            result.append("<li>Apellido materno requerido</li>");
        } else if (materno.trim().length() < 3 || materno.trim().length() > 50) {
            result.append("<li>La dimensión del apellido materno debe estar entre")
                    .append("3 a 50 caracteres</li>");
        }
        if (fecha_ingreso == null) {
            result.append("<li>Fecha requerida</li>");
        }
        if (deuda < 0d) {
            result.append("<li>La deuda no puede ser negativa</li>");
        }
        Inquilinos inquilino = new Inquilinos();
        inquilino.setIdinquilinos(idinquilino);
        inquilino.setDni(dni);
        inquilino.setNombres(nombres);
        inquilino.setPaterno(paterno);
        inquilino.setMaterno(materno);
        inquilino.setFecha_ingreso(fecha_ingreso);
        inquilino.setTelefono(telefono);
        inquilino.setDeuda(deuda);
        inquilino.setCorreo(correo);
        if (result.length() == 4) {
            String msg = upd
                    ? daoInquilinos.inquilinosUpd(inquilino)
                    : daoInquilinos.inquilinosIns(inquilino);
            if (msg != null) {
                result.append("<li>").append(msg).append("</li>");
            }
        }
        if (result.length() > 4) {
            request.setAttribute("inquilino", inquilino);
        }
        return result.length() == 4 ? null : result.append("</ul>").toString();
    }
    public String inquilinosDel() {
        List<Integer> ids = DeString.ids(request.getParameter("ids"));
        String result = (ids != null)
                ? daoInquilinos.inquilinosDel(ids)
                : "IDs incorrectos";
        return result;
    }
}