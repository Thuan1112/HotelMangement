/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author asus
 */
public class MyConnection {

    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=hotelmanagement;user=THUAN;password=123456";
    private static Connection con = null;

    public static Connection makeconnection(){
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            System.out.println("Lost connection with SQL!!!!");
        }
        return con;
    }
}
