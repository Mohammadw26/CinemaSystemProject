/**
 * Sample Skeleton for 'bookingOrder.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.BookingRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.FullOrderRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BookingOrderController {
	private static BookingRequest request;
	private static Screening screening;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="movieInfo"
    private Label movieInfo; // Value injected by FXMLLoader

    @FXML // fx:id="poster"
    private ImageView poster; // Value injected by FXMLLoader

    @FXML // fx:id="nameField"
    private TextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="lastNameField"
    private TextField lastNameField; // Value injected by FXMLLoader

    @FXML // fx:id="idField"
    private TextField idField; // Value injected by FXMLLoader

    @FXML // fx:id="emailField"
    private TextField emailField; // Value injected by FXMLLoader

    @FXML // fx:id="cardField"
    private TextField cardField; // Value injected by FXMLLoader

    @FXML // fx:id="signUpCheck"
    private CheckBox signUpCheck; // Value injected by FXMLLoader

    @FXML // fx:id="signUpAnchor"
    private AnchorPane signUpAnchor; // Value injected by FXMLLoader

    @FXML // fx:id="newUserField"
    private TextField newUserField; // Value injected by FXMLLoader

    @FXML // fx:id="newPasswordField"
    private TextField newPasswordField; // Value injected by FXMLLoader

    @FXML // fx:id="useEmailCheck"
    private CheckBox useEmailCheck; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="oldUserField"
    private TextField oldUserField; // Value injected by FXMLLoader

    @FXML // fx:id="oldPasswordField"
    private TextField oldPasswordField; // Value injected by FXMLLoader

    @FXML // fx:id="logInButton"
    private Button logInButton; // Value injected by FXMLLoader

    @FXML // fx:id="confirmButton"
    private Button confirmButton; // Value injected by FXMLLoader

    @FXML // fx:id="warning"
    private Label warning; // Value injected by FXMLLoader
    
    public static BookingRequest getRequest() {
		return request;
	}

	public static void setRequest(BookingRequest request) {
		BookingOrderController.request = request;
	}

	@FXML
    void CancelOrder(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("#CatalogRequest");
	    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ConfirmOrder(ActionEvent event) {
    	if (nameField.getText() == "" || lastNameField.getText() == "" 
    		|| idField.getText() == "" || emailField.getText() == "" || cardField.getText() == "" 
    		|| (signUpCheck.isSelected() & (newUserField.getText() == "" || newPasswordField.getText() == ""))) {
    		warning.setVisible(true);
    	}
    	else {
    		FullOrderRequest request2 = new FullOrderRequest(nameField.getText(), lastNameField.getText()
    				, emailField.getText(), Integer.parseInt(idField.getText()), Integer.parseInt(cardField.getText()));
    		request2.setRequest(request);
    		if (signUpCheck.isPressed()) {
    			request2.setUsername(newUserField.getText());
    			request2.setPassword(newPasswordField.getText());
    			request2.setSignupFlag(true);
    			request2.setNewCustomerFlag(true);
    		} else {
    			request2.setUsername("");
    			request2.setPassword("");
    			request2.setSignupFlag(false);
    			request2.setNewCustomerFlag(true);
    		}
    		try {
    			warning.setVisible(true);
				SimpleClient.getClient().sendToServer(new Message("#FinishOrder", request2));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

    @FXML
    void copyEmail(ActionEvent event) {
    	newUserField.setText(emailField.getText());
    }

    @FXML
    void logIn(ActionEvent event) {
    	
    }

    @FXML
    void viewSignUp(ActionEvent event) {
    	if (signUpCheck.isSelected()) {
    		signUpAnchor.setVisible(true);
    	} else {
    		signUpAnchor.setVisible(false);
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert movieInfo != null : "fx:id=\"movieInfo\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert poster != null : "fx:id=\"poster\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert lastNameField != null : "fx:id=\"lastNameField\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert cardField != null : "fx:id=\"cardField\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert signUpCheck != null : "fx:id=\"signUpCheck\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert signUpAnchor != null : "fx:id=\"signUpAnchor\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert newUserField != null : "fx:id=\"newUserField\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert newPasswordField != null : "fx:id=\"newPasswordField\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert useEmailCheck != null : "fx:id=\"useEmailCheck\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert oldUserField != null : "fx:id=\"oldUserField\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert oldPasswordField != null : "fx:id=\"oldPasswordField\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert logInButton != null : "fx:id=\"logInButton\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'bookingOrder.fxml'.";
        screening = request.getScreening();
        Image image = new Image(screening.getMovie().getImage().getImgURL());
		poster.setImage(image);
		poster.setPreserveRatio(false);
		String temp = "Order Summary:\nMovie: " + screening.getMovie().getMovieTitle() + " - " + screening.getMovie().getMovieTitleHeb()
				+ "\nDate: " + screening.getScreeningDate() 
		+ "\nTime: " + screening.getScreeningTime()
		+ "\nNumber of tickets: " + request.getArrSize()
		+ "\nTotal Cost: " + screening.getMovie().getTicketCost()*request.getArrSize()
		+ "\nSeats IDs: ";
		for (int i = 0; i < request.getArrSize(); i++) {
			temp += request.getSeatIds()[i] + " ";
		}
		movieInfo.setText(temp);
    }
}
