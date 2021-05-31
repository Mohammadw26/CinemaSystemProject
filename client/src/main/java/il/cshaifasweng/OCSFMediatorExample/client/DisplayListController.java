package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.security.auth.RefreshFailedException;
import javax.security.auth.Refreshable;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class DisplayListController {

	public static List<Movie> movieList;
	private static int rowsNum = 2;
	private static int colsNum = 2;
	private int page = 1;
	private int pages = 1;
	
	
	
    public static List<Movie> getMovieList() {
		return movieList;
	}

	public static void setMovieList(List<Movie> movieList) {
		DisplayListController.movieList = movieList;
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="homeBtn"
    private Button homeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="editBtn"
    private Button editBtn; // Value injected by FXMLLoader

    @FXML // fx:id="gridList"
    private GridPane gridList; // Value injected by FXMLLoader
    
    @FXML
    private Label pagesLabel;

    @FXML
    private Button nxtBtn;

    @FXML
    private Button prevBtn;

    @FXML
    void nextPage(ActionEvent event) throws IOException {
    	if (page < movieList.size()/4 + 1) {
    		page++;
        	fillGrids();
    	}
    }

    @FXML
    void prevPage(ActionEvent event) throws IOException {
    	if (page > 1) {
    	page--;
    	fillGrids();
    	}
    }
    
    @FXML
    void returnToMain(ActionEvent event) {
		try {
			App.setRoot("primary");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void fillGrids() throws IOException {
    	pagesLabel.setText("page " + page + " out of " + pages);
    	Platform.runLater(() -> {
    		gridList.getChildren().clear();
            for (int i = 0; i < rowsNum; i++) {
                for (int j = 0; j < colsNum; j++) {
                    Pair<Parent, Object> viewData = null;
                    try {
                        viewData = LayoutManager.getInstance().getFXML("displayMovieData");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Node itemCell = viewData.getKey();
                    DisplayMovieDataController controller =
                            (DisplayMovieDataController) viewData.getValue();
                    int index = (page - 1) * colsNum * rowsNum + i * colsNum + j;
                    if (index >= movieList.size())
                        break;
                    Movie item = movieList.get(index);
                    controller.setMovie(item);
                    controller.setDisplay();
                    gridList.add(itemCell, j, i);
                }
            }
         });
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	pages = movieList.size()/4 + 1;
    	pagesLabel.setText("page " + page + " out of " + pages);
    	Platform.runLater(() -> {
    		gridList.getChildren().clear();
            for (int i = 0; i < rowsNum; i++) {
                for (int j = 0; j < colsNum; j++) {
                    Pair<Parent, Object> viewData = null;
                    try {
                        viewData = LayoutManager.getInstance().getFXML("displayMovieData");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Node itemCell = viewData.getKey();
                    DisplayMovieDataController controller =
                            (DisplayMovieDataController) viewData.getValue();
                    int index = (page - 1) * colsNum * rowsNum + i * colsNum + j;
                    if (index >= movieList.size())
                        break;
                    Movie item = movieList.get(index);
                    controller.setMovie(item);
                    controller.setDisplay();
                    gridList.add(itemCell, j, i);
                }
            }
         });
    }
    
    /*@FXML
    void startDisplay(ActionEvent event) {
    	Platform.runLater(() -> {
    		gridList.getChildren().clear();
            for (int i = 0; i < rowsNum; i++) {
                for (int j = 0; j < colsNum; j++) {
                    Pair<Parent, Object> viewData = null;
                    try {
                        viewData = LayoutManager.getInstance().getFXML("displayMovieInfo");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Node itemCell = viewData.getKey();
                    MovieInfoDisplayController controller =
                            (MovieInfoDisplayController) viewData.getValue();
                    int index = (page - 1) * colsNum * rowsNum + i * colsNum + j;
                    if (index >= movieList.size())
                        break;
                    Movie item = movieList.get(index);
                    controller.setMovie(item);
                    controller.setDisplay();
                    gridList.add(itemCell, j, i);
                }
            }
         });
    }*/
}

