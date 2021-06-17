package il.cshaifasweng.OCSFMediatorExample.client;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AdminPanelController {
	private static List<Worker> allWorkers;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordBar;

    @FXML
    private FontAwesomeIconView userIcon;

    @FXML
    private FontAwesomeIconView passwordICon;

    @FXML
    private Button LoginBtn;

    @FXML
    private Tooltip passwordTip;
    
    @FXML
    private CheckBox passToggle;
    
    @FXML
    private TextField passText;
    
    @FXML // fx:id="homePage"
    private Button homePage; // Value injected by FXMLLoader

    @FXML
    private Label invalidText;
 
    @FXML
    void onKey(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		LoginAction(null);
    	}
//    	else if(event.getCode().equals(KeyCode.TAB)) {
//    		passText.focusTraversableProperty();
//    	}

    }
   
    
    @FXML
    void hideError(MouseEvent event) {
    	invalidText.setVisible(false);

    }

    @FXML
    void showPassword(ActionEvent event) {
    	if(passToggle.isSelected()) {
    		passText.setText(passwordBar.getText());
    		passText.setVisible(true);
    		passwordBar.setVisible(false);
    		return;
    	}
    	passwordBar.setText(passText.getText());
    	passwordBar.setVisible(true);
    	passText.setVisible(false);
    }
    
    @FXML
    void goHomePage(ActionEvent event) {
    	try {
			App.setRoot("primary");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    @FXML
    void LoginAction(ActionEvent event) {
		for(Worker worker: allWorkers) {
			if(worker.getWokerUsername().equals(usernameField.getText())) {
				if(worker.getWorkerPassword().equals(passwordBar.getText())) {
					DisplayListController.setWorker(worker);
					try {
						SimpleClient.getClient().sendToServer("#CatalogRequest");
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					invalidText.setVisible(true);
				}
			}
			else {
				invalidText.setVisible(true);
				
			}
			
		}
		
    }
    
    @FXML
    void initialize() {
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'adminPanel.fxml'.";
        assert passwordBar != null : "fx:id=\"passwordBar\" was not injected: check your FXML file 'adminPanel.fxml'.";
        assert userIcon != null : "fx:id=\"userIcon\" was not injected: check your FXML file 'adminPanel.fxml'.";
        assert passwordICon != null : "fx:id=\"passwordICon\" was not injected: check your FXML file 'adminPanel.fxml'.";
        assert LoginBtn != null : "fx:id=\"LoginBtn\" was not injected: check your FXML file 'adminPanel.fxml'.";
        assert homePage != null : "fx:id=\"homePage\" was not injected: check your FXML file 'adminPanel.fxml'.";
        assert invalidText != null : "fx:id=\"invalidText\" was not injected: check your FXML file 'adminPanel.fxml'.";
        assert passText != null : "fx:id=\"passText\" was not injected: check your FXML file 'adminPanel.fxml'.";
        assert passToggle != null : "fx:id=\"passToggle\" was not injected: check your FXML file 'adminPanel.fxml'.";


    }

	public static List<Worker> getAllWorkers() {
		return allWorkers;
	}

	public static void setAllWorkers(List<Worker> allWorkers) {
		AdminPanelController.allWorkers = allWorkers;
	}
}
