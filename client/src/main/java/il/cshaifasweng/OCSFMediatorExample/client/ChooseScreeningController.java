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
import il.cshaifasweng.OCSFMediatorExample.entities.TavSagoal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ChooseScreeningController {
	private static TavSagoal restrictions;
	private Screening screening;
	private static CinemaMovie movie;
	private static List<SirtyaBranch> allBranches;
	
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
    
    @FXML // fx:id="coronaLabel"
    private Label coronaLabel; // Value injected by FXMLLoader
    
    @FXML // fx:id="coronaCombo"
    private ComboBox<String> coronaCombo; // Value injected by FXMLLoader
    
	public static TavSagoal getRestrictions() {
		return restrictions;
	}

	public static void setRestrictions(TavSagoal newRests) {
		restrictions = newRests;
	}
	
	public static void setMovie(CinemaMovie movay) {
		movie = movay;
	}
    
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
    	if(!restrictions.isEffective()) {
    		try {
    			App.setRoot("SeatChoosing");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	} else if (coronaCombo.getValue() == null){
    		for (int i = 1; i <= screening.getAvailableSeats(); i++) {
    			coronaCombo.getItems().addAll(Integer.toString(i));
    		}
    		coronaCombo.setVisible(true);
    		coronaLabel.setVisible(true);
    	}
    	else {
    		SeatChoosingController.setRequestedNum(Integer.parseInt(coronaCombo.getValue()));
    		try {
    			App.setRoot("SeatChoosing");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }

    @FXML
    void setCelltoField(MouseEvent event) {
		Screening temp = screeningsTable.getItems().get(screeningsTable.getSelectionModel().getSelectedIndex());
		SeatChoosingController.setScreening(temp);
		screening = temp;
	}
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert screeningsTable != null : "fx:id=\"screeningsTable\" was not injected: check your FXML file 'chooseScreening.fxml'.";
        assert dateCol != null : "fx:id=\"dateCol\" was not injected: check your FXML file 'chooseScreening.fxml'.";
        assert timeCol != null : "fx:id=\"timeCol\" was not injected: check your FXML file 'chooseScreening.fxml'.";
        assert branchCol != null : "fx:id=\"branchCol\" was not injected: check your FXML file 'chooseScreening.fxml'.";
        assert hallCol != null : "fx:id=\"hallCol\" was not injected: check your FXML file 'chooseScreening.fxml'.";
        assert seatsNumCol != null : "fx:id=\"seatsNumCol\" was not injected: check your FXML file 'chooseScreening.fxml'.";
        assert poster != null : "fx:id=\"poster\" was not injected: check your FXML file 'chooseScreening.fxml'.";
        assert select != null : "fx:id=\"select\" was not injected: check your FXML file 'chooseScreening.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'chooseScreening.fxml'.";
        assert returnBtn != null : "fx:id=\"returnBtn\" was not injected: check your FXML file 'chooseScreening.fxml'.";
        assert coronaCombo != null : "fx:id=\"coronaCombo\" was not injected: check your FXML file 'chooseScreening.fxml'.";
        assert coronaLabel != null : "fx:id=\"coronaLabel\" was not injected: check your FXML file 'chooseScreening.fxml'.";
    	if (restrictions.isEffective()) {
    		for (Screening screening: movie.getScreenings()) {
    			int temp;
    			if (screening.getHall().getSeatsNum() > 1.2*restrictions.getY()) {
    				temp = restrictions.getY();
    			} else if (screening.getHall().getSeatsNum() > 0.8*restrictions.getY()) {
    				temp = (int) (0.8 * ((double) restrictions.getY()));
    			} else {
    				temp = screening.getHall().getSeatsNum()/2;
    			}
    			screening.setAvailableSeats(temp - screening.getSoldSeats());
    		}
    	} else {
    		for (Screening screening: movie.getScreenings()) {
    			screening.setAvailableSeats(screening.getHall().getSeatsNum() - screening.getSoldSeats());
    		}
    	}
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
