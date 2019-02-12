package glitchpicprototype;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GlitchPicPrototype extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root, 700, 500);
        
        stage.setTitle("Glitch Pic");
        stage.setScene(scene);
        stage.setMinWidth(550);
        stage.setMinHeight(400);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("photos/GPLogo.PNG")));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
