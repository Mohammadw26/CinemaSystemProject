/**
 * Sample Skeleton for 'purchaseHistory.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.CasualBuyer;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMember;
import il.cshaifasweng.OCSFMediatorExample.entities.LogInRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.TabPurchase;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PurchaseHistoryController {
	private static List<Purchase> purchaseList;
	private static CasualBuyer unregClient;
	private static int status = 0;
	private Purchase temp;

	public static int getStatus() {
		return status;
	}

	public static void setStatus(int status) {
		PurchaseHistoryController.status = status;
	}

	public static List<Purchase> getPurchaseList() {
		return purchaseList;
	}

	public static void setPurchaseList(List<Purchase> purchaseList) {
		PurchaseHistoryController.purchaseList = purchaseList;
	}

	public static CasualBuyer getUnregClient() {
		return unregClient;
	}

	public static void setUnregClient(CasualBuyer unregClient) {
		PurchaseHistoryController.unregClient = unregClient;
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
	
	@FXML // fx:id="logOutButton"
	private Button homeBtn; // Value injected by FXMLLoader

	@FXML // fx:id="invalidLogin"
	private Label invalidLogin; // Value injected by FXMLLoader

	@FXML // fx:id="loginAnchor1"
	private AnchorPane loginAnchor1; // Value injected by FXMLLoader

	@FXML // fx:id="loginAnchorLabel11"
	private Text loginAnchorLabel11; // Value injected by FXMLLoader

	@FXML // fx:id="clientID"
	private TextField clientID; // Value injected by FXMLLoader

	@FXML // fx:id="cardDigits"
	private PasswordField cardDigits; // Value injected by FXMLLoader

	@FXML // fx:id="loginAnchorLabel21"
	private Text loginAnchorLabel21; // Value injected by FXMLLoader

	@FXML // fx:id="logInButton1"
	private Button logInButton1; // Value injected by FXMLLoader

	@FXML // fx:id="logOutButton1"
	private Button logOutButton1; // Value injected by FXMLLoader

	@FXML // fx:id="invalidLogin1"
	private Label invalidLogin1; // Value injected by FXMLLoader

	@FXML // fx:id="historyTable"
	private TableView<Purchase> historyTable; // Value injected by FXMLLoader

	@FXML // fx:id="type"
	private TableColumn<Purchase, String> type; // Value injected by FXMLLoader

	@FXML // fx:id="time"
	private TableColumn<Purchase, String> time; // Value injected by FXMLLoader

	@FXML // fx:id="cost"
	private TableColumn<Purchase, Double> cost; // Value injected by FXMLLoader

	@FXML // fx:id="details"
	private TableColumn<Purchase, String> details; // Value injected by FXMLLoader

	@FXML
	private TableColumn<Purchase, String> statusCol;

	@FXML // fx:id="transactionID"
	private TableColumn<Purchase, Long> transactionID; // Value injected by FXMLLoader

	@FXML // fx:id="welcomeLabel"
	private Label welcomeLabel; // Value injected by FXMLLoader

	@FXML // fx:id="cancelBtn"
	private Button cancelBtn; // Value injected by FXMLLoader

	@FXML // fx:id="waitingAnchor"
	private AnchorPane waitingAnchor; // Value injected by FXMLLoader

	@FXML
	private Label successLabel;

	@FXML
	private Label warningLabel;

	@FXML
	private AnchorPane userAnchor;


	@FXML
	void backToCatalog(ActionEvent event) {
		try {
			App.setRoot("displayList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void CancelPurchase(ActionEvent event) {
		if (historyTable.getItems().isEmpty()) {
			Warning new_warning = new Warning("You don't have any purchases to cancel");
			EventBus.getDefault().post(new WarningEvent((Warning) new_warning));
			return;
		} else if (temp == null ) {
			Warning new_warning = new Warning("You did not select an item.");
			EventBus.getDefault().post(new WarningEvent((Warning) new_warning));
			return;
		} else {
		}
			if (temp.getClass() == TabPurchase.class) {
				Warning new_warning = new Warning("You can't cancel a tab order");
				EventBus.getDefault().post(new WarningEvent((Warning) new_warning));
				return;
			}
			else if(temp.getStatus().contains("Canceled")){
				Warning new_warning = new Warning("This item has already been canceled");
				EventBus.getDefault().post(new WarningEvent((Warning) new_warning));
				return;
				
			}
			waitingAnchor.setVisible(true);
			try {
				if (DisplayListController.getMember() == null)
					SimpleClient.getClient().sendToServer(new Message("#CancelOrder", unregClient, temp));
				else
					SimpleClient.getClient()
							.sendToServer(new Message("#CancelOrder", DisplayListController.getMember(), temp));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	

	@FXML
	void LogOut(ActionEvent event) {
		DisplayListController.setMember(null);
		DisplayListController.setWorker(null);
		try {
			App.setRoot("purchaseHistory");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void logIn(ActionEvent event) {
		if (oldUserField.getText() != "" && oldPasswordField.getText() != "") {
			LogInRequest newRequest = new LogInRequest(oldUserField.getText(), oldPasswordField.getText());
			try {
				SimpleClient.getClient().sendToServer(new Message("#LoginRequestHistory", newRequest));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void search(ActionEvent event) {
		if (clientID.getText() != "" && cardDigits.getText() != "") {
			LogInRequest newRequest = new LogInRequest(clientID.getText(), cardDigits.getText());
			try {
				SimpleClient.getClient().sendToServer(new Message("#SearchForClient", newRequest));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void setCell(MouseEvent event) {
		warningLabel.setVisible(false);
		temp = historyTable.getItems().get(historyTable.getSelectionModel().getSelectedIndex());
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		invalidLogin.setVisible(false);
		invalidLogin1.setVisible(false);
		logOutButton.setVisible(false);
		if (DisplayListController.getMember() != null && status == 0) {
			userAnchor.setVisible(true);
			logOutButton.setVisible(true);
			CinemaMember member = DisplayListController.getMember();
			loginAnchorLabel1
					.setText("You are already logged in as: " + member.getFirstName() + " " + member.getLastName());
			loginAnchorLabel2.setText("please enter you password to confirm it's you");
			oldUserField.setText(member.getUsername());
			oldUserField.setDisable(true);
			logInButton.setText("Confirm");
			loginAnchor1.setVisible(false);
		} else if (status == 1 || status == 6) {
			userAnchor.setVisible(true);
			welcomeLabel.setText(DisplayListController.getMember().getFirstName() + " "
					+ DisplayListController.getMember().getLastName());
			loginAnchor.setVisible(false);
			loginAnchor1.setVisible(false);
			populateTable();
			status = 0;
		} else if (status == 2) {
			if (DisplayListController.getMember()!=null) {
				userAnchor.setVisible(true);
				logOutButton.setVisible(true);
				CinemaMember member = DisplayListController.getMember();
				loginAnchorLabel1.setText("You are already logged in as: " + member.getFirstName() + " " + member.getLastName());
				loginAnchorLabel2.setText("please enter you password to confirm it's you");
				oldUserField.setText(member.getUsername());
				oldUserField.setDisable(true);
				logInButton.setText("Confirm");
				loginAnchor1.setVisible(false);
			}
			invalidLogin.setVisible(true);
			status = 0;
		} else if (status == 4) {
			invalidLogin1.setVisible(true);
			status = 0;
		} else if (status == 3 || status == 5) {
			userAnchor.setVisible(true);
			if (status == 5) {
				successLabel.setVisible(true);
			}
			welcomeLabel.setText("Welcome, " + unregClient.getFirstName() + " " + unregClient.getLastName());
			loginAnchor.setVisible(false);
			loginAnchor1.setVisible(false);
			populateTable();
			status = 0;
		}
	}

	void populateTable() {
		ObservableList<Purchase> oPurchaseList = FXCollections.observableArrayList(purchaseList);
		type.setCellValueFactory(new PropertyValueFactory<>("purchaseType"));
		time.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));
		cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
		details.setCellValueFactory(new PropertyValueFactory<>("details"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		transactionID.setCellValueFactory(new PropertyValueFactory<>("id"));
		historyTable.setItems(oPurchaseList);
	}
}
