
package testing;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mecic
 */
public class FXMLSettingsController implements Initializable {
       
    // the combo box for the spinner names
    @FXML
    private ComboBox categoryComboBox;
    
    // the scroll pane that holds the gridPane
    @FXML
    private ScrollPane gridContainer;
    
    // horizontal container that holds the vertical boxes
    @FXML
    private HBox gridHBox;
    
    // vertical container that holds the spinner name label and textfield
    @FXML
    private VBox vBoxSpinnerName;
    
    // vertical container that holds the field count label and textfield
    @FXML
    private VBox vBoxFieldCount;
    
    // button for saving the spinner
    @FXML
    private Button saveSpinner;
    
    @FXML
    private Button deleteSpinner;
    
    @FXML
    private Button addField;
    
    // this method is triggered when the back button is clicked
    @FXML
    public void mainSceneButtonAction(ActionEvent event) throws IOException {
        Parent stage = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(stage, 1000, 600);
        scene.getStylesheets().add("/styles/styles.css");
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    
    // this is the method that saves the currently displayed spinner
    public void saveSpinner() {
        int selectedSpinner = categoryComboBox.getSelectionModel().getSelectedIndex();
        
        TextField spinnerNameTF = (TextField)vBoxSpinnerName.getChildren().get(1);
        TextField numberOfFieldsTF = (TextField)vBoxFieldCount.getChildren().get(1);
        String spinnerName = spinnerNameTF.getText();
        Integer numberOfFields = Integer.parseInt(numberOfFieldsTF.getText());
        
        GridPane grid = (GridPane)gridContainer.getContent();
        
        ArrayList<Spinner> spinnersArray = IOUtil.load();
        Spinner newSpinner = new Spinner(spinnerName, numberOfFields);
        ArrayList<String> fields = new ArrayList<>();
        
        for(int i = 0; i < numberOfFields * 2; i++) {
            if(i % 2 != 0) {
                TextField currentTextField = (TextField)grid.getChildren().get(i);
                fields.add(currentTextField.getText());
            }
        }
        
        newSpinner.setField(fields); // save fields to spinner object
        //spinnersArray.remove(selectedSpinner);
        if(selectedSpinner != spinnersArray.size()) {
            spinnersArray.remove(selectedSpinner);
            spinnersArray.add(selectedSpinner, newSpinner);
        }
        else {
            spinnersArray.add(newSpinner); // add spinner object to arraylist
        }
        
        try {
            IOUtil.save(spinnersArray); 
        } catch (IOException ex) {
            Logger.getLogger(FXMLSettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<Spinner> spinners = loadALL();
        
        categoryComboBox.setItems(createString());
        categoryComboBox.getSelectionModel().select(selectedSpinner);
        createTextFields(selectedSpinner, spinners); 
    }
    
    public void deleteSpinner() {
        int selectedSpinner = categoryComboBox.getSelectionModel().getSelectedIndex();
        
        ArrayList<Spinner> spinnersArray = IOUtil.load();
        
        if (selectedSpinner != spinnersArray.size()) {
            spinnersArray.remove(selectedSpinner);
            try {
                IOUtil.save(spinnersArray); 
            } catch (IOException ex) {
                Logger.getLogger(FXMLSettingsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
        }
        
        categoryComboBox.setItems(createString());
        vBoxSpinnerName.getChildren().clear();
        vBoxFieldCount.getChildren().clear();
        GridPane grid = (GridPane)gridContainer.getContent();
        grid.getChildren().clear(); 
    }
    
    //this method gets called when a spinner drop down option is selected.
    //this method builds a gridpane dynamically with all of the item labels and textfields
    //int x is the selected index of the dropdown
    //spinners is the array list of spinners
    private GridPane createTextFields(int x, ArrayList<Spinner> spinners){
        vBoxSpinnerName.getChildren().clear();
        vBoxFieldCount.getChildren().clear();
        
        //creating a instance of a spinner
        Spinner spinner;
        
        //this is necessary due to when we change the combobox list
        //the combobox index is set to -1 for a fraction of a second while the list is being overriden
        //when the index changes this code tries to execute then explodes when it sees -1
        try {
            spinner = spinners.get(x);//getting a specific spinner object by using the passed on index (x value)
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
            spinner = spinners.get(0);
        }
        
        //creating the labels and textfields for the spinner names and field count
        Label spinnerNameLB = new Label("Spinner Name");
        spinnerNameLB.getStyleClass().add("text-label-build");
        Label spinnerFieldCountLB = new Label("Number of Fields");
        spinnerFieldCountLB.getStyleClass().add("text-label-build");

        TextField  spinnerNameTF = new TextField(spinner.getSpinnerName());
        TextField  spinnerFieldCountTF = new TextField(Integer.toString(spinner.getNumFields()));
        
        //adding the labels and textfields to the vertical containers
        vBoxSpinnerName.getChildren().add(spinnerNameLB);
        vBoxSpinnerName.getChildren().add(spinnerNameTF);
        vBoxFieldCount.getChildren().add(spinnerFieldCountLB);
        vBoxFieldCount.getChildren().add(spinnerFieldCountTF);
        
        // creating an array of items/fields from the spinner
        ArrayList<String> items = spinner.getFields();
        
        //building the gridpane that holds all the spinner item labels and textfields
        GridPane grid = new GridPane();
        
        grid.setAlignment(Pos.CENTER);
        grid.getStyleClass().add("grid-pane");
        grid.setMinWidth(980);
        
        //creating textfield and label arrays with the size/length according to the selected spinner items
        TextField textField[] = new TextField[items.size()];
        Label textLabel[] = new Label[items.size()];
        
        //iteration controls
        int column = 0; //controls the column in the gridPane
        int row = 0; //controls row in the gridPane
        int labelNumber = 1; //item number that will show up in the label
        int labelCount = 0; // control for which label the loop is on
        int textFieldCount = 0; // control for which textfield the loop is on
        
        //looping through and populating the gridPane with all the values
        //each cell in the grid pane can hold only 1 thing. So one cell holds a label, the next cell holds the textfield
        //this means we need to loop through twice the size of the spinner item array to populate the gridPane correctly
        for(int i = 0; i < items.size() * 2; i++) {
            //every 4 cells we go to the next row and reset our column value to 0
            if(i % 4 == 0 && column != 0) {
                column = 0;
                row++;
            }
            //every other gridPane cell holds a textfield. 
            if(i % 2 == 0) {
                textLabel[labelCount] = new Label("Field " + labelNumber + ":"); //creating new label
                textLabel[labelCount].getStyleClass().add("text-label");
                grid.setConstraints(textLabel[labelCount], column, row);
                grid.getChildren().add(textLabel[labelCount]);
                GridPane.setHalignment(textLabel[labelCount], HPos.RIGHT);
                labelCount++;
                labelNumber++;
            } else {
                textField[textFieldCount] = new TextField(items.get(textFieldCount)); //creating a textfield
                textField[textFieldCount].getStyleClass().add("text-field");
                grid.setConstraints(textField[textFieldCount], column, row);
                grid.getChildren().add(textField[textFieldCount]);
                textFieldCount++;
            }
            column++;
        }
        // adjusting the width of each cell
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(10);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(25);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(10);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(25);
        
        //adding the created cells to the gridPane
        grid.getColumnConstraints().addAll(column1, column2, column3, column4);
        //adding the gridPane to the gridContainer (scrollPane)
        gridContainer.setContent(grid);
        return grid;
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    
    /* PAGE STARTS EXECUTING HERE */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        categoryComboBox.setPromptText("Select Spinner..."); //default dropdown text
        categoryComboBox.setItems(createString()); //this adds the spinners to the dropdown by calling on the createString() method
        
        //the following line adds an event listener to each dropdown option. When a user clicks on one of the dropdown options, the createTextFields() method is triggered. 
        //This method needs 2 parameters: the index of the selected dropdown option and the array of spinners
        categoryComboBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> createTextFields(categoryComboBox.getSelectionModel().getSelectedIndex(), loadALL()));
        saveSpinner.setOnAction(e -> {
            saveSpinner();
        });
        
        deleteSpinner.setOnAction(e -> {
            deleteSpinner();
        });
        
        addField.setOnAction(e -> {
            setFields();
        });
    }  
    
    // this method retrieves all existing spinner names and returns a list of those names
    public ObservableList<String> createString(){
        ArrayList<Spinner> spinnersArray = loadALL(); //retrieving the spinners by calling on the loadALL() method;
        int countArray = 0;
        countArray = spinnersArray.size();
        ArrayList<String> spinnerNames = new ArrayList<>(); // this is a temporary array that will hold the spinner names
        for (int i = 0; i < countArray; i++) { //looping through the spinners and adding names to the spinnerNames array 
            Spinner spinner = spinnersArray.get(i);
            spinnerNames.add(spinner.getSpinnerName());
        }
        ObservableList<String> categoryList = FXCollections.observableArrayList(spinnerNames);  //converting the spinnerArray to an observable string array for the dropdown
        
        return categoryList;
    }
    
    // this method gets an array of spinners from the spinners.txt file
    public ArrayList<Spinner> loadALL() {
        ArrayList<Spinner> spinnersArray = IOUtil.load(); //calls on the load() method from the IOUtil.java
        
        //creating a new spinner 
        Spinner newSpinner = new Spinner("New", 10);
        
        ArrayList<String> fields = new ArrayList<>();
        fields.add("");
        fields.add("");
        fields.add("");
        fields.add("");
        fields.add("");
        fields.add("");
        fields.add("");
        fields.add("");
        fields.add("");
        fields.add("");
        newSpinner.setField(fields);
        
        spinnersArray.add(newSpinner); //adding the new spinner to the array of spinners
        
        return spinnersArray;
    }
    
    public void setFields() {
        int x = categoryComboBox.getSelectionModel().getSelectedIndex();
        
        ArrayList<Spinner> spinners = loadALL();
        TextField numberOfFieldsTF = (TextField)vBoxFieldCount.getChildren().get(1);
        //creating a instance of a spinner 
        Spinner spinner = spinners.get(x); //getting a specific spinner object by using the passed on index (x value)
        
        int newF = 0;
        
        try {
            newF = Integer.parseInt(numberOfFieldsTF.getText());
        }
        catch(java.lang.NumberFormatException e) {
            newF = -1;
        }
        
        GridPane grid = (GridPane)gridContainer.getContent();
        int c = grid.getChildren().size();
        
        ArrayList<String> fields = new ArrayList<>();
        
        for(int i = 0; i < c; i++) {
            if(i % 2 != 0) {
                TextField currentTextField = (TextField)grid.getChildren().get(i);
                fields.add(currentTextField.getText());
            }
        }
        if (newF == -1) {
            newF = (c / 2);
        }
        else if (fields.size() > newF){
            while (fields.size() > newF) {
                int y = fields.size() - 1;
                fields.remove(y);
            }
        }
        else if (fields.size() < newF) {
            while (fields.size() < newF) {
                fields.add("");
            }
        }
        
        TextField spinnerNameTF = (TextField)vBoxSpinnerName.getChildren().get(1);
        String spinnerName = spinnerNameTF.getText();
        
        spinner.setField(fields);
        spinner.setNumFields(newF);
        spinner.setSpinnerName(spinnerName);
        
        spinners.remove(x);
        spinners.add(x, spinner);
        
        createTextFields(x, spinners);
    }    
}
