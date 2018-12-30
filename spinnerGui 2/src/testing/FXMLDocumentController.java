
package testing;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author mecic
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    public Button exitButton;
    
    @FXML
    private Label label;
    
     
   @FXML
    public void gameSetupSceneButtonAction(ActionEvent event) throws IOException {
        Parent stage = FXMLLoader.load(getClass().getResource("FXMLGameSetup.fxml"));
        Scene scene = new Scene(stage, 1000, 600);
        scene.getStylesheets().add("/styles/styles.css");
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    public void settingsSceneButtonAction(ActionEvent event) throws IOException {
        Parent stage = FXMLLoader.load(getClass().getResource("FXMLSettings.fxml"));
        Scene scene = new Scene(stage, 1000, 600);
        scene.getStylesheets().add("/styles/styles.css");
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    public void handleExitProgramAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
}
