package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.List;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.application.Platform;
import il.cshaifasweng.OCSFMediatorExample.entities.BookingRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.CasualBuyer;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMember;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.FullOrderRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.LogInRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.RentRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import il.cshaifasweng.OCSFMediatorExample.entities.TavSagoal;

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
		} else if (msgString.startsWith("#BranchesList2")) {
			FilteringController.setAllBranches((List<SirtyaBranch>) ((Message) msg).getObject());
			FilteringController.setAllMovies((List<Movie>) ((Message) msg).getObject2());
			try {
				App.setRoot("filtering");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#BranchesList")) {
			EventBus.getDefault().post(new BranchesReceivedEvent((Message) msg));
		} else if (msgString.startsWith("#WorkersList")) {
			EventBus.getDefault().post(new WorkersReceivedEvent((Message) msg));
		} else if (msgString.startsWith("#RefreshCatalog")) {
			EventBus.getDefault().post(new RefreshCatalogEvent((Message) msg));
		} else if (msgString.startsWith("#RefreshPriceRequest")) {
			EventBus.getDefault().post(new PriceReceivedEvent((Message) msg));
		} else if (msgString.startsWith("#RefreshAdd")) {
			EditMovieScreeningsController.setMovieRegular((CinemaMovie) ((Message) msg).getObject());
			try {
				App.setRoot("editMovieScreenings");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#RefreshEdit")) {
			EditMovieScreeningsController.setMovieRegular((CinemaMovie) ((Message) msg).getObject());
			try {
				App.setRoot("editMovieScreenings");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#TavSagoalUpdated")) {
			TavSagoalUpdatingController.setUpdateValues((TavSagoal) ((Message) msg).getObject());
			TavSagoalUpdatingController.setFlag(true);
			try {
				App.setRoot("TavSagoalUpdating");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#TavSagoalStatus")) {
			ChooseScreeningController.setRestrictions((TavSagoal) (((Message) msg).getObject()));
			try {
				App.setRoot("chooseScreening");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#RefreshDelete")) {
			EditMovieScreeningsController.setMovieRegular((CinemaMovie) ((Message) msg).getObject());
			try {
				App.setRoot("editMovieScreenings");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#ReportsList")) {
			try {
				App.setRoot("rentLinksReports");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EventBus.getDefault().post(new TabReportsEvent((Message) msg));
		} else if (msgString.startsWith("#complaintsReportsList")) {
			ComplaintsReportsController.setAllBranches(((List<SirtyaBranch>) ((Message) msg).getObject()));
			try {
				App.setRoot("complaintsReports");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#RefundReportsList")) {
			try {
				App.setRoot("refundReports");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EventBus.getDefault().post(new RefundReportsEvent((Message) msg));
		} else if (msgString.startsWith("#RefreshRefundReportsRequest")) {
			EventBus.getDefault().post(new RefundReportsEvent((Message) msg));
		} else if (msgString.startsWith("#SendBranchTicketsReports")) {
			System.out.println("wa7wa7 is here");

			try {
				App.setRoot("ticketsSalesByBranchReports");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("wa7wa7 is here");

			EventBus.getDefault().post(new LinksBranchesReportsEvent((Message) msg));
		}
		else if (msgString.startsWith("#RefreshfreeSeats")) {
			EventBus.getDefault().post(new SeatSavedEvent((Message) msg));
		}
		else if (msgString.startsWith("#RefreshSeatsSaved")) {
			EventBus.getDefault().post(new SeatSavedEvent((Message) msg));
		}
		else if (msgString.startsWith("#SeatsSaved")) {
			BookingOrderController.setRequest((BookingRequest) ((Message) msg).getObject());
			try {
				App.setRoot("bookingOrder");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#SeatsFreed")) {
			try {
				SimpleClient.getClient().sendToServer("#CatalogRequest");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#BookedNonMember")) {
			BookingSummaryController.setRequest((FullOrderRequest) ((Message) msg).getObject());
			try {
				App.setRoot("bookingSummary");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#RentedNonMember")) {
			RentingSummaryController.setRequest((RentRequest) ((Message) msg).getObject());
			try {
				App.setRoot("rentingSummary");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#BookedMember")) {
			BookingSummaryController.setRequest((FullOrderRequest) ((Message) msg).getObject());
			DisplayListController.setMember((CinemaMember) ((Message) msg).getObject2());
			try {
				App.setRoot("bookingSummary");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#RentedMember")) {
			RentingSummaryController.setRequest((RentRequest) ((Message) msg).getObject());
			DisplayListController.setMember((CinemaMember) ((Message) msg).getObject2());
			try {
				App.setRoot("rentingSummary");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#WorkerLogIn")) {
			DisplayListController.setWorker((Worker) ((Message) msg).getObject());
			try {
				App.setRoot("primary");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#MemberLogIn2")) {
			DisplayListController.setMember((CinemaMember) ((Message) msg).getObject());
			try {
				BookingOrderController.setStatus(1);
				App.setRoot("bookingOrder");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#MemberLogIn3")) {
			DisplayListController.setMember((CinemaMember) ((Message) msg).getObject());
			try {
				RentMovieController.setStatus(1);
				App.setRoot("rentMovie");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#MemberLogIn5")) {
			DisplayListController.setMember((CinemaMember) ((Message) msg).getObject());
			try {
				App.setRoot("contactUs");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#MemberLogIn4")) {
			DisplayListController.setMember((CinemaMember) ((Message) msg).getObject());
			PurchaseHistoryController.setPurchaseList((List<Purchase>) ((Message) msg).getObject2());
			PurchaseHistoryController.setStatus(1);
			try {
				RentMovieController.setStatus(1);
				App.setRoot("purchaseHistory");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#MemberLogIn")) {
			DisplayListController.setMember((CinemaMember) ((Message) msg).getObject());
			try {
				App.setRoot("primary");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#LogInFailed2")) {
			AdminPanelController.setRequest((LogInRequest) ((Message) msg).getObject());
			try {
				BookingOrderController.setStatus(2);
				App.setRoot("bookingOrder");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#LogInFailed3")) {
			AdminPanelController.setRequest((LogInRequest) ((Message) msg).getObject());
			try {
				RentMovieController.setStatus(2);
				App.setRoot("rentMovie");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#LogInFailed5")) {
			try {
				ContactUsController.setStatus(1);
				App.setRoot("ContactUs");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#LogInFailed4")) {
			try {
				PurchaseHistoryController.setStatus(2);
				App.setRoot("purchaseHistory");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#LogInFailed")) {
			AdminPanelController.setRequest((LogInRequest) ((Message) msg).getObject());
			try {
				App.setRoot("adminPanel");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#RefreshMovieDelete")) {
			System.out.println("At RefreshDeleteMovie\n");
			try {
				Warning new_warning = new Warning("Deleting movie is in progress");
				EventBus.getDefault().post(new WarningEvent((Warning) new_warning));
				SimpleClient.getClient().sendToServer("#getAllMovies");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#PricesList")) {
			try {
				App.setRoot("priceRequest");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EventBus.getDefault().post(new PriceReceivedEvent((Message) msg));

		} else if (msgString.startsWith("#RefreshDeletePrice")) {

			EventBus.getDefault().post(new PriceReceivedEvent((Message) msg));

		} else if (msgString.startsWith("#RefreshChangePrice")) {
			EventBus.getDefault().post(new PriceReceivedEvent((Message) msg));
		} else if (msgString.startsWith("#RefreshRentSellings")) {
			EventBus.getDefault().post(new TabReportsEvent((Message) msg));
		} else if (msgString.startsWith("#RefreshTicketsSellings")) {
			EventBus.getDefault().post(new LinksReportsEvent((Message) msg));
			EventBus.getDefault().post(new LinksBranchesReportsEvent((Message) msg));

		} else if (msgString.startsWith("#SendTicketsReports")) {
				try {
					App.setRoot("ticketsSalesReport");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			EventBus.getDefault().post(new LinksReportsEvent((Message) msg));
		} else if (msgString.startsWith("#SendBranchTicketsReport")) {
			try {
				App.setRoot("ticketsSalesByBranchReport");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		EventBus.getDefault().post(new LinksReportsEvent((Message) msg));
		} else if (msgString.startsWith("#CasualBuyerSearch")) {
			if (((Message) msg).getObject() == null) {
				PurchaseHistoryController.setStatus(4);
			} else {
				PurchaseHistoryController.setPurchaseList((List<Purchase>) ((Message) msg).getObject2());
				PurchaseHistoryController.setUnregClient((CasualBuyer) ((Message) msg).getObject());
				PurchaseHistoryController.setStatus(3);
			}
			try {
				App.setRoot("purchaseHistory");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#CanceledOrder")) {
			PurchaseHistoryController.setPurchaseList((List<Purchase>) ((Message) msg).getObject2());
			if (((Message) msg).getObject().getClass() == CasualBuyer.class) {
				PurchaseHistoryController.setUnregClient((CasualBuyer) ((Message) msg).getObject());
				PurchaseHistoryController.setStatus(5);
			} else {

				PurchaseHistoryController.setStatus(6);
			}
			try {
				App.setRoot("purchaseHistory");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#ShowComplaintsInTable")) {
			SubmitComplaintController.setComplaints((List<Complaint>) ((Message) msg).getObject());
			try {
				App.setRoot("submitComplaint");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#Warning")) {
			EventBus.getDefault().post(new WarningEvent((Warning) ((Message) msg).getObject()));
		} else if (msgString.startsWith("#ComplaintSubmitted")) {
			System.out.println("Complain submitted.");
			ContactUsController.setComplainer((CasualBuyer) ((Message) msg).getObject());
			try {
				App.setRoot("contactUs");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#ComplaintsList")) {
			RespondToComplaintsController.setAllComplaints((List<Complaint>) ((Message) msg).getObject());
			try {
				App.setRoot("responseToComplaints");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#ComplainerSearch")) {
			if ((CasualBuyer) ((Message) msg).getObject() == null) {
				ContactUsController.setStatus(2);
			}
			ContactUsController.setComplainer((CasualBuyer) ((Message) msg).getObject());
			try {
				App.setRoot("contactUs");
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
