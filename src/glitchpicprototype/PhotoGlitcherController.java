/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glitchpicprototype;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Stanford Chastain
 */
public class PhotoGlitcherController implements Initializable {

    @FXML
    private ImageView imgPane;
    
    @FXML
    private Spinner startSpinner, endSpinner;
    
    @FXML
    private Button optionOneButton, optionTwoButton, optionThreeButton,
            optionFourButton, glitchButton, undoButton, redoButton;
    
    @FXML
    private ComboBox colorPicker, directionPicker;
    
    @FXML
    private Slider slider;
    
    @FXML
    private Label glitchAmountLabel, colorPickerLabel, targetRangeLabel, 
            directionLabel;
    
    @FXML
    private MenuItem mnuClose, mnuNew, mnuSave, mnuGlitch, mnuUndo;
    
    private Image img1, original;
    private Color c;
    private BufferedImage bufferedImg;
    private ArrayList<BufferedImage> images;
    private int choice, index;
    private static final double INIT_VALUE = 50;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // set up nodes
        slider.setValue(INIT_VALUE);
        slider.setDisable(true);
        
        glitchAmountLabel.setText("Amount: " + Double.toString(INIT_VALUE) + "%");
        glitchAmountLabel.textProperty().bind(Bindings.format("Amount: %.1f%s", slider.valueProperty(), "%"));
        glitchAmountLabel.setDisable(true);
        
        colorPicker.getItems().addAll("Red", "Green", "Blue");
        directionPicker.getItems().addAll("East", "West");
        
