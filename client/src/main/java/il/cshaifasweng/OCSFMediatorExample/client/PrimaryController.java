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
    void adminLogin(ActionEvent event) {
    	try {
			SimpleClient.getClient().sendToServer("#WorkersRequest");
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

	}
}
