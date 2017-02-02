/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eci.webbrowser;

/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.darkweb.uicomponents.WebEngineSingleton;
import org.darkweb.utils.WebDataExtractor;

/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class SuperWebBrowser extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Super-Web Browser. -http://www.example.com");

        
        
        WebView myBrowser = new WebView();
        
        WebEngineSingleton.getInstance().loadContent(myBrowser.getEngine(), new ProxyWork(new WebDataExtractor(),"http://www.example.com"));

        
        BorderPane root = new BorderPane();
        TextField tf = new TextField("http://www.example.com");
        tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    try {
                        WebEngineSingleton.getInstance().loadContent(myBrowser.getEngine(),new ProxyWork( new WebDataExtractor(),tf.getText()));
                        primaryStage.setTitle("Super-Web Browser. -"+tf.getText());
                    } catch (IOException ex) {                        
                        Logger.getLogger(SuperWebBrowser.class.getName()).log(Level.SEVERE, null, ex);
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid URL or Connection error:"+tf.getText());

                        alert.showAndWait();
                    }
                }
            }
        });

        root.setTop(tf);
        root.setCenter(myBrowser);
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
}
