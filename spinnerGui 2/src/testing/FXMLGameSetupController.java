/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mecic
 */
public class FXMLGameSetupController implements Initializable {
    // the combo box for the spinner names
    @FXML
    private ComboBox categoryComboBox;
    
    @FXML
    private Button addToGame;
    
    @FXML
    private Button deleteItem;
    
    //vertical container that holds the spinner name label and textfield
    @FXML
    private VBox vBoxSpinnerName;
    
    @FXML
    private ListView spinnerGameList;
    
    @FXML
    public void mainSceneButtonAction(ActionEvent event) throws IOException {
        //clear selected spinners on exit from screen
        ArrayList selectedSpinners = IOUtil.loadSIndex();
        selectedSpinners.clear();
        IOUtil.saveSIndex(selectedSpinners);

        //load stage
        Parent stage = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(stage, 1000, 600);
        scene.getStylesheets().add("/styles/styles.css");
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    public void addSpinnerItems() throws IOException {
        try {
            int selectedIndex = categoryComboBox.getSelectionModel().getSelectedIndex();
            ArrayList<Spinner> spinnersArray = IOUtil.load();
            Spinner spinner = spinnersArray.get(selectedIndex);
            String spinnerName = spinner.getSpinnerName();
            ObservableList items = FXCollections.observableArrayList();
        
            ArrayList<Integer> selectedSpinners = IOUtil.loadSIndex();
        
            try {
                items = spinnerGameList.getItems();
            }
            catch (Exception e) {
            }
        
            if (items.size() >= 5) {
            }
            else if (items.size() == 0) {
                selectedSpinners.clear();
                IOUtil.saveSIndex(selectedSpinners);
                items.add(spinnerName);
                spinnerGameList.setItems(items);
                selectedSpinners.add(selectedIndex);
                IOUtil.saveSIndex(selectedSpinners);
            }
            else {
                items.add(spinnerName);
                spinnerGameList.setItems(items);
                selectedSpinners.add(selectedIndex);
                IOUtil.saveSIndex(selectedSpinners);
            }
            
            System.out.println(selectedSpinners);
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e) {
            //no index was selected  :c
        }
    }
    
        
    public void deleteSpinnerItems() throws IOException {
        try {
            spinnerGameList.getItems().remove(spinnerGameList.getSelectionModel().getSelectedIndex());
            ArrayList<Integer> selectedSpinners = IOUtil.loadSIndex();
            selectedSpinners.remove(spinnerGameList.getSelectionModel().getSelectedIndex() + 1);
            IOUtil.saveSIndex(selectedSpinners);
            System.out.println(selectedSpinners);
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e) {
            //no index was selected  :c
        }
    }
    
    @FXML
    public void inGameSceneButtonAction(ActionEvent event) throws IOException {
        Parent stage = FXMLLoader.load(getClass().getResource("FXMLInGame.fxml"));
        Scene scene = new Scene(stage, 1000, 600);
        scene.getStylesheets().add("/styles/styles.css");
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoryComboBox.setPromptText("Select Spinner..."); //default dropdown text
        categoryComboBox.setItems(createString()); //this adds the spinners to the dropdown by calling on the createString() method
        addToGame.setOnAction(e -> {
            try {
                addSpinnerItems();
            } catch (IOException ex) {
                Logger.getLogger(FXMLGameSetupController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        deleteItem.setOnAction(e -> {
            try {   
                deleteSpinnerItems();
            } catch (IOException ex) {
                Logger.getLogger(FXMLGameSetupController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            //System.out.println(spinner.getSpinnerName() + "HAH"); //this line is just a console check, can be deleted from the final version of project
            spinnerNames.add(spinner.getSpinnerName());
        }
        
        ObservableList<String> categoryList = FXCollections.observableArrayList(spinnerNames);  //converting the spinnerArray to an observable string array for the dropdown
        
        return categoryList;
    }
    // this method gets an array of spinners from the spinners.txt file
    public ArrayList<Spinner> loadALL() {
        ArrayList<Spinner> spinnersArray = IOUtil.load(); //calls on the load() method from the IOUtil.java

        return spinnersArray;
    } 
}
