package spingame;

/**
 * Angela Petsuch
 * 
 *
 */
public class SpinGame {

    public static void main(String[] args) {

        System.out.println("Create a new Spinner!");
        System.out.println();
        // get info for new spinner object
        String name = Console.getString("What would you like to name the Spinner?: ");
        int numberFields = Console.getInt("How many slots do you want for this Spinner?: ");
        
        // create spinner object from user input
        Spinner spinner = new Spinner(name, numberFields);

             // create array
        SpinnerField[] fields = new SpinnerField[numberFields];
        // get data
        for(int i = 0; i < fields.length; i++) {
            String content = Console.getString("Field " + (i+1) + ": ");
            
            fields[i] = new SpinnerField(content); 
        }
        System.out.println();
        System.out.println(spinner.toString());
        System.out.println("**********************");
        for(SpinnerField f : fields) {
            System.out.println(f.toString());
        }
    } // end main method
} // end main class
