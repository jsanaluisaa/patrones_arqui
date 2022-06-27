/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PXNDX
 */
public class ConectaBD {
    private final String URL="jdbc:postgresql://localhost:5432/blog";
    private final String DRIVER="org.postgresql.Driver";
    private final String USER="postgres";
    private final String PASS="DNSLOOKUP";
    
    public Connection conexionDB() throws SQLException{
        Connection c=null;
            try {
                Class.forName(DRIVER).newInstance();
                c= DriverManager.getConnection(URL, USER, PASS);
             } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
                 throw new SQLException(e.getMessage());
             }
        return c;
    }
}
