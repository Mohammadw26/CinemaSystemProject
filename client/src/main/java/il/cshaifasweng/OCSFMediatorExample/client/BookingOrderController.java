/**
 * Sample Skeleton for 'bookingOrder.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.BookingRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMember;
import il.cshaifasweng.OCSFMediatorExample.entities.FullOrderRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.LogInRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class BookingOrderController {
	private static int status = 0;
	private static BookingRequest request;
	private static Screening screening;
	
	
    public static int getStatus() {
		return status;
	}

	public static void setStatus(int status) {
		BookingOrderController.status = status;
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="movieInfo"
    private Label movieInfo; // Value injected by FXMLLoader

    @FXML // fx:id="poster"
    private ImageView poster; // Value injected by FXMLLoader

    @FXML // fx:id="nameField"
    private TextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="lastNameField"
    private TextField lastNameField; // Value injected by FXMLLoader

    @FXML // fx:id="idField"
    private TextField idField; // Value injected by FXMLLoader

    @FXML // fx:id="emailField"
    private TextField emailField; // Value injected by FXMLLoader

    @FXML // fx:id="cardField"
    private TextField cardField; // Value injected by FXMLLoader

    @FXML // fx:id="signUpCheck"
    private CheckBox signUpCheck; // Value injected by FXMLLoader

    @FXML // fx:id="signUpAnchor"
    private AnchorPane signUpAnchor; // Value injected by FXMLLoader

    @FXML // fx:id="newUserField"
    private TextField newUserField; // Value injected by FXMLLoader

    @FXML // fx:id="newPasswordField"
    private TextField newPasswordField; // Value injected by FXMLLoader

    @FXML // fx:id="useEmailCheck"
    private CheckBox useEmailCheck; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="oldUserField"
    private TextField oldUserField; // Value injected by FXMLLoader

    @FXML // fx:id="oldPasswordField"
    private TextField oldPasswordField; // Value injected by FXMLLoader

    @FXML // fx:id="logInButton"
    private Button logInButton; // Value injected by FXMLLoader

    @FXML // fx:id="confirmButton"
    private Button confirmButton; // Value injected by FXMLLoader

    @FXML // fx:id="warning"
    private Label warning; // Value injected by FXMLLoader
    
    @FXML // fx:id="memberPerksAnchor"
    private AnchorPane memberPerksAnchor; // Value injected by FXMLLoader
    
    @FXML // fx:id="loginAnchor"
    private AnchorPane loginAnchor; // Value injected by FXMLLoader
    
    @FXML // fx:id="loginAnchorLabel1"
    private Text loginAnchorLabel1; // Value injected by FXMLLoader
    
    @FXML // fx:id="loginAnchorLabel2"
    private Text loginAnchorLabel2; // Value injected by FXMLLoader

    @FXML // fx:id="logOutButton"
    private Button logOutButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="packageCheck"
    private CheckBox packageCheck; // Value injected by FXMLLoader
    
    @FXML // fx:id="costInfo"
    private Label costInfo; // Value injected by FXMLLoader
    
    @FXML
    void buypackage(ActionEvent event) {
    	String temp = "";
    	if (packageCheck.isSelected() && DisplayListController.getMember()==null) {
    		temp = "\nCost:\n+ " + screening.getMovie().getTicketCost() + " X " + request.getArrSize();
    		temp += "\n+ 600 (Tab - 20 tickets)\n- ";
    		if (request.getArrSize()<=20) {
    			temp += screening.getMovie().getTicketCost() + " X " + request.getArrSize() + "  (" + request.getArrSize() + "/20 tickets will be used)" + "\n-------------------\nTotal: +600 NIS";
    			temp += "\nPrevius tickets balance: 0\nNew tickets balance: " + (20 - request.getArrSize());
    			costInfo.setText(temp);
    		} else {
    			temp += screening.getMovie().getTicketCost() + " X " + 20 + "  (" + request.getArrSize() + "/20 tickets will be used)" + "\n-------------------\nTotal: +" + (screening.getMovie().getTicketCost()*request.getArrSize() - screening.getMovie().getTicketCost() + 600 +" NIS\n");
    			temp += "\nPrevius tickets balance: 0\nNew tickets balance: 0";
    			costInfo.setText(temp);
    		}
    	} else if (packageCheck.isSelected() && DisplayListController.getMember()!=null){
    		temp = "\nCost:\n+ " + screening.getMovie().getTicketCost() + " X " + request.getArrSize();
    		temp += "\n+ 600 (Tab - 20 tickets)\n- ";
    		if (request.getArrSize()<=DisplayListController.getMember().getTicketsCredit()+20) {
    			temp += screening.getMovie().getTicketCost() + " X " + request.getArrSize() + "  (" + request.getArrSize() + "/"+ (DisplayListController.getMember().getTicketsCredit() + 20) + " tickets will be used)" + "\n-------------------\nTotal: +600 NIS";
    			temp += "\nPrevius tickets balance: " + DisplayListController.getMember().getTicketsCredit() + "\nNew tickets balance: "  + (DisplayListController.getMember().getTicketsCredit() + 20 - request.getArrSize());
    			costInfo.setText(temp);
    		} else {
    			temp += screening.getMovie().getTicketCost() + " X " + DisplayListController.getMember().getTicketsCredit() + "  (" + request.getArrSize() + "/"+ DisplayListController.getMember().getTicketsCredit() +" tickets will be used)" + "\n-------------------\nTotal: +" + (screening.getMovie().getTicketCost()*request.getArrSize() - screening.getMovie().getTicketCost()*DisplayListController.getMember().getTicketsCredit() + 600 +" NIS"); 
    			temp += "\nPrevius tickets balance: " + DisplayListController.getMember().getTicketsCredit() + "\nNew tickets balance: 0";
    			costInfo.setText(temp);
    		}
    	} else if (!packageCheck.isSelected() && DisplayListController.getMember()!=null) {
       		temp = "\nCost:\n+ " + screening.getMovie().getTicketCost() + " X " + request.getArrSize();
    		if (request.getArrSize()<=DisplayListController.getMember().getTicketsCredit()) {
    			temp += "\n- " + screening.getMovie().getTicketCost() + " X " + request.getArrSize() + "  (" + request.getArrSize() + "/"+ (DisplayListController.getMember().getTicketsCredit()) + " tickets will be used)" + "\n-------------------\nTotal: +0.0 NIS";
    			temp += "\nPrevius tickets balance: " + DisplayListController.getMember().getTicketsCredit()  + "\nNew tickets balance: " + (DisplayListController.getMember().getTicketsCredit() - request.getArrSize());
    			costInfo.setText(temp);
    		} else {
    			temp += "\n- " + screening.getMovie().getTicketCost() + " X " + DisplayListController.getMember().getTicketsCredit() + "  (" + DisplayListController.getMember().getTicketsCredit() + "/"+ DisplayListController.getMember().getTicketsCredit() +" tickets will be used)" + "\n-------------------\nTotal: +" + (screening.getMovie().getTicketCost()*request.getArrSize() - screening.getMovie().getTicketCost()*DisplayListController.getMember().getTicketsCredit() +" NIS"); 
    			temp += "\nPrevius tickets balance: " + DisplayListController.getMember().getTicketsCredit() + "\nNew tickets balance: 0";
    			costInfo.setText(temp);
    		}
    	}else if (!packageCheck.isSelected() && DisplayListController.getMember()==null) {
    		temp = "\nCost:\n+ " + screening.getMovie().getTicketCost() + " X " + request.getArrSize();
    		temp += "\n-------------------\nTotal: +" + (screening.getMovie().getTicketCost()*request.getArrSize() + " NIS"); 
    			costInfo.setText(temp);
    	}
    }
    
    public static BookingRequest getRequest() {
		return request;
	}
    
    @FXML
    void LogOut(ActionEvent event) {
    	DisplayListController.setMember(null);
    	DisplayListController.setWorker(null);
    	try {
    		App.setRoot("bookingOrder");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public static void setRequest(BookingRequest request) {
		BookingOrderController.request = request;
	}

	@FXML
    void CancelOrder(ActionEvent event) {
    	try {
		SimpleClient.getClient().sendToServer(new Message("#UndoSaveSeats", request));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ConfirmOrder(ActionEvent event) {
    	if (nameField.getText() == "" || lastNameField.getText() == "" 
    		|| idField.getText() == "" || emailField.getText() == "" || cardField.getText() == "" 
    		|| (signUpCheck.isSelected() & (newUserField.getText() == "" || newPasswordField.getText() == ""))) {
    		warning.setVisible(true);
    	}
    	else {
    		FullOrderRequest request2 = new FullOrderRequest(nameField.getText(), lastNameField.getText()
    				, emailField.getText(), Integer.parseInt(idField.getText()), Long.parseLong(cardField.getText()));
    		request2.setRequest(request);
    		request2.setCheck(costInfo.getText());
    		if (signUpCheck.isSelected()) {
    			request2.setUsername(newUserField.getText());
    			request2.setPassword(newPasswordField.getText());
    			request2.setSignupFlag(true);
    			request2.setNewCustomerFlag(true);
    		} else if (DisplayListController.getMember()!=null)  {
    			request2.setUsername(DisplayListController.getMember().getUsername());
    			request2.setPassword(DisplayListController.getMember().getPassword());
    			request2.setSignupFlag(false);
    			request2.setNewCustomerFlag(false);
    		} else if (DisplayListController.getMember()==null){
    			request2.setUsername("");
    			request2.setPassword("");
    			request2.setSignupFlag(false);
    			request2.setNewCustomerFlag(true);
    		}
    		try {
    			if (packageCheck.isSelected()) {
    				request2.setBuyPack(true);
    				request2.setUsePack(request2.getRequest().getArrSize());
    			} else if (DisplayListController.getMember()!=null) {
    				request2.setUsePack(DisplayListController.getMember().getTicketsCredit());
    			}
				SimpleClient.getClient().sendToServer(new Message("#FinishOrder", request2));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

    @FXML
    void copyEmail(ActionEvent event) {
    	newUserField.setText(emailField.getText());
    }

    @FXML
    void logIn(ActionEvent event) {
    	if (logInButton.getText() == "Confirm" && oldPasswordField.getText()!= "") {
    		if (DisplayListController.getMember().getPassword().equals(oldPasswordField.getText())) {
    			packageCheck.setDisable(false);
    			nameField.setDisable(false);
    			lastNameField.setDisable(false);
    			idField.setDisable(false);
    			emailField.setDisable(false);
    			cardField.setDisable(false);
    			CinemaMember member = DisplayListController.getMember();
    			nameField.setText(member.getFirstName());
    			lastNameField.setText(member.getLastName());
    			idField.setText(String.valueOf(member.getCustomerId()));
    			emailField.setText(member.getElectronicMail());
    			cardField.setText(String.valueOf(member.getCreditNum()));
    			String temp = "\nCost:\n" + screening.getMovie().getTicketCost() + " X " + request.getArrSize() + "\n- ";
    			if (DisplayListController.getMember().getTicketsCredit()>request.getArrSize()) {
    				temp += screening.getMovie().getTicketCost() + " X " + request.getArrSize() + " (" + request.getArrSize()+ "/" +DisplayListController.getMember().getTicketsCredit() + " tickets will be used)";
    				temp += "\n-------------------\nTotal: +0.0 NIS";
    				costInfo.setText(temp);
    			}
    			else if (DisplayListController.getMember().getCreditNum()>0) {
    				int temp2 = request.getArrSize() - DisplayListController.getMember().getTicketsCredit();
    				temp += screening.getMovie().getTicketCost() + " X " + DisplayListController.getMember().getTicketsCredit() + " (" + DisplayListController.getMember().getTicketsCredit() + "/" +DisplayListController.getMember().getTicketsCredit() + " tickets will be used)";
    				temp += "\n-------------------\nTotal: +" + temp2*screening.getMovie().getTicketCost() + " NIS";
    				costInfo.setText(temp);
    			}
    		}
    	} else if (oldUserField.getText()!= "" && oldPasswordField.getText()!= "") {
        	LogInRequest newRequest = new LogInRequest(oldUserField.getText(),oldPasswordField.getText());
        	try {
    			SimpleClient.getClient().sendToServer(new Message("#LoginRequestWhileBooking", newRequest));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }

    @FXML
    void viewSignUp(ActionEvent event) {
    	if (signUpCheck.isSelected()) {
    		signUpAnchor.setVisible(true);
    		packageCheck.setDisable(false);
    	} else {
    		signUpAnchor.setVisible(false);
    		packageCheck.setDisable(true);
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	loginAnchorLabel1.setText("Already a member of the Sirtya?");
    	loginAnchorLabel2.setText("Sign in and we'll fill the rest of your info on your behalf.");
        screening = request.getScreening();
        Image image = new Image(screening.getMovie().getImage().getImgURL());
		poster.setImage(image);
		poster.setPreserveRatio(false);
		String temp = "Order Summary:\nMovie: " + screening.getMovie().getMovieTitle() + " - " + screening.getMovie().getMovieTitleHeb()
				+ "\nDate: " + screening.getScreeningDate() 
		+ "\nTime: " + screening.getScreeningTime()
		+ "\nNumber of tickets: " + request.getArrSize() + "\nSeats IDs: ";
		for (int i = 0; i < request.getArrSize(); i++) {
			temp += request.getSeatIds()[i] + " ";
		}
		movieInfo.setText(temp);
		temp = "\nCost:\n" + screening.getMovie().getTicketCost() + " X " + request.getArrSize() + "\n-------------------\nTotal: +" + screening.getMovie().getTicketCost()*request.getArrSize() + " NIS";
		costInfo.setText(temp);
		if (DisplayListController.getMember()!=null && status == 0) {
			CinemaMember member = DisplayListController.getMember();
	    	loginAnchorLabel1.setText("You are already logged in as: " + member.getFirstName() + " " + member.getLastName());
	    	loginAnchorLabel2.setText("please enter you password to confirm it's you");
			nameField.setDisable(true);
			lastNameField.setDisable(true);
			idField.setDisable(true);
			emailField.setDisable(true);
			cardField.setDisable(true);
			signUpCheck.setVisible(false);
			memberPerksAnchor.setVisible(false);
			oldUserField.setText(member.getUsername());
			oldUserField.setDisable(true);
			logInButton.setText("Confirm");
		} else if (DisplayListController.getMember()!=null && status == 1) {
			packageCheck.setDisable(false);
			CinemaMember member = DisplayListController.getMember();
			nameField.setText(member.getFirstName());
			lastNameField.setText(member.getLastName());
			idField.setText(String.valueOf(member.getCustomerId()));
			emailField.setText(member.getElectronicMail());
			cardField.setText(String.valueOf(member.getCreditNum()));
			signUpCheck.setVisible(false);
			memberPerksAnchor.setVisible(false);
			loginAnchor.setVisible(false);
			status = 0;
			temp = "\nCost:\n" + screening.getMovie().getTicketCost() + " X " + request.getArrSize() + "\n- ";
			if (DisplayListController.getMember().getTicketsCredit()>=request.getArrSize()) {
				temp += screening.getMovie().getTicketCost() + " X " + request.getArrSize() + " (" + request.getArrSize()+ "/" +DisplayListController.getMember().getTicketsCredit() + " tickets will be used)";
				temp += "\n-------------------\nTotal: +0.0 NIS";
				temp += "\nPrevius tickets balance: " + DisplayListController.getMember().getTicketsCredit() + " \nNew tickets balance: " + (DisplayListController.getMember().getTicketsCredit() - request.getArrSize());
				costInfo.setText(temp);
			}
			else if (DisplayListController.getMember().getCreditNum()>=0) {
				int temp2 = request.getArrSize() - DisplayListController.getMember().getTicketsCredit();
				temp += screening.getMovie().getTicketCost() + " X " + DisplayListController.getMember().getTicketsCredit() + " (" + DisplayListController.getMember().getTicketsCredit() + "/" +DisplayListController.getMember().getTicketsCredit() + " tickets will be used)";
				temp += "\n-------------------\nTotal: +" + temp2*screening.getMovie().getTicketCost() + " NIS";
				temp += "\nPrevius tickets balance: " + DisplayListController.getMember().getTicketsCredit() + "\nNew tickets balance: 0";
				costInfo.setText(temp);
			}
			//+ "\n-------------------\n" + screening.getMovie().getTicketCost()*request.getArrSize() + " NIS";
		}
    }
}
