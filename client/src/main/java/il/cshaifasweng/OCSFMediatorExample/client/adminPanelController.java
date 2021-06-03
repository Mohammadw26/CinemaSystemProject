package il.cshaifasweng.OCSFMediatorExample.client;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.event.ActionEvent;

public class adminPanelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordBar;

    @FXML
    private FontAwesomeIconView userIcon;

    @FXML
    private FontAwesomeIconView passwordICon;

    @FXML
    private Button LoginBtn;

    @FXML
    private Label wrongPassword;

    @FXML
    private Tooltip passwordTip;
    
    @FXML
    private CheckBox passToggle;
    
    @FXML
    private TextField passText;

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
    void initialize() {
        assert passwordBar != null : "fx:id=\"passwordBar\" was not injected: check your FXML file 'adminPanel.fxml'.";
        assert userIcon != null : "fx:id=\"userIcon\" was not injected: check your FXML file 'adminPanel.fxml'.";
        assert passwordICon != null : "fx:id=\"passwordICon\" was not injected: check your FXML file 'adminPanel.fxml'.";
        assert LoginBtn != null : "fx:id=\"LoginBtn\" was not injected: check your FXML file 'adminPanel.fxml'.";
        assert wrongPassword != null : "fx:id=\"wrongPassword\" was not injected: check your FXML file 'adminPanel.fxml'.";

    }
}
