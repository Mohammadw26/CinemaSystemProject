package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMember;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.ComingSoonMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.ContentManager;
import il.cshaifasweng.OCSFMediatorExample.entities.CustomerServiceEmployee;
import il.cshaifasweng.OCSFMediatorExample.entities.GeneralManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.OnDemandMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.util.Pair;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class DisplayListController {
	private static Worker worker;
	private static CinemaMember member;
	public static List<CinemaMovie> movieList;
	public static List<ComingSoonMovie> soonList;
	public static List<OnDemandMovie> onDemandList;
	private static int rowsNum = 1;
	private static int colsNum = 3;
	private int page = 1;
	private int pageSoon = 1;
	private int pageDemand = 1;
	private int pages = 1;
	private int pagesSoon = 1;
	private int pagesDemand = 1;

	public static CinemaMember getMember() {
		return member;
	}

	public static void setMember(CinemaMember member) {
		DisplayListController.member = member;
	}

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

	public static List<OnDemandMovie> getOnDemandList() {
		return onDemandList;
	}

	public static void setMovieList(List<CinemaMovie> list) {
		DisplayListController.movieList = list;
	}

	public static void setSoonList(List<ComingSoonMovie> list) {
		DisplayListController.soonList = list;
	}

	public static void setOnDemandList(List<OnDemandMovie> list) {
		DisplayListController.onDemandList = list;
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
	private Button addMovieBtnNew;

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
	private Button employeeBtn;

	@FXML
	private Button salesReportsBtn;

	@FXML
	private Button logOutBtn;

	@FXML // fx:id="identityLabel"
	private Label identityLabel; // Value injected by FXMLLoader
	
	@FXML
    private FontAwesomeIconView prevBtn1;

	@FXML
	private FontAwesomeIconView nxtBtn1;

    @FXML
    private FontAwesomeIconView nxtBtn2;

    @FXML
    private FontAwesomeIconView prevBtn2;

    @FXML
    void GoToAddComplaint(ActionEvent event) {
    	if (member != null) {
    		SubmitComplaintController.setBuyer(member);
    	} else if (worker != null && worker.getClass().equals(CustomerServiceEmployee.class)) {
    		SubmitComplaintController.setWorker((CustomerServiceEmployee)worker);
    	}
    	try {
			SimpleClient.getClient().sendToServer(new Message("#GetComplaints", member));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


	@FXML
	private Button tavSagoalBtn;

    @FXML
    private Button purchaseHistory;
    
    @FXML
    private Button filterLists;

	@FXML
	void loadTavSagoalUpdate(ActionEvent event) {
		try {
			App.setRoot("TavSagoalUpdating");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @FXML
    void loadHistoryPage(ActionEvent event) {
		try {
			App.setRoot("purchaseHistory");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void loadFiltering(ActionEvent event) {
    	try {
			SimpleClient.getClient().sendToServer("#BranchesListRequest2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@FXML
	void nextPage2(MouseEvent event) throws IOException {
		pageDemand++;
		fillGrids(gridList2, 2);
		prevBtn2.setVisible(true);
		if (pageDemand >= pagesDemand) {
			nxtBtn2.setVisible(false);
		}
	}

	@FXML
	void prevPage2(MouseEvent event) throws IOException {
		if (pageDemand > 1) {
			pageDemand--;
			fillGrids(gridList2, 2);
			nxtBtn2.setVisible(true);
		}
		if (pageDemand == 1) {
			prevBtn2.setVisible(false);
		}
	}

	@FXML
	void nextPage(MouseEvent event) throws IOException {
		page++;
		fillGrids(gridList, 1);
		prevBtn.setVisible(true);
		if (page >= pages)
			nxtBtn.setVisible(false);
	}

	@FXML
	void nextPage1(MouseEvent event) throws IOException {
		pageSoon++;
		fillGrids(gridList3, 3);
		prevBtn1.setVisible(true);
		if (pageSoon >= pagesSoon) {
			nxtBtn1.setVisible(false);
		}
	}

	@FXML
	void prevPage(MouseEvent event) throws IOException {
		if (page > 1) {
			page--;
			fillGrids(gridList, 1);
			nxtBtn.setVisible(true);
		}
		if (page == 1) {
			prevBtn.setVisible(false);
		}
	}

	@FXML
	void prevPage1(MouseEvent event) throws IOException {
		if (pageSoon > 1) {
			pageSoon--;
			fillGrids(gridList3, 3);
			nxtBtn1.setVisible(true);
		}
		if (pageSoon == 1) {
			prevBtn1.setVisible(false);
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

	private void fillGrids(GridPane gridList, int listNum) throws IOException {
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
					// if (worker != null)
					// controller.setWorkerMode();
					int index;
					if (listNum == 1)
						index = (page - 1) * colsNum * rowsNum + i * colsNum + j;
					else if (listNum == 2)
						index = (pageDemand - 1) * colsNum * rowsNum + i * colsNum + j;
					else {
						index = (pageSoon - 1) * colsNum * rowsNum + i * colsNum + j;
					}
					if (listNum == 1 && index >= movieList.size() || listNum == 2 && index >= onDemandList.size()
							|| listNum == 3 && index >= soonList.size())
						break;
					if (listNum == 1) {
						CinemaMovie item = movieList.get(index);
						controller.setType(listNum);
						controller.setMovie(item);
					}
					if (listNum == 2) {
						OnDemandMovie item = onDemandList.get(index);
						controller.setType(listNum);
						controller.setMovie(item);
					}
					if (listNum == 3) {
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
		logOutBtn.setVisible(false);
		worker = null;
		member = null;
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
			App.setRoot("addMovie");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void GoToAddMovie(ActionEvent event) {
		try {
			App.setRoot("addMovie");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert purchaseHistory != null : "fx:id=\"purchaseHistory\" was not injected: check your FXML file 'displayList.fxml'.";
		EventBus.getDefault().register(this);
		identityLabel.setVisible(false);
		if (worker != null) {
			identityLabel.setText("Logged in as:\n" + worker.getWorkerName());
			identityLabel.setVisible(true);
			DisplayMovieDataController.setWorkerMode();
			compliantsBtn.setVisible(true);
			addMovieBtn.setVisible(true);
			salesReportsBtn.setVisible(true);
			employeeBtn.setVisible(true);
			logOutBtn.setVisible(true);
			tavSagoalBtn.setVisible(true);
			if (worker.getClass().equals(GeneralManager.class)) {
				compliantsBtn.setDisable(true);
				addMovieBtn.setDisable(false);
				salesReportsBtn.setDisable(false);
				employeeBtn.setDisable(false);
				logOutBtn.setDisable(false);
				tavSagoalBtn.setDisable(false);
			} else if (worker.getClass().equals(ContentManager.class)) {
				compliantsBtn.setDisable(true);
				addMovieBtn.setDisable(false);
				salesReportsBtn.setDisable(true);
				employeeBtn.setDisable(false);
				logOutBtn.setDisable(false);
			} else if (worker.getClass().equals(CustomerServiceEmployee.class)) {
				compliantsBtn.setDisable(false);
				addMovieBtn.setDisable(true);
				salesReportsBtn.setDisable(true);
				employeeBtn.setDisable(true);
				logOutBtn.setDisable(false);
				tavSagoalBtn.setDisable(false);
			} else if (worker.getClass().equals(BranchManager.class)) {
				compliantsBtn.setDisable(true);
				addMovieBtn.setDisable(true);
				salesReportsBtn.setDisable(false);
				employeeBtn.setDisable(false);
				logOutBtn.setDisable(false);
			}
		} else if (member != null) {
			identityLabel.setText("Logged in as:\n" + member.getFirstName() + " " + member.getLastName());
			identityLabel.setVisible(true);
			compliantsBtn.setVisible(true);
			compliantsBtn.setDisable(false);
			logOutBtn.setVisible(true);
		}
		pages = movieList.size() / 3;
		if ((movieList.size() / 3) * 3 < movieList.size()) {
			pages = pages + 1;
		}
		prevBtn.setVisible(false);
		nxtBtn.setVisible(pages > 1);

		pagesSoon = soonList.size() / 3;
		if ((soonList.size() / 3) * 3 < soonList.size()) {
			pagesSoon = pagesSoon + 1;
		}
		prevBtn1.setVisible(false);
		nxtBtn1.setVisible(pagesSoon > 1);

		pagesDemand = onDemandList.size() / 3;
		if ((onDemandList.size() / 3) * 3 < onDemandList.size()) {
			pagesDemand = pagesDemand + 1;
		}
		prevBtn2.setVisible(false);
		nxtBtn2.setVisible(pagesDemand > 1);

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
			fillGrids(gridList2, 2);
			fillGrids(gridList3, 3);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void ViewReports(ActionEvent event) {
		try {
			App.setRoot("ReportsReview");
//			SimpleClient.getClient().sendToServer("#ReportsRequest");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@FXML
	void ViewRequests(ActionEvent event) {

			try {
				SimpleClient.getClient().sendToServer(new Message("#PricesListRequest"));
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

	@Subscribe
	public void onRefreshCatalogEvent(RefreshCatalogEvent event) {
		movieList = event.getMoviesList();
		soonList = event.getSoonMovieList();
		onDemandList = event.getMoviesListDemand();
		try {
			fillGrids(gridList, 1);
			fillGrids(gridList2, 2);
			fillGrids(gridList3, 3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
