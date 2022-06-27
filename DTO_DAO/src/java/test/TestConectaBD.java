/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import biblioteca.ConectaBD;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author PXNDX
 */
public class TestConectaBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConectaBD bD =new ConectaBD();
        try(Connection c=bD.conexionDB()) {
            System.out.println("Operacion Existosa");
        } catch (SQLException e) {
            System.out.println(" "+e.getMessage());
        }
    }
    
    
}
