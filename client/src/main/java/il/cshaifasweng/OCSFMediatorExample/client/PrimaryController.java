package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button showCatalog;
	
    @FXML
    private Label welcomeLabel;

    @FXML
    private Label cinemaLabel;

    @FXML
    private Button adminLabel;
    
    @FXML
    private Button signUpBtn;

    @FXML // fx:id="identityLabel"
    private Label identityLabel; // Value injected by FXMLLoader
    

    @FXML
    void adminLogin(ActionEvent event) {
    	if (adminLabel.getText().equals("Log-in")) {
    		try {
    			App.setRoot("adminPanel");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else {
    		DisplayListController.setMember(null);
    		DisplayListController.setWorker(null);
    		SubmitComplaintController.setBuyer(null);
    		SubmitComplaintController.setWorker(null);
    		try {
    			App.setRoot("primary");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
    

    @FXML
    void signUp(ActionEvent event) {
		try {
			App.setRoot("signUp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@FXML
	void sendCatalogRequest(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("#CatalogRequest");
	    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void initialize() {
		assert showCatalog != null : "fx:id=\"showCatalog\" was not injected: check your FXML file 'primary.fxml'.";
		assert adminLabel != null : "fx:id=\"showCatalog\" was not injected: check your FXML file 'primary.fxml'.";
		identityLabel.setVisible(false);
		signUpBtn.setVisible(true);
		adminLabel.setText("Log-in");
		if (DisplayListController.getWorker()!=null) {
			identityLabel.setText("Logged in as: " + DisplayListController.getWorker().getWorkerName());
			identityLabel.setVisible(true);
			adminLabel.setText("Log-out");
			signUpBtn.setVisible(false);
		}
		if (DisplayListController.getMember()!=null) {
			identityLabel.setText("Logged in as: " + DisplayListController.getMember().getFirstName() + " " + DisplayListController.getMember().getLastName() );
			identityLabel.setVisible(true);
			adminLabel.setText("Log-out");
			signUpBtn.setVisible(false);
		}
	}
}
