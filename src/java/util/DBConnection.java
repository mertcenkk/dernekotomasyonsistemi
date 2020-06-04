/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author kmert
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class DBConnection {
    private Connection connection;
    
    public Connection connect() {
      
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.connection= DriverManager.getConnection("jdbc:mariadb://localhost:3306/dernek", "root", "1234");
            System.out.println("Başarılı db");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return this.connection;
    }
}
