/**
 * Sample Skeleton for 'contactUs.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.CasualBuyer;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.LogInRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ContactUsController {
	private static CasualBuyer complainer = null;
	private static int status;
	private static Complaint complaint;
	
    public static int getStatus() {
		return status;
	}

	public static void setStatus(int status) {
		ContactUsController.status = status;
	}

	public static CasualBuyer getComplainer() {
		return complainer;
	}

	public static void setComplainer(CasualBuyer complainer) {
		ContactUsController.complainer = complainer;
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="loginAnchor"
    private AnchorPane loginAnchor; // Value injected by FXMLLoader

    @FXML // fx:id="loginAnchorLabel1"
    private Text loginAnchorLabel1; // Value injected by FXMLLoader

    @FXML // fx:id="oldUserField"
    private TextField oldUserField; // Value injected by FXMLLoader

    @FXML // fx:id="oldPasswordField"
    private PasswordField oldPasswordField; // Value injected by FXMLLoader

    @FXML // fx:id="loginAnchorLabel2"
    private Text loginAnchorLabel2; // Value injected by FXMLLoader

    @FXML // fx:id="logInButton"
    private Button logInButton; // Value injected by FXMLLoader

    @FXML // fx:id="logOutButton"
    private Button logOutButton; // Value injected by FXMLLoader

    @FXML // fx:id="invalidLogin"
    private Label invalidLogin; // Value injected by FXMLLoader

    @FXML // fx:id="loginAnchor1"
    private AnchorPane loginAnchor1; // Value injected by FXMLLoader

    @FXML // fx:id="loginAnchorLabel11"
    private Text loginAnchorLabel11; // Value injected by FXMLLoader

    @FXML // fx:id="clientID"
    private TextField clientID; // Value injected by FXMLLoader

    @FXML 
    private PasswordField emailSearch; // Value injected by FXMLLoader

    @FXML // fx:id="loginAnchorLabel21"
    private Text loginAnchorLabel21; // Value injected by FXMLLoader

    @FXML // fx:id="logInButton1"
    private Button logInButton1; // Value injected by FXMLLoader

    @FXML // fx:id="logOutButton1"
    private Button logOutButton1; // Value injected by FXMLLoader

    @FXML // fx:id="invalidLogin1"
    private Label invalidLogin1; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="submissionAnchor"
    private AnchorPane submissionAnchor; // Value injected by FXMLLoader

    @FXML // fx:id="nameField"
    private TextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="lastNameField"
    private TextField lastNameField; // Value injected by FXMLLoader

    @FXML // fx:id="idField"
    private TextField idField; // Value injected by FXMLLoader

    @FXML // fx:id="emailField"
    private TextField emailField; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionField"
    private TextArea descriptionField; // Value injected by FXMLLoader

    @FXML // fx:id="submitBtn"
    private Button submitBtn; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<Complaint> table; // Value injected by FXMLLoader

    @FXML // fx:id="identifierCol"
    private TableColumn<Complaint, Long> identifierCol; // Value injected by FXMLLoader

    @FXML // fx:id="statusCol"
    private TableColumn<Complaint, String> statusCol; // Value injected by FXMLLoader

    @FXML // fx:id="modeBtn"
    private Button modeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="responseAnchor"
    private AnchorPane responseAnchor; // Value injected by FXMLLoader

    @FXML // fx:id="inquiryDetails"
    private TextArea inquiryDetails; // Value injected by FXMLLoader

    @FXML // fx:id="responseDetails"
    private TextArea responseDetails; // Value injected by FXMLLoader
    
    @FXML // fx:id="warning"
    private Label warning; // Value injected by FXMLLoader
    
    @FXML
    private ComboBox<String> branchCombo;
    
    @FXML // fx:id="tableAnchor"
    private AnchorPane tableAnchor; // Value injected by FXMLLoader
    
    ObservableList<Complaint> complaints;

    @FXML
    void LogOut(ActionEvent event) {
    	DisplayListController.setMember(null);
    	DisplayListController.setWorker(null);
    	try {
    		App.setRoot("contactUs");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void backToCatalog(ActionEvent event) {
    	complainer = null;
    	try {
			App.setRoot("displayList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void changeMode() {
    	if (!tableAnchor.isVisible()){
    		tableAnchor.setVisible(true);
    		responseAnchor.setVisible(true);
    		submissionAnchor.setVisible(false);
    		modeBtn.setText("Submit another inquiry");
    		if (complaints.size()>0)
    			complaint = complaints.get(0);
        	inquiryDetails.setText(complaint.getDescription() + "\n\n" + complaint.getSubmissionDate());
        	if (complaint.getStatus().equals("Closed")){
        		responseDetails.setText(complaint.getResponse()+ "\n\n" + complaint.getResponseDate());
        	} else {
        		responseDetails.setText("Your request is pending, no answer has been received yet.");
        		responseDetails.setDisable(true);
        	}
    	} else {
    		tableAnchor.setVisible(false);
    		responseAnchor.setVisible(false);
    		submissionAnchor.setVisible(true);
    		modeBtn.setText("View previous submissions");
    	}
    }

    @FXML
    void logIn(ActionEvent event) {
    	if (oldUserField.getText()!= "" && oldPasswordField.getText()!= "") {
        	LogInRequest newRequest = new LogInRequest(oldUserField.getText(),oldPasswordField.getText());
        	try {
    			SimpleClient.getClient().sendToServer(new Message("#LoginRequestContactUs", newRequest));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }

    @FXML
    void search(ActionEvent event) {
    	if (clientID.getText()!= "" && emailSearch.getText()!= "") {
        	LogInRequest newRequest = new LogInRequest(clientID.getText(),emailSearch.getText());
        	try {
    			SimpleClient.getClient().sendToServer(new Message("#SearchForClient2", newRequest));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }

    @FXML
    void submitRequest(ActionEvent event) {
    	if(descriptionField.getText() == "" || nameField.getText()=="" || lastNameField.getText() == "" || idField.getText() == "" || emailField.getText()=="") {
    		warning.setVisible(true);
    	} else {
    		modeBtn.setVisible(true);
    		for (SirtyaBranch branch : DisplayListController.getAllBranches()) {
    			if (branch.getAddress() == branchCombo.getValue()) {
    				try {
    		    		if (DisplayListController.getMember()==null) {
    		    			SimpleClient.getClient().sendToServer(new Message("#SubmitComplaint", idField.getText(),emailField.getText(), nameField.getText() + " " + lastNameField.getText() + ":\n" + descriptionField.getText(), branch));
    		    		} else {
    		    			SimpleClient.getClient().sendToServer(new Message("#SubmitComplaint", DisplayListController.getMember(),emailField.getText(), nameField.getText() + " " + lastNameField.getText() + ":\n" + descriptionField.getText(), branch));
    		    		}
    		    		} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    		    		}
    				return;
    			}
    		}
			try {
	    		if (DisplayListController.getMember()==null) {
	    			SimpleClient.getClient().sendToServer(new Message("#SubmitComplaint", idField.getText(),emailField.getText(), nameField.getText() + " " + lastNameField.getText() + ":\n" + descriptionField.getText(),null));
	    		} else {
	    			SimpleClient.getClient().sendToServer(new Message("#SubmitComplaint", DisplayListController.getMember(),emailField.getText(), nameField.getText() + " " + lastNameField.getText() + ":\n" + descriptionField.getText(),null));
	    		}
	    		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	    		}
    		
    	}
    }
    
    @FXML
    void setCell(MouseEvent event) {
    	complaint = table.getItems().get(table.getSelectionModel().getSelectedIndex());
    	inquiryDetails.setText(complaint.getDescription() + "\n\n" + complaint.getSubmissionDate());
    	if (complaint.getStatus().equals("Closed")){
    		responseDetails.setText(complaint.getResponse()+ "\n\n" + complaint.getResponseDate());
    	} else {
    		responseDetails.setText("Your request is pending, no answer has been received yet.");
    		responseDetails.setDisable(true);
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	if (status == 1) {
    		invalidLogin.setVisible(true);
    		status = 0;
    	} if (status == 2) {
    		invalidLogin1.setVisible(true);
    	}
		responseAnchor.setVisible(false);
		submissionAnchor.setVisible(true);
    	tableAnchor.setVisible(false);
    	warning.setVisible(false);
    	for (SirtyaBranch brnch : DisplayListController.getAllBranches()) {
    		branchCombo.getItems().add(brnch.getAddress());
    	}
    	if (DisplayListController.getMember()!=null) {
    		responseAnchor.setVisible(true);
    		submissionAnchor.setVisible(false);
    		loginAnchor.setVisible(false);
    		loginAnchor1.setVisible(false);
    		complainer = DisplayListController.getMember();
    	}
    	statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    	identifierCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	if (complainer!=null) {
    		nameField.setText(complainer.getFirstName());
    		lastNameField.setText(complainer.getLastName());
    		emailField.setText(complainer.getElectronicMail());
    		idField.setText(String.valueOf(complainer.getId()));
    		complaints = FXCollections.observableArrayList(complainer.getComplaints());
    		table.setItems(complaints);
    		changeMode();
    	}
    	if (complainer==null) {
    		modeBtn.setVisible(false);
    	}
    }
}
