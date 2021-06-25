package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.FullOrderRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;

public class SignUpController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

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

    @FXML // fx:id="newUserField"
    private TextField newUserField; // Value injected by FXMLLoader

    @FXML // fx:id="newPasswordField"
    private TextField newPasswordField; // Value injected by FXMLLoader

    @FXML // fx:id="useEmailCheck"
    private CheckBox useEmailCheck; // Value injected by FXMLLoader

    @FXML // fx:id="memberPerksAnchor"
    private AnchorPane memberPerksAnchor; // Value injected by FXMLLoader

    @FXML
    void copyEmail(ActionEvent event) {
    	newUserField.setText(emailField.getText());
    }
    
    @FXML
    void signUp(MouseEvent event) {
    	FullOrderRequest request = new FullOrderRequest(nameField.getText(), lastNameField.getText()
				, emailField.getText(), Integer.parseInt(idField.getText()), Long.parseLong(cardField.getText()));
		request.setUsername(newUserField.getText());
		request.setPassword(newPasswordField.getText());
		try {
			SimpleClient.getClient().sendToServer(new Message("#SignUpRequest", request));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'signUp.fxml'.";
        assert lastNameField != null : "fx:id=\"lastNameField\" was not injected: check your FXML file 'signUp.fxml'.";
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'signUp.fxml'.";
        assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'signUp.fxml'.";
        assert cardField != null : "fx:id=\"cardField\" was not injected: check your FXML file 'signUp.fxml'.";
        assert newUserField != null : "fx:id=\"newUserField\" was not injected: check your FXML file 'signUp.fxml'.";
        assert newPasswordField != null : "fx:id=\"newPasswordField\" was not injected: check your FXML file 'signUp.fxml'.";
        assert useEmailCheck != null : "fx:id=\"useEmailCheck\" was not injected: check your FXML file 'signUp.fxml'.";
        assert memberPerksAnchor != null : "fx:id=\"memberPerksAnchor\" was not injected: check your FXML file 'signUp.fxml'.";

    }
}

