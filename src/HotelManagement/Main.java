/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HotelManagement;

import DAO.DButil;
import DTO.Menu;
import validdation.CheckValidation;

/**
 *
 * @author asus
 */
public class Main {

    public static void main(String[] args) throws Exception {
        CheckValidation check = new CheckValidation();
        Menu menu = new Menu();
        int choice = 0;
        Manager m = new Manager();
        do {
            menu.userMenu();
            System.out.print("Enter choice: ");
            choice = check.checkInputNumber(1, 3);
            
            switch (choice) {
                case 1:
                    m.login();
                    break;
                case 2:
                    m.register();
                    break;

            }
        } while (choice > 0 && choice < 3);
    }
    
    

}
