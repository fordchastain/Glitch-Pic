package glitchpicprototype;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public final class PhotoGlitcher {
    private final File imageFile;
    private Image image;
    private BufferedImage bufImg;
    
    public PhotoGlitcher(File f){
        imageFile = f;
        initializeImage();
    }
    
    public Image getImage(){
        return image;
    }
    
    public void initializeImage(){
        try {
            image = new Image(new FileInputStream(imageFile));
            bufImg = ImageIO.read(imageFile);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Problem accessing file: " + 
                    imageFile.getAbsolutePath(), "Error", JOptionPane.OK_OPTION);
        } 
    }
    
    public void start (Stage window) throws Exception {
        // create new scene for editing and show it in current window
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PhotoGlitcher.fxml"));
        Scene scene = new Scene(loader.load(), 900, 650);
        window.setScene(scene);
        window.setMinHeight(690);
        window.setMinWidth(916);
        PhotoGlitcherController controller = loader.getController();
        controller.setImage(image, bufImg);
        window.show();
    }
}