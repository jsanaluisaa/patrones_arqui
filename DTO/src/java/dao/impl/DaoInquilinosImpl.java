package dao.impl;

import biblioteca.ConectaBD;
import dao.DaoInquilinos;
import dto.Inquilinos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DaoInquilinosImpl implements DaoInquilinos {

    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoInquilinosImpl() {
        this.conectaDb = new ConectaBD();
    }

    @Override
    public List<Inquilinos> inquilinosSel() {
        List<Inquilinos> list = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("idinquilinos,")
                .append("dni,")
                .append("nombres,")
                .append("paterno,")
                .append("materno,")
                .append("telefono,")
                .append("correo,")
                .append("deuda,")
                .append("fecha_ingreso")
                .append(" FROM inquilinos");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                Inquilinos inquilinos = new Inquilinos();
                inquilinos.setIdinquilinos(rs.getInt(1));
                inquilinos.setDni(rs.getString(2));
                inquilinos.setNombres(rs.getString(3));
                inquilinos.setPaterno(rs.getString(4));
                inquilinos.setMaterno(rs.getString(5));
                inquilinos.setTelefono(rs.getString(6));
                inquilinos.setCorreo(rs.getString(7));
                inquilinos.setDeuda(rs.getDouble(8));
                inquilinos.setFecha_ingreso(LocalDate.parse(rs.getString(9)));
                list.add(inquilinos);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return list;
    }

    @Override
    public Inquilinos inquilinosGet(Integer id) {
        Inquilinos inquilino = new Inquilinos();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("idinquilinos,")
                .append("dni,")
                .append("nombres,")
                .append("paterno,")
                .append("materno,")
                .append("telefono,")
                .append("correo,")
                .append("deuda,")
                .append("fecha_ingreso")
                .append(" FROM inquilinos WHERE idinquilinos = ?");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    inquilino.setIdinquilinos(rs.getInt(1));
                    inquilino.setDni(rs.getString(2));
                    inquilino.setNombres(rs.getString(3));
                    inquilino.setPaterno(rs.getString(4));
                    inquilino.setMaterno(rs.getString(5));
                    inquilino.setTelefono(rs.getString(6));
                    inquilino.setCorreo(rs.getString(7));
                    inquilino.setDeuda(rs.getDouble(8));
                    inquilino.setFecha_ingreso(LocalDate.parse(rs.getString(9)));
                } else {
                    inquilino = null;
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return inquilino;
    }

    @Override
    public String inquilinosIns(Inquilinos inquilino) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO inquilinos( ")
                .append("dni,")
                .append("nombres,")
                .append("paterno,")
                .append("materno,")
                .append("telefono,")
                .append("correo,")
                .append("deuda,")
                .append("fecha_ingreso")
                .append(") VALUES (?,?,?,?,?,?,?,?) ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, inquilino.getDni());
            ps.setString(2, inquilino.getNombres());
            ps.setString(3, inquilino.getPaterno());
            ps.setString(4, inquilino.getMaterno());
            ps.setString(5, inquilino.getTelefono());
            ps.setString(6, inquilino.getCorreo());
            ps.setDouble(7, inquilino.getDeuda());
            ps.setString(8, inquilino.getFecha_ingreso().toString());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                mensaje = "cero filas insertadas";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String inquilinosDel(List<Integer> ids) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM inquilinos WHERE ")
                .append("idinquilinos = ? ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            cn.setAutoCommit(false);
            boolean ok = true;
            for (int id = 0; id < ids.size(); id++) {
                ps.setInt(1, ids.get(id));
                int ctos = ps.executeUpdate();
                if (ctos == 0) {
                    ok = false;
                    mensaje = "ID: " + ids.get(id) + " no existe";
                }
            }
            if (ok) {
                cn.commit();
            } else {
                cn.rollback();
            }
            cn.setAutoCommit(true);
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String inquilinosUpd(Inquilinos inquilino) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE inquilinos SET ")
                .append("dni = ?,")
                .append("nombres = ?,")
                .append("paterno = ?,")
                .append("materno = ?,")
                .append("telefono = ?,")
                .append("correo = ?,")
                .append("deuda = ?,")
                .append("fecha_ingreso = ? ")
                .append("WHERE idinquilinos = ? ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, inquilino.getDni());
            ps.setString(2, inquilino.getNombres());
            ps.setString(3, inquilino.getPaterno());
            ps.setString(4, inquilino.getMaterno());
            ps.setString(5, inquilino.getTelefono());
            ps.setString(6, inquilino.getCorreo());
            ps.setDouble(7, inquilino.getDeuda());
            ps.setString(8, inquilino.getFecha_ingreso().toString());
            ps.setInt(9, inquilino.getIdinquilinos());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                mensaje = "No se pudo actualizar";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
