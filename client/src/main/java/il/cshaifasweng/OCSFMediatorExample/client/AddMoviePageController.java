package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AddMoviePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField engMovieTitle;

    @FXML
    private TextField hebMovieTitle;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField producersField;

    @FXML
    private TextField actorsField;

    @FXML
    private TextField urlField;

    @FXML
    private ImageView showImage;

    @FXML
    private Label showProducers;

    @FXML
    private Label showActors;

    @FXML
    private Label showEngTitle;

    @FXML
    private Label showHebTitle;

    @FXML
    private Button approveBtn;

    @FXML
    private Button cancelBtn;
    
    @FXML
    private Label showDescription;
    
    @FXML
    private Button setImage;

    @FXML // fx:id="signUpBtn"
    private Button signUpBtn; // Value injected by FXMLLoader
    
    @FXML
    void returnToCatalog(ActionEvent event) {
    	try {
			App.setRoot("DisplayList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void addMovieAction(ActionEvent event) {

    }

    @FXML
    void setActors(KeyEvent event) {
    	showActors.setText(actorsField.getText());
    }

    @FXML
    void setDescription(KeyEvent event) {
    	showDescription.setText(descriptionField.getText());
    }

    @FXML
    void setEngTitle(KeyEvent event) {
    	showEngTitle.setText(engMovieTitle.getText());
    }

    @FXML
    void setHebTitle(KeyEvent event) {
    	showHebTitle.setText(hebMovieTitle.getText());
    }
    
    @FXML
    void setImageURL(MouseEvent event) {
    	Image image = new Image(urlField.getText());
    	showImage.setImage(image);
    	showImage.setPreserveRatio(false);
    }

    @FXML
    void setProducers(KeyEvent event) {
    	showProducers.setText(producersField.getText());
    }

    @FXML
    void signUp(MouseEvent event) {

    }
    
    @FXML
    void initialize() {
        assert engMovieTitle != null : "fx:id=\"engMovieTitle\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert hebMovieTitle != null : "fx:id=\"hebMovieTitle\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert descriptionField != null : "fx:id=\"descriptionField\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert producersField != null : "fx:id=\"producersField\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert actorsField != null : "fx:id=\"actorsField\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert urlField != null : "fx:id=\"urlField\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert showImage != null : "fx:id=\"showImage\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert showProducers != null : "fx:id=\"showProducers\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert showActors != null : "fx:id=\"showActors\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert showEngTitle != null : "fx:id=\"showEngTitle\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert showHebTitle != null : "fx:id=\"showHebTitle\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert approveBtn != null : "fx:id=\"approveBtn\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert cancelBtn != null : "fx:id=\"cancelBtn\" was not injected: check your FXML file 'addMoviePage.fxml'.";

    }
}
