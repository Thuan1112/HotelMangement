/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validdation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author asus
 */
public class CheckValidation {
    private static final Scanner sc = new Scanner(System.in);
//    private static final String PHONE_REGEX = "^\\d{10}\\d*$";
//    private static final String EMAIL_REGEX = "^[A-Za-z0-9.+-_%]+@[A-Za-z.-]+\\.[A-Za-z]{2,4}$";
                    
            

    public int checkInputNumber(int minNum, int maxNum) {
        int num = 0;
        boolean valid;

        do {
            valid = true;
            try {
                num = Integer.parseInt(sc.nextLine());
                if (num < minNum || num > maxNum) {
                    System.out.println("Invalid input!");
                    System.out.print("Input again: ");
                    valid = false;
                }
            } catch (Exception e) {
                System.out.println("Please input number in rage [" + minNum + ", " + maxNum + "]");
                System.out.print("Input again: ");
                valid = false;
            }
        } while (!valid);
        return num;

    }

    public String checkInputString() {
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }



}
