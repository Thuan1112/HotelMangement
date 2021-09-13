package HotelManagement;

import DAO.DButil;
import DAO.RoomDAO;
import DAO.UserDAO;
import DTO.Menu;
import java.util.ArrayList;
import java.util.Scanner;
import validdation.CheckValidation;
import DTO.Room;
import DTO.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author asus
 */
public class Manager {

    static Scanner sc = new Scanner(System.in);
    Menu m = new Menu();
    RoomDAO rd = new RoomDAO();

    public void login() throws Exception {
        try {
            System.out.println("Enter user name:");
            String userName = sc.nextLine();
            System.out.println("Enter pass:");
            String pass = sc.nextLine();
            if (userName.equals("") || pass.equals("")) {
                System.err.println("Please enter infomation again");
                return;
            } else {
                DButil dao = new DButil();
                String role = dao.checkLogin(userName, pass);

                if (role.equals("admin")) {
                    System.out.println("Welcome admin");
                    doAdmin();
                } else if (role.equals("hotelClerk")) {
                    System.out.println("Welcome clerk");
                    doClerk();
                } else if (role.equals("customer")) {
                    System.out.println("Welcome customer");
                    doCustomer();
                }
            }

        } finally {

        }

    }

    public void register() throws Exception {
        try {
            System.out.println("Enter user name:");
            String userName = sc.nextLine();
            System.out.println("Enter pass:");
            String pass = sc.nextLine();
            if (userName.equals("") || pass.equals("")) {
                System.err.println("Please enter infomation again");
                return;
            } else {
                DButil dao = new DButil();
                String check = dao.checkRegister(userName, pass, 3);
            }

        } finally {

        }

    }

    public void doAdmin() throws Exception {
        CheckValidation check = new CheckValidation();
        int choice = 0;

        do {
            m.adminMenu();
            System.out.print("Enter choice: ");
            choice = check.checkInputNumber(1, 3);

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    updateRole();
                    break;

            }
        } while (choice > 0 && choice < 3);
    }

    public void createUser() throws Exception {
        try {
            System.out.println("Enter user name:");
            String userName = sc.nextLine();
            System.out.println("Enter pass:");
            String pass = sc.nextLine();
            if (userName.equals("") || pass.equals("")) {
                System.err.println("Please enter infomation again");
                return;
            } else {
                System.out.println("Choose Role");
                System.out.println("1. Admin");
                System.out.println("2. Hotel Clerk");
                System.out.println("3. Customer");
                CheckValidation check = new CheckValidation();
                int role = check.checkInputNumber(1, 3);
                DButil dao = new DButil();
                dao.checkRegister(userName, pass, role);
            }

        } finally {

        }

    }

    public void updateRole() throws Exception {
        System.out.println("Enter user name change role:");
        String userName = sc.nextLine();
        UserDAO userDAO = new UserDAO();
        User dto = userDAO.getUserByID(userName);
        if (dto!= null) {
            System.out.println("Choose Role");
                System.out.println("1. Admin");
                System.out.println("2. Hotel Clerk");
                System.out.println("3. Customer");
                CheckValidation check = new CheckValidation();
                int role = check.checkInputNumber(1, 3);
                if (userDAO.updateRole(userName, role)) {
                    System.out.println("Change role succesfully");
                } else {
                    System.out.println("Change role failed");
                }
        } else {
            System.out.println("User not existed");
        }
    }

    public void doClerk() throws Exception {
        CheckValidation check = new CheckValidation();
        int choice = 0;

        do {
            m.hotelClerkMenu();
            System.out.print("Enter choice: ");
            choice = check.checkInputNumber(1, 4);

            switch (choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    searchRoom();
                    break;
                case 3:
                    updateRooms();
                    break;

            }
        } while (choice > 0 && choice < 4);

    }

    public void viewRooms() throws Exception {
        ArrayList<Room> listRoom = (ArrayList<Room>) rd.getAllRoom();
        System.out.println("|======================ROOMS======================|");
        System.out.println("|  ID  |   Price   |    Description    |  Status  |");
        for (Room room : listRoom) {
            System.out.printf("|  %-4s|   %-8.1f|    %-15s|  %-8b|\n",
                    room.getRoomID(),
                    room.getPrice(),
                    room.getDescription(),
                    room.isStatus());
        }
        System.out.println("|=================================================|");
    }

    public void updateRooms() throws Exception {
        CheckValidation check = new CheckValidation();
        viewRooms();
        System.out.println("Enter RoomID to change : ");
        String roomID = sc.nextLine();
        if (rd.getRoomByID(roomID) == null) {
            System.out.println("Can't change");
            return;
        }
        System.out.println("1 . Change to true");
        System.out.println("2 . Change to false");
        int choice = check.checkInputNumber(1, 2);
        boolean changeStatus = false;
        switch (choice) {
            case 1:
                changeStatus = true;
                break;
            case 2:
                changeStatus = false;
                break;
        }
        if (rd.updateStatus(roomID, changeStatus)) {
            System.out.println("Update succesfully");
        }
    }

    public void searchRoom() throws Exception {
        System.out.println("Enter RoomID to change : ");
        String roomID = sc.nextLine();
        Room searchRoom = rd.getRoomByID(roomID);
        if (searchRoom == null) {
            System.out.println("Don't have this room !");
            return;
        } else {
            System.out.printf("|  %-4s|   %-8.1f|    %-15s|  %-8b|\n",
                    searchRoom.getRoomID(),
                    searchRoom.getPrice(),
                    searchRoom.getDescription(),
                    searchRoom.isStatus());
        }
    }

    public void doCustomer() throws Exception {
        CheckValidation check = new CheckValidation();
        int choice = 0;

        do {
            m.customerMenu();
            System.out.print("Enter choice: ");
            choice = check.checkInputNumber(1, 2);

            switch (choice) {
                case 1:
                    bookRoom();
                    break;
                case 2:
                    return;
            }
        } while (choice > 0 && choice < 2);

    }

    public void bookRoom() throws Exception {
        ArrayList<Room> listRoom = (ArrayList<Room>) rd.getAllRoomEmpty();
        System.out.println("|======================ROOMS======================|");
        System.out.println("|  ID  |   Price   |    Description    |  Status  |");
        for (Room room : listRoom) {
            System.out.printf("|  %-4s|   %-8.1f|    %-15s|  %-8b|\n",
                    room.getRoomID(),
                    room.getPrice(),
                    room.getDescription(),
                    room.isStatus());
        }
        System.out.println("|=================================================|");
        System.out.println("Enter RoomID to book : ");
        String roomID = sc.nextLine();
        boolean check = true;
        Room roomTmp = rd.getRoomByID(roomID);
        if (roomTmp != null) {
            check = roomTmp.isStatus();
        }

        if (check) {
            System.out.println("Can't book");
            return;
        }
        if (rd.updateStatus(roomID, true)) {
            System.out.println("Booking successfully");
        } else {
            System.out.println("Booking failed !");
        }

    }

}
