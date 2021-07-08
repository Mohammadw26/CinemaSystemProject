package il.cshaifasweng.OCSFMediatorExample.client;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class RespondToComplaintsController {
	private static List<Complaint> allComplaints = new ArrayList<Complaint>();
	ObservableList<Complaint> open = FXCollections.observableArrayList();
	ObservableList<Complaint> closed = FXCollections.observableArrayList();
	ObservableList<Complaint> highPriority = FXCollections.observableArrayList();
	private Complaint complaint;
	private static int counter = 0;

    public static List<Complaint> getAllComplaints() {
		return allComplaints;
	}

	public static void setAllComplaints(List<Complaint> allComplaints) {
		RespondToComplaintsController.allComplaints = allComplaints;
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="title"
    private Label title; // Value injected by FXMLLoader
    
    @FXML
    private TabPane tabPane;

    @FXML // fx:id="openTab"
    private Tab openTab; // Value injected by FXMLLoader

    @FXML // fx:id="ClosedTab"
    private Tab ClosedTab; // Value injected by FXMLLoader

    @FXML // fx:id="priorityTab"
    private Tab priorityTab; // Value injected by FXMLLoader

    @FXML // fx:id="summuray"
    private Label summuray; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<Complaint> table1; // Value injected by FXMLLoader

    @FXML // fx:id="identifierCol"
    private TableColumn<Complaint, Long> identifierCol; // Value injected by FXMLLoader

    @FXML // fx:id="subDateCol"
    private TableColumn<Complaint, String> subDateCol; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionCol"
    private TableColumn<Complaint, String> descriptionCol; // Value injected by FXMLLoader
    
    @FXML
    private TableView<Complaint> table2;

    @FXML
    private TableColumn<Complaint, Long> identifierCol1;

    @FXML
    private TableColumn<Complaint, String> subDateCol1;

    @FXML
    private TableColumn<Complaint, String> descriptionCol1;

    @FXML
    private TableView<Complaint> table3;

    @FXML
    private TableColumn<Complaint, Long> identifierCol2;

    @FXML
    private TableColumn<Complaint, String> subDateCol2;
    
    @FXML
    private TableColumn<Complaint, String> descriptionCol2;

    @FXML // fx:id="customerMessage"
    private TextArea customerMessage; // Value injected by FXMLLoader

    @FXML // fx:id="response"
    private TextArea response; // Value injected by FXMLLoader

    @FXML // fx:id="refundCheck"
    private CheckBox refundCheck; // Value injected by FXMLLoader

    @FXML // fx:id="refundValue"
    private TextField refundValue; // Value injected by FXMLLoader
    
    @FXML
    private Text inqNumLabel;

    @FXML
    private Button saveResponseBTN;
    
    @FXML
    private Button backBtn;
    
    @FXML
    private Label invalidInputs;

    @FXML
    private AnchorPane WaitingAnchor;
    
    @FXML
    private AnchorPane actionAnchor;
    
    @FXML
    private ComboBox<String> branchCombo;
    
    @FXML
    private FontAwesomeIconView refreshIcon;
    
    @FXML
    private TextField feedOfBranch;
    
    private static String branchComboBackup = "All Branches";
    
    private static int tabBackup = 0;
    

    @FXML
    void filter() {
    	branchComboBackup = branchCombo.getValue();
    	counter = 0;
    	String address = branchCombo.getValue();
    	highPriority.clear();
    	closed.clear();
    	open.clear();
        for (Complaint temp : allComplaints) {
        	if (temp.getStatus().equals("Closed")) {
        		closed.add(temp);
        	} else {
        		open.add(temp);
       			if (LocalDateTime.parse(temp.getSubmissionDate() , DateTimeFormatter.ofPattern("dd/MM/yyyy ',' HH:mm")).plusHours(16).isBefore(LocalDateTime.now())) {
       				highPriority.add(temp);
       				if (LocalDateTime.parse(temp.getSubmissionDate() , DateTimeFormatter.ofPattern("dd/MM/yyyy ',' HH:mm")).plusHours(24).isBefore(LocalDateTime.now())) {
       					counter++;
       				}
       			}
       		}
       	}
        if (address != "All Branches") {
    		for (int i = 0; i < highPriority.size() ; i ++) {
    			if (highPriority.get(i).getBranch()!=null) {
    				if (!highPriority.get(i).getBranch().getAddress().equals(address)) {
    					highPriority.remove(i);
    					i--;
    				}
    			}
    		}
    		for (int i = 0; i < open.size() ; i ++) {
    			if (open.get(i).getBranch()!=null) {
    				if (!open.get(i).getBranch().getAddress().equals(address)) {
    					open.remove(i);
    					i--;
    				}
    			}
    		}
    		for (int i = 0; i < closed.size() ; i ++) {
    			if (closed.get(i).getBranch()!=null) {
    				if (!closed.get(i).getBranch().getAddress().equals(address)) {
    					closed.remove(i);
    					i--;
    				}
    			}
    		}
    	}
    	table1.setDisable(true);
    	table2.setDisable(true);
    	table3.setDisable(true);
    	table1.setItems(highPriority);
    	table2.setItems(open);
    	table3.setItems(closed);
    	if (highPriority.size()>0)
    		table1.setDisable(false);
    	if (open.size()>0)
    		table2.setDisable(false);
    	if (closed.size()>0)
    		table3.setDisable(false);
    	priorityTab.setText("High priority (" + highPriority.size()+ ")");
    	openTab.setText("Open Tickets (" + open.size()+ ")");
    	ClosedTab.setText("Closed Tickets (" + closed.size()+ ")");
    	summuray.setText("Total Tickets (In selected branch): " + (closed.size() + open.size()) +"\nTickets waiting for response: " 
    	+ open.size() + "\nTickets with tight response time: " + highPriority.size() + "\nTickets with waiting Time greater than SLA: " + counter);

    }
    
    @FXML
    void refreshTickets(MouseEvent event) {
    	try {
			SimpleClient.getClient().sendToServer("#ComplaintsList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void backToCatalog(ActionEvent event) {
    	WaitingAnchor.setVisible(true);
    	try {
			App.setRoot("displayList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void saveResponse(ActionEvent event) {
    	tabBackup = tabPane.getSelectionModel().getSelectedIndex();
    	if(response.getText()=="" || response.getText()==null || (refundCheck.isSelected() || complaint == null && (refundValue.getText()==null || refundValue.getText()==""))) {
    		invalidInputs.setVisible(true);
    		return;
    	}
    	WaitingAnchor.setVisible(true);
    	System.out.println("SendingResponseToServer");
    	try {
			SimpleClient.getClient().sendToServer(new Message("#UpdateResponse", complaint.getId(), response.getText(),refundValue.getText(), DisplayListController.getWorker() ));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void setRefundField(ActionEvent event) {
    	if (refundCheck.isSelected()) {
    		refundValue.setDisable(false);
    	} else {
    		refundValue.setDisable(true);
    	}
    }

    @FXML
    void viewInquiry1(MouseEvent event) {
    	actionAnchor.setVisible(true);
    	complaint = table1.getItems().get(table1.getSelectionModel().getSelectedIndex());
    	customerMessage.setText(complaint.getDescription());
    	inqNumLabel.setText("Inquiry - " + complaint.getId());
    	if (complaint.getBranch()!=null) {
    		feedOfBranch.setText(complaint.getBranch().getAddress());
    	} else {
    		feedOfBranch.setText("");
    	}
    	response.setEditable(true);
    	refundCheck.setDisable(false);
    	saveResponseBTN.setDisable(false);
    }
    
    @FXML
    void viewInquiry2(MouseEvent event) {
    	actionAnchor.setVisible(true);
    	complaint = table2.getItems().get(table2.getSelectionModel().getSelectedIndex());
    	customerMessage.setText(complaint.getDescription());
    	inqNumLabel.setText("Inquiry - " + complaint.getId());
    	if (complaint.getBranch()!=null) {
    		feedOfBranch.setText(complaint.getBranch().getAddress());
    	} else {
    		feedOfBranch.setText("");
    	}
    	response.setEditable(true);
    	refundCheck.setDisable(false);
    	saveResponseBTN.setDisable(false);
    }
    
    @FXML
    void viewInquiry3(MouseEvent event) {
    	actionAnchor.setVisible(true);
    	complaint = table3.getItems().get(table3.getSelectionModel().getSelectedIndex());
    	customerMessage.setText(complaint.getDescription());
    	inqNumLabel.setText("Inquiry - " + complaint.getId());
    	if (complaint.getBranch()!=null) {
    		feedOfBranch.setText(complaint.getBranch().getAddress());
    	} else {
    		feedOfBranch.setText("");
    	}
    	response.setText("<" + complaint.getResponseDate() + ">\n" + complaint.getResponse());
    	response.setEditable(false);
    	refundCheck.setDisable(true);
    	saveResponseBTN.setDisable(true);
    	refundValue.setDisable(true);
    }
    
    
    @FXML
    void initialize() {
    	//summuray.setText("Total inquiries: " + (closed.size() + open.size()));
    	System.out.println(allComplaints.size());
    	System.out.println(open.size());
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy ',' HH:mm");
     	identifierCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	subDateCol.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
    	descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
     	identifierCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
    	subDateCol1.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
    	descriptionCol1.setCellValueFactory(new PropertyValueFactory<>("description"));
     	identifierCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
    	subDateCol2.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
    	descriptionCol2.setCellValueFactory(new PropertyValueFactory<>("description"));
    	branchCombo.getItems().add("All Branches");
    	for (SirtyaBranch brnch : DisplayListController.getAllBranches()) {
    		branchCombo.getItems().add(brnch.getAddress());
    	}
    	/*for (Complaint temp : allComplaints) {
    		if (temp.getStatus().equals("Closed")) {
    			closed.add(temp);
    		} else {
    			open.add(temp);
    			if (LocalDateTime.parse(temp.getSubmissionDate() , formatter).plusHours(16).isBefore(LocalDateTime.now())) {
    				highPriority.add(temp);
    			}
    		}
    	}
    	table1.setDisable(true);
    	table2.setDisable(true);
    	table3.setDisable(true);
    	table1.setItems(highPriority);
    	table2.setItems(open);
    	table3.setItems(closed);
    	if (highPriority.size()>0)
    		table1.setDisable(false);
    	if (open.size()>0)
    		table2.setDisable(false);
    	if (closed.size()>0)
    		table3.setDisable(false);
    	priorityTab.setText("High priority (" + highPriority.size()+ ")");
    	openTab.setText("Open Tickets (" + open.size()+ ")");
    	ClosedTab.setText("Closed Tickets (" + closed.size()+ ")");
    	summuray.setText("Total Tickets: " + (closed.size() + open.size()) +"\nTickets waiting for response: " + open.size() + "\nTickets with tight response time: " + highPriority.size());
    	 */
    	branchCombo.setValue(branchComboBackup);
    	filter();
    	tabPane.getSelectionModel().select(tabBackup);
    }
}
