package spingame;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Angela Petsuch
 * 
 *
 */
public class SpinGame {

    public static void main(String[] args) throws IOException {
        /////////* TESTING STUFF *///////////
        ////////*****************///////////
        
        // load the spinners if it exists
        ArrayList<Spinner> spinners = IOUtil.load();
        
        getSpinnerNames(spinners);

        System.out.println("Create a new Spinner!");
        System.out.println();
        // get info for new spinner object
        String name = Console.getString("What would you like to name the Spinner?: ");
        int numberFields = Console.getInt("How many slots do you want for this Spinner?: ");
        
        // create spinner object from user input
        Spinner spinner = new Spinner(name, numberFields);
        ArrayList <String> fields = new ArrayList<>();
        
        getFieldContent(numberFields, fields);
        
        spinner.setField(fields); // save fields to spinner object
        spinners.add(spinner); // add spinner object to arraylist
        IOUtil.save(spinners);        
    } // end main method

    private static void getFieldContent(int numberFields, ArrayList<String> fields) {
        // get field info from user
        for (int i = 0; i < numberFields; i++) {
            String field = Console.getString("Field " + (i+1) + ": ");
            fields.add(field);
        } 
    } // end getFieldContent method

    private static void getSpinnerNames(ArrayList<Spinner> spinners) {
        // display spinner names
        int numSpinners = spinners.size();
        
        for (int i = 0; i < numSpinners; i++) {
            Spinner spinner = spinners.get(i);
            System.out.println(spinner.getSpinnerName());
        }
    } // end getSpinnerNames method
} // end main class
