package com.colegio.maventulio.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static Connection con = null;

    public static Connection getConnection() throws SQLException {
        try {
            if (con == null) {
                String driver = "com.mysql.jdbc.Driver"; //el driver varia segun la DB que usemos
                String url = "jdbc:mysql://localhost:3306/bdmatricula";
                String pwd = "";
                String usr = "root";
                Class.forName(driver);
                con = DriverManager.getConnection(url, usr, pwd);
//                System.out.println("Conectionesfull");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }

//    public static void main(String[] args) throws SQLException {
//        Connection conn = Conexion.getConnection();
//        
//        System.out.println("EXITOS");
//    }
    
}
