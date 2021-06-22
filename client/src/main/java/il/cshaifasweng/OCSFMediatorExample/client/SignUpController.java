package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMember;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField passTxt;

    @FXML
    private TextField firstNameTxt;

    @FXML
    private TextField lastNameTxt;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField creditCardTxt;

    @FXML
    private TextField mailTxt;

    void CommitChanges(ActionEvent event) {
    	CinemaMember member = new CinemaMember();
    	member.setUsername(usernameTxt.getText());
    	member.setPassword(passTxt.getText());
    	member.setFirstName(firstNameTxt.getText());
    	member.setLastName(lastNameTxt.getText());
    	member.setCreditNum(Integer.parseInt(creditCardTxt.getText()));
    	member.setElectronicMail(mailTxt.getText());
    	try {
			SimpleClient.getClient().sendToServer(new Message("#AddCinemaMember",member));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

    @FXML
    void returnToCatalog(ActionEvent event) {
    	try {
			SimpleClient.getClient().sendToServer("#CatalogRequest");
	    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
        assert signUpBtn != null : "fx:id=\"signUpBtn\" was not injected: check your FXML file 'signUp.fxml'.";
        assert usernameTxt != null : "fx:id=\"usernameTxt\" was not injected: check your FXML file 'signUp.fxml'.";
        assert passTxt != null : "fx:id=\"passTxt\" was not injected: check your FXML file 'signUp.fxml'.";
        assert firstNameTxt != null : "fx:id=\"firstNameTxt\" was not injected: check your FXML file 'signUp.fxml'.";
        assert lastNameTxt != null : "fx:id=\"lastNameTxt\" was not injected: check your FXML file 'signUp.fxml'.";
        assert idTxt != null : "fx:id=\"idTxt\" was not injected: check your FXML file 'signUp.fxml'.";
        assert creditCardTxt != null : "fx:id=\"creditCardTxt\" was not injected: check your FXML file 'signUp.fxml'.";
        assert mailTxt != null : "fx:id=\"mailTxt\" was not injected: check your FXML file 'signUp.fxml'.";

    }
}
