package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Image;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AddMoviePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button returnBtn;

    @FXML
    private TextField engName;

    @FXML
    private TextField hebName;

    @FXML
    private TextField description;

    @FXML
    private TextField producer;

    @FXML
    private TextField actors;

    @FXML
    private TextField thumbnail;

    @FXML
    private TextField price;

    @FXML
    private TextField streaming;

    @FXML
    private AnchorPane anchPane;

    @FXML
    private Label showEngTitle;

    @FXML
    private ImageView showImage;

    @FXML
    private Label showHebTitle;

    @FXML
    private Label showProducers;

    @FXML
    private Label showActors;

    @FXML
    private Label showDescription;

    @FXML
    private Button confirmButton;

    @FXML
    private Label pricelabel;

    @FXML
    private Label price1;

    @FXML
    private CheckBox regularCheck;

    @FXML
    private CheckBox onDemandCheck;

    @FXML
    private CheckBox comingSoonCheck;
    
    @FXML
    private Label warning;

    @FXML
    void ComingSoonCheck(ActionEvent event) {
    	if(comingSoonCheck.isSelected() == true) {
    		streaming.setDisable(true);
    		price.setDisable(true);
    		regularCheck.setSelected(false);
    		onDemandCheck.setSelected(false);
    	}
    }

    @FXML
    void ConfirmAddition(ActionEvent event) {
    	if (engName.getText() == "" || hebName.getText() == "" 
        		|| producer.getText() == "" || actors.getText() == "" || description.getText() == "" 
        		|| thumbnail.getText() == "" ||(regularCheck.isSelected() & (price.getText() == "")) || (onDemandCheck.isSelected() & price.getText() == "")
        		|| (onDemandCheck.isSelected() & streaming.getText() == "")) {
        		warning.setVisible(true);
        	}
    	else {
    		if (regularCheck.isSelected() == true) {
    			Image newImage = new Image ("thumbnail", thumbnail.getText());
    			CinemaMovie newMovie = new CinemaMovie (engName.getText(), hebName.getText(),  producer.getText(), actors.getText(), description.getText(), Double.parseDouble(price.getText()) , newImage);
    			try {
					SimpleClient.getClient().sendToServer(new Message("#AddMovie", newMovie));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    }

    @FXML
    void OnDemandCheck(ActionEvent event) {
    	if (onDemandCheck.isSelected() == true) {
    		streaming.setDisable(false);
    		price.setDisable(false);
    		comingSoonCheck.setSelected(false);
    		regularCheck.setSelected(false);
    	}
    	
    }

    @FXML
    void RegularCheck(ActionEvent event) {
    	if(regularCheck.isSelected() == true) {
    		streaming.setDisable(true);
    		price.setDisable(false);
    		comingSoonCheck.setSelected(false);
    		onDemandCheck.setSelected(false);
    	}
    }
    @FXML
    void returnToCatalog(ActionEvent event) {
    	try {
			App.setRoot("displayList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void setActors(KeyEvent event) {

    }

    @FXML
    void setDescription(KeyEvent event) {

    }

    @FXML
    void setEngTitle(KeyEvent event) {

    }

    @FXML
    void setHebTitle(KeyEvent event) {

    }

    @FXML
    void setImageURL(MouseEvent event) {

    }

    @FXML
    void setProducers(KeyEvent event) {

    }

    @FXML
    void initialize() {
        assert returnBtn != null : "fx:id=\"returnBtn\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert engName != null : "fx:id=\"engName\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert hebName != null : "fx:id=\"hebName\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert producer != null : "fx:id=\"producer\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert actors != null : "fx:id=\"actors\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert thumbnail != null : "fx:id=\"thumbnail\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert price != null : "fx:id=\"engName1\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert streaming != null : "fx:id=\"streaming\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert anchPane != null : "fx:id=\"anchPane\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert showEngTitle != null : "fx:id=\"showEngTitle\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert showImage != null : "fx:id=\"showImage\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert showHebTitle != null : "fx:id=\"showHebTitle\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert showProducers != null : "fx:id=\"showProducers\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert showActors != null : "fx:id=\"showActors\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert showDescription != null : "fx:id=\"showDescription\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert pricelabel != null : "fx:id=\"price\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert price1 != null : "fx:id=\"price1\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert regularCheck != null : "fx:id=\"regularCheck\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert onDemandCheck != null : "fx:id=\"onDemandCheck\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert comingSoonCheck != null : "fx:id=\"comingSoonCheck\" was not injected: check your FXML file 'addMoviePage.fxml'.";
        assert warning != null : "fx:id=\"warning\" was not injected: check your FXML file 'addMoviePage.fxml'.";

        warning.setVisible(false);
        regularCheck.setSelected(true);
        onDemandCheck.setSelected(false);
        comingSoonCheck.setSelected(false);
        
        streaming.setDisable(true);
        price.setDisable(false);

    }
}
