/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.User;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author asus
 */
public class UserDAO implements Serializable{
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rst;

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
    
    public User getUserByID(String userName) throws Exception {
        User dto = null;
        try {
            String sql = "Select userName From tblUser Where userName = ?";
            con = MyConnection.makeconnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, userName);
            rst = pst.executeQuery();
            if(rst.next()) {
                dto = new User();
                dto.setUsername(rst.getString("userName"));
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public boolean updateRole(String userName, int roleID) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tblUser Set idRole = ? Where userName = ?";
            con = MyConnection.makeconnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, roleID);
            pst.setString(2, userName);
            check =  pst.executeUpdate() > 0;
            
        } finally {
            closeConnection();
        }
        return check;
    }
}
