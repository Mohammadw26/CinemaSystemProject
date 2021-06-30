package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.ComingSoonMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Image;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.OnDemandMovie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AddMovieController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox regularCheck;

    @FXML
    private CheckBox onDemandCheck;

    @FXML
    private CheckBox comingSoonCheck;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField engName;

    @FXML
    private TextField hebName;

    @FXML
    private TextField producer;

    @FXML
    private TextField actors;

    @FXML
    private AnchorPane signUpAnchor;

    @FXML
    private TextField newUserField;

    @FXML
    private TextField newPasswordField;

    @FXML
    private CheckBox useEmailCheck;

    @FXML
    private AnchorPane loginAnchor;

    @FXML
    private Text loginAnchorLabel1;

    @FXML
    private TextField oldUserField;

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private Text loginAnchorLabel2;

    @FXML
    private Button logInButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Label invalidLogin;

    @FXML
    private Button confirmButton;

    @FXML
    private Label warning;

    @FXML
    private TextArea description;

    @FXML
    private TextField price;

    @FXML
    private TextField thumbnail;

    @FXML
    private TextField streaming;
    
    @FXML
    private ComboBox<Integer> hourField;

    @FXML
    private ComboBox<Integer> minuteField;

    @FXML
    private DatePicker datePick;

    @FXML
    void CancelAddition(ActionEvent event) {
    	try {
			App.setRoot("displayList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ComingSoonCheck(ActionEvent event) {
    	if(comingSoonCheck.isSelected() == true) {
    		streaming.setDisable(true);
    		price.setDisable(true);
    		regularCheck.setSelected(false);
    		onDemandCheck.setSelected(false);
    		hourField.setDisable(true);
    		minuteField.setDisable(true);
    		datePick.setDisable(true);
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
    			Image newImage = new Image ("Thumbnail", thumbnail.getText());
    			CinemaMovie newMovie = new CinemaMovie (engName.getText(), hebName.getText(),  producer.getText(), actors.getText(), description.getText(), Double.parseDouble(price.getText()) , newImage);
    			try {
					SimpleClient.getClient().sendToServer(new Message("#AddRegularMovie", newMovie));
					SimpleClient.getClient().sendToServer(new Message("#CatalogRequest", newMovie));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			try {
					App.setRoot("displayList");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		else if (comingSoonCheck.isSelected() == true) {
    			Image newImage = new Image ("Thumbnail", thumbnail.getText());
    			ComingSoonMovie newMovie = new ComingSoonMovie (engName.getText(), hebName.getText(),  producer.getText(), actors.getText(), description.getText(),  newImage);
    			try {
					SimpleClient.getClient().sendToServer(new Message("#AddComingSoonMovie", newMovie));
					SimpleClient.getClient().sendToServer(new Message("#CatalogRequest", newMovie));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			try {
					App.setRoot("displayList");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
			else if (onDemandCheck.isSelected() == true) {
    			Image newImage = new Image ("Thumbnail", thumbnail.getText());
    			ZonedDateTime start = LocalDateTime.of(datePick.getValue().getYear(),datePick.getValue().getMonthValue(),
    									datePick.getValue().getDayOfMonth(),hourField.getValue(),minuteField.getValue())
    					.atZone(ZoneId.of("Asia/Jerusalem"));
    			OnDemandMovie newMovie = new OnDemandMovie (engName.getText(), hebName.getText(),
    									producer.getText(), actors.getText(), description.getText(), 
    									Double.parseDouble(price.getText()), newImage, start, start.plusHours(10));
    			newMovie.setStreamingLink(streaming.getText());
    			try {
					SimpleClient.getClient().sendToServer(new Message("#AddOnDemandMovie", newMovie));
					SimpleClient.getClient().sendToServer(new Message("#CatalogRequest", newMovie));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			try {
					App.setRoot("displayList");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    }

    @FXML
    void LogOut(ActionEvent event) {

    }

    @FXML
    void OnDemandCheck(ActionEvent event) {
    	if (onDemandCheck.isSelected() == true) {
    		streaming.setDisable(false);
    		price.setDisable(false);
    		comingSoonCheck.setSelected(false);
    		regularCheck.setSelected(false);
    		hourField.setDisable(false);
    		minuteField.setDisable(false);
    		datePick.setDisable(false);
    	}
    	
    }

    @FXML
    void RegularCheck(ActionEvent event) {
    	if(regularCheck.isSelected() == true) {
    		streaming.setDisable(true);
    		price.setDisable(false);
    		comingSoonCheck.setSelected(false);
    		onDemandCheck.setSelected(false);
    		hourField.setDisable(true);
    		minuteField.setDisable(true);
    		datePick.setDisable(true);
    	}
    }

    @FXML
    void copyEmail(ActionEvent event) {

    }

    @FXML
    void logIn(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert regularCheck != null : "fx:id=\"regularCheck\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert onDemandCheck != null : "fx:id=\"onDemandCheck\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert comingSoonCheck != null : "fx:id=\"comingSoonCheck\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert engName != null : "fx:id=\"engName\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert hebName != null : "fx:id=\"hebName\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert producer != null : "fx:id=\"producer\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert actors != null : "fx:id=\"actors\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert signUpAnchor != null : "fx:id=\"signUpAnchor\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert newUserField != null : "fx:id=\"newUserField\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert newPasswordField != null : "fx:id=\"newPasswordField\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert useEmailCheck != null : "fx:id=\"useEmailCheck\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert loginAnchor != null : "fx:id=\"loginAnchor\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert loginAnchorLabel1 != null : "fx:id=\"loginAnchorLabel1\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert oldUserField != null : "fx:id=\"oldUserField\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert oldPasswordField != null : "fx:id=\"oldPasswordField\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert loginAnchorLabel2 != null : "fx:id=\"loginAnchorLabel2\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert logInButton != null : "fx:id=\"logInButton\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert logOutButton != null : "fx:id=\"logOutButton\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert invalidLogin != null : "fx:id=\"invalidLogin\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert warning != null : "fx:id=\"warning\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert thumbnail != null : "fx:id=\"thumbnail\" was not injected: check your FXML file 'addMovie.fxml'.";
        assert streaming != null : "fx:id=\"streaming\" was not injected: check your FXML file 'addMovie.fxml'.";
        
        for (int i = 0; i < 24; i++) {
			hourField.getItems().addAll(i);
		}
		for (int i = 0; i < 60; i = i + 5) {
			minuteField.getItems().addAll(i);
		}
        
        regularCheck.setSelected(true);
        onDemandCheck.setSelected(false);
        comingSoonCheck.setSelected(false);
        
        streaming.setDisable(true);
        price.setDisable(false);

    }
}
