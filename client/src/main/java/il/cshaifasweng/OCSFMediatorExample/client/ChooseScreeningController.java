/**
 * Sample Skeleton for 'chooseScreening.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import il.cshaifasweng.OCSFMediatorExample.entities.TavSagoal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
	private static List<Screening> filtered_list = new ArrayList<Screening>();
	
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
    
    @FXML // fx:id="date1"
    private DatePicker date1; // Value injected by FXMLLoader

    @FXML // fx:id="date2"
    private DatePicker date2; // Value injected by FXMLLoader
    
    @FXML // fx:id="h1"
    private ComboBox<String> h1; // Value injected by FXMLLoader

    @FXML // fx:id="m1"
    private ComboBox<String> m1; // Value injected by FXMLLoader

    @FXML // fx:id="h2"
    private ComboBox<String> h2; // Value injected by FXMLLoader

    @FXML // fx:id="m2"
    private ComboBox<String> m2; // Value injected by FXMLLoader
    
    @FXML // fx:id="searchBtn"
    private Button searchBtn; // Value injected by FXMLLoader

    @FXML // fx:id="warningLabel"
    private Label warningLabel; // Value injected by FXMLLoader
    
    @FXML // fx:id="branchCombo"
    private ComboBox<String> branchCombo; // Value injected by FXMLLoader
    
    static void setImportedValues(String branchComboValue, LocalDate d1, LocalDate d2, String hh1, String hh2, String mm1, String mm2) {
    	branchCombo_init = branchComboValue;
    	date1_init = d1;
    	date2_init = d2;
    	h1_init = hh1;
    	h2_init = hh2;
    	m1_init = mm1;
    	m2_init = mm2;
    }
    
    @FXML
    private Button resetButton;
    private static LocalDate date1_init;
    private static LocalDate date2_init;
    private static String h1_init;
    private static String h2_init;
    private static String m1_init;
    private static String m2_init;
    private static String branchCombo_init;
    
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
    void resetFilters(ActionEvent event) {
		h1.setValue(null);
		h2.setValue(null);
		m1.setValue(null);
		m2.setValue(null);
		date1.setValue(null);
		date2.setValue(null);
		branchCombo.setValue(null);
		branchCombo.getEditor().setText("All");
		search();
		branchCombo_init = null;
    	date1_init = null;
    	date2_init = null;
    	h1_init = null;
    	h2_init = null;
    	m1_init = null;
    	m2_init = null;
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
    	if (screening == null) {
    		warningLabel.setText("You must choose a screening.");
    		warningLabel.setVisible(true);
    		return;
    	}
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
    			SeatChoosingController.setAutomaticFlag(true);
    			App.setRoot("SeatChoosing");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }

    @FXML
    void setCelltoField(MouseEvent event) {
    	if (warningLabel.isVisible() && warningLabel.getText().equals("You must choose a screening.")) {
    		warningLabel.setVisible(false);
    	}
		Screening temp = screeningsTable.getItems().get(screeningsTable.getSelectionModel().getSelectedIndex());
		SeatChoosingController.setScreening(temp);
		screening = temp;
	}
    
    @FXML
    void search() {
    	if (((h1.getValue()!=null && h1.getValue()!="") || (h2.getValue()!=null && h2.getValue()!="") || (m1.getValue()!=null && m1.getValue()!="") || (m2.getValue()!=null && m2.getValue()!=""))
    			&& !(h1.getValue()!=null && h2.getValue()!=null && m1.getValue()!=null && m2.getValue()!= null)) {
    		warningLabel.setText("If you would like to narrow the search for specific screening hours, please fill all the time fields. otherwise, free the time fields.");
    		warningLabel.setVisible(true);
    		return;
    	}
    	if ((date1.getValue()!=null && date2.getValue()==null) || (date1.getValue()==null && date2.getValue()!=null)) {
    		warningLabel.setText("If you would like to narrow the search for specific dates, please choose two dates. otherwise, free the date fields.");
    		warningLabel.setVisible(true);
    		return;
    	}
    	if (date1.getEditor().getText()=="" || date2.getEditor().getText()=="") {
    		date1.setValue(null);
    		date2.setValue(null);
    	}
    	if (h2.getValue()=="" || h2.getValue()=="" || m1.getValue()=="" || m2.getValue()=="") {
    		h1.setValue(null);
    		h2.setValue(null);
    		m1.setValue(null);
    		m2.setValue(null);
    	}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    	filtered_list.clear();
    	filtered_list.addAll(movie.getScreenings());
		if (date1.getValue()!=null && date2.getValue()!=null) {
			for (int i = 0 ; i < filtered_list.size(); i++) {
				int counter = 0;
				Screening screening = filtered_list.get(i);
				LocalDate tempDate = LocalDate.parse(screening.getScreeningDate(),formatter);
				if ( (screening.getBranch().getAddress().equals(branchCombo.getValue()) || branchCombo.getValue() == "All" || branchCombo.getValue() == "" ||branchCombo.getValue() == null )
					&& ( ( tempDate.isAfter( date1.getValue().minusDays(1) ) ) && ( tempDate.isBefore( date2.getValue().plusDays(1) ) ) ) ){
					if (h1.getValue()!=null && h2.getValue()!=null && m1.getValue()!=null && m2.getValue()!= null) {
						LocalDateTime tempTime = LocalDateTime.parse("01/01/2021 " + screening.getScreeningTime(), formatter2);
						LocalDateTime startTime = LocalDateTime.parse("01/01/2021 " + h1.getValue() + ":" + m1.getValue(), formatter2);
						LocalDateTime endTime = LocalDateTime.parse("01/01/2021 " + h2.getValue() + ":" + m2.getValue(), formatter2);
						if (tempTime.isAfter(startTime.minusMinutes(1)) && tempTime.isBefore(endTime.plusMinutes(1))) {
							counter++;
						}
					} else {
						counter ++;
					}
				}
				if (counter<=0) {
					filtered_list.remove(screening);
					i--;
				}
			}
		} else {
			if (branchCombo.getValue() != "All" && branchCombo.getValue() != "" && branchCombo.getValue() != null) {
				for (int i = 0 ; i < filtered_list.size(); i++) {
					Screening screening = filtered_list.get(i);
					if (!screening.getBranch().getAddress().equals(branchCombo.getValue())) {
						filtered_list.remove(screening);
						i--;
					}
				}
			}
			if (h1.getValue()!=null && h2.getValue()!=null && m1.getValue()!=null && m2.getValue()!= null) {
				for (int i = 0 ; i < filtered_list.size(); i++) {
					Screening screening = filtered_list.get(i);
					int counter = 0;
					LocalDateTime tempTime = LocalDateTime.parse("01/01/2021 " + screening.getScreeningTime(), formatter2);
					LocalDateTime startTime = LocalDateTime.parse("01/01/2021 " + h1.getValue()+ ":" + m1.getValue(), formatter2);
					LocalDateTime endTime = LocalDateTime.parse("01/01/2021 " + h2.getValue() + ":" + m2.getValue(), formatter2);
					if (tempTime.isAfter(startTime.minusMinutes(1)) && tempTime.isBefore(endTime.plusMinutes(1))) {
						counter++;
					}
					if (counter<=0) {
						filtered_list.remove(screening);
						i--;
					}
				}
			}
		}
		ObservableList<Screening> screeningList = FXCollections.observableArrayList(filtered_list);
		screeningsTable.setItems(screeningList);
		warningLabel.setVisible(false);
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
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
        branchCombo.getItems().addAll("All");
		branchCombo.getItems().addAll("Elm's street 25, Varrock","Riverdale 29, Falador","Wa7awee7 117, Lumbrige");
		h1.getItems().addAll("");
		h2.getItems().addAll("");
		for (int i = 0; i < 10; i++) {
			h1.getItems().add("0" + Integer.toString(i));
			h2.getItems().add("0" + Integer.toString(i));
		}
		for (int i = 10; i < 24; i++) {
			h1.getItems().addAll(Integer.toString(i));
			h2.getItems().addAll(Integer.toString(i));
		}
		m1.getItems().addAll("","00","05");
		m2.getItems().addAll("","00","05");
		for (int i = 10; i < 60; i = i + 5) {
			m1.getItems().addAll(Integer.toString(i));
			m2.getItems().addAll(Integer.toString(i));
		}
		if (branchCombo_init!="") {
			branchCombo.getEditor().setText(branchCombo_init);
			branchCombo.setValue(branchCombo_init);
		}
		if (date1_init!=null) {
		date1.setValue(date1_init);
		date1.getEditor().setText(date1.getValue().toString());
		date2.setValue(date2_init);
		date2.getEditor().setText(date2.getValue().toString());
		}
		if (h1_init!="") {
		h1.setValue(h1_init);
		h1.getEditor().setText(h1.getValue());
		h2.setValue(h2_init);
		h2.getEditor().setText(h2.getValue());
		m1.setValue(m1_init);
		m1.getEditor().setText(m1.getValue());
		m2.setValue(m2_init);
		m2.getEditor().setText(m2.getValue());
		}
		search();
    }
}
