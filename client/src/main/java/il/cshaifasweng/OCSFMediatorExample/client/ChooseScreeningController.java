/**
 * Sample Skeleton for 'chooseScreening.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ChooseScreeningController {

	private static CinemaMovie movie;
	
	private static List<SirtyaBranch> allBranches;
	
	public static void setMovie(CinemaMovie movay) {
		movie = movay;
	}
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="screeningsTable"
    private TableView<Screening> screeningsTable; // Value injected by FXMLLoader

    @FXML // fx:id="poster"
    private ImageView poster; // Value injected by FXMLLoader

    @FXML // fx:id="select"
    private Button select; // Value injected by FXMLLoader
    
	@FXML
	private TableColumn<Screening, String> dateCol;

	@FXML
	private TableColumn<Screening, String> timeCol;

	@FXML
	private TableColumn<Screening, String> branchCol;
	
    @FXML
    private TableColumn<Screening, String> hallCol;
    

    @FXML // fx:id="seatsNumCol"
    private TableColumn<Screening, int[]> seatsNumCol; // Value injected by FXMLLoader
    
    @FXML // fx:id="title"
    private Label title; // Value injected by FXMLLoader
    
    @FXML // fx:id="returnBtn"
    private Button returnBtn; // Value injected by FXMLLoader
    
    @FXML
    void returnToCat(MouseEvent event) {
		try {
			SimpleClient.getClient().sendToServer("#CatalogRequest");
	    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void selectScreening(MouseEvent event) {
    	try {
    		App.setRoot("SeatChoosing");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void setCelltoField(MouseEvent event) {
		Screening temp = screeningsTable.getItems().get(screeningsTable.getSelectionModel().getSelectedIndex());
		SeatChoosingController.setScreening(temp);
	}
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	title.setText(movie.getMovieTitle() + " - " + movie.getMovieTitleHeb());
		Image image = new Image(movie.getImage().getImgURL());
		poster.setImage(image);
		poster.setPreserveRatio(false);
    	ObservableList<Screening> screeningList = FXCollections.observableArrayList(movie.getScreenings());
		timeCol.setCellValueFactory(new PropertyValueFactory<>("screeningTime"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("screeningDate"));
		branchCol.setCellValueFactory(new PropertyValueFactory<>("screeningBranch"));
		hallCol.setCellValueFactory(new PropertyValueFactory<>("screeningHall"));
		seatsNumCol.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));
		screeningsTable.setItems(screeningList);
    }
}
