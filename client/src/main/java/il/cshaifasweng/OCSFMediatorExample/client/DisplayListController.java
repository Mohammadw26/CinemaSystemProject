package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.File;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.security.auth.RefreshFailedException;
import javax.security.auth.Refreshable;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.ComingSoonMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.ContentManager;
import il.cshaifasweng.OCSFMediatorExample.entities.CustomerServiceEmployee;
import il.cshaifasweng.OCSFMediatorExample.entities.GeneralManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class DisplayListController {
	private static Worker worker;
	public static List<CinemaMovie> movieList;
	public static List<ComingSoonMovie> soonList;
	private static int rowsNum = 1;
	private static int colsNum = 3;
	private int page = 1;
	private int pages = 1;

	public static void setWorker(Worker worker) {
		DisplayListController.worker = worker;
	}

	public static Worker getWorker() {
		return worker;
	}

	public static List<CinemaMovie> getMovieList() {
		return movieList;
	}
	
	public static List<ComingSoonMovie> getSoonList() {
		return soonList;
	}

	public static void setMovieList(List<CinemaMovie> list) {
		DisplayListController.movieList = list;
	}
	
	public static void setSoonList(List<ComingSoonMovie> list) {
		DisplayListController.soonList = list;
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
    private GridPane gridList2;
    
    @FXML
    private GridPane gridList3;

	@FXML
	private FontAwesomeIconView prevBtn;

	@FXML
	private FontAwesomeIconView nxtBtn;

	@FXML
	private AnchorPane anchorpane;

	@FXML
	private Button compliantsBtn;

	@FXML
	private Button addMovieBtn;

	@FXML
	private Button reviewRequestBtn;

	@FXML
	private Button employeeBtn;

	@FXML
	private Button salesReportsBtn;

	@FXML
	private Button logOutBtn;

	@FXML
	void nextPage(MouseEvent  event) throws IOException {
		if (page < movieList.size() / 3 + 1) {
			page++;
			fillGrids(gridList,1);
			prevBtn.setVisible(true);
		} 
		if (page == movieList.size() / 3 + 1) {
			nxtBtn.setVisible(false);
		}
	}

	@FXML
	void prevPage(MouseEvent  event) throws IOException {
		if (page > 1) {
			page--;
			fillGrids(gridList,1);
			nxtBtn.setVisible(true);
		}
		if (page == 1) {
			prevBtn.setVisible(false);
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

	private void fillGrids(GridPane gridList, int listNum ) throws IOException {
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
					DisplayMovieDataController controller = (DisplayMovieDataController) viewData.getValue();
					//if (worker != null)
						//controller.setWorkerMode();
					int index = (page - 1) * colsNum * rowsNum + i * colsNum + j;
					if (listNum==1 && index >= movieList.size() || listNum==3 && index >= soonList.size() )
						break;
					if (listNum==1) {
						CinemaMovie item = movieList.get(index);
						controller.setType(listNum);
						controller.setMovie(item);
					}
					if (listNum==3) {
						ComingSoonMovie item = soonList.get(index);
						controller.setType(listNum);
						controller.setMovie(item);
					}
					controller.setDisplay();
					gridList.add(itemCell, j, i);
				}
			}
		});
	}

	@FXML
	void logOut(ActionEvent event) {
		DisplayMovieDataController.resetMode();
		compliantsBtn.setVisible(false);
		addMovieBtn.setVisible(false);
		salesReportsBtn.setVisible(false);
		employeeBtn.setVisible(false);
		reviewRequestBtn.setVisible(false);
		logOutBtn.setVisible(false);
		worker = null;
		try {
			App.setRoot("primary");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
    void addMovie(ActionEvent event) {
		try {
			App.setRoot("addMoviePage");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		if (worker != null) {
			DisplayMovieDataController.setWorkerMode();
			compliantsBtn.setVisible(true);
			addMovieBtn.setVisible(true);
			salesReportsBtn.setVisible(true);
			employeeBtn.setVisible(true);
			reviewRequestBtn.setVisible(true);
			logOutBtn.setVisible(true);
			if (worker.getClass().equals(GeneralManager.class)) {
				compliantsBtn.setDisable(false);
				addMovieBtn.setDisable(false);
				salesReportsBtn.setDisable(false);
				employeeBtn.setDisable(false);
				reviewRequestBtn.setDisable(false);
				logOutBtn.setDisable(false);

			} else if (worker.getClass().equals(ContentManager.class)) {
				compliantsBtn.setDisable(true);
				addMovieBtn.setDisable(false);
				salesReportsBtn.setDisable(true);
				employeeBtn.setDisable(false);
				reviewRequestBtn.setDisable(true);
				logOutBtn.setDisable(false);

			} else if (worker.getClass().equals(CustomerServiceEmployee.class)) {
				compliantsBtn.setDisable(false);
				addMovieBtn.setDisable(true);
				salesReportsBtn.setDisable(true);
				employeeBtn.setDisable(true);
				reviewRequestBtn.setDisable(true);
				logOutBtn.setDisable(false);

			} else if (worker.getClass().equals(BranchManager.class)) {
				compliantsBtn.setDisable(false);
				addMovieBtn.setDisable(true);
				salesReportsBtn.setDisable(false);
				employeeBtn.setDisable(false);
				reviewRequestBtn.setDisable(true);
				logOutBtn.setDisable(false);
			}
		}
		pages = movieList.size() / 3 + 1;
		prevBtn.setVisible(false);
		nxtBtn.setVisible(pages>1);
//		File imagfile1 = new File(System.getProperty("user.dir") + "/Drapes.jpeg");
//		FileInputStream Image1pixels;
//		try {
//			Image1pixels = new FileInputStream(imagfile1);
//			Image image = new Image(Image1pixels);
//			gridList.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT,
//					BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
//			gridList.setPadding(new Insets(50, 0, 50, 0));
//			
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//    	gridList.setBackground(
//    			new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		try {
			fillGrids(gridList, 1);
			fillGrids(gridList3, 3);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @FXML void startDisplay(ActionEvent event) { Platform.runLater(() -> {
	 * gridList.getChildren().clear(); for (int i = 0; i < rowsNum; i++) { for (int
	 * j = 0; j < colsNum; j++) { Pair<Parent, Object> viewData = null; try {
	 * viewData = LayoutManager.getInstance().getFXML("displayMovieInfo"); } catch
	 * (IOException e) { e.printStackTrace(); } Node itemCell = viewData.getKey();
	 * MovieInfoDisplayController controller = (MovieInfoDisplayController)
	 * viewData.getValue(); int index = (page - 1) * colsNum * rowsNum + i * colsNum
	 * + j; if (index >= movieList.size()) break; Movie item = movieList.get(index);
	 * controller.setMovie(item); controller.setDisplay(); gridList.add(itemCell, j,
	 * i); } } }); }
	 */
	
}
