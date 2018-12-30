
package testing;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mecic
 */
public class FXMLInGameController implements Initializable {
    private ArrayList<Spinner> spinnersExh;
    private int exh = 0;
    
    @FXML
    private ScrollPane gridContainer1;
    
    @FXML
    private ScrollPane gridContainer2;
    
    @FXML
    private Button spin;
    
    @FXML
    private Button reset;
    
    @FXML
    private Button exhaust;

    @FXML
    public void setupSceneButtonAction(ActionEvent event) throws IOException {
        Parent stage = FXMLLoader.load(getClass().getResource("FXMLGameSetup.fxml"));
        Scene scene = new Scene(stage, 1000, 600);
        scene.getStylesheets().add("/styles/styles.css");
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    public void mainSceneButtonAction(ActionEvent event) throws IOException {
        Parent stage = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(stage, 1000, 600);
        scene.getStylesheets().add("/styles/styles.css");
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    public GridPane preGameLoad() {
        ArrayList<Spinner> spinnersArray = loadSpinnerArray();
        ArrayList<Integer> indices = loadSelectedIndices();
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.getStyleClass().add("grid-pane");
        grid.setMinWidth(980);
        
        Label textLabel[] = new Label[indices.size()];
        
        //iteration controls
        int column = 0; //controls the column in the gridPane
        int row = 0; //controls row in the gridPane
        int labelNumber = 1; //item number that will show up in the label
        int labelCount = 0; // control for which label the loop is on
        
        for(int i = 0; i < indices.size(); i++) {
            //every 5 cells we go to the next row and reset our column value to 0
            if(i % 5 == 0 && column != 0) {
                column = 0;
                row++;
            }
            //every other gridPane cell holds a textfield. 
            if(i == i) {
                //"Spinner " + labelNumber + ": " + 
                textLabel[labelCount] = new Label(spinnersArray.get(indices.get(i)).getSpinnerName()); //creating new label
                textLabel[labelCount].getStyleClass().add("text-label-big");
                grid.setConstraints(textLabel[labelCount], column, row);
                grid.getChildren().add(textLabel[labelCount]);
                GridPane.setHalignment(textLabel[labelCount], HPos.RIGHT);
                labelCount++;
                labelNumber++;
            } else {
            }
            column++;
        }
        // adjusting the width of each cell
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(20);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(20);
        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPercentWidth(20);
        
        //adding the created cells to the gridPane
        grid.getColumnConstraints().addAll(column1, column2, column3, column4, column5);
        //adding the gridPane to the gridContainer (scrollPane)
        gridContainer1.setContent(grid);
        return grid;
    }
    
    public GridPane spinSpinners() {
        ArrayList<Spinner> spinners;
    
        if (exh == 0) {
            spinners = loadSpinnerArray();
        }
        else {
            spinners = spinnersExh;
        }
        
        ArrayList<Integer> indices = loadSelectedIndices();
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.getStyleClass().add("grid-pane");
        grid.setMinWidth(980);
        
        Label textLabel[] = new Label[indices.size()];
        
        //iteration controls
        int column = 0; //controls the column in the gridPane
        int row = 0; //controls row in the gridPane
        int labelNumber = 1; //item number that will show up in the label
        int labelCount = 0; // control for which label the loop is on
        
        for(int i = 0; i < indices.size(); i++) {
            //every 5 cells we go to the next row and reset our column value to 0
            if(i % 5 == 0 && column != 0) {
                column = 0;
                row++;
            }
            //every other gridPane cell holds a textfield. 
            if(i == i) {
                //"Spinner " + labelNumber + ": " + 
                if (exh == 0) {
                    textLabel[labelCount] = new Label(spinSpinner(spinners, indices.get(i))); //creating new label
                }
                else {
                    String s = spinSpinner(spinners, indices.get(i));
                    textLabel[labelCount] = new Label (s);
                    spinners.get(indices.get(i)).getFields().remove(s);
                    spinnersExh = spinners;
                }
                textLabel[labelCount].getStyleClass().add("text-label-field");
                grid.setConstraints(textLabel[labelCount], column, row);
                grid.getChildren().add(textLabel[labelCount]);
                GridPane.setHalignment(textLabel[labelCount], HPos.RIGHT);
                labelCount++;
                labelNumber++;
            } else {
            }
            column++;
        }
        // adjusting the width of each cell
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(20);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(20);
        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPercentWidth(20);
        
        //adding the created cells to the gridPane
        grid.getColumnConstraints().addAll(column1, column2, column3, column4, column5);
        //adding the gridPane to the gridContainer (scrollPane)
        gridContainer2.setContent(grid);
        return grid;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spinnersExh = loadSpinnerArray();
        preGameLoad();

        spin.setOnAction(e -> {
            spinSpinners();            
        });
        
        reset.setOnAction(e -> {
            spinnersExh = loadSpinnerArray();
        });

        exhaust.setOnAction(e -> {
            if (exh == 0) {
                exh = 1;
                exhaust.setText("Enable Duplicate Fields");
            }
            else {
                exh = 0;
                exhaust.setText("Disable Duplicate Fields");
            }   
        });
    }
    
    public ArrayList<Spinner> loadSpinnerArray() {
        ArrayList<Spinner> spinnersArray = IOUtil.load(); //calls on the load() method from the IOUtil.java

        return spinnersArray;
    }
    
    public ArrayList<Integer> loadSelectedIndices() {
        ArrayList<Integer> selectedSpinners = IOUtil.loadSIndex();

        return selectedSpinners;
    }

    private static String spinSpinner(ArrayList<Spinner> spinners, int selectedSpinner) {
        String field;
        
        int numFields = spinners.get(selectedSpinner).getFields().size();
        
        if (numFields >= 1) {
            int randomNum =  randInt(numFields);
            field = spinners.get(selectedSpinner).getField(randomNum);
        }
        else {
            field = "";
        }
        return field;
    }
    
    public static int randInt(int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max);
        
        return randomNum;
    }
}