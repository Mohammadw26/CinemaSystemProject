package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMember;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class SubmitComplaintController{
	
	public static CinemaMember buyer;
	private static Worker worker;

	
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
    void SubmitAnswer(ActionEvent event) {

    }
    
    @FXML
    void SubmitComplaint(ActionEvent event) {
    	
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


        
        if(buyer != null) {
        	System.out.println("member not null");
        	usernameLabel.setText(buyer.getUsername());
        	description.setDisable(false);
        	submit.setDisable(false);
        	answer.setDisable(true);
        	submit1.setDisable(true);
        }
        else if(worker != null) {
        	System.out.println("worker not null");

        	usernameLabel.setText(worker.getWokerUsername());
        	answer.setDisable(false);
        	submit1.setDisable(false);
        	description.setDisable(true);
        	submit.setDisable(true);

        }
    }

	public static Worker getWorker() {
		return worker;
	}

	public static void setWorker(Worker worker) {
		SubmitComplaintController.worker = worker;
	}
}
