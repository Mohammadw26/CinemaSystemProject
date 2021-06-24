package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMember;
import il.cshaifasweng.OCSFMediatorExample.entities.LogInRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.OnDemandMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.RentRequest;
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

public class RentMovieController {
	private static int status = 0;
	private static OnDemandMovie movie;
	
	
    public static int getStatus() {
		return status;
	}

	public static void setStatus(int status) {
		RentMovieController.status = status;
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
    
    
    public static OnDemandMovie getMovie() {
		return movie;
	}

	public static void setMovie(OnDemandMovie movie) {
		RentMovieController.movie = movie;
	}

	@FXML
    void LogOut(ActionEvent event) {
    	DisplayListController.setMember(null);
    	DisplayListController.setWorker(null);
    	try {
    		App.setRoot("rentMovie");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@FXML
    void CancelOrder(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("#CatalogRequest");
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
    		RentRequest request = new RentRequest(nameField.getText(), lastNameField.getText()
    				, emailField.getText(), Integer.parseInt(idField.getText()), Long.parseLong(cardField.getText()),movie);
    		if (signUpCheck.isSelected()) {
    			request.setUsername(newUserField.getText());
    			request.setPassword(newPasswordField.getText());
    			request.setSignupFlag(true);
    			request.setNewCustomerFlag(true);
    		} else if (DisplayListController.getMember()!=null)  {
    			request.setUsername(DisplayListController.getMember().getUsername());
    			request.setPassword(DisplayListController.getMember().getPassword());
    			request.setSignupFlag(false);
    			request.setNewCustomerFlag(false);
    		} else if (DisplayListController.getMember()==null){
    			request.setUsername("");
    			request.setPassword("");
    			request.setSignupFlag(false);
    			request.setNewCustomerFlag(true);
    		}
    		try {
				SimpleClient.getClient().sendToServer(new Message("#RentingRequest", request));
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
    		}
    	} else if (oldUserField.getText()!= "" && oldPasswordField.getText()!= "") {
        	LogInRequest newRequest = new LogInRequest(oldUserField.getText(),oldPasswordField.getText());
        	try {
    			SimpleClient.getClient().sendToServer(new Message("#LoginRequestWhileRenting", newRequest));
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
    	} else {
    		signUpAnchor.setVisible(false);
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	loginAnchorLabel1.setText("Already a member of the Sirtya?");
    	loginAnchorLabel2.setText("Sign in and we'll fill the rest of your info on your behalf.");
        Image image = new Image(movie.getImage().getImgURL());
		poster.setImage(image);
		poster.setPreserveRatio(false);
		String temp = "Rent Summary:\nMovie: " + movie.getMovieTitle() + " - " + movie.getMovieTitleHeb()
		+ "\nCost for 24 hours Rent: " + movie.getCost() + " NIS";
		movieInfo.setText(temp);
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
		}
    }
}