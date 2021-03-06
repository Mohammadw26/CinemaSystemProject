package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.OnDemandEditRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.OnDemandMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Price;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.Rent;
import il.cshaifasweng.OCSFMediatorExample.entities.RentRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.ScreeningsUpdateRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import il.cshaifasweng.OCSFMediatorExample.entities.TabPurchase;
import il.cshaifasweng.OCSFMediatorExample.entities.TavSagoal;
import il.cshaifasweng.OCSFMediatorExample.entities.Ticket;
import il.cshaifasweng.OCSFMediatorExample.entities.BookingRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import il.cshaifasweng.OCSFMediatorExample.entities.CasualBuyer;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMember;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.ComingSoonMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.ContentManager;
import il.cshaifasweng.OCSFMediatorExample.entities.CustomerServiceEmployee;
import il.cshaifasweng.OCSFMediatorExample.entities.FullOrderRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.GeneralManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Image;
import il.cshaifasweng.OCSFMediatorExample.entities.LogInRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import il.cshaifasweng.OCSFMediatorExample.helpers.ScreeningTimeComparator;
import il.cshaifasweng.OCSFMediatorExample.helpers.SendEmailTLS;

public class SimpleServer extends AbstractServer {
	private static SessionFactory sessionFactory = getSessionFactory();
	private static Session session;
	private static List<Movie> allmoviesList;
	private static List<CinemaMovie> moviesList;
	private static List<OnDemandMovie> moviesListDemand;
	private static List<Price> priceList;
	private static List<Screening> screeningsList;
	private static List<SirtyaBranch> branchesList;