        choice = 1;
        optionOneButton.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, new CornerRadii(2.0), new BorderWidths(2.0))));
        
        SpinnerValueFactory<Integer> startValueFactory = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 128);
        SpinnerValueFactory<Integer> endValueFactory = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 225);
        startSpinner.setValueFactory(startValueFactory);
        startSpinner.setEditable(true);
        endSpinner.setValueFactory(endValueFactory);
        
        setupButtons();
        setupMenuItems();
        
        // create new ArrayList for storing all images
        images = new ArrayList<>();
    }

    @FXML
    private void performOptionOneClickedAction(ActionEvent e){
        // adjust settings for option one
        clearButtonBorders();
        glitchAmountLabel.setDisable(true);
        slider.setDisable(true);
        colorPicker.setDisable(false);
        colorPickerLabel.setDisable(false);
        targetRangeLabel.setDisable(false);
        startSpinner.setDisable(false);
        endSpinner.setDisable(false);
        directionPicker.setDisable(false);
        directionPicker.getItems().removeAll(directionPicker.getItems());
        directionPicker.getItems().addAll("East", "West");
        directionLabel.setDisable(false);
        
        // set up option one button as selected choice
        choice = 1;
        optionOneButton.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, new CornerRadii(2.0), new BorderWidths(2.0))));
    }
    
    @FXML
    private void performOptionTwoClickedAction(ActionEvent e){
        // adjust settings for option two
        clearButtonBorders();
        glitchAmountLabel.setDisable(true);
        slider.setDisable(true);
        colorPicker.setDisable(false);
        colorPickerLabel.setDisable(false);
        targetRangeLabel.setDisable(false);
        startSpinner.setDisable(false);
        endSpinner.setDisable(false);
        directionPicker.setDisable(false);
        directionPicker.getItems().removeAll(directionPicker.getItems());
        directionPicker.getItems().addAll("North", "South");
        directionLabel.setDisable(false);
        
        // set up option two button as selected choice
        choice = 2;
        optionTwoButton.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, new CornerRadii(2.0), new BorderWidths(2.0))));
    }
    
    @FXML
    private void performOptionThreeClickedAction(ActionEvent e){
        // adjust settings for option three
        clearButtonBorders();
        glitchAmountLabel.setDisable(true);
        slider.setDisable(true);
        colorPicker.setDisable(false);
        colorPickerLabel.setDisable(false);
        targetRangeLabel.setDisable(false);
        startSpinner.setDisable(false);
        endSpinner.setDisable(false);
        directionPicker.setDisable(false);
        directionPicker.getItems().removeAll(directionPicker.getItems());
        directionPicker.getItems().addAll("Northeast", "Northwest", "Southeast", 
                "Southwest");
        directionLabel.setDisable(false);
        
        // set up option three as selected choice
        choice = 3;
        optionThreeButton.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, new CornerRadii(2.0), new BorderWidths(2.0))));
    }
    
    @FXML
    private void performOptionFourClickedAction(ActionEvent e){
        // adjust settings for option four
        clearButtonBorders();
        glitchAmountLabel.setDisable(false);
        slider.setDisable(false);
        colorPicker.setDisable(true);
        colorPickerLabel.setDisable(true);
        targetRangeLabel.setDisable(true);
        startSpinner.setDisable(true);
        endSpinner.setDisable(true);
        directionPicker.setDisable(true);
        directionLabel.setDisable(true);
        
        // set up option four as selected choice
        clearButtonBorders();
        choice = 4;
        optionFourButton.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, new CornerRadii(2.0), new BorderWidths(2.0))));
    }
    
    @FXML
    private void performMnuSaveAction(ActionEvent e){
        // create file chooser and save file to selected location
        Stage stage = (Stage) glitchButton.getScene().getWindow();
        FileChooser fileSaver = new FileChooser();
        fileSaver.setTitle("Save Image");
        fileSaver.setInitialDirectory(new File(System.getProperty("user.home")));
        File saveLocation = fileSaver.showSaveDialog(stage);
        if (saveLocation != null){
            saveFile(saveLocation.getAbsolutePath(), bufferedImg);
        }
    }
    
    @FXML
    private void performGlitchButtonAction(ActionEvent e){
        if (colorPicker.getValue() == null && (choice == 1 || choice == 2 ||
                choice == 3)){
            // display alert dialog if the color picker has no value
            Alert confirmDialog;
            confirmDialog = new Alert(AlertType.CONFIRMATION);
            confirmDialog.setTitle("Empty Field Error");
            confirmDialog.setHeaderText("Error");
            confirmDialog.setContentText("You must select a Target RGB.");
            confirmDialog.showAndWait();
        }
        else if (directionPicker.getValue() == null && choice != 4){
             // display alert dialog if the direction picker has no value
            Alert confirmDialog;
            confirmDialog = new Alert(AlertType.CONFIRMATION);
            confirmDialog.setTitle("Empty Field Error");
            confirmDialog.setHeaderText("Error");
            confirmDialog.setContentText("You must select a Target Direction.");
            confirmDialog.showAndWait();
        }
        else {
            // create copy of image and glitch photo with effect of current choice 
            bufferedImg = copyImage(bufferedImg);
            switch (choice) {
                case 1:
                {
                    PixelSort hps = new PixelSort(bufferedImg);
                    hps.setRange((int)startSpinner.getValue(), 
                            (int)endSpinner.getValue());
                    hps.setTargetRGB(colorPicker.getValue().toString());
                    hps.setTargetDirection(directionPicker.getValue().toString());
                    bufferedImg = hps.horizontalPixelSort();
                    images.add(bufferedImg);
                    index++;
                    img1 = SwingFXUtils.toFXImage(bufferedImg, null);
                    imgPane.setImage(img1);
                    break;
                }
                case 2:
                {
                    PixelSort vps = new PixelSort(bufferedImg);
                    vps.setRange((int)startSpinner.getValue(), (int)endSpinner.getValue());
                    vps.setTargetRGB(colorPicker.getValue().toString());
                    vps.setTargetDirection(directionPicker.getValue().toString());
                    bufferedImg = vps.verticalPixelSort();
                    images.add(bufferedImg);
                    index++;
                    imgPane.setImage(SwingFXUtils.toFXImage(bufferedImg, null));
                    break;
                }
                case 3:
                {
                    PixelSort wps = new PixelSort(bufferedImg);
                    wps.setRange((int)startSpinner.getValue(), (int)endSpinner.getValue());
                    wps.setTargetRGB(colorPicker.getValue().toString());
                    wps.setTargetDirection(directionPicker.getValue().toString());
                    bufferedImg = wps.diagonalPixelSort();
                    images.add(bufferedImg);
                    index++;
                    imgPane.setImage(SwingFXUtils.toFXImage(bufferedImg, null));
                    break;
                }
                case 4:
                {
                    BoxedPixelSort hps = new BoxedPixelSort(bufferedImg, slider.getValue());
                    bufferedImg = hps.glitchPhoto();
                    images.add(bufferedImg);
                    index++;
                    imgPane.setImage(SwingFXUtils.toFXImage(bufferedImg, null));
                    break;
                }
                default:
                    break;
            }
        }
    }
    
    @FXML
    private void performUndoButtonAction(ActionEvent e){
        // change image index and display photo
        if (index >= 1){
            index--;
        }
        bufferedImg = images.get(index);
        img1 = SwingFXUtils.toFXImage(images.get(index), null);
        imgPane.setImage(img1);
        if (images.size() >= 2){
            images.remove(images.size() - 1);
        }
    }
    
    @FXML
    private void performRedoButtonAction(ActionEvent e){}
    
    @FXML
    private void performImgPaneDragDroppedAction(ActionEvent e){}
    
    @FXML
    private void performMnuCloseAction(ActionEvent e) throws IOException{
        // show confirm dialog before exiting
        Alert confirmDialog = new Alert(AlertType.CONFIRMATION);
        confirmDialog.setTitle("Close Confirmation");
        confirmDialog.setHeaderText("Warning");
        confirmDialog.setContentText("Are you sure you want to exit?");
        
        Optional<ButtonType> result = confirmDialog.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) glitchButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

            stage.setMinWidth(550);
            stage.setMinHeight(400);
            stage.setWidth(700);
            stage.setHeight(500);
            Scene scene = new Scene(root, 700, 500);
            stage.setTitle("Glitch Pic Prototype");
            stage.setScene(scene);
            stage.show();
        }
    }
    
    @FXML
    private void performMnuNewAction(ActionEvent e){
        // create a new file chooser for photo
        Stage stage = (Stage) glitchButton.getScene().getWindow();
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
        
        // try to load the chosen photo
        File imageFile = fileChooser.showOpenDialog(stage);
        if (imageFile != null){
            try {
                img1 = new Image(new FileInputStream(imageFile));
                bufferedImg = ImageIO.read(imageFile);
                images.clear();
                setImage(img1, bufferedImg);
            } catch (IOException ex) {}
        }
    }
    
    @FXML
    private void performMnuUndoAction(ActionEvent e){
        undoButton.fire();
    }
    
    @FXML
    private void performMnuGlitchAction(ActionEvent e){
        glitchButton.fire();
    }
    
    public void setImage(Image img, BufferedImage bi){
        imgPane.setImage(img);
        img1 = img;
        bufferedImg = bi;
        centerImage();
        images.add(bi);
        index = 0;
    }
    
    public void clearButtonBorders(){
        optionOneButton.setBorder(Border.EMPTY);
        optionTwoButton.setBorder(Border.EMPTY);
        optionThreeButton.setBorder(Border.EMPTY);
        optionFourButton.setBorder(Border.EMPTY);
    }
    
    public void centerImage() {
        Image img = imgPane.getImage();
        if (img != null) {
            double w;
            double h;
            double ratioX = imgPane.getFitWidth() / img.getWidth();
            double ratioY = imgPane.getFitHeight() / img.getHeight();
            double reducCoeff;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } 
            else {
                reducCoeff = ratioX;
            }
            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;
            imgPane.setX((imgPane.getFitWidth() - w) / 2);
            imgPane.setY((imgPane.getFitHeight() - h) / 2);
        }
    }
    
    public void setupMenuItems(){
        Image saveImg = new Image(getClass().getResource("photos/save.png").toString());
        ImageView saveImgView = new ImageView(saveImg);
        mnuSave.setGraphic(saveImgView);
        Image newImg = new Image(getClass().getResource("photos/new.png").toString());
        ImageView newImgView = new ImageView(newImg);
        mnuNew.setGraphic(newImgView);
        Image undoImg = new Image(getClass().getResource("photos/undo.png").toString());
        ImageView undoImgView = new ImageView(undoImg);
        mnuUndo.setGraphic(undoImgView);
    }
    
    public void setupButtons(){
        // set content for button options
        Image optionOne = new Image(getClass().getResource("photos/bird_horiz.jpg").toString());
        ImageView optOneImgView = new ImageView(optionOne);
        optionOneButton.setContentDisplay(ContentDisplay.BOTTOM);
        optionOneButton.setText("Horizontal Leak");
        optionOneButton.setGraphic(optOneImgView);
        
        Image optionTwo = new Image(getClass().getResource("photos/bird_vert.jpg").toString());
        ImageView optTwoImgView = new ImageView(optionTwo);
        optionTwoButton.setContentDisplay(ContentDisplay.BOTTOM);
        optionTwoButton.setText("Vertical Leak");
        optionTwoButton.setGraphic(optTwoImgView);
        
        Image optionThree = new Image(getClass().getResource("photos/bird_diag.jpg").toString());
        ImageView optThreeImgView = new ImageView(optionThree);
        optionThreeButton.setContentDisplay(ContentDisplay.BOTTOM);
        optionThreeButton.setText("Diagonal Leak");
        optionThreeButton.setGraphic(optThreeImgView);
        
        Image optionFour = new Image(getClass().getResource("photos/bird_boxed.jpg").toString());
        ImageView optFourImgView = new ImageView(optionFour);
        optionFourButton.setContentDisplay(ContentDisplay.BOTTOM);
        optionFourButton.setText("Boxed Random");
        optionFourButton.setGraphic(optFourImgView);
    }
    
    private void saveFile(String location, BufferedImage imageFile){
        try {
            ImageIO.write(imageFile, "jpg", new File(location + ".jpg"));
        } catch (IOException ex) {
            Logger.getLogger(PhotoGlitcherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }
}
