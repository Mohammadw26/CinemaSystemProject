package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.ComingSoonMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.OnDemandMovie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;


public class DisplayMovieDataController {
	private int typeIndex; // 1 - Cinema Movie || 2 - On-Demand || 3 - Coming soon
	private Movie movie;
	private int counter;
	private static boolean isWorker = false;
	private String temp = "Screening times:\n";
	
    @FXML
    private ImageView backgrndImg;

    @FXML // fx:id="anchPane"
    private AnchorPane anchPane; // Value injected by FXMLLoader
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="imageField"
    private ImageView imageField; // Value injected by FXMLLoader

    @FXML // fx:id="titleField"
    private Label titleField; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionField"
    private Label descriptionField; // Value injected by FXMLLoader

    @FXML // fx:id="ProducersField"
    private Label ProducersField; // Value injected by FXMLLoader

    /*@FXML // fx:id="starsField"
    private Label starsField; // Value injected by FXMLLoader*/

    @FXML // fx:id="costField"
    private Label costField; // Value injected by FXMLLoader

    @FXML // fx:id="screeningField"
    private Label screeningField; // Value injected by FXMLLoader

//    @FXML // fx:id="editBtn"
//    private Button editBtn; // Value injected by FXMLLoader

    @FXML
    private FontAwesomeIconView addToCartBtn;

    @FXML
    private FontAwesomeIconView editBtn;

    @FXML
    private FontAwesomeIconView deleteBtn;
    
    @FXML
    private Label titleField2;

    @FXML
    private TabPane tabPane;
    
    public static void setWorkerMode() {
    	isWorker = true;
    }
    
    public static void resetMode() {
    	isWorker = false;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	editBtn.setVisible(isWorker);
    	deleteBtn.setVisible(isWorker);
    	tabPane.getStyleClass().add("floating");
        assert imageField != null : "fx:id=\"imageField\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert titleField != null : "fx:id=\"titleField\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert descriptionField != null : "fx:id=\"descriptionField\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert ProducersField != null : "fx:id=\"ProducersField\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        //assert starsField != null : "fx:id=\"starsField\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert costField != null : "fx:id=\"costField\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert screeningField != null : "fx:id=\"screeningField\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert addToCartBtn != null : "fx:id=\"addToCartBtn\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert editBtn != null : "fx:id=\"editBtn\" was not injected: check your FXML file 'displayMovieData.fxml'.";
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'displayMovieData.fxml'.";
    }
    
	public void setDisplay () {
		titleField.setText(movie.getMovieTitle());
		titleField2.setText(movie.getMovieTitleHeb());
		descriptionField.setText("" + movie.getMovieDescription());
		ProducersField.setText("Movie Producers: " + movie.getMovieProducer() + "\n"  + "Starring Actors: " +movie.getStarringActors());
		//starsField.setText("Starring Actors: " +movie.getStarringActors());
		if (typeIndex == 1 ) {
			CinemaMovie tempCast = (CinemaMovie) movie;
			costField.setText("Cost per Ticket: " + tempCast.getTicketCost() + " NIS");
			counter = tempCast.getScreenings().size();
			for (int i = 0 ; i < counter; i++) {
				temp += tempCast.getScreenings().get(i).getScreeningDate() + " ";
				temp += tempCast.getScreenings().get(i).getScreeningTime() + " at ";
				temp += tempCast.getScreenings().get(i).getBranch().getAddress() + "\n";
			screeningField.setText(temp);
			}
		}
		if (typeIndex == 2 ) {
			costField.setText("24H rent cost: " + ((OnDemandMovie) movie).getCost() + " NIS");
		}
		if (typeIndex == 3) {
			addToCartBtn.setVisible(false);
			costField.setText("");
		}
	

		//try {
			/*InputStream stream = new FileInputStream(movie.getImage().getImgURL());
			Image image = new Image(stream);*/
			//imageField = ImageViewBuilder.create().image(new Image(movie.getImage().getImgURL())).build();
			Image image = new Image(movie.getImage().getImgURL());
			imageField.setImage(image);
			imageField.setPreserveRatio(false);
		/*} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	
	
	public void setType(int typeIndex) {
		this.typeIndex = typeIndex;
	}
	
//    @FXML
//    void uploadAddScreening(ActionEvent event) {
//    	AddMovieScreeningController.setMovie(movie);
//		try {
//			SimpleClient.getClient().sendToServer("#BranchesListRequest");
//	    	} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
//
//    @FXML
//    void uploadDeleteScreening(ActionEvent event) {
//    	EditMovieScreeningsController.setMovie(movie);
//    	try {
//			App.setRoot("deleteMovieScreening");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }

	@FXML
    void uploadEditScreening(MouseEvent event) {
		EditMovieScreeningsController.setMovie((CinemaMovie) movie);
    	try {
			SimpleClient.getClient().sendToServer("#BranchesListRequest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    @FXML
    void deleteCurrentMovie(MouseEvent event) {
    	System.out.println("At MovieDataController\n");

    	if (typeIndex == 1) {
    		CinemaMovie request = new CinemaMovie (movie.getId(),movie.getMovieTitle(),movie.getMovieTitleHeb(),movie.getMovieProducer(),movie.getStarringActors(),movie.getMovieDescription(),0.0, movie.getImage());
	    	try {
	    		SimpleClient.getClient().sendToServer(new Message("#DeleteMovieRegular",request));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else if(typeIndex == 2) {
    		OnDemandMovie request = new OnDemandMovie (movie.getId(),movie.getMovieTitle(),movie.getMovieTitleHeb(),movie.getMovieProducer(),movie.getStarringActors(),movie.getMovieDescription(),0.0, movie.getImage());
				try {
					SimpleClient.getClient().sendToServer(new Message("#DeleteMovieDemand", request));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	}
    	else if(typeIndex == 3) {
    		ComingSoonMovie request = new ComingSoonMovie (movie.getId(),movie.getMovieTitle(),movie.getMovieTitleHeb(),movie.getMovieProducer(),movie.getStarringActors(),movie.getMovieDescription(), movie.getImage());
    		try {
				SimpleClient.getClient().sendToServer(new Message("#DeleteMovieComingSoon", request));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
	
    @FXML
    void chooseSeating(MouseEvent event) {
    	if (typeIndex == 1 ) {
    		ChooseScreeningController.setMovie((CinemaMovie)movie);
    		try {
    			App.setRoot("chooseScreening");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if (typeIndex == 2 ) {
      		RentMovieController.setMovie((OnDemandMovie)movie);
    		try {
    			App.setRoot("rentMovie");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

}
	
//    @FXML
//    void uploadEditScreening(ActionEvent event) {
//    	EditMovieScreeningsController.setMovie(movie);
//    	try {
//			SimpleClient.getClient().sendToServer("#BranchesListRequest");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
//}
