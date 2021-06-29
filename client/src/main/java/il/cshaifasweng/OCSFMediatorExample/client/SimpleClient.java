package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.List;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import il.cshaifasweng.OCSFMediatorExample.entities.BookingRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMember;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.FullOrderRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.LogInRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.RentRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;


public class SimpleClient extends AbstractClient {
	/**
	 * 
	 */
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		String msgString = ((Message) msg).getMessage();
		if (msgString.startsWith("#SendLists")) {
			EventBus.getDefault().post(new MoviesReceivedEvent((Message) msg));
		}
		else if (msgString.startsWith("#BranchesList")) {
			EventBus.getDefault().post(new BranchesReceivedEvent((Message) msg));
		}
		else if (msgString.startsWith("#WorkersList")) {
			EventBus.getDefault().post(new WorkersReceivedEvent((Message) msg));
		}
		else if (msgString.startsWith("#RefreshAdd")) {
			EditMovieScreeningsController.setMovie((CinemaMovie) ((Message) msg).getObject());
			try {
				App.setRoot("editMovieScreenings");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#RefreshEdit")) {
			EditMovieScreeningsController.setMovie((CinemaMovie) ((Message) msg).getObject());
			try {
				App.setRoot("editMovieScreenings");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#RefreshDelete")) {
			EditMovieScreeningsController.setMovie((CinemaMovie) ((Message) msg).getObject());
			try {
				App.setRoot("editMovieScreenings");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#ReportsList")) {
			TicketsSalesReportController.setAllBranches((List<SirtyaBranch>) ((Message) msg).getObject());
			RentLinksReportsController.setOthersList((List<Purchase>)((Message)msg).getObject2());
			try {
				App.setRoot("ReportsReview");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		else if (msgString.startsWith("#SeatsSaved")) {
			BookingOrderController.setRequest((BookingRequest) ((Message) msg).getObject());
			try {
				App.setRoot("bookingOrder");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#SeatsFreed")) {
			try {
				SimpleClient.getClient().sendToServer("#CatalogRequest");
		    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#BookedNonMember")) {
			BookingSummaryController.setRequest((FullOrderRequest) ((Message) msg).getObject());
			try {
				App.setRoot("bookingSummary");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#RentedNonMember")) {
			RentingSummaryController.setRequest((RentRequest) ((Message) msg).getObject());
			try {
				App.setRoot("rentingSummary");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#BookedMember")) {
			BookingSummaryController.setRequest((FullOrderRequest) ((Message) msg).getObject());
			DisplayListController.setMember((CinemaMember) ((Message) msg).getObject2());
			try {
				App.setRoot("bookingSummary");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#RentedMember")) {
			RentingSummaryController.setRequest((RentRequest) ((Message) msg).getObject());
			DisplayListController.setMember((CinemaMember) ((Message) msg).getObject2());
			try {
				App.setRoot("rentingSummary");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#WorkerLogIn")) {
			DisplayListController.setWorker((Worker) ((Message) msg).getObject());
			try {
				App.setRoot("primary");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#MemberLogIn2")) {
			DisplayListController.setMember((CinemaMember) ((Message) msg).getObject());
			try {
				BookingOrderController.setStatus(1);
				App.setRoot("bookingOrder");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#MemberLogIn3")) {
			DisplayListController.setMember((CinemaMember) ((Message) msg).getObject());
			try {
				RentMovieController.setStatus(1);
				App.setRoot("rentMovie");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#MemberLogIn")) {
			DisplayListController.setMember((CinemaMember) ((Message) msg).getObject());
			try {
				App.setRoot("primary");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#LogInFailed2")) {
			AdminPanelController.setRequest((LogInRequest) ((Message) msg).getObject());
			try {
				BookingOrderController.setStatus(2);
				App.setRoot("bookingOrder");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#LogInFailed3")) {
			AdminPanelController.setRequest((LogInRequest) ((Message) msg).getObject());
			try {
				RentMovieController.setStatus(2);
				App.setRoot("rentMovie");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#LogInFailed")) {
			AdminPanelController.setRequest((LogInRequest) ((Message) msg).getObject());
			try {
				App.setRoot("adminPanel");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(msgString.startsWith("#RefreshMovieDelete")) {
			System.out.println("At RefreshDeleteMovie\n");
			try {
				SimpleClient.getClient().sendToServer("#CatalogRequest");
		    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
			
			
//		if (msg.getClass().equals(MovieList.class)) {
//			EventBus.getDefault().register(this);
//			EventBus.getDefault().post(new MoviesReceivedEvent((MovieList) msg));
//		}
//	}

	
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
