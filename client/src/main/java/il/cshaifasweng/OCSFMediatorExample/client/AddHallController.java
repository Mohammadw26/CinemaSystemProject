package il.cshaifasweng.OCSFMediatorExample.client;
/**
 * Sample Skeleton for 'addHall.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddHallController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="branchBox"
    private ChoiceBox<?> branchBox; // Value injected by FXMLLoader

    @FXML // fx:id="rowsBox"
    private ChoiceBox<?> rowsBox; // Value injected by FXMLLoader

    @FXML // fx:id="colsBox"
    private ChoiceBox<?> colsBox; // Value injected by FXMLLoader

    @FXML // fx:id="numField"
    private TextField numField; // Value injected by FXMLLoader

    @FXML // fx:id="confirmBtn"
    private Button confirmBtn; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert branchBox != null : "fx:id=\"branchBox\" was not injected: check your FXML file 'addHall.fxml'.";
        assert rowsBox != null : "fx:id=\"rowsBox\" was not injected: check your FXML file 'addHall.fxml'.";
        assert colsBox != null : "fx:id=\"colsBox\" was not injected: check your FXML file 'addHall.fxml'.";
        assert numField != null : "fx:id=\"numField\" was not injected: check your FXML file 'addHall.fxml'.";
        assert confirmBtn != null : "fx:id=\"confirmBtn\" was not injected: check your FXML file 'addHall.fxml'.";

    }
}
