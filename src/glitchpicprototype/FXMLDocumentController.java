package glitchpicprototype;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button btnExit, btnImport;
    private PhotoGlitcher photoGlitcher;
    
    @FXML
    private BorderPane borderPaneOne;
    
    @FXML
    private void handleBtnImportAction(ActionEvent e) throws Exception {
        // get a handle to the stage
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        
        // create a new file chooser for photo
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Photo");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.gif", "*.bmp", "*.png"), 
            new FileChooser.ExtensionFilter("JPG", "*.jpg"), 
            new FileChooser.ExtensionFilter("GIF", "*.gif"),
            new FileChooser.ExtensionFilter("BMP", "*.bmp"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File imageFile = fileChooser.showOpenDialog(stage);
        
        // create new photo glitcher
        if (imageFile != null){
            photoGlitcher = new PhotoGlitcher(imageFile);
            photoGlitcher.start(stage);
        }
    }
    
    @FXML
    private void handleBtnExitAction(ActionEvent e) throws IOException {
        // get a handle to the stage and exit
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();  
    }
    
    @FXML
    private void handleMnuNewAction(ActionEvent e) {
        btnImport.fire();
    }
    
    @FXML
    private void handleMnuCloseAction(ActionEvent e) {
        btnExit.fire();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img1 = null;
        img1 = new Image(getClass().getResource("photos/img2.jpg").toString());
        if (img1 != null){
            borderPaneOne.setBackground(new Background(new BackgroundImage(
            img1, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT)));
        }
    }
    
}
