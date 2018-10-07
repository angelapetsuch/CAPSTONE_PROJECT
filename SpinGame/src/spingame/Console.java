
package spingame;

import java.util.Scanner;

/**
 *
 * @author angela
 */
// HELPER CLASS

// A helper class that automatically reads input from the console 
// by creating a Scanner object and determines its data type

// in the future, you could add a new method that addresses a type of input
// (data type) that isnt on here yet so that you can use it for whatever
// you need
public class Console {
    
    private static Scanner sc = new Scanner(System.in);
    
    public static String getString(String prompt) {
        System.out.print(prompt); // accepts whatever prompt is passed
        String s = sc.nextLine(); // store input as "s"
        return s; // return stored data
    }
    
    public static int getInt(String prompt) {
        // handles integers and validates that it truly is an int
        int i = 0;
        boolean isValid = false;

        while(!isValid) {
            System.out.print(prompt);
            if(sc.hasNextInt()) {
                i = sc.nextInt();
                isValid = true;
            } else {
                System.out.println("Error - enter a valid integer");
            }
            sc.nextLine(); // disregard old input that was wrong
        }
        return i;
    }
    
    
    
    
    
}
