package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
    private TableColumn<Complaint, String> idCol;
    

    @FXML
    void SubmitAnswer(ActionEvent event) { // not gonna work now because comlaint is null
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
    }
    
    @FXML
    void SubmitComplaint(ActionEvent event) {
    	Complaint newComplaint = new Complaint();
    	newComplaint.setDescription(description.getText());
    	ZonedDateTime start = ZonedDateTime.now(ZoneId.of("Asia/Jerusalem"));
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy ',' HH:mm:ss");
		String transactionTime = formatter.format(start);
		newComplaint.setSubmissionDate(transactionTime);
		newComplaint.setClient(buyer);
		
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
    void selectScreening(MouseEvent event) {

    }

    @FXML
    void initialize() {
    	
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


        
        if(buyer != null) {
        	//System.out.println("member not null");
        	usernameLabel.setText(buyer.getUsername());
        	description.setDisable(false);
        	submit.setDisable(false);
        	answer.setDisable(true);
        	submit1.setDisable(true);
        	comboAnchor.setVisible(false);
        	
        }
        else if(worker != null) {
        	//System.out.println("worker not null");

        	usernameLabel.setText(worker.getWokerUsername());
        	answer.setDisable(false);
        	submit1.setDisable(false);
        	description.setDisable(true);
        	submit.setDisable(true);
        	comboAnchor.setVisible(true);

        }
        
//        ObservableList<Complaint> complaintsList = FXCollections.observableArrayList(movie.getScreenings());
//		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
//		dateCol.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
//		nameCol.setCellValueFactory(new PropertyValueFactory<>("complaintClient"));
//		complaintsTable.setItems(complaintsList);
    }

	public static CustomerServiceEmployee getWorker() {
		return worker;
	}

	public static void setWorker(CustomerServiceEmployee worker) {
		SubmitComplaintController.worker = worker;
	}
}
