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
public class DButil {

    /**
     * @param args the command line arguments
     */
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rst;
    static Scanner sc = new Scanner(System.in);

    public static void closeConnection() throws Exception {
        if (rst != null) {
            rst.close();
        }
        if (con != null) {
            con.close();
        }
        if (pst != null) {
            pst.close();
        }
    }

    public String checkLogin(String userName, String pass) throws Exception {
        String result = "failed";
        try {
            String sql = "SELECT r.role FROM tblUser u join tblRole r on u.idRole = r.idRole \n"
                    + "WHERE userName = ? AND pass = ?";
            con = MyConnection.makeconnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, userName);
            pst.setString(2, pass);
            rst = pst.executeQuery();
            if (rst.next()) {
                result = rst.getString("role");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public String checkRegister(String userName, String pass, int roleID) throws Exception {
        String result = "failed";
        try {
            con = MyConnection.makeconnection();
            String sql = "Insert into tblUser (userName,pass,idRole) values(?,?,?) ";
            pst = con.prepareStatement(sql);
            pst.setString(1, userName);
            pst.setString(2, pass);
            pst.setInt(3, roleID);
            boolean check = false;
            check = pst.executeUpdate() > 0;

            if (check) {
                System.out.println("Add successfull");
            } else {
                System.out.println("Please enter infomation again");
            }

        } catch (Exception e) {
            if (e.getMessage().contains("duplicate")) {
                System.out.println("This username is existed !");
            } else {
                System.out.println("Disconnection Fail");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

}
