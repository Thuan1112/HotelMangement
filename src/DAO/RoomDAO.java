/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Room;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class RoomDAO implements Serializable {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public boolean updateStatus(String roomID, boolean status) throws Exception {
        boolean check = false;
        try {
            connection = MyConnection.makeconnection();
            if (connection != null) {
                String sql = "UPDATE tblRoom "
                        + "SET Status=? "
                        + "WHERE RoomID=?";
                ps = connection.prepareStatement(sql);
                ps.setBoolean(1, status);
                ps.setString(2, roomID);
                check = ps.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public Room getRoomByID(String roomID) throws Exception {
        Room room = null;
        try {
            connection = MyConnection.makeconnection();
            if (connection != null) {
                String sql = "select roomID,price,description,status from tblRoom where roomID = ? ";
                ps = connection.prepareStatement(sql);
                ps.setString(1, roomID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    room = new Room();
                    room.setRoomID(rs.getString("roomID"));
                    room.setPrice(rs.getFloat("price"));
                    room.setDescription(rs.getString("description"));
                    room.setStatus(rs.getBoolean("status"));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return room;
    }
    
     public List<Room> getAllRoom() throws Exception {
        List<Room> rooms = null;
        try {
            connection = MyConnection.makeconnection();
            if (connection != null) {
                String sql = "select roomID,price,description,status from tblRoom ";
                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery();
                rooms = new ArrayList<>();
                while (rs.next()) {
                    Room room = new Room();
                    room.setRoomID(rs.getString("roomID"));
                    room.setPrice(rs.getFloat("price"));
                    room.setDescription(rs.getString("description"));
                    room.setStatus(rs.getBoolean("status"));
                    rooms.add(room);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return rooms;
    }

    public List<Room> getAllRoomEmpty() throws Exception {
        List<Room> rooms = null;
        try {
            connection = MyConnection.makeconnection();
            if (connection != null) {
                String sql = "select roomID,price,description,status from tblRoom Where status = 0";
                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery();
                rooms = new ArrayList<>();
                while (rs.next()) {
                    Room room = new Room();
                    room.setRoomID(rs.getString("roomID"));
                    room.setPrice(rs.getFloat("price"));
                    room.setDescription(rs.getString("description"));
                    room.setStatus(rs.getBoolean("status"));
                    rooms.add(room);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return rooms;
    }
    
}
