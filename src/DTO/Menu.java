/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author asus
 */
public class Menu {

    public void userMenu() {
        System.out.println("1.Login");
        System.out.println("2.Register");
        System.out.println("3.Exit");
    }

    public void adminMenu() {
        System.out.println("1.Create new user");
        System.out.println("2.Set role");
        System.out.println("3.Exit");
    }

    public void hotelClerkMenu() {
        System.out.println("1.View all rooms");
        System.out.println("2.Search/Filter rooms");
        System.out.println("3.Update status of rooms");
        System.out.println("4.Exit");
    }

    public void customerMenu() {
        System.out.println("1.Booking rooms");
        System.out.println("2.Exit");
    }
// 123
}
