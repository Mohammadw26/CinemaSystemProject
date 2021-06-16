/**
 * Sample Skeleton for 'SeatChoosing.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import il.cshaifasweng.OCSFMediatorExample.entities.BookingRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class SeatChoosingController {
	
	private Hall hall;
	private static Screening screening;
	private int counter = 0;
	private String chosenSeats;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="gridSeats"
    private GridPane gridSeats; // Value injected by FXMLLoader

    @FXML // fx:id="s1"
    private MaterialIconView s1; // Value injected by FXMLLoader

    @FXML // fx:id="s2"
    private MaterialIconView s2; // Value injected by FXMLLoader

    @FXML // fx:id="s3"
    private MaterialIconView s3; // Value injected by FXMLLoader

    @FXML // fx:id="s4"
    private MaterialIconView s4; // Value injected by FXMLLoader

    @FXML // fx:id="s5"
    private MaterialIconView s5; // Value injected by FXMLLoader

    @FXML // fx:id="s6"
    private MaterialIconView s6; // Value injected by FXMLLoader

    @FXML // fx:id="s7"
    private MaterialIconView s7; // Value injected by FXMLLoader

    @FXML // fx:id="s8"
    private MaterialIconView s8; // Value injected by FXMLLoader

    @FXML // fx:id="s9"
    private MaterialIconView s9; // Value injected by FXMLLoader

    @FXML // fx:id="s10"
    private MaterialIconView s10; // Value injected by FXMLLoader

    @FXML // fx:id="s11"
    private MaterialIconView s11; // Value injected by FXMLLoader

    @FXML // fx:id="s12"
    private MaterialIconView s12; // Value injected by FXMLLoader

    @FXML // fx:id="s13"
    private MaterialIconView s13; // Value injected by FXMLLoader

    @FXML // fx:id="s14"
    private MaterialIconView s14; // Value injected by FXMLLoader

    @FXML // fx:id="s15"
    private MaterialIconView s15; // Value injected by FXMLLoader

    @FXML // fx:id="s16"
    private MaterialIconView s16; // Value injected by FXMLLoader

    @FXML // fx:id="s17"
    private MaterialIconView s17; // Value injected by FXMLLoader

    @FXML // fx:id="s18"
    private MaterialIconView s18; // Value injected by FXMLLoader

    @FXML // fx:id="s19"
    private MaterialIconView s19; // Value injected by FXMLLoader

    @FXML // fx:id="s20"
    private MaterialIconView s20; // Value injected by FXMLLoader

    @FXML // fx:id="s21"
    private MaterialIconView s21; // Value injected by FXMLLoader

    @FXML // fx:id="s22"
    private MaterialIconView s22; // Value injected by FXMLLoader

    @FXML // fx:id="s23"
    private MaterialIconView s23; // Value injected by FXMLLoader

    @FXML // fx:id="s24"
    private MaterialIconView s24; // Value injected by FXMLLoader

    @FXML // fx:id="s25"
    private MaterialIconView s25; // Value injected by FXMLLoader

    @FXML // fx:id="s26"
    private MaterialIconView s26; // Value injected by FXMLLoader

    @FXML // fx:id="s27"
    private MaterialIconView s27; // Value injected by FXMLLoader

    @FXML // fx:id="s28"
    private MaterialIconView s28; // Value injected by FXMLLoader

    @FXML // fx:id="s29"
    private MaterialIconView s29; // Value injected by FXMLLoader

    @FXML // fx:id="s30"
    private MaterialIconView s30; // Value injected by FXMLLoader

    @FXML // fx:id="s31"
    private MaterialIconView s31; // Value injected by FXMLLoader

    @FXML // fx:id="s32"
    private MaterialIconView s32; // Value injected by FXMLLoader

    @FXML // fx:id="s33"
    private MaterialIconView s33; // Value injected by FXMLLoader

    @FXML // fx:id="s34"
    private MaterialIconView s34; // Value injected by FXMLLoader

    @FXML // fx:id="s35"
    private MaterialIconView s35; // Value injected by FXMLLoader

    @FXML // fx:id="s36"
    private MaterialIconView s36; // Value injected by FXMLLoader

    @FXML // fx:id="s37"
    private MaterialIconView s37; // Value injected by FXMLLoader

    @FXML // fx:id="s38"
    private MaterialIconView s38; // Value injected by FXMLLoader

    @FXML // fx:id="s39"
    private MaterialIconView s39; // Value injected by FXMLLoader

    @FXML // fx:id="s40"
    private MaterialIconView s40; // Value injected by FXMLLoader

    @FXML // fx:id="s41"
    private MaterialIconView s41; // Value injected by FXMLLoader

    @FXML // fx:id="s42"
    private MaterialIconView s42; // Value injected by FXMLLoader

    @FXML // fx:id="bookSeat"
    private Button bookSeat; // Value injected by FXMLLoader

    @FXML // fx:id="availableSeatsLabel"
    private Label availableSeatsLabel; // Value injected by FXMLLoader

    @FXML // fx:id="chosenNum"
    private Label chosenNum; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="movieInfo"
    private Label movieInfo; // Value injected by FXMLLoader

    @FXML // fx:id="warning"
    private Label warning; // Value injected by FXMLLoader
    
    @FXML // fx:id="rowsGrid"
    private GridPane rowsGrid; // Value injected by FXMLLoader

    @FXML // fx:id="rowsGrid"
    private GridPane colsGrids; // Value injected by FXMLLoader

    @FXML // fx:id="rowA"
    private Label rowA; // Value injected by FXMLLoader

    @FXML // fx:id="rowB"
    private Label rowB; // Value injected by FXMLLoader

    @FXML // fx:id="rowC"
    private Label rowC; // Value injected by FXMLLoader

    @FXML // fx:id="rowD"
    private Label rowD; // Value injected by FXMLLoader

    @FXML // fx:id="rowE"
    private Label rowE; // Value injected by FXMLLoader

    @FXML // fx:id="rowF"
    private Label rowF; // Value injected by FXMLLoader

    @FXML // fx:id="c1"
    private Label c1; // Value injected by FXMLLoader

    @FXML // fx:id="c2"
    private Label c2; // Value injected by FXMLLoader

    @FXML // fx:id="c3"
    private Label c3; // Value injected by FXMLLoader

    @FXML // fx:id="c4"
    private Label c4; // Value injected by FXMLLoader

    @FXML // fx:id="c5"
    private Label c5; // Value injected by FXMLLoader

    @FXML // fx:id="c6"
    private Label c6; // Value injected by FXMLLoader

    @FXML // fx:id="c7"
    private Label c7; // Value injected by FXMLLoader
    
    @FXML // fx:id="luba"
    private Label luba; // Value injected by FXMLLoader
    
    @FXML
    private Label confLabel;
    
    @FXML
    private ImageView poster;
    
    @FXML
    void backToPrevScene(ActionEvent event) {
    	ChooseScreeningController.setMovie(screening.getMovie());
    	try {
    		App.setRoot("chooseScreening");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void bookSeat(MouseEvent event) {
    	int counter2 = 0;
    	int[] temp = new int[counter];
    	int seatNum = 0;
    	chosenSeats = " ";
    	if ((hall.getCols()/2)*2 == (hall.getCols())) {
        	for (int k = 0 ; k < hall.getRows() ; k ++) {
        		rowsGrid.getChildren().get(k).setVisible(true);
            	int i = 3 - hall.getCols()/2, j = 3 + hall.getCols()/2;
        		for (; i <= j && seatNum < hall.getSeatsNum() ; i ++) {
        			if (i != 3) {
        				if (gridSeats.getChildren().get(i + k*7).getStyle()
                        .equals("-fx-fill:#ff0000; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;")) {
                    		chosenSeats += 'A'+ k;
                    		if(confLabel.isVisible()) {
                        		screening.setTakenSeatAt(seatNum);
                        		temp[counter2]=seatNum;
                        		counter2++;
                        	}
                    		if (i<3)
                    			chosenSeats += Integer.toString(i - (3 - (hall.getCols()-1)/2) + 1) + "	";
                    		if (i>3)
                    			chosenSeats += Integer.toString(i - (3 - (hall.getCols()-1)/2)) + "	";
                    	}
        				seatNum++;
        			}
        		}
        	}
        } else {
        	for (int k = 0 ; k < hall.getRows() ; k ++) {
        		rowsGrid.getChildren().get(k).setVisible(true);
            	int i = 3 - (hall.getCols()-1)/2, j = 3 + (hall.getCols()-1)/2;
        		for (; i <= j && seatNum < hall.getSeatsNum() ; i ++) {
        			if (gridSeats.getChildren().get(i + k*7).getStyle()
                    .equals("-fx-fill:#ff0000; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;")) {
                		chosenSeats+= Character.toString((char) 65 + k);
                		chosenSeats += Integer.toString(i + 1 - (3 - (hall.getCols()-1)/2)) + " ";
                		if(confLabel.isVisible()) {
                    		screening.setTakenSeatAt(seatNum);
                    		temp[counter2]=seatNum;
                    		counter2++;
                    	}
                	}
        			seatNum++;
        		}
        	}
        }
		if(confLabel.isVisible()) {
			BookingRequest request = new BookingRequest(temp, screening.getId(),counter);
	    	try {
			SimpleClient.getClient().sendToServer(new Message("#BookSeats", request));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    	}else {
    	confLabel.setText("You have selected the following seats:" + chosenSeats +
    			"\nTotal Cost: " + Double.toString(screening.getMovie().getTicketCost()*counter) +" NIS"
    			+"\nPlease click 'Book Seats' again to confirm and continue your purchase");
    	confLabel.setVisible(true);
    	}
}

    
    public static  void setScreening(Screening scrn) {
    	screening = scrn;
    }

    @FXML
    void selectSeat(MouseEvent event) {
    	confLabel.setVisible(false);
    	if (((Node) event.getSource()).getStyle()
                .equals("-fx-fill:#e7d1d1; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;")) {
    		warning.setVisible(true);
    	} else if (((Node) event.getSource()).getStyle()
                .equals("-fx-fill:#ff0000; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;")) {
    		((Node) event.getSource()).setStyle(
    				"-fx-fill:#792f2f; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;");
    		counter--;
            chosenNum.setText("Number of chosen seats: " + Integer.toString(counter));
    		warning.setVisible(false);
    	} else if (((Node) event.getSource()).getStyle()
                .equals("-fx-fill:#792f2f; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;")) {
    		((Node) event.getSource()).setStyle(
    				"-fx-fill:#ff0000; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;");
    		counter++;
            chosenNum.setText("Number of chosen seats: " + Integer.toString(counter));
    		warning.setVisible(false);
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert gridSeats != null : "fx:id=\"gridSeats\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s1 != null : "fx:id=\"s1\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s2 != null : "fx:id=\"s2\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s3 != null : "fx:id=\"s3\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s4 != null : "fx:id=\"s4\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s5 != null : "fx:id=\"s5\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s6 != null : "fx:id=\"s6\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s7 != null : "fx:id=\"s7\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s8 != null : "fx:id=\"s8\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s9 != null : "fx:id=\"s9\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s10 != null : "fx:id=\"s10\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s11 != null : "fx:id=\"s11\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s12 != null : "fx:id=\"s12\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s13 != null : "fx:id=\"s13\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s14 != null : "fx:id=\"s14\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s15 != null : "fx:id=\"s15\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s16 != null : "fx:id=\"s16\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s17 != null : "fx:id=\"s17\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s18 != null : "fx:id=\"s18\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s19 != null : "fx:id=\"s19\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s20 != null : "fx:id=\"s20\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s21 != null : "fx:id=\"s21\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s22 != null : "fx:id=\"s22\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s23 != null : "fx:id=\"s23\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s24 != null : "fx:id=\"s24\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s25 != null : "fx:id=\"s25\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s26 != null : "fx:id=\"s26\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s27 != null : "fx:id=\"s27\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s28 != null : "fx:id=\"s28\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s29 != null : "fx:id=\"s29\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s30 != null : "fx:id=\"s30\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s31 != null : "fx:id=\"s31\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s32 != null : "fx:id=\"s32\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s33 != null : "fx:id=\"s33\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s34 != null : "fx:id=\"s34\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s35 != null : "fx:id=\"s35\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s36 != null : "fx:id=\"s36\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s37 != null : "fx:id=\"s37\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s38 != null : "fx:id=\"s38\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s39 != null : "fx:id=\"s39\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s40 != null : "fx:id=\"s40\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s41 != null : "fx:id=\"s41\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert s42 != null : "fx:id=\"s42\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert bookSeat != null : "fx:id=\"bookSeat\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert availableSeatsLabel != null : "fx:id=\"availableSeatsLabel\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert chosenNum != null : "fx:id=\"chosenNum\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert movieInfo != null : "fx:id=\"movieInfo\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert warning != null : "fx:id=\"warning\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert rowsGrid != null : "fx:id=\"rowsGrid\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        assert colsGrids != null : "fx:id=\"colsGrid\" was not injected: check your FXML file 'SeatChoosing.fxml'.";
        counter = 0;
        hall = screening.getHall();
		Image image = new Image(screening.getMovie().getImage().getImgURL());
		poster.setImage(image);
		movieInfo.setText("Movie: " + screening.getMovie().getMovieTitle() + " - " + screening.getMovie().getMovieTitleHeb()
				+ "\nDate: " + screening.getScreeningDate() 
		+ "\nTime: " + screening.getScreeningTime()
		+ "\nCost per ticket: " + screening.getMovie().getTicketCost());
        chosenNum.setText("Number of chosen seats: " + Integer.toString(counter));
        availableSeatsLabel.setText("Available seats: " + Integer.toString(screening.getAvailableSeats()));
        drawSeats();
    }
    
    private void drawSeats() {
        int seatNum = 0;
        if ((hall.getCols()/2)*2 == (hall.getCols())) {
        	for (int k = 0 ; k < hall.getRows() ; k ++) {
        		rowsGrid.getChildren().get(k).setVisible(true);
            	int i = 3 - hall.getCols()/2, j = 3 + hall.getCols()/2;
        		for (; i <= j && seatNum < hall.getSeatsNum() ; i ++) {
        			if (i != 3) {
        				if (!screening.getSeatStatus(seatNum)) {
        					gridSeats.getChildren().get(i + k*7).setStyle(
        						"-fx-fill:#792f2f; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;");
        				} else {
        					gridSeats.getChildren().get(i + k*7).setStyle(
            					"-fx-fill:#e7d1d1; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;");
        				}
        				seatNum++;
        			}
        		}
        	}
       		int k = 1;
    		int i = 3 - hall.getCols()/2;
    		int j = 3 + hall.getCols()/2;
    		for (; i <= j ; i ++) {
    			if (i != 3) {
    			Label text = new Label("hi");
    			text.setText(Integer.toString(k));
    			colsGrids.add(text, i, 0);
    			k++;
    			}
    		}
        } else {
        	for (int k = 0 ; k < hall.getRows() ; k ++) {
        		rowsGrid.getChildren().get(k).setVisible(true);
            	int i = 3 - (hall.getCols()-1)/2, j = 3 + (hall.getCols()-1)/2;
        		for (; i <= j && seatNum < hall.getSeatsNum() ; i ++) {
    				if (!screening.getSeatStatus(seatNum)) {
    					gridSeats.getChildren().get(i + k*7).setStyle(
    						"-fx-fill:#792f2f; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;");
    				} else {
    					gridSeats.getChildren().get(i + k*7).setStyle(
        					"-fx-fill:#e7d1d1; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;");
    				}
    				seatNum++;
        		}
        	}
       		int k = 1;
    		int i = 3 - hall.getCols()/2;
    		int j = 3 + hall.getCols()/2;
    		for (; i <= j ; i ++) {
    			Label text = new Label("hi");
    			text.setText(Integer.toString(k));
    			colsGrids.add(text, i, 0);
    			k++;
    		}
        }
    }
}
