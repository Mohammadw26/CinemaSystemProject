/**
 * Sample Skeleton for 'TavSagoalUpdating.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.TavSagoal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class TavSagoalUpdatingController {
	private static boolean flag = false;

    public static boolean isFlag() {
		return flag;
	}

	public static void setFlag(boolean flag) {
		TavSagoalUpdatingController.flag = flag;
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="returnBtn"
    private Button returnBtn; // Value injected by FXMLLoader

    @FXML // fx:id="toggle"
    private CheckBox toggle; // Value injected by FXMLLoader

    @FXML // fx:id="fromDate"
    private DatePicker fromDate; // Value injected by FXMLLoader

    @FXML // fx:id="toDate"
    private DatePicker toDate; // Value injected by FXMLLoader

    @FXML // fx:id="maxNum"
    private TextField maxNum; // Value injected by FXMLLoader

    @FXML // fx:id="updateButton"
    private Button updateButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="warn"
    private Label warn; // Value injected by FXMLLoader

    @FXML // fx:id="msg"
    private Label msg; // Value injected by FXMLLoader
    
    @FXML // fx:id="img"
    private ImageView img; // Value injected by FXMLLoader
   

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
    void update(ActionEvent event) {
    	TavSagoal newTav = new TavSagoal();
    	newTav.setFromDate("");
    	newTav.setToDate("");
    	newTav.setEffective(toggle.isSelected());
    	if (toggle.isSelected() && (maxNum.getText() == "")) {
    		warn.setText("Maximum number of people in a closed space must be updated.");
    		warn.setVisible(true);
    		return;
    	} else if  (toggle.isSelected() && ((fromDate.getValue() == null && toDate.getValue() != null)||(fromDate.getValue() != null && toDate.getValue() == null))) {
    		warn.setText("You can either fill both dates, or neither.");
    		warn.setVisible(true);
    	}
    	if (!toggle.isSelected()) {
    		newTav.setEffective(toggle.isSelected());
    		newTav.setY(100);
    		newTav.setFromDate("");
    		newTav.setToDate("");
    	} else if (toggle.isSelected()) {
    		newTav.setEffective(toggle.isSelected());
    		newTav.setY(Integer.parseInt(maxNum.getText()));
    		if (fromDate.getValue() != null) {
    			newTav.setFromDate(fromDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    			newTav.setToDate(toDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    		}
    	}
        img.setVisible(true);
    	try {
			SimpleClient.getClient().sendToServer(new Message("#UpdateTav",newTav));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert returnBtn != null : "fx:id=\"returnBtn\" was not injected: check your FXML file 'TavSagoalUpdating.fxml'.";
        assert toggle != null : "fx:id=\"toggle\" was not injected: check your FXML file 'TavSagoalUpdating.fxml'.";
        assert fromDate != null : "fx:id=\"fromDate\" was not injected: check your FXML file 'TavSagoalUpdating.fxml'.";
        assert toDate != null : "fx:id=\"toDate\" was not injected: check your FXML file 'TavSagoalUpdating.fxml'.";
        assert maxNum != null : "fx:id=\"maxNum\" was not injected: check your FXML file 'TavSagoalUpdating.fxml'.";
        assert warn != null : "fx:id=\"warn\" was not injected: check your FXML file 'TavSagoalUpdating.fxml'.";
        assert msg != null : "fx:id=\"msg\" was not injected: check your FXML file 'TavSagoalUpdating.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'TavSagoalUpdating.fxml'.";
        assert img != null : "fx:id=\"img\" was not injected: check your FXML file 'TavSagoalUpdating.fxml'.";
        if (isFlag()) {
        	img.setVisible(false);
        	msg.setVisible(true);
        	flag = false;
        }
    }
}
