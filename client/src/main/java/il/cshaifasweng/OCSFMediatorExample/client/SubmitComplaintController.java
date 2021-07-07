package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMember;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.CustomerServiceEmployee;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SubmitComplaintController{
	
	public static List <Complaint> complaints;
	public static CinemaMember buyer;
	private static CustomerServiceEmployee worker;
	private static Complaint complaint;
	
	public static CinemaMember getBuyer() {
		return SubmitComplaintController.buyer;
	}
	
	public static void setBuyer(CinemaMember buyer) {
		SubmitComplaintController.buyer = buyer;
	}
	

    @FXML
    private AnchorPane generalAnchor;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button submit;

    @FXML
    private Label title;

    @FXML
    private Button returnBtn;

    @FXML
    private Label usernameLabel;
    
    @FXML
    private Label warning;
    
    @FXML
    private Label warning1;

    @FXML
    private TextArea description;

    @FXML
    private TextArea answer;
    
    @FXML
    private Button submit1;
    
        
    @FXML
    private AnchorPane comboAnchor;
    
    @FXML
    private TableView<Complaint> complaintsTable;

    @FXML
    private TableColumn<Complaint, String> dateCol;

    @FXML
    private TableColumn<Complaint, String> nameCol;

    @FXML
    private TableColumn<Complaint, Integer> idCol;
    
    @FXML
    private Button newComplaint;
    
    @FXML
    private Label pleaseMsg;
    
    
    @FXML
    void AddNewComplaint(ActionEvent event) {
    	description.setText("");
    	answer.setText("");
    	description.setDisable(false);
    	submit.setDisable(false);
    }

    @FXML
    void SubmitAnswer(ActionEvent event) { 
    	if(answer.getText() != "" && answer.getText() != null) {
	    	complaint.setResponse(answer.getText());
	    	complaint.setRepresentetive(worker);
	    	try {
				SimpleClient.getClient().sendToServer(new Message("#SubmitResponseForComplaint", complaint));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				App.setRoot("displayList");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else {
    		warning1.setVisible(true);
    	}
    }
    
    @FXML
    void SubmitComplaint(ActionEvent event) {
    	/*if(description.getText() != "") {
	    	Complaint newComplaint = new Complaint(buyer , description.getText(),"lll");
	    	try {
				SimpleClient.getClient().sendToServer(new Message("#SubmitComplaint", newComplaint));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				App.setRoot("displayList");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else {*/
    		warning.setVisible(true);
    	//}
    }

    @FXML
    void returnToCat(MouseEvent event) {
    	try {
			App.setRoot("displayList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void setCelltoField(MouseEvent event) {
    	int index = complaintsTable.getSelectionModel().getSelectedIndex();
    	if(index>=0) {
			complaint = complaintsTable.getItems().get(index);
			description.setText(complaint.getDescription());
			description.setDisable(true);
			answer.setText(complaint.getResponse());
			submit.setDisable(true);
			if(worker != null) {
				newComplaint.setVisible(false);
				newComplaint.setDisable(true);
				answer.setDisable(complaint.getResponse() != null && complaint.getResponse() != "");
	        	submit1.setDisable(complaint.getResponse() != null && complaint.getResponse() != "");
			}
			else {
				newComplaint.setVisible(true);
				newComplaint.setDisable(false);
			}
    	}
    }
    

    @FXML
    void dissapearWarnings(MouseEvent event) {
//    	warning.setVisible(false);
//    	warning1.setVisible(false);
    }

    @FXML
    void initialize() {
    	
        assert generalAnchor != null : "fx:id=\"generalAnchor\" was not injected: check your FXML file 'submitComplaint.fxml'.";
        assert submit != null : "fx:id=\"submit\" was not injected: check your FXML file 'SubmitComplaint.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'SubmitComplaint.fxml'.";
        assert returnBtn != null : "fx:id=\"returnBtn\" was not injected: check your FXML file 'SubmitComplaint.fxml'.";
        assert usernameLabel != null : "fx:id=\"usernameLabel\" was not injected: check your FXML file 'SubmitComplaint.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'SubmitComplaint.fxml'.";
        assert answer != null : "fx:id=\"answer\" was not injected: check your FXML file 'submitComplaint.fxml'.";
        assert warning != null : "fx:id=\"warning\" was not injected: check your FXML file 'submitComplaint.fxml'.";
        assert warning1 != null : "fx:id=\"warning1\" was not injected: check your FXML file 'submitComplaint.fxml'.";
        assert submit1 != null : "fx:id=\"submit1\" was not injected: check your FXML file 'submitComplaint.fxml'.";
        assert comboAnchor != null : "fx:id=\"comboAnchor\" was not injected: check your FXML file 'submitComplaint.fxml'.";
        assert complaintsTable != null : "fx:id=\"complaintsTable\" was not injected: check your FXML file 'submitComplaint.fxml'.";
        assert dateCol != null : "fx:id=\"dateCol\" was not injected: check your FXML file 'submitComplaint.fxml'.";
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'submitComplaint.fxml'.";
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'submitComplaint.fxml'.";
        assert newComplaint != null : "fx:id=\"newComplaint\" was not injected: check your FXML file 'submitComplaint.fxml'.";
        assert pleaseMsg != null : "fx:id=\"pleaseMsg\" was not injected: check your FXML file 'submitComplaint.fxml'.";


        
        if(buyer != null) {
        	usernameLabel.setText(buyer.getFirstName() + " " + buyer.getLastName());
        	description.setDisable(false);
        	submit.setDisable(false);
        	answer.setDisable(true);
        	submit1.setDisable(true);  
        	pleaseMsg.setText("please write your complaint in the description area below");
        }
        else if(worker != null) {
        	usernameLabel.setText(worker.getWorkerName());
        	answer.setDisable(complaint != null);
        	submit1.setDisable(complaint != null);
        	description.setDisable(true);
        	submit.setDisable(true);
        	newComplaint.setDisable(true);
        	newComplaint.setVisible(false);
        	pleaseMsg.setText("please write your answer in the response area below");

        }
        ObservableList<Complaint> complaintsList = FXCollections.observableArrayList(complaints);
 		idCol.setCellValueFactory(new PropertyValueFactory<Complaint , Integer>("id"));
 		dateCol.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
 		nameCol.setCellValueFactory(new PropertyValueFactory<Complaint , String>("CustomerName"));
 		complaintsTable.setItems(complaintsList);
       
    }

	public static CustomerServiceEmployee getWorker() {
		return worker;
	}

	public static void setWorker(CustomerServiceEmployee worker) {
		SubmitComplaintController.worker = worker;
	}

	public static void setComplaints(List<Complaint> object) {
		SubmitComplaintController.complaints = object;
	}
}
