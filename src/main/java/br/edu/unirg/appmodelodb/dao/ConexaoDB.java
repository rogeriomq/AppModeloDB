/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.appmodelodb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author man1gold
 */
public class ConexaoDB {
    private Connection con;
    public Connection connect() {
        if (con == null) {
            try {
                String strJDBC = "jdbc:h2:/home/man1gold/NetBeansProjects/AppModeloDB/banco/appModeloDB;AUTO_SERVER=TRUE;IFEXISTS=TRUE;MODE=MYSQL";
                Class.forName("org.h2.Driver");
                con = DriverManager.getConnection(strJDBC, "sa", "sa");
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return con;
    }
}


