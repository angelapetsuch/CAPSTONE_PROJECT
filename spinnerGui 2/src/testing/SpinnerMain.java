
package testing;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author mecic
 */
public class SpinnerMain extends Application {
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        launch(args);

        ArrayList<Spinner> spinners = IOUtil.load();
        
        getSpinnerNames(spinners);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        primaryStage.setTitle("Spinner Of Opportunity");
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add("/styles/styles.css");
        
        try {
            String musicFile = "src/media/music.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }
        catch (Exception e) {
            
        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private static void getSpinnerNames(ArrayList<Spinner> spinners) {
        // display spinner names
        int numSpinners = spinners.size();
        
        for (int i = 0; i < numSpinners; i++) {
            Spinner spinner = spinners.get(i);
        }
    }
}