	public SimpleServer(int port) {
		super(port);

	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// movieList.setMoviesList(getAll(Movie.class));
		// catalog =getAll(Movie.class);
		// MovieList movieList = new MovieList(getAll(Movie.class));
		String msgString = msg.toString();
		if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#CatalogRequest")) {
			// movieList.setMoviesList(getAll(Movie.class));
			try {
				session = sessionFactory.openSession();
				ArrayList<CinemaMovie> cinMovieList = getAll(CinemaMovie.class);
				ArrayList<ComingSoonMovie> soonMovieList = getAll(ComingSoonMovie.class);
				ArrayList<OnDemandMovie> onDemandList = getAll(OnDemandMovie.class);
				ArrayList<SirtyaBranch> allBranches = getAll(SirtyaBranch.class);
				client.sendToClient(new Message("#SendLists", cinMovieList, soonMovieList, onDemandList, allBranches));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.close();
		} else if (msgString.startsWith("#BranchesListRequest")) {
			try {
				session = sessionFactory.openSession();
				ArrayList<SirtyaBranch> branchesList = getAllBranches();
				if (msgString.startsWith("#BranchesListRequest2")) {
					List<Movie> temp = getAll(Movie.class);
					client.sendToClient(new Message("#BranchesList2", branchesList, temp));
				} else {
					/*
					 * for (SirtyaBranch brnch : branchesList) {
					 * System.out.println(brnch.getHalls().size()); }
					 */
					client.sendToClient(new Message("#BranchesList", branchesList));
				}
				session.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#LogOut")) {
			try {
				logOutRequest(((Message) msg), client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#ReportsRequest")) {
			// movieList.setMoviesList(getAll(Movie.class));
			try {
				session = sessionFactory.openSession();
				ArrayList<SirtyaBranch> branchesList = getAllBranches();
				ArrayList<Purchase> linksList = getAll(Purchase.class);

				client.sendToClient(new Message("#ReportsList", branchesList, linksList));
				session.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#ComplaintsReportsRequest")) {
			// movieList.setMoviesList(getAll(Movie.class));
			try {
				session = sessionFactory.openSession();
				ArrayList<SirtyaBranch> branchesList = getAllBranches();
				ArrayList<Purchase> linksList = getAll(Purchase.class);

				client.sendToClient(new Message("#complaintsReportsList", branchesList, linksList));
				session.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#RefundReportsRequest")) {
			// movieList.setMoviesList(getAll(Movie.class));
			try {
				session = sessionFactory.openSession();
				ArrayList<SirtyaBranch> branchesList = getAllBranches();
				ArrayList<Purchase> linksList = getAll(Purchase.class);

				client.sendToClient(new Message("#RefundReportsList", branchesList, linksList));
				session.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#TicketsReportsRequest")) {
			session = sessionFactory.openSession();
			branchesList = getAll(SirtyaBranch.class);
			try {
				client.sendToClient(new Message("#SendTicketsReports", branchesList));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.close();
		} else if (msgString.startsWith("#BranchesTicketsReportsRequest")) {
			session = sessionFactory.openSession();
			branchesList = getAll(SirtyaBranch.class);
			if(branchesList == null) {
				System.out.println("aaaaaaaa7");
			}
			try {
				client.sendToClient(new Message("#SendBranchTicketsReports", branchesList));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.close();

		} else if (msgString.startsWith("#DeleteScreening")) {
			deleteScreening((ScreeningsUpdateRequest) ((Message) msg).getObject(), client);
		} else if (msgString.startsWith("#AddScreening")) {
			addScreening((ScreeningsUpdateRequest) ((Message) msg).getObject(), client);
		} else if (msgString.startsWith("#EditScreening")) {
			try {
				editScreening((ScreeningsUpdateRequest) ((Message) msg).getObject(), client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#SaveSeats")) {
			try {
				saveSeats((BookingRequest) ((Message) msg).getObject(), client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#UndoSaveSeats")) {
			try {
				freeSeats((BookingRequest) ((Message) msg).getObject(), client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#FinishOrder")) {
			try {
				finishOrder((FullOrderRequest) ((Message) msg).getObject(), client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#RentingRequest")) {
			try {
				rentMovie((RentRequest) ((Message) msg).getObject(), client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#WorkersRequest")) {
			try {
				session = sessionFactory.openSession();
				ArrayList<Worker> workersList = getAllWorkers();

				client.sendToClient(new Message("#WorkersList", workersList));
				session.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#LoginRequest")) {
			handleLoginRequest((Message) msg, client);
		} else if (msgString.startsWith("#SignUpRequest")) {
			try {
				signUpRequestHandler((FullOrderRequest) ((Message) msg).getObject(), client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#AddRegularMovie")) {
			addRegularMovie((CinemaMovie) ((Message) msg).getObject(), client);
		} else if (msgString.startsWith("#AddComingSoonMovie")) {
			addComingSoonMovie((ComingSoonMovie) ((Message) msg).getObject(), client);
		} else if (msgString.startsWith("#AddOnDemandMovie")) {
			addOnDemandMovie((OnDemandMovie) ((Message) msg).getObject(), client);
		} else if (msgString.startsWith("#DeleteMovieRegular")) {
			DeleteMovieRegular((CinemaMovie) ((Message) msg).getObject(), client);
		} else if (msgString.startsWith("#DeleteMovieDemand")) {
			DeleteMovieDemand((OnDemandMovie) ((Message) msg).getObject(), client);
		} else if (msgString.startsWith("#DeleteMovieComingSoon")) {
			DeleteMovieComingSoon((ComingSoonMovie) ((Message) msg).getObject(), client);
		} else if (msgString.startsWith("#UpdateTav")) {
			UpdateTav((Message) msg, client);
		} else if (msgString.startsWith("#TavSagoalStatus")) {
			sendTavSagoal(client);
		} else if (msgString.startsWith("#getAllMovies")) {
			sendRefreshcatalogevent();
		} else if (msgString.startsWith("#AddPriceRequest")) {
			addPriceRequest((Price) ((Message) msg).getObject(), client);
		} else if (msgString.startsWith("#changePrice")) {
			changePriceRequest((Price) ((Message) msg).getObject(), client);
		} else if (msgString.startsWith("#deletePrice")) {
			deletePriceRequest((Price) ((Message) msg).getObject(), client);
		} else if (msgString.startsWith("#PricesListRequest")) {
			try {
				session = sessionFactory.openSession();
				ArrayList<Price> pricesList = getAll(Price.class);

				client.sendToClient(new Message("#PricesList", pricesList));
				session.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#EditMovieAbstract")) {
			EditMovieAbstract((Movie) ((Message) msg).getObject(), client);
		} else if (msgString.startsWith("#EditMoviePriceAndLink")) {
			EditPriceAndStreamingLink((OnDemandMovie) ((Message) msg).getObject(), client);
		} else if (msgString.startsWith("#SubmitComplaint")) {
			SubmitComplaints(((Message) msg), client);
		} else if (msgString.startsWith("#GetComplaints")) {
			GetComplaints((CasualBuyer) ((Message) msg).getObject(), client);
		} else if (msgString.startsWith("#SubmitResponseForComplaint")) {
			StoreComplaintResponse((Complaint) ((Message) msg).getObject());
		} else if (msgString.startsWith("#SearchForClient")) {
			SearchForClient((Message) msg, client);
		} else if (msgString.startsWith("#CancelOrder")) {
			cancelOrder((Message) msg, client);
		} else if (msgString.startsWith("#ComplaintsList")) {
			try {
				session = sessionFactory.openSession();
				ArrayList<Complaint> temp = getAll(Complaint.class);

				client.sendToClient(new Message("#ComplaintsList", temp));
				session.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.close();
		} else if (msgString.startsWith("#UpdateResponse")) {
			updateResponse(msg, client);
		}
	}

	private void updateResponse(Object msg, ConnectionToClient client) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<Complaint> complaints = getAll(Complaint.class);
		for (Complaint temp : complaints) {
			if (temp.getId() == (int) ((Message) msg).getObject()) {
				temp.setResponse((String) ((Message) msg).getObject2());
				temp.setRepresentetive((CustomerServiceEmployee) ((Message) msg).getObject4());
				temp.setResponseDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy ',' HH:mm")));
				if ((String) ((Message) msg).getObject3() == null || (String) ((Message) msg).getObject3() == "") {
					temp.setRefundValue(Double.parseDouble("0.0"));
				} else {
					temp.setRefundValue(Double.parseDouble((String) ((Message) msg).getObject3()));
				}
				temp.setStatus("Closed");
				String text = "Your Request: \n<" + temp.getSubmissionDate() + ">\n";
				text += temp.getDescription() + "\n\n" + "Representetive Response: \n<";
				text += temp.getResponseDate() + ">\n" + temp.getResponse();
				if (temp.getRefundValue() > 0) {
					text += "\n\nAutomatically Generated Summary:\nYou were found eligible for a refund of value: "
							+ temp.getRefundValue() + " NIS";
					text += "\nThe refund will appear on your credit card balance within 7 business days.";
				}
				text += "\n\n -Dream Palace Cinema";
				SendEmailTLS.SendMailTo(temp.getEmail(), "Response to your request", text);
				session.save(temp);
				session.flush();
				session.getTransaction().commit();
				break;
			}
		}
		try {
			ArrayList<Complaint> temp = getAll(Complaint.class);
			client.sendToClient(new Message("#ComplaintsList", temp));
			session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.close();
	}

	private void StoreComplaintResponse(Complaint object) {
		session = sessionFactory.openSession();
		List<Complaint> complaintList = null;
		try {
			complaintList = getAllComplaints();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Complaint complaint : complaintList) {
			if (complaint.getId() == object.getId()) {
				session.beginTransaction();
				complaint.setResponse(object.getResponse());
				complaint.setRepresentetive(object.getRepresentetive());
				session.save(complaint);
				session.flush();
				session.getTransaction().commit();
				break;
			}
		}
		session.close();
	}

	private void GetComplaints(CasualBuyer casualBuyer, ConnectionToClient client) {
		try {
			ArrayList<Complaint> complaintsList = getAllComplaints();
			if (casualBuyer != null) {
				ArrayList<Complaint> complaintsList1 = new ArrayList<Complaint>();
				for (Complaint complaint : complaintsList) {
					// System.out.print(complaint.getClient().getCustomerId());

					if (complaint.getClient().getCustomerId() == casualBuyer.getCustomerId()) {
						complaintsList1.add(complaint);
					}
				}
				complaintsList = complaintsList1;
			}
			Message msg = new Message("#ShowComplaintsInTable", complaintsList);
			this.sendToAllClients(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void SubmitComplaints(Message msg, ConnectionToClient client) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		SirtyaBranch relBranch = null;
		if ((SirtyaBranch) msg.getObject4() != null) {
			for (SirtyaBranch brnch : getAll(SirtyaBranch.class)) {
				if (brnch.getId() == ((SirtyaBranch) msg.getObject4()).getId()) {
					relBranch = brnch;
					relBranch.setBranchComplaintNum(relBranch.getBranchComplaintNum() + 1);
					
				}
			}
		} else {
			SirtyaBranch.setGeneralComplaintNum(SirtyaBranch.getGeneralComplaintNum() +1);
		}
		int complainerID;
		if (msg.getObject().getClass() == CinemaMember.class) {
			complainerID = ((CinemaMember) msg.getObject()).getId();
			List<CinemaMember> temp = getAll(CinemaMember.class);
			for (CinemaMember member : temp) {
				if (member.getId() == complainerID) {
					Complaint newComplaint = new Complaint(member, (String) msg.getObject3(), (String) msg.getObject2(),
							relBranch);
					
					session.save(newComplaint);
					session.save(member);
					session.flush();
					SendEmailTLS.SendMailTo((String) msg.getObject2(), "Inquiry submission", "Your request ["
							+ newComplaint.getId()
							+ "] has been received and is being reviewed by our support team. We'll contact you as soon as we have an answer for you.\n\n - Dream Palace Cinema");
					try {
						client.sendToClient(new Message("#MemberLogIn5", newComplaint.getClient()));

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		} else {
			complainerID = Integer.parseInt((String) msg.getObject());
			Complaint newComplaint = null;
			List<CasualBuyer> temp = getAll(CasualBuyer.class);
			for (CasualBuyer buyer : temp) {
				if (buyer.getCustomerId() == complainerID) {
					newComplaint = new Complaint(buyer, (String) msg.getObject3(), (String) msg.getObject2(),
							relBranch);
					session.save(newComplaint);
					session.save(buyer);
					session.flush();
				}
			}
			if (newComplaint == null) {
				String firstName = ((String) msg.getObject3()).substring(0, ((String) msg.getObject3()).indexOf(" "));
				String lastName = ((String) msg.getObject3()).substring(((String) msg.getObject3()).indexOf(" ") + 1,
						((String) msg.getObject3()).indexOf(":"));
				CasualBuyer person = new CasualBuyer(firstName, lastName, Integer.parseInt((String) msg.getObject()), 0,
						(String) msg.getObject2());
				newComplaint = new Complaint(person, (String) msg.getObject3(), (String) msg.getObject2(), relBranch);
				session.save(newComplaint);
				session.save(person);
				session.flush();
			}
			try {
				
				client.sendToClient(new Message("#ComplaintSubmitted", newComplaint.getClient()));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SendEmailTLS.SendMailTo((String) msg.getObject2(), "Inquiry submission", "Your request ["
					+ newComplaint.getId()
					+ "] has been received and is being reviewed by our support team. We'll contact you as soon as we have an answer for you.");
		}

		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	private void EditPriceAndStreamingLink(OnDemandMovie object, ConnectionToClient client) {
		session = sessionFactory.openSession();
		List<Movie> movieList = null;
		try {

			movieList = getAllMovies();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Movie movie : movieList) {
			if (movie.getId() == object.getId()) {
				session.beginTransaction();
				((OnDemandMovie) movie).setCost(object.getCost());
				((OnDemandMovie) movie).setStreamingLink(object.getStreamingLink());
				session.save(movie);
				session.flush();
				session.getTransaction().commit();
				break;
			}
		}
		session.close();

	}

	private void EditMovieAbstract(Movie object, ConnectionToClient client) {
		session = sessionFactory.openSession();
		List<Movie> movieList = null;
		try {
			movieList = getAllMovies();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Movie movie : movieList) {
			if (movie.getId() == object.getId()) {
				session.beginTransaction();
				movie.setMovieTitle(object.getMovieTitle());
				movie.setMovieTitleHeb(object.getMovieTitleHeb());
				movie.setMovieProducer(object.getMovieProducer());
				movie.setStarringActors(object.getStarringActors());
				movie.setMovieDescription(object.getMovieDescription());
				movie.getImage().setImgURL(object.getImage().getImgURL());
				session.save(movie);
				session.flush();
				session.getTransaction().commit();
				break;
			}
		}
		session.close();

	}

	private void sendRefreshcatalogevent() {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<CinemaMovie> cinMovieList = getAll(CinemaMovie.class);
			List<ComingSoonMovie> soonMovieList = getAll(ComingSoonMovie.class);
			List<OnDemandMovie> onDemandList = getAll(OnDemandMovie.class);
			Message msg = new Message("#RefreshCatalog", cinMovieList, onDemandList, soonMovieList);
			this.sendToAllClients(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
	}

	private void deletePriceRequest(Price request, ConnectionToClient client) {
		session = sessionFactory.openSession();
		try {
			priceList = getAll(Price.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.beginTransaction();
		Price temp = new Price();
		for (Price price : priceList) {
			if (price.getID() == request.getPriceId()) {
				temp = price;
				break;
			}

		}
		String temp2 = ("Dear Mr/Mrs "+request.getWorkerName()+"\nSadly, your request has been denied."
				+"\nRequest Details:"+"\nMovie name: "+request.getMovieName()+
				"\nRequested to change price from: " + request.getOldPrice() + " to: " + request.getNewPrice()
				+ "\nSubmission Time: " + request.getRequestDate() + " " + request.getRequestTime());
		List<Worker> workersList = getAll(Worker.class);
		System.out.println(request.getWorkerID());
		for(Worker worker : workersList) {
			if(worker.getId() == request.getWorkerID()) {
				SendEmailTLS.SendMailTo(worker.getWorkerEmail(), "Price Change Request Response", temp2);
				break;
			}
		}
		
		session.delete(temp);
		session.flush();
		session.getTransaction().commit();
		priceList = getAll(Price.class);
		try {
			client.sendToClient(new Message("#PricesList", priceList));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.close();
	}

	private void changePriceRequest(Price request, ConnectionToClient client) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		moviesList = getAll(CinemaMovie.class);
		for (CinemaMovie movie : moviesList) {
			if (movie.getId() == request.getMovieID()) {
				movie.setTicketCost(request.getNewPrice());
				session.save(movie);
				session.flush();
				try {
					priceList = getAll(Price.class);
					Price temp = new Price();
					for (Price price : priceList) {
						if (price.getID() == request.getPriceId()) {
							temp = price;
							break;
						}

					}
					String temp2 = ("Dear Mr/Mrs "+request.getWorkerName()+"\nYour request has been accepted."
							+"\nRequest Details:"+"\nMovie name: "+request.getMovieName()+
							"\nRequested to change price from: " + request.getOldPrice() + " to: " + request.getNewPrice()
							+ "\nSubmission Time: " + request.getRequestDate() + " " + request.getRequestTime());
					List<Worker> workersList = getAll(Worker.class);
					System.out.println(request.getWorkerID());
					for(Worker worker : workersList) {
						if(worker.getId() == request.getWorkerID()) {
							SendEmailTLS.SendMailTo(worker.getWorkerEmail(), "Price Change Request Response", temp2);
							break;
						}
					}
					session.delete(temp);
					session.flush();
					session.getTransaction().commit();
					priceList = getAll(Price.class);
					client.sendToClient(new Message("#RefreshChangePrice", priceList));
					sendRefreshcatalogevent();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		session.close();
	}

	private void addPriceRequest(Price object, ConnectionToClient client) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<GeneralManager> m = getAll(GeneralManager.class);
		String mail = m.get(0).getWorkerEmail();
		Price request = object;
		String temp2 = ("Dear General Manager, you have a new price change request waiting for you response,"
				+ "\nhave a nice day");
		SendEmailTLS.SendMailTo(mail, "New Price Request", temp2);
		session.save(request);
		session.flush();
		session.getTransaction().commit();
		priceList = getAll(Price.class);
		Message msg = new Message("#RefreshPriceRequest", priceList);
		this.sendToAllClients(msg);
		session.close();

	}

	private void sendTavSagoal(ConnectionToClient client) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		ArrayList<TavSagoal> temp = getAll(TavSagoal.class);
		// TavSagoal temp2 = temp.get(0);

		try {
			client.sendToClient(new Message("#TavSagoalStatus", temp.get(0)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.close();
	}

	private void UpdateTav(Message msg, ConnectionToClient client) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		ArrayList<TavSagoal> temp = getAll(TavSagoal.class);
		TavSagoal temp2 = temp.get(0);
		TavSagoal temp3 = (TavSagoal) msg.getObject();
		temp2.setEffective(temp3.isEffective());
		temp2.setFromDate(temp3.getFromDate());
		temp2.setToDate(temp3.getToDate());
		temp2.setY(temp3.getY());
		session.save(temp2);
		session.flush();
		String str1 = temp2.getFromDate();
		String str2 = temp2.getToDate();
		if (str1 != "" && str2 != "") {
			ArrayList<Screening> screeningTemp = getAll(Screening.class);
			ScreeningTimeComparator util = new ScreeningTimeComparator();
			for (Screening screening : screeningTemp) {
				if (util.compare(screening, str1) != -1 && util.compare(screening, str2) != 1) {
					ScreeningCancelationEmail(screening);
//					screening.getMovie().getScreenings().remove(screening);
//					screening.getBranch().getScreenings().remove(screening);
//					screening.getHall().getScreening().remove(screening);
//					screening.setMovie(null);
//					screening.setBranch(null);
//					screening.setHall(null);
					session.save(screening);
					session.delete(screening);
					session.flush();
				}
			}
		}
		session.getTransaction().commit();
		try {
			client.sendToClient(new Message("#TavSagoalUpdated", temp2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.close();
	}

	private void ScreeningCancelationEmail(Screening screening) {
		List<Ticket> list = screening.getTickets();
		System.out.println(list.size());
		if (list.size() == 0)
			return;
		for (Ticket ticket : list) {
			session.save(screening);
			String temp = "Dear Mr/Ms " + ticket.getCustomer().getFirstName() + " " + ticket.getCustomer().getLastName()
					+ ",\n\nWe're Sorry to inform you that due to the Tav-Sagoal restrictions the screening of the movie \""
					+ screening.getMovie().getMovieTitle();
			temp += "\" on " + screening.getScreeningDate() + " at " + screening.getScreeningTime()
					+ " has been canceled.\n";
			temp += "That includes the cancelation of your reservation over ticket ID " + ticket.getId() + ", Seat ID "
					+ ticket.getSeat() + ".\n";
			if (ticket.getCost() > 0 || !ticket.getStatus().contains("Canceled")) {
				String temp2 = String.valueOf(ticket.getCreditCardNum());
				temp2 = temp2.substring(temp2.length() - 4);
				temp += "A refund to your credit card, *" + temp2 + ", will be made.\n\n -Dream Palace Cinema.";
				ticket.setRefunded(true);
				ticket.setStatus("CanceledBySystem");
				ticket.setScreening(null);
				if (screening.getBranch()!= null) {
					screening.getBranch().setTotalPurpleRefund(ticket.getCost());
					System.out.println(screening.getBranch().getId());
					session.update(screening.getBranch());
				}
				else
					System.out.println("nefse amoot");
				//screening.getTickets().remove(ticket);
				session.save(ticket);
				//session.save(screening);
				session.flush();
			}
			SendEmailTLS.SendMailTo(ticket.getCustomer().getElectronicMail(), "Screening Cancelation Refund", temp);
		}
		branchesList = getAll(SirtyaBranch.class);
		List<Purchase> linksList = getAll(Purchase.class);
		this.sendToAllClients(new Message("#RefreshRefundReportsList", branchesList, linksList));
		return;
	}

	private void DeleteMovieRegular(CinemaMovie object, ConnectionToClient client) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		CinemaMovie request = object;
		ArrayList<CinemaMovie> movieList = getAll(CinemaMovie.class);
		ArrayList<Ticket> tickets = getAll(Ticket.class);
		CinemaMovie temp = null;
		for (CinemaMovie movie : movieList) {
			if (movie.getId() == request.getId()) {
				for (Ticket ticket : tickets) {
					session.delete(ticket);
				}
				temp = movie;
				break;
			}
		}
		for (Screening screening : temp.getScreenings()) {
			session.delete(screening);
		}
		session.delete(temp);
		session.flush();
		session.getTransaction().commit();
		try {
			client.sendToClient(new Message("#RefreshMovieDelete"));
			sendRefreshcatalogevent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.close();
	}

	private void DeleteMovieDemand(OnDemandMovie object, ConnectionToClient client) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		OnDemandMovie request = object;
		ArrayList<OnDemandMovie> movieList = getAll(OnDemandMovie.class);
		ArrayList<Rent> rents = getAll(Rent.class);
		OnDemandMovie temp = null;
		for (OnDemandMovie movie : movieList) {
			if (movie.getId() == request.getId()) {
				for (Rent rent : rents) {
					if (rent.getMovie().getId() == movie.getId()) {
						session.delete(rent);
					}
				}
				temp = movie;
				break;
			}
		}
		session.delete(temp);
		session.flush();
		session.getTransaction().commit();
		try {
			client.sendToClient(new Message("#RefreshMovieDelete"));
			sendRefreshcatalogevent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.close();
	}

	private void DeleteMovieComingSoon(ComingSoonMovie object, ConnectionToClient client) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		ComingSoonMovie request = object;
		ArrayList<ComingSoonMovie> movieList = getAll(ComingSoonMovie.class);
		ComingSoonMovie temp = null;
		for (ComingSoonMovie movie : movieList) {
			if (movie.getId() == request.getId()) {
				temp = movie;
				break;
			}
		}
		session.delete(temp);
		session.flush();
		session.getTransaction().commit();
		try {
			client.sendToClient(new Message("#RefreshMovieDelete"));
			sendRefreshcatalogevent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.close();
	}

	private void addOnDemandMovie(OnDemandMovie object, ConnectionToClient client) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		OnDemandMovie request = object;
		session.save(request);
		session.flush();
		session.getTransaction().commit();
		sendRefreshcatalogevent();
		session.close();

	}

	private void addComingSoonMovie(ComingSoonMovie object, ConnectionToClient client) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		ComingSoonMovie request = object;
		session.save(request);
		session.flush();
		session.getTransaction().commit();
		sendRefreshcatalogevent();
		session.close();

	}

	private void addRegularMovie(CinemaMovie object, ConnectionToClient client) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		CinemaMovie request = object;
		session.save(request);
		session.flush();
		session.getTransaction().commit();
		sendRefreshcatalogevent();
		session.close();
	}

	private void logOutRequest(Message msg, ConnectionToClient client) {
		LogInRequest object = (LogInRequest) msg.getObject();
		session = sessionFactory.openSession();
		String username = object.getUsername();
		try {
			ArrayList<Worker> workersList = getAllWorkers();
			ArrayList<CinemaMember> membersList = getAll(CinemaMember.class);
			if (!workersList.isEmpty()) {
				for (Worker worker : workersList) {
					if (worker.getWokerUsername().equals(username)) {
						worker.setConnected(false);
						session.beginTransaction();
						session.update(worker);
						session.getTransaction().commit();
						return;
					}
				}
			}
			if (!membersList.isEmpty()) {
				for (CinemaMember member : membersList) {
					if (member.getUsername().equals(username)) {
						member.setConnected(false);
						session.beginTransaction();
						session.update(member);
						session.getTransaction().commit();
						return;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void handleLoginRequest(Message msg, ConnectionToClient client) {
		LogInRequest object = (LogInRequest) msg.getObject();
		session = sessionFactory.openSession();
		String username = object.getUsername();
		String password = object.getPassword();
		try {
			ArrayList<Worker> workersList = getAllWorkers();
			ArrayList<CinemaMember> membersList = getAll(CinemaMember.class);
			if (!workersList.isEmpty()) {
				for (Worker worker : workersList) {
					if (worker.getWokerUsername().equals(username)) {
						if (worker.getWorkerPassword().equals(password)) {
							if (!worker.isConnected()) {
								worker.setConnected(true);
								session.beginTransaction();
								session.update(worker);
								session.getTransaction().commit();
								client.sendToClient(new Message("#WorkerLogIn", worker));
								return;
							} else {
								Warning new_warning = new Warning("Dear Worker,\nyou are already logged in");
								client.sendToClient(new Message("#Warning", new_warning));
								return;
							}
						}
					}
				}
			}
			if (!membersList.isEmpty()) {
				for (CinemaMember member : membersList) {
					if (member.getUsername().equals(username)) {
						if (member.getPassword().equals(password)) {
							if (!member.isConnected()) {
								member.setConnected(true);
								session.beginTransaction();
								session.update(member);
								session.getTransaction().commit();
								if (msg.toString().equals("#LoginRequestHistory")) {
									System.out.println("aaa7");
									Hibernate.initialize(member.getPurchases());
									List<Purchase> temp = member.getPurchases();
									client.sendToClient(new Message("#MemberLogIn4", member, temp));
								} else if (msg.toString().equals("#LoginRequestWhileBooking")) {
									client.sendToClient(new Message("#MemberLogIn2", member));
								} else if (msg.toString().equals("#LoginRequestWhileRenting")) {
									client.sendToClient(new Message("#MemberLogIn3", member));
								} else if (msg.toString().equals("#LoginRequestContactUs")) {
									client.sendToClient(new Message("#MemberLogIn5", member));
								} else {
									client.sendToClient(new Message("#MemberLogIn", member));
								}
								return;
							} else {
								if (msg.toString().equals("#LoginRequestHistory")) {
									System.out.println("aaa7");
									Hibernate.initialize(member.getPurchases());
									List<Purchase> temp = member.getPurchases();
									client.sendToClient(new Message("#MemberLogIn4", member, temp));
								} else if (msg.toString().equals("#LoginRequestWhileBooking")) {
									client.sendToClient(new Message("#MemberLogIn2", member));
								} else if (msg.toString().equals("#LoginRequestWhileRenting")) {
									client.sendToClient(new Message("#MemberLogIn3", member));
								} else if (msg.toString().equals("#LoginRequestContactUs")) {
									client.sendToClient(new Message("#MemberLogIn5", member));
								} else if (msg.toString().equals("#LoginRequest")) {
									client.sendToClient(new Message("#MemberLogIn", member));
								}
								else {
								Warning new_warning = new Warning("Dear Customer,\nyou are already logged in");
								client.sendToClient(new Message("#Warning", new_warning));
								return;
								}
							}
							return;
						}
					}
				}
			}
			if (msg.toString().equals("#LoginRequestWhileBooking")) {
				client.sendToClient(new Message("#LogInFailed2", object));
			} else if (msg.toString().equals("#LoginRequestWhileRenting")) {
				client.sendToClient(new Message("#LogInFailed3", object));
			} else if (msg.toString().equals("#LoginRequestContactUs")) {
				client.sendToClient(new Message("#LogInFailed5", object));
			} else if (msg.toString().equals("#LoginRequestHistory")) {
				client.sendToClient(new Message("#LogInFailed4", object));
			} else {
				client.sendToClient(new Message("#LogInFailed", object));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void deleteScreening(ScreeningsUpdateRequest request, ConnectionToClient client) {
		session = sessionFactory.openSession();
		try {
			screeningsList = getAllScreenings();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.beginTransaction();
		Screening temp = new Screening();
		for (Screening scrn : screeningsList) {
			if (scrn.getId() == request.getScrnID()) {
				temp = scrn;
				break;
			}
		}
		temp.getMovie().getScreenings().remove(temp);
		temp.getBranch().getScreenings().remove(temp);
		ScreeningCancelationEmail(temp);
		session.delete(temp);
		session.flush();
		session.getTransaction().commit();
		try {
			moviesList = getAll(CinemaMovie.class);
			for (Movie movie : moviesList) {
				if (movie.getId() == request.getMovieID()) {
					client.sendToClient(new Message("#RefreshDelete", movie));
					sendRefreshcatalogevent();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.close();
	}

	private void editScreening(ScreeningsUpdateRequest request, ConnectionToClient client) throws Exception {
		session = sessionFactory.openSession();
		List<Screening> screeningsList = getAllScreenings();
		for (Screening scrn : screeningsList) {
			if (scrn.getId() == ((ScreeningsUpdateRequest) request).getScrnID()) {
				session.beginTransaction();
				scrn.setScreeningDate(((ScreeningsUpdateRequest) request).getDate());
				scrn.setScreeningTime(((ScreeningsUpdateRequest) request).getTime());
				session.save(scrn);
				session.flush();
				session.getTransaction().commit();
				try {
					moviesList = getAll(CinemaMovie.class);
					for (Movie movie : moviesList) {
						if (movie.getId() == request.getMovieID()) {
							client.sendToClient(new Message("#RefreshEdit", movie));
							sendRefreshcatalogevent();
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		session.close();
	}

	private void saveSeats(BookingRequest request, ConnectionToClient client) throws Exception {
		session = sessionFactory.openSession();
		List<Screening> screeningsList = getAllScreenings();
		for (Screening scrn : screeningsList) {
			if (scrn.getId() == ((BookingRequest) request).getScreening().getId()) {
				session.beginTransaction();
				for (int i = 0; i < request.getArrSize(); i++) {
					scrn.setTakenSeatAt(request.getSeats()[i]);
				}
				scrn.setAvailableSeats(scrn.getAvailableSeats() - request.getArrSize());
				scrn.setSoldSeats(scrn.getSoldSeats() + request.getArrSize());
				session.save(scrn);
				session.flush();
				session.getTransaction().commit();
				this.sendToAllClients(new Message("#RefreshSeatsSaved", scrn));
				try {
					client.sendToClient(new Message("#SeatsSaved", request));
					/*
					 * screeningsList = getAll(Screening.class); for (Screening screening :
					 * screeningsList) { if (screening.getId()==request.getScreening().getId()) {
					 * request.setScreening(screening); client.sendToClient(new
					 * Message("#SeatsBooked",request)); } }
					 */
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		session.close();
	}

	private void freeSeats(BookingRequest request, ConnectionToClient client) throws Exception {
		session = sessionFactory.openSession();
		List<Screening> screeningsList = getAllScreenings();
		for (Screening scrn : screeningsList) {
			if (scrn.getId() == ((BookingRequest) request).getScreening().getId()) {
				session.beginTransaction();
				for (int i = 0; i < request.getArrSize(); i++) {
					scrn.setAvailableSeatAt(request.getSeats()[i]);
				}
				scrn.setAvailableSeats(scrn.getAvailableSeats() + request.getArrSize());
				scrn.setSoldSeats(scrn.getSoldSeats() - request.getArrSize());
				session.save(scrn);
				session.flush();
				session.getTransaction().commit();
				this.sendToAllClients(new Message("#RefreshfreeSeats", scrn));
				try {
					client.sendToClient(new Message("#SeatsFreed", request));
					/*
					 * screeningsList = getAll(Screening.class); for (Screening screening :
					 * screeningsList) { if (screening.getId()==request.getScreening().getId()) {
					 * request.setScreening(screening); client.sendToClient(new
					 * Message("#SeatsBooked",request)); } }
					 */
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		session.close();
	}

	private void signUpRequestHandler(FullOrderRequest request, ConnectionToClient client) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		CinemaMember newCus = new CinemaMember(request.getFirstName(), request.getLastName(), request.getCustomerID(),
				request.getCardNum(), request.getEmail(), request.getUsername(), request.getPassword());
		newCus.setConnected(true);
		newCus.setTicketsCredit(0);
		session.save(newCus);
		session.flush();
		session.getTransaction().commit();
		String temp = ("Dear Mr/Ms " + request.getFirstName() +" "+ request.getLastName()+" ,"+
				"\nThank you for registering to Dream Palace Cinema."
				+"\nYour username is: " + request.getUsername()+
				"\nYour password is: "+request.getPassword() +
				"\nWe hope that you would have a great time! \nThank you!");
		SendEmailTLS.SendMailTo(request.getEmail(), "Membership Details", temp);
		client.sendToClient(new Message("#MemberLogIn", newCus));
	}

	private void finishOrder(FullOrderRequest request, ConnectionToClient client) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy ',' HH:mm:ss");
		String transactionTime = formatter.format(LocalDateTime.now());
		request.setTransactionTime(transactionTime);
		session = sessionFactory.openSession();
		session.beginTransaction();
		branchesList = getAll(SirtyaBranch.class);
		moviesList = getAll(CinemaMovie.class);
		List<Screening> tempList = getAll(Screening.class);
		for (Screening tempScrn : tempList) {
			if (tempScrn.getId() == request.getRequest().getScreening().getId()) {
				request.getRequest().setScreening(tempScrn);
			}
		}
		if (request.isNewCustomerFlag() && !request.isSignupFlag()) {
			List<CasualBuyer> tempClientsList = getAll(CasualBuyer.class);
			CasualBuyer newCus = null;
			for (CasualBuyer buyer : tempClientsList) {
				if (buyer.getCustomerId() == request.getCustomerID() && buyer.getCreditNum() == request.getCardNum()) {
					newCus = buyer;
				}
			}
			if (newCus == null) {
				newCus = new CasualBuyer(request.getFirstName(), request.getLastName(), request.getCustomerID(),
						request.getCardNum(), request.getEmail());
			}
			session.save(newCus);
			session.flush();
			BookingRequest temp = request.getRequest();
			for (int i = 0; i < temp.getArrSize(); i++) {
				Ticket newTicket = new Ticket(temp.getScreening(), newCus, temp.getSeatIds()[i], temp.getSeats()[i],
						temp.getCost(), request.getCardNum(), transactionTime);
				session.save(temp.getScreening());
				session.save(newTicket);
				session.flush();
			}
			for (CinemaMovie movie : moviesList) {
				if (temp.getScreening().getMovie().getId() == movie.getId()) {
					movie.setTicketsSold(temp.getArrSize() + movie.getTicketsSold());
					movie.calcMovieIncome();
					session.save(movie);
					session.flush();
					for (SirtyaBranch branch : branchesList) {
						if (temp.getScreening().getBranch().getId() == branch.getId()) {
							branch.setTotalTicketsSold(branch.getTotalTicketsSold() + temp.getArrSize());
							branch.setTotalTicketsIncome(
									branch.getTotalTicketsIncome() + temp.getArrSize() * movie.getTicketCost());
							session.save(branch);
							session.flush();
						}

					}
				}
			}

			session.save(newCus);
			session.flush();
			session.getTransaction().commit();
			try {
				client.sendToClient(new Message("#BookedNonMember", request));
				branchesList = getAll(SirtyaBranch.class);
				List<Purchase> linksList = getAll(Purchase.class);
				Message msg = new Message("#RefreshTicketsSellings", branchesList, linksList);
				this.sendToAllClients(msg);
				SendEmail(request);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (request.isNewCustomerFlag() && request.isSignupFlag()) {
			CinemaMember newCus = new CinemaMember(request.getFirstName(), request.getLastName(),
					request.getCustomerID(), request.getCardNum(), request.getEmail(), request.getUsername(),
					request.getPassword());
			session.save(newCus);
			session.flush();
			if (request.isBuyPack()) {
				newCus.setTicketsCredit(20);
				TabPurchase newTab = new TabPurchase(request.getCardNum(), newCus, transactionTime);
				TabPurchase.tabNum = TabPurchase.tabNum + 1;
				TabPurchase.tabTotalIncome = TabPurchase.tabTotalIncome + 600;
				session.save(newTab);
				session.flush();
			}
			BookingRequest temp = request.getRequest();
			for (int i = 0; i < temp.getArrSize(); i++) {
				if (request.getUsePack() > 0 && newCus.getTicketsCredit() > 0) {
					Ticket newTicket = new Ticket(temp.getScreening(), newCus, temp.getSeatIds()[i], temp.getSeats()[i],
							0, request.getCardNum(), transactionTime);
					request.setUsePack(request.getUsePack() - 1);
					newCus.setTicketsCredit(newCus.getTicketsCredit() - 1);
					newCus.setTicketsCredit(newCus.getTicketsCredit() - 1);
					for (SirtyaBranch branch : branchesList) {
						if (temp.getScreening().getBranch().getId() == branch.getId()) {
							branch.setTotalTabTicketsSold(branch.getTotalTabTicketsSold() + 1);
							session.save(branch);
							session.flush();
						}

					}
					session.save(newTicket);
					session.flush();
				} else {
					Ticket newTicket = new Ticket(temp.getScreening(), newCus, temp.getSeatIds()[i], temp.getSeats()[i],
							temp.getCost(), request.getCardNum(), transactionTime);
					for (CinemaMovie movie : moviesList) {
						if (temp.getScreening().getMovie().getId() == movie.getId()) {
							movie.setTicketsSold(movie.getTicketsSold() + 1);
							movie.calcMovieIncome();
							session.save(movie);
							session.flush();
							for (SirtyaBranch branch : branchesList) {
								if (temp.getScreening().getBranch().getId() == branch.getId()) {
									branch.setTotalTicketsSold(1 + branch.getTotalTicketsSold());
									branch.setTotalTicketsIncome(
											branch.getTotalTicketsIncome() + movie.getTicketCost());
									session.save(branch);
									session.flush();
								}

							}
						}
					}
					session.save(temp.getScreening());
					session.save(newTicket);
					session.flush();
				}
			}
			session.save(newCus);
			session.flush();
			session.getTransaction().commit();
			try {
				client.sendToClient(new Message("#BookedMember", request, newCus));
				branchesList = getAll(SirtyaBranch.class);
				List<Purchase> linksList = getAll(Purchase.class);
				Message msg = new Message("#RefreshTicketsSellings", branchesList, linksList);
				this.sendToAllClients(msg);
				SendEmail(request);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			moviesList = getAll(CinemaMovie.class);
			branchesList = getAll(SirtyaBranch.class);
			List<CinemaMember> membersList = getAll(CinemaMember.class);
			if (!membersList.isEmpty()) {
				for (CinemaMember member : membersList) {
					if (member.getUsername().equals(request.getUsername())) {
						if (member.getPassword().equals(request.getPassword())) {
							if (request.isBuyPack()) {
								member.setTicketsCredit(member.getTicketsCredit() + 20);
								TabPurchase newTab = new TabPurchase(request.getCardNum(), member, transactionTime);
								TabPurchase.tabNum = TabPurchase.tabNum + 1;
								TabPurchase.tabTotalIncome = TabPurchase.tabTotalIncome + 600;
								session.save(newTab);
								session.flush();
							}
							BookingRequest temp = request.getRequest();
							for (int i = 0; i < temp.getArrSize(); i++) {
								if (request.getUsePack() > 0 && member.getTicketsCredit() > 0) {
									Ticket newTicket = new Ticket(temp.getScreening(), member, temp.getSeatIds()[i],
											temp.getSeats()[i], 0, request.getCardNum(), transactionTime);
									request.setUsePack(request.getUsePack() - 1);
									member.setTicketsCredit(member.getTicketsCredit() - 1);
									for (SirtyaBranch branch : branchesList) {
										if (temp.getScreening().getBranch().getId() == branch.getId()) {
											branch.setTotalTabTicketsSold(branch.getTotalTabTicketsSold() + 1);
											session.save(branch);
											session.flush();
										}

									}
									session.save(temp.getScreening());
									session.save(newTicket);
									session.flush();
								} else {
									Ticket newTicket = new Ticket(temp.getScreening(), member, temp.getSeatIds()[i],
											temp.getSeats()[i], temp.getCost(), request.getCardNum(), transactionTime);
									for (CinemaMovie movie : moviesList) {
										if (temp.getScreening().getMovie().getId() == movie.getId()) {
											movie.setTicketsSold(movie.getTicketsSold() + 1);
											movie.calcMovieIncome();
											session.save(movie);
											session.flush();
											for (SirtyaBranch branch : branchesList) {
												if (temp.getScreening().getBranch().getId() == branch.getId()) {
													branch.setTotalTicketsSold(1 + branch.getTotalTicketsSold());
													branch.setTotalTicketsIncome(
															branch.getTotalTicketsIncome() + movie.getTicketCost());
													session.save(branch);
													session.flush();
												}

											}
										}
									}
									session.save(temp.getScreening());
									session.save(newTicket);
									session.flush();
								}
							}
							session.save(member);
							session.flush();
							session.getTransaction().commit();
							client.sendToClient(new Message("#BookedMember", request, member));
							branchesList = getAll(SirtyaBranch.class);
							List<Purchase> linksList = getAll(Purchase.class);
							Message msg = new Message("#RefreshTicketsSellings", branchesList, linksList);
							this.sendToAllClients(msg);
							SendEmail(request);
							return;
						}
					}
				}
			}
		}
	}

	private void SendEmail(FullOrderRequest request) {
		BookingRequest request2 = request.getRequest();
		String temp = ("Mr/Mrs " + request.getFirstName() + " " + request.getLastName() + "\n" + "Customer ID: "
				+ request.getCustomerID() + "\nE-mail: " + request.getEmail() + "\nMovie: "
				+ request2.getScreening().getMovie().getMovieTitle() + " - "
				+ request2.getScreening().getMovie().getMovieTitleHeb() + "\nBranch: "
				+ request2.getScreening().getBranch().getAddress() + "\nScreening Time: "
				+ request2.getScreening().getScreeningDate() + " , " + request2.getScreening().getScreeningTime()
				+ "\nNumber Of Seats: " + request2.getArrSize() + "\nSeats IDs: ");
		for (int i = 0; i < request2.getArrSize(); i++) {
			temp += request2.getSeatIds()[i] + " ";
		}
		temp += ("\n" + request.getCheck() + "\nTransactionTime: " + request.getTransactionTime());

		SendEmailTLS.SendMailTo(request.getEmail(), "Tickets Order", temp);
	}

	private void SendEmail1(RentRequest request) {
		String temp = ("Mr/Mrs " + request.getFirstName() + " " + request.getLastName() + "\n" + "Customer ID: "
				+ request.getCustomerID() + "\nE-mail: " + request.getEmail() + "\nMovie: "
				+ request.getMovie().getMovieTitle() + " - " + request.getMovie().getMovieTitleHeb());
		temp += ("\nTotal Cost: " + request.getMovie().getCost() + " NIS\nTransaction time:"
				+ request.getTransactionTime() + "\n Start:"
				+ request.getActivationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy ',' HH:mm"))
				+ "\n\nA link will be sent to you upon activation time.\n"
				+ "We ask of you to be patient until then, Enjoy!");

		SendEmailTLS.SendMailTo(request.getEmail(), "Receipt for Your Payment", temp);
	}

	private void rentMovie(RentRequest request, ConnectionToClient client) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm:ss");
		String transactionTime = formatter.format(LocalDateTime.now());
		request.setTransactionTime(transactionTime);
		if (request.isNewCustomerFlag() && !request.isSignupFlag()) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<CasualBuyer> tempClientsList = getAll(CasualBuyer.class);
			CasualBuyer newCus = null;
			for (CasualBuyer buyer : tempClientsList) {
				if (buyer.getCustomerId() == request.getCustomerID() && buyer.getCreditNum() == request.getCardNum()) {
					newCus = buyer;
				}
			}
			if (newCus == null) {
				newCus = new CasualBuyer(request.getFirstName(), request.getLastName(), request.getCustomerID(),
						request.getCardNum(), request.getEmail());
			}
			session.save(newCus);
			session.flush();
			Rent newRent = new Rent(newCus, request.getMovie().getCost(), request.getMovie(),
					request.getMovie().getStreamingLink(), request.getCardNum(), transactionTime,
					request.getActivationDate());
			session.save(newRent);
			session.flush();
			session.getTransaction().commit();
			try {
				client.sendToClient(new Message("#RentedNonMember", request));
				SendEmail1(request);
				branchesList = getAll(SirtyaBranch.class);
				List<Purchase> linksList = getAll(Purchase.class);
				Message msg = new Message("#RefreshRentSellings", branchesList, linksList);
				this.sendToAllClients(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (request.isNewCustomerFlag() && request.isSignupFlag()) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			CinemaMember newCus = new CinemaMember(request.getFirstName(), request.getLastName(),
					request.getCustomerID(), request.getCardNum(), request.getEmail(), request.getUsername(),
					request.getPassword());
			session.save(newCus);
			session.flush();
			Rent newRent = new Rent(newCus, request.getMovie().getCost(), request.getMovie(),
					request.getMovie().getStreamingLink(), request.getCardNum(), transactionTime,
					request.getActivationDate());
			session.save(newRent);
			session.flush();
			session.getTransaction().commit();
			try {
				client.sendToClient(new Message("#RentedMember", request, newCus));
				branchesList = getAll(SirtyaBranch.class);
				List<Purchase> linksList = getAll(Purchase.class);
				Message msg = new Message("#RefreshRentSellings", branchesList, linksList);
				this.sendToAllClients(msg);
				SendEmail1(request);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<CinemaMember> membersList = getAll(CinemaMember.class);
			if (!membersList.isEmpty()) {
				for (CinemaMember member : membersList) {
					if (member.getUsername().equals(request.getUsername())) {
						if (member.getPassword().equals(request.getPassword())) {
							Rent newRent = new Rent(member, request.getMovie().getCost(), request.getMovie(),
									request.getMovie().getStreamingLink(), request.getCardNum(), transactionTime,
									request.getActivationDate());
							session.save(newRent);
							session.flush();
							session.getTransaction().commit();
							client.sendToClient(new Message("#RentedMember", request, member));
							SendEmail1(request);
							branchesList = getAll(SirtyaBranch.class);
							List<Purchase> linksList = getAll(Purchase.class);
							Message msg = new Message("#RefreshRentSellings", branchesList, linksList);
							this.sendToAllClients(msg);
							return;
						}
					}
				}
			}
		}
	}

	private void addScreening(ScreeningsUpdateRequest request, ConnectionToClient client) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("inside add screening");
		/*List<Hall> searchHall = getAll(Hall.class);
		List<SirtyaBranch> searchBranch = getAll(SirtyaBranch.class);
		List<CinemaMovie> searchMovie = getAll(CinemaMovie.class);
		for (Hall tempHall : searchHall) {
			if (tempHall.getId() == request.getHall().getId())
				request.setHall(tempHall);
		}
		for (SirtyaBranch tempBranch : searchBranch) {
			if (tempBranch.getId() == request.getBranch().getId())
				request.setBranch(tempBranch);
		}
		for (CinemaMovie tempMovie : searchMovie) {
			if (tempMovie.getId() == request.getMovie().getId())
				request.setMovie(tempMovie);
		}*/
		List<TavSagoal> tavs = getAll(TavSagoal.class);
		TavSagoal tav = tavs.get(0);
		if (tav.isEffective()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate start = LocalDate.parse(tav.getFromDate() , formatter);
			LocalDate end = LocalDate.parse(tav.getToDate() , formatter);
			LocalDate requestedDate = LocalDate.parse(request.getDate() , formatter);
			System.out.println(start);
			System.out.println(end);
			System.out.println(requestedDate);
			if (requestedDate.isAfter(start.minusDays(1)) && requestedDate.isBefore(end.plusDays(1))) {
				System.out.println("restricting add screening");
				Warning new_warning = new Warning("Due to Tav-Sagoal restriction you can't add screenings to this date.");
				try {
					client.sendToClient(new Message("#Warning", new_warning));
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
		}
		List<SirtyaBranch> searchBranch = getAll(SirtyaBranch.class);
		List<CinemaMovie> searchMovie = getAll(CinemaMovie.class);
		for (SirtyaBranch tempBranch : searchBranch) {
			if (tempBranch.getId() == request.getBranch().getId())
				request.setBranch(tempBranch);
		}
		for (Hall tempHall : request.getBranch().getHalls()) {
			if (tempHall.getId() == request.getHall().getId())
				request.setHall(tempHall);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy ',' HH:mm");
			for (Screening hakrana : tempHall.getScreenings()) {
				LocalDateTime curr = LocalDateTime.parse(hakrana.getScreeningDate() + " , " + hakrana.getScreeningTime(), formatter);
				LocalDateTime requestedDate = LocalDateTime.parse(request.getDate() + " , " + request.getTime() , formatter);
				
				if (requestedDate.plusMinutes(1).isAfter(curr) && requestedDate.minusMinutes(1).isBefore(curr.plusHours(2))) {
					System.out.println("restricting existing screening");
					Warning new_warning = new Warning("The chosen hall is already occupied during these hours.");
					try {
						client.sendToClient(new Message("#Warning", new_warning));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}
			}
		}
		for (CinemaMovie tempMovie : searchMovie) {
			if (tempMovie.getId() == request.getMovie().getId())
				request.setMovie(tempMovie);
		}
		Hibernate.initialize(request.getHall().getScreenings());
		List<Screening> tempList = request.getHall().getScreenings();
		System.out.println("hall screenings");
		System.out.println(tempList.size());
		Screening newScrn = new Screening(request.getDate(), request.getTime(), request.getMovie(),
				request.getBranch(),request.getHall());
		System.out.println("after constructing.");
		session.save(newScrn);
		session.flush();
		session.getTransaction().commit();
		try {
			moviesList = getAll(CinemaMovie.class);
			for (Movie movie : moviesList) {
				if (movie.getId() == request.getMovieID()) {
					client.sendToClient(new Message("#RefreshAdd", request.getMovie()));
					sendRefreshcatalogevent();

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.close();
	}

//			List<Movie> catalog =getAll(Movie.class);
//			MovieList movieList = new MovieList(catalog);
//			try {
//				client.sendToClient(movieList);
//				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

	private void SearchForClient(Message msg, ConnectionToClient client) {
		LogInRequest object = (LogInRequest) msg.getObject();
		session = sessionFactory.openSession();
		int id = Integer.parseInt(object.getUsername());
		String lastDigits = object.getPassword();
		System.out.println(object.getUsername() + " " + object.getPassword());
		List<CasualBuyer> buyersList = getAll(CasualBuyer.class);
		for (CasualBuyer buyer : buyersList) {
			System.out.println(buyer.getCustomerId() + " " + buyer.getElectronicMail() + " " + msg.toString());
			if (buyer.getCustomerId() == id && buyer.getElectronicMail().equals(object.getPassword())
					&& msg.toString().startsWith("#SearchForClient2")) {
				try {
					System.out.println("fucking in the right place.");
					client.sendToClient(new Message("#ComplainerSearch", buyer));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.close();
				return;
			}
			if (buyer.getCustomerId() == id && !msg.toString().startsWith("#SearchForClient2")) {
				String temp = String.valueOf(buyer.getCreditNum());
				temp = temp.substring(temp.length() - 4);
				if (temp.equals(lastDigits)) {
					Hibernate.initialize(buyer.getPurchases());
					List<Purchase> tempList = buyer.getPurchases();
					try {
						client.sendToClient(new Message("#CasualBuyerSearch", buyer, tempList));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					session.close();
					return;
				}

			}
		}
		try {
			if (msg.toString().startsWith("#SearchForClient2")) {
				client.sendToClient(new Message("#ComplainerSearch", null));
			} else {
				client.sendToClient(new Message("#CasualBuyerSearch", null, null));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.close();
		return;
	}

	private void cancelOrder(Message msg, ConnectionToClient client) {
		String newText = "";
		session = sessionFactory.openSession();
		session.beginTransaction();
		CasualBuyer customer = (CasualBuyer) ((Message) msg).getObject();
		Purchase purchase = (Purchase) ((Message) msg).getObject2();
		List<CasualBuyer> customersList = getAll(CasualBuyer.class);
		customersList = getAll(CasualBuyer.class);
		for (CasualBuyer buyer : customersList) {
			if (customer.getId() == buyer.getId()) {
				Hibernate.initialize(buyer.getPurchases());
				List<Purchase> ordersList = buyer.getPurchases();
				for (Purchase order : ordersList) {
					if (order.getId() == purchase.getId()) {
						purchase = order;
						if (purchase.getClass() == Ticket.class) {
							newText = "Mr/Ms " + buyer.getFirstName() + " " + buyer.getLastName() + ",\n";
							newText += "We have received your request to cancel the purchase of the following cinema ticket: \n";
							newText += "Movie: " + ((Ticket) purchase).getScreening().getMovie().getMovieTitle();
							String screeningDate = ((Ticket) purchase).getScreening().getScreeningDate();
							String screeningTime = ((Ticket) purchase).getScreening().getScreeningTime();
							newText += "\nScreening Time: " + screeningDate + " , " + screeningTime;
							newText += "\nSeat ID: " + ((Ticket) purchase).getSeat();
							ScreeningTimeComparator util = new ScreeningTimeComparator();
							int temp = util.eligibleTicketRefund(screeningDate, screeningTime);
							if (temp == 3 && ((Ticket) purchase).getCost() > 0) {
								String tempText = String.valueOf(((Ticket) purchase).getCreditCardNum());
								tempText = tempText.substring(tempText.length() - 4);
								newText += "\nA full refund will be made to your credit card that ends with the digits *"
										+ tempText;
								newText += "\nRefund of: " + ((Ticket) purchase).getCost();
							} else if (temp == 1 && ((Ticket) purchase).getCost() > 0) {
								String tempText = String.valueOf(((Ticket) purchase).getCreditCardNum());
								tempText = tempText.substring(tempText.length() - 4);
								newText += "\nYou'll receive a refund equal to 50% of the ticket cost, to the credit card that ends with the digits *"
										+ tempText;
								newText += "\nRefund of: " + ((Ticket) purchase).getCost() / 2;
							}
							((Ticket) purchase).getScreening().setAvailableSeatAt((((Ticket) purchase).getSeatNum()));
							((Ticket) purchase).getScreening()
									.setSoldSeats(((Ticket) purchase).getScreening().getSoldSeats() - 1);
							purchase.setStatus("Canceled");
							session.save(purchase);
							session.save(buyer);
							session.save(((Ticket) purchase).getScreening());
							session.flush();
							session.getTransaction().commit();
						} else if (purchase.getClass() == Rent.class) {
							newText = "Mr/Ms " + buyer.getFirstName() + " " + buyer.getLastName() + ",\n";
							newText += "We have received your request to cancel the purchase of the following On-Demand order: \n";
							newText += "Movie: " + ((Rent) purchase).getMovie().getMovieTitle();
							LocalDateTime temp = LocalDateTime.now().plusHours(1);
							if (temp.isBefore(((Rent) purchase).getActivationDate())) {
								String tempText = String.valueOf(((Rent) purchase).getCreditCardNum());
								tempText = tempText.substring(tempText.length() - 4);
								newText += "\nA refund equal to 50% of the renting value will be made to your credit card that ends with *"
										+ tempText;
								newText += "\nAmount of refund: " + ((Rent) purchase).getCost() / 2 + "NIS";
							}
							purchase.setStatus("Canceled");
							session.save(purchase);
							session.save(buyer);
							session.flush();
							session.getTransaction().commit();
						}

						try {
							client.sendToClient(new Message("#CanceledOrder", buyer, ordersList));
						} catch (IOException e) {
							// TODO Auto-generated catch block e.printStackTrace();
						}
						SendEmailTLS.SendMailTo(buyer.getElectronicMail(), "Cinema Ticket Cancelation", newText);
						return;
					}
				}
			}
		}

		customersList = getAll(CasualBuyer.class);
		for (CasualBuyer buyer : customersList) {
			if (customer.getId() == buyer.getId()) {
				Hibernate.initialize(buyer.getPurchases());
				List<Purchase> ordersList = buyer.getPurchases();
				try {
					client.sendToClient(new Message("#CanceledOrder", buyer, ordersList));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SendEmailTLS.SendMailTo(buyer.getElectronicMail(), "Cinema Ticket Cancelation", newText);
				session.close();
				return;
			}
		}
	}

	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Movie.class);
		configuration.addAnnotatedClass(Screening.class);
		configuration.addAnnotatedClass(SirtyaBranch.class);
		configuration.addAnnotatedClass(Image.class);
		configuration.addAnnotatedClass(Worker.class);
		configuration.addAnnotatedClass(GeneralManager.class);
		configuration.addAnnotatedClass(ContentManager.class);
		configuration.addAnnotatedClass(CustomerServiceEmployee.class);
		configuration.addAnnotatedClass(BranchManager.class);
		// configuration.addAnnotatedClass(Movie.class);
		configuration.addAnnotatedClass(ComingSoonMovie.class);
		configuration.addAnnotatedClass(CinemaMovie.class);
		configuration.addAnnotatedClass(Hall.class);
		configuration.addAnnotatedClass(Ticket.class);
		configuration.addAnnotatedClass(CasualBuyer.class);
		configuration.addAnnotatedClass(Complaint.class);
		configuration.addAnnotatedClass(CinemaMember.class);
		configuration.addAnnotatedClass(OnDemandMovie.class);
		configuration.addAnnotatedClass(Rent.class);
		configuration.addAnnotatedClass(TabPurchase.class);
		configuration.addAnnotatedClass(TavSagoal.class);
		configuration.addAnnotatedClass(Price.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		return configuration.buildSessionFactory(serviceRegistry);
	}

	public void connectData() {
		try {
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();

			initializeData();
			session.close();
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
				session.getSessionFactory().close();
			}
		}
	}

	private static void initializeData() throws Exception {
		TavSagoal temp = new TavSagoal();
		temp.setEffective(false);
		temp.setFromDate("");
		temp.setToDate("");
		temp.setY(0);
		session.save(temp);
		session.flush();
		Image image_1 = new Image("Thumbnail", "https://upload.wikimedia.org/wikipedia/en/3/36/Haunt2019Poster.jpg");
		Image image_2 = new Image("Thumbnail",
				"https://upload.wikimedia.org/wikipedia/en/0/00/Us_%282019%29_theatrical_poster.png");
		Image image_3 = new Image("Thumbnail",
				"https://upload.wikimedia.org/wikipedia/en/b/b8/A_Beautiful_Mind_Poster.jpg");
		Image image_4 = new Image("Thumbnail",
				"https://upload.wikimedia.org/wikipedia/en/3/3d/The_Lion_King_poster.jpg");
		Image image_5 = new Image("Thumbnail",
				"https://upload.wikimedia.org/wikipedia/en/3/3c/Ice_Age_%282002_film%29_poster.jpg");
		Image image_6 = new Image("Thumbnail",
				"https://upload.wikimedia.org/wikipedia/en/2/2e/Inception_%282010%29_theatrical_poster.jpg");
		Image image_7 = new Image("Thumbnail", "https://upload.wikimedia.org/wikipedia/en/1/17/Fury_2014_poster.jpg");
		Image image_8 = new Image("Thumbnail",
				"https://upload.wikimedia.org/wikipedia/en/e/e9/Black_Widow_%282021_film%29_poster.jpg");
		Image image_9 = new Image("Thumbnail",
				"https://upload.wikimedia.org/wikipedia/he/0/0a/Venom_Let_There_Be_Carnage_Poster.jpg");
		Image image_10 = new Image("Thumbnail", "https://upload.wikimedia.org/wikipedia/en/8/8b/Avatar_2_logo.jpg");
		Image image_11 = new Image("Thumbnail",
				"https://upload.wikimedia.org/wikipedia/en/1/1b/The_Flash_film_logo.png");
		session.save(image_1);
		session.save(image_2);
		session.save(image_3);
		session.save(image_4);
		session.save(image_5);
		session.save(image_6);
		session.save(image_7);
		session.save(image_8);
		session.save(image_9);
		session.save(image_10);
		session.save(image_11);
		session.flush();

		session.flush();
		CinemaMovie movie1 = new CinemaMovie("Haunt", "????????", "Eli Roth", "Katie Stevens",
				"On Halloween, a group of friends encounter an extreme haunted house that promises to feed on their darkest fears. The night turns deadly as they come to the horrifying realization that some nightmares are real.",
				44.90, image_1);
		session.save(movie1);
		session.flush();

		CinemaMovie movie2 = new CinemaMovie("Us", "??????????", "Jason Blum", "Lupita Nyong'o",
				"A family's serene beach vacation turns to chaos when their doppelg??ngers appear and begin to terrorize them.",
				49.90, image_2);
		session.save(movie2);
		session.flush();

		CinemaMovie movie3 = new CinemaMovie("A Beaultiful Mind", "???????????? ????????????", "Brian Grazer, Ron Howard",
				"Russell Crowe, Ed Harris, Jennifer Connelly",
				"After John Nash, a brilliant but asocial mathematician, accepts secret work in cryptography, his life takes a turn for the nightmarish.",
				30.00, image_3);
		session.save(movie3);
		session.flush();

		CinemaMovie movie4 = new CinemaMovie("The Lion King", "?????? ????????????", "Don Hahn",
				"Matthew Broderick, Jeremy Irons, James Earl Jones",
				"Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself.",
				36.00, image_4);
		session.save(movie4);
		session.flush();

		ZonedDateTime start = ZonedDateTime.now(ZoneId.of("Asia/Jerusalem"));

		OnDemandMovie movie5 = new OnDemandMovie("Ice Age", "???????? ????????", "Lori Forte",
				"Denis Leary, John Leguizamo, Ray Romano",
				"The story revolves around sub-zero heroes: a woolly mammoth, a saber-toothed tiger, a sloth and a prehistoric combination of a squirrel and rat, known as Scrat.",
				20.00, image_5, start.plusMinutes(5), start.plusHours(3).plusMinutes(5));
		movie5.setStreamingLink("https://www.youtube.com/watch?v=i4noiCRJRoE&ab_channel=MovieclipsClassicTrailers");
		session.save(movie5);
		session.flush();

		OnDemandMovie movie6 = new OnDemandMovie("Inception", "?????????? ", "Emma Thomas , Christopher Nolan",
				"Leonardo DiCaprio, Joseph Gordon-Levitt , Elliot Page",
				"A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
				25.90, image_6, start.plusMinutes(3), start.plusHours(5).plusMinutes(3));
		movie6.setStreamingLink("https://www.youtube.com/watch?v=YoHD9XEInc0");
		session.save(movie6);
		session.flush();

		CinemaMovie movie7 = new CinemaMovie("Fury", "??????", "David Ayer", "Brad Pitt, Shia Labeouf, Logan Lerman",
				"A grizzled tank commander makes tough decisions as he and his crew fight their way across Germany in April, 1945.",
				45.00, image_7);
		session.save(movie7);
		session.flush();

		ComingSoonMovie movie8 = new ComingSoonMovie("Black Widow (2021)", "?????????? ??????????", "Cate Shortland",
				"Scarlett Johansson, Florence Pugh, David Harbour",
				"A film about Natasha Romanoff in her quests between the films Civil War and Infinity War.", image_8);
		session.save(movie8);
		session.flush();

		ComingSoonMovie movie9 = new ComingSoonMovie("Venom 2 (2021)", "???????? 2", "Andy Serkis",
				"Stephen Graham, Tom Hardy, Michelle Williams",
				"Plot is yet to be announced. Sequel to 'Venom' that was released in 2018", image_9);
		session.save(movie9);
		session.flush();

		ComingSoonMovie movie10 = new ComingSoonMovie("Avatar 2 (2022)", "???????????? 2", "James Cameron",
				"Sam Worthington, Zoe Saldana, Stephen Lang",
				"Plot is yet to be announced. Sequel to its 'Avatar' that was released in 2009", image_10);
		session.save(movie10);
		session.flush();

		ComingSoonMovie movie11 = new ComingSoonMovie("The Flash (2022)", "??????????", "Andy Muschietti",
				"Ezra Miller, Ben Affleck, Michael Keaton",
				"Barry Allen travels back in time to prevent his mother's murder, which brings unintentional consequences to his timeline",
				image_11);
		session.save(movie11);
		session.flush();

		SirtyaBranch branch1 = new SirtyaBranch("Elm's street 25, Varrock");
		SirtyaBranch branch2 = new SirtyaBranch("Riverdale 29, Falador");
		SirtyaBranch branch3 = new SirtyaBranch("Wa7awee7 117, Lumbrige");

		session.save(branch1);
		session.save(branch2);
		session.save(branch3);
		session.flush();

		Hall hall1 = new Hall(4, 5, 18, "1", branch1);
		Hall hall2 = new Hall(5, 5, 25, "2", branch1);
		Hall hall3 = new Hall(6, 6, 36, "3", branch1);
		Hall hall4 = new Hall(5, 6, 28, "4", branch1);
		Hall hall5 = new Hall(3, 6, 18, "1", branch2);
		Hall hall6 = new Hall(6, 5, 30, "5", branch1);
		Hall hall7 = new Hall(5, 5, 23, "6", branch1);
		Hall hall8 = new Hall(5, 4, 20, "1", branch3);
		Hall hall9 = new Hall(4, 5, 18, "7", branch1);
		Hall hall10 = new Hall(5, 5, 23, "8", branch1);
		Hall hall11 = new Hall(4, 4, 16, "9", branch1);
		session.save(hall1);
		session.save(hall2);
		session.save(hall3);
		session.save(hall4);
		session.save(hall5);
		session.save(hall6);
		session.save(hall7);
		session.save(hall8);
		session.save(hall9);
		session.save(hall10);
		session.save(hall11);
		session.save(branch1);
		session.save(branch2);
		session.save(branch3);
		session.flush();

		movie1.getSirtyaBranch().add(branch1);
		movie1.getSirtyaBranch().add(branch2);
		movie2.getSirtyaBranch().add(branch1);
		movie2.getSirtyaBranch().add(branch2);
		movie2.getSirtyaBranch().add(branch3);
		movie3.getSirtyaBranch().add(branch1);
		movie4.getSirtyaBranch().add(branch3);
		// movie5.getSirtyaBranch().add(branch3);
		movie7.getSirtyaBranch().add(branch1);
		movie7.getSirtyaBranch().add(branch2);

		Screening screening_1 = new Screening("01/06/2021", "22:30", movie1, branch1, hall1);
		Screening screening_2 = new Screening("02/06/2021", "23:45", movie1, branch2, hall5);
		Screening screening_3 = new Screening("01/06/2021", "20:30", movie2, branch1, hall2);
		Screening screening_4 = new Screening("03/06/2021", "22:00", movie1, branch2, hall5);
		Screening screening_5 = new Screening("04/06/2021", "19:30", movie2, branch1, hall3);
		Screening screening_6 = new Screening("02/06/2021", "23:45", movie2, branch3, hall8);
		Screening screening_7 = new Screening("02/06/2021", "20:45", movie3, branch1, hall3);
		Screening screening_8 = new Screening("03/06/2021", "16:45", movie4, branch3, hall8);
		Screening screening_9 = new Screening("03/06/2021", "19:00", movie7, branch1, hall4);
		Screening screening_10 = new Screening("05/06/2021", "17:00", movie7, branch2, hall6);
		session.save(screening_1);
		session.save(screening_2);
		session.save(screening_3);
		session.save(screening_4);
		session.save(screening_5);
		session.save(screening_6);
		session.save(screening_7);
		session.save(screening_8);
		session.save(screening_9);
		session.save(screening_10);
		session.save(hall1);
		System.out.println(hall1.getScreenings().size());
		session.flush();

		Worker worker_1 = new GeneralManager();
		worker_1.setWokerUsername("GeneralManager");
		worker_1.setWorkerEmail("Mohammadw996@gmail.com");
		worker_1.setWorkerID("206794018");
		worker_1.setWorkerName("Mohammad Wattad");
		worker_1.setWorkerPassword("wa7wa7");

		Worker worker_2 = new ContentManager();
		worker_2.setWokerUsername("ContentManager");
		worker_2.setWorkerEmail("jerryabuayob@gmail.com");
		worker_2.setWorkerID("318156171");
		worker_2.setWorkerName("Jerry ");
		worker_2.setWorkerPassword("wa7wa7");

		BranchManager worker_4 = new BranchManager();
		worker_4.setWokerUsername("BranchManager");
		worker_4.setWorkerEmail("eliaso_sh@hotmail.com");
		worker_4.setWorkerID("205350598");
		worker_4.setWorkerName("Elias Shalloufi");
		worker_4.setWorkerPassword("wa7wa7");
		worker_4.setBranch(branch3);

		Worker worker_3 = new CustomerServiceEmployee();
		worker_3.setWokerUsername("CustomerService");
		worker_3.setWorkerEmail("jerryabuayob@gmail.com");
		worker_3.setWorkerID("318156171");
		worker_3.setWorkerName("Service tester");
		worker_3.setWorkerPassword("wa7wa7");

		CinemaMember client_1 = new CinemaMember("client1", "tester", 318156171, 123456789, "jerryabuayob@gmail.com",
				"CinemaMember1", "wa7wa7");
		CinemaMember client_2 = new CinemaMember("client2", "tester", 125874569, 0000000, "jerryabuayob@gmail.com",
				"CinemaMember2", "wa7wa7");

		session.save(client_2);
		session.save(worker_2);
		session.save(worker_1);
		session.save(worker_3);
		session.save(worker_4);

		session.save(client_1);
		session.flush();

		// tests for purchases
		// session.save(purchase2);
		// session.save(purchase1);
		// session.flush();

		Complaint complaint0 = new Complaint(client_1, "Your seats are uncomfortable", "eliaso_sh@hotmail.com",
				branch1);
		Complaint complaint1 = new Complaint(client_1, "The screen is too bright!", "eliaso_sh@hotmail.com", branch1);
		Complaint complaint2 = new Complaint(client_2, "The screen is too dark!", "eliaso_sh@hotmail.com", branch1);
		complaint0.setResponse("hahaha");

		session.save(complaint2);
		session.save(complaint1);
		session.save(complaint0);
		session.flush();

		session.getTransaction().commit();

	}

	public static <T> ArrayList<T> getAll(Class<T> object) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(object);
		query.from(object);
		ArrayList<T> data = (ArrayList<T>) session.createQuery(query).getResultList();
		return data;
	}

	static ArrayList<Complaint> getAllComplaints() throws Exception {
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Complaint> query = builder.createQuery(Complaint.class);
		query.from(Complaint.class);
		ArrayList<Complaint> data = (ArrayList<Complaint>) session.createQuery(query).getResultList();
		return data;
	}

	static ArrayList<Movie> getAllMovies() throws Exception {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
		query.from(Movie.class);
		ArrayList<Movie> data = (ArrayList<Movie>) session.createQuery(query).getResultList();
		/*
		 * for(Movie movie : data) {
		 * 
		 * }
		 */
		return data;
	}

	static ArrayList<SirtyaBranch> getAllBranches() throws Exception {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<SirtyaBranch> query = builder.createQuery(SirtyaBranch.class);
		query.from(SirtyaBranch.class);
		ArrayList<SirtyaBranch> data = (ArrayList<SirtyaBranch>) session.createQuery(query).getResultList();
		/*
		 * for(SirtyaBranch movie : data) {
		 * 
		 * }
		 */
		return data;
	}

	static ArrayList<Screening> getAllScreenings() throws Exception {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Screening> query = builder.createQuery(Screening.class);
		query.from(Screening.class);
		ArrayList<Screening> data = (ArrayList<Screening>) session.createQuery(query).getResultList();
		/*
		 * for(SirtyaBranch movie : data) {
		 * 
		 * }
		 */
		return data;
	}

	static ArrayList<Worker> getAllWorkers() throws Exception {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Worker> query = builder.createQuery(Worker.class);
		query.from(Worker.class);
		ArrayList<Worker> data = (ArrayList<Worker>) session.createQuery(query).getResultList();
		/*
		 * for(SirtyaBranch movie : data) {
		 * 
		 * }
		 */
		return data;
	}

	public void updateData() {
		try {
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();

			// modifyData(msg, client);
			session.getTransaction().commit();
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
				// session.getSessionFactory().close();
			}
		}
		session.close();
	}

	// TODO private void modifyData (Object msg, ConnectionToClient client) {
	// }

}