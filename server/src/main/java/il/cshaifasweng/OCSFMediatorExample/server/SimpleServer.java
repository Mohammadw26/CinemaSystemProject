package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.OnDemandMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Rent;
import il.cshaifasweng.OCSFMediatorExample.entities.RentRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.ScreeningsUpdateRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import il.cshaifasweng.OCSFMediatorExample.entities.TabPurchase;
import il.cshaifasweng.OCSFMediatorExample.entities.Ticket;
import il.cshaifasweng.OCSFMediatorExample.entities.BookingRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.CasualBuyer;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMember;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.ComingSoonMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.CustomerServiceEmployee;
import il.cshaifasweng.OCSFMediatorExample.entities.FullOrderRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.GeneralManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Image;
import il.cshaifasweng.OCSFMediatorExample.entities.LogInRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;


public class SimpleServer extends AbstractServer {
	private static SessionFactory sessionFactory = getSessionFactory();
	private static Session session;
	private static List<CinemaMovie> moviesList;
	private static List<Screening> screeningsList;
	@SuppressWarnings("unused")
	private static List<SirtyaBranch> branchesList;

	public SimpleServer(int port) {
		super(port);
		
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) { 
		//movieList.setMoviesList(getAll(Movie.class));
		//catalog =getAll(Movie.class);
		//MovieList movieList = new MovieList(getAll(Movie.class));
		String msgString = msg.toString();
		if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#CatalogRequest")) {
					//movieList.setMoviesList(getAll(Movie.class));
				try {
					session = sessionFactory.openSession();
					ArrayList<CinemaMovie> cinMovieList = getAll(CinemaMovie.class);
					ArrayList<ComingSoonMovie> soonMovieList = getAll(ComingSoonMovie.class);
					ArrayList<OnDemandMovie> onDemandList = getAll(OnDemandMovie.class);
					client.sendToClient(new Message("#SendLists",cinMovieList,soonMovieList,onDemandList));
				}catch(IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.close();
		}
		else if (msgString.startsWith("#BranchesListRequest")) {
			//movieList.setMoviesList(getAll(Movie.class));
		try {
			session = sessionFactory.openSession();
			ArrayList<SirtyaBranch> branchesList = getAllBranches();
			
			client.sendToClient(new Message("#BranchesList",branchesList));
			session.close();
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(msgString.startsWith("#DeleteScreening")) {
			deleteScreening((ScreeningsUpdateRequest) ((Message) msg).getObject(), client);
		}
		else if(msgString.startsWith("#AddScreening")) {
			addScreening((ScreeningsUpdateRequest) ((Message) msg).getObject(), client);
		}
		else if(msgString.startsWith("#EditScreening")) {
			try {
				editScreening((ScreeningsUpdateRequest) ((Message) msg).getObject(), client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(msgString.startsWith("#SaveSeats")) {
			try {
				saveSeats((BookingRequest) ((Message) msg).getObject(), client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(msgString.startsWith("#FinishOrder")) {
			try {
				finishOrder((FullOrderRequest) ((Message) msg).getObject(), client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(msgString.startsWith("#RentingRequest")) {
			try {
				rentMovie((RentRequest) ((Message) msg).getObject(), client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#WorkersRequest")) {
			try {
				session = sessionFactory.openSession();
				ArrayList<Worker> workersList = getAllWorkers();
			
				client.sendToClient(new Message("#WorkersList",workersList));
				session.close();
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(msgString.startsWith("#LoginRequest")) {
			handleLoginRequest((Message) msg, client);
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
				for(Worker worker: workersList) {
					if(worker.getWokerUsername().equals(username)) {
						if(worker.getWorkerPassword().equals(password)) {
							client.sendToClient(new Message("#WorkerLogIn",worker));
							return;
						}
					}
				}
			}
			if (!membersList.isEmpty()) {
				for(CinemaMember member: membersList) {
					if(member.getUsername().equals(username)) {
						if(member.getPassword().equals(password)) {
							if(msg.toString().equals("#LoginRequestWhileBooking")) {
								client.sendToClient(new Message("#MemberLogIn2",member));
							} else  if (msg.toString().equals("#LoginRequestWhileRenting")){
								client.sendToClient(new Message("#MemberLogIn3",member));
							} else {
								client.sendToClient(new Message("#MemberLogIn",member));
							}
							return;
						}
					}
				}
			}
			else {
				if(msg.toString().equals("#LoginRequestWhileBooking")) {
					client.sendToClient(new Message("#LogInFailed2",object));
				} else if(msg.toString().equals("#LoginRequestWhileRenting")) {
					client.sendToClient(new Message("#LogInFailed3",object));
				} else {
					client.sendToClient(new Message("#LogInFailed",object));
				}
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
			if (scrn.getId()== request.getScrnID()) {
				temp = scrn;
				break;
			}
		}
		temp.getMovie().getScreenings().remove(temp);
		temp.getBranch().getScreenings().remove(temp);
		session.delete(temp);
		session.flush();
		session.getTransaction().commit();
		try {
			moviesList = getAll(CinemaMovie.class);
			for (Movie movie : moviesList) {
				if (movie.getId()==request.getMovieID()) {
					client.sendToClient(new Message("#RefreshDelete",movie));
				}
			}
		}catch(IOException e) {
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
			if (scrn.getId()==((ScreeningsUpdateRequest) request).getScrnID()) {
				session.beginTransaction();
				scrn.setScreeningDate(((ScreeningsUpdateRequest) request).getDate());
				scrn.setScreeningTime(((ScreeningsUpdateRequest) request).getTime());
				session.save(scrn);
				session.flush();
				session.getTransaction().commit();
				try {
					moviesList = getAll(CinemaMovie.class);
					for (Movie movie : moviesList) {
						if (movie.getId()==request.getMovieID()) {
							client.sendToClient(new Message("#RefreshEdit",movie));
						}
					}
				}catch(IOException e) {
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
			if (scrn.getId()==((BookingRequest) request).getScreening().getId()) {
				session.beginTransaction();
				for(int i = 0 ; i < request.getArrSize() ; i ++) {
					scrn.setTakenSeatAt(request.getSeats()[i]);
				}
				scrn.setAvailableSeats(scrn.getAvailableSeats()-request.getArrSize());
				session.save(scrn);
				session.flush();
				session.getTransaction().commit();
				try {
					client.sendToClient(new Message("#SeatsSaved",request));
					/*screeningsList = getAll(Screening.class);
					for (Screening screening : screeningsList) {
						if (screening.getId()==request.getScreening().getId()) {
							request.setScreening(screening);
							client.sendToClient(new Message("#SeatsBooked",request));
						}
					}*/
				}catch(IOException e) {
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
	
	private void finishOrder(FullOrderRequest request, ConnectionToClient client) throws Exception {
		if (request.isNewCustomerFlag() && !request.isSignupFlag()) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			CasualBuyer newCus = new CasualBuyer(request.getFirstName(),request.getLastName(),request.getCustomerID(),request.getCardNum(),request.getEmail());
			session.save(newCus);
			session.flush();
			BookingRequest temp = request.getRequest();
			for (int i = 0; i < temp.getArrSize(); i ++) {
				Ticket newTicket = new Ticket(temp.getScreening(), newCus , temp.getSeatIds()[i], temp.getCost());
				session.save(newTicket);
				session.flush();
			}
			session.save(newCus);
			session.flush();
			session.getTransaction().commit();
			try {
				client.sendToClient(new Message("#BookedNonMember",request));
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (request.isNewCustomerFlag() && request.isSignupFlag()) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			CinemaMember newCus = new CinemaMember(request.getFirstName(),request.getLastName(),request.getCustomerID(),request.getCardNum(),request.getEmail(), request.getUsername(), request.getPassword());
			session.save(newCus);
			session.flush();
			if (request.isBuyPack()) {
				newCus.setTicketsCredit(20);
				TabPurchase newTab = new TabPurchase (request.getCardNum(), newCus);
				session.save(newTab);
				session.flush();
			}
			BookingRequest temp = request.getRequest();
			for (int i = 0; i < temp.getArrSize(); i ++) {
				if (request.getUsePack()>0 && newCus.getTicketsCredit() > 0) {
					Ticket newTicket = new Ticket(temp.getScreening(), newCus , temp.getSeatIds()[i], 0);
					request.setUsePack(request.getUsePack()-1);
					newCus.setTicketsCredit(newCus.getTicketsCredit()-1);
					session.save(newTicket);
					session.flush();
				}else {
					Ticket newTicket = new Ticket(temp.getScreening(), newCus , temp.getSeatIds()[i], temp.getCost());
					session.save(newTicket);
					session.flush();
				}
			}
			session.save(newCus);
			session.flush();
			session.getTransaction().commit();
			try {
				client.sendToClient(new Message("#BookedMember", request, newCus));
			}catch(IOException e) {
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
				for(CinemaMember member: membersList) {
					if(member.getUsername().equals(request.getUsername())) {
						if(member.getPassword().equals(request.getPassword())) {
							if (request.isBuyPack()) {
								member.setTicketsCredit(member.getTicketsCredit()+20);
								TabPurchase newTab = new TabPurchase (request.getCardNum(), member);
								session.save(newTab);
								session.flush();
							}
							BookingRequest temp = request.getRequest();
							for (int i = 0; i < temp.getArrSize(); i ++) {
								if (request.getUsePack()>0 && member.getTicketsCredit() > 0) {
									Ticket newTicket = new Ticket(temp.getScreening(), member , temp.getSeatIds()[i], 0);
									request.setUsePack(request.getUsePack()-1);
									member.setTicketsCredit(member.getTicketsCredit()-1);
									session.save(newTicket);
									session.flush();
								}else {
									Ticket newTicket = new Ticket(temp.getScreening(), member , temp.getSeatIds()[i], temp.getCost());
									session.save(newTicket);
									session.flush();
								}
							}
							session.save(member);
							session.flush();
							session.getTransaction().commit();
							client.sendToClient(new Message("#BookedMember", request, member));
							return;
						}
					}
				}
			}
		}
	}
	
	private void rentMovie(RentRequest request, ConnectionToClient client) throws Exception {
		if (request.isNewCustomerFlag() && !request.isSignupFlag()) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			CasualBuyer newCus = new CasualBuyer(request.getFirstName(),request.getLastName(),request.getCustomerID(),request.getCardNum(),request.getEmail());
			session.save(newCus);
			session.flush();
			Rent newRent = new Rent(newCus, request.getMovie().getCost(), request.getMovie(), request.getMovie().getStreamingLink(),request.getCardNum());
			session.save(newRent);
			session.save(newCus);
			session.flush();
			session.getTransaction().commit();
			try {
				client.sendToClient(new Message("#RentedNonMember",request));
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (request.isNewCustomerFlag() && request.isSignupFlag()) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			CinemaMember newCus = new CinemaMember(request.getFirstName(),request.getLastName(),request.getCustomerID(),request.getCardNum(),request.getEmail(), request.getUsername(), request.getPassword());
			session.save(newCus);
			session.flush();
			Rent newRent = new Rent(newCus, request.getMovie().getCost(), request.getMovie(), request.getMovie().getStreamingLink(),request.getCardNum());
			session.save(newRent);
			session.save(newCus);
			session.flush();
			session.getTransaction().commit();
			try {
				client.sendToClient(new Message("#RentedMember", request, newCus));
			}catch(IOException e) {
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
				for(CinemaMember member: membersList) {
					if(member.getUsername().equals(request.getUsername())) {
						if(member.getPassword().equals(request.getPassword())) {
							Rent newRent = new Rent(member, request.getMovie().getCost(), request.getMovie(), request.getMovie().getStreamingLink(),request.getCardNum());
							session.save(newRent);
							session.save(member);
							session.flush();
							session.getTransaction().commit();
							client.sendToClient(new Message("#RentedMember", request, member));
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
		Screening newScrn = new Screening(request.getDate(),request.getTime(),request.getMovie(),request.getBranch());
		session.save(newScrn);
		session.flush();
		session.getTransaction().commit();
		try {
			moviesList = getAll(CinemaMovie.class);
			for (Movie movie : moviesList) {
				if (movie.getId()==request.getMovieID()) {
					client.sendToClient(new Message("#RefreshAdd",request.getMovie()));
				}
			}
		}catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.close();
	}
	
//				else {
//					Warning warning = new Warning("#notEmpty");
//					try {
//						client.sendToClient(warning);
//						System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//				
				
//			List<Movie> catalog =getAll(Movie.class);
//			MovieList movieList = new MovieList(catalog);
//			try {
//				client.sendToClient(movieList);
//				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
	
	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Movie.class);
		configuration.addAnnotatedClass(Screening.class);
		configuration.addAnnotatedClass(SirtyaBranch.class);
		configuration.addAnnotatedClass(Image.class);
		configuration.addAnnotatedClass(Worker.class);
		configuration.addAnnotatedClass(GeneralManager.class);
		configuration.addAnnotatedClass(CustomerServiceEmployee.class);
		//configuration.addAnnotatedClass(Movie.class);
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
		Image image_1 = new Image("Thumbnail", "https://upload.wikimedia.org/wikipedia/en/3/36/Haunt2019Poster.jpg");
		Image image_2 = new Image("Thumbnail", "https://upload.wikimedia.org/wikipedia/en/0/00/Us_%282019%29_theatrical_poster.png");
		Image image_3 = new Image("Thumbnail", "https://upload.wikimedia.org/wikipedia/en/b/b8/A_Beautiful_Mind_Poster.jpg");
		Image image_4 = new Image("Thumbnail", "https://upload.wikimedia.org/wikipedia/en/3/3d/The_Lion_King_poster.jpg");
		Image image_5 = new Image("Thumbnail", "https://upload.wikimedia.org/wikipedia/en/3/3c/Ice_Age_%282002_film%29_poster.jpg");
		Image image_6 = new Image("Thumbnail", "https://upload.wikimedia.org/wikipedia/en/2/2e/Inception_%282010%29_theatrical_poster.jpg");
		session.save(image_1);
		session.save(image_2);
		session.save(image_3);
		session.save(image_4);
		session.save(image_5);
		session.save(image_6);
		session.flush();
		
		Hall hall1 = new Hall(4,5,18, "1");
		session.save(hall1);
		session.flush();
		CinemaMovie movie1 = new CinemaMovie("Haunt","רדוף", "Eli Roth", "Katie Stevens",
				"On Halloween, a group of friends encounter an extreme haunted house that promises to feed on their darkest fears. The night turns deadly as they come to the horrifying realization that some nightmares are real.",
				44.90, image_1);
		session.save(movie1);
		session.flush();

		CinemaMovie movie2 = new CinemaMovie("Us","אנחנו", "Jason Blum", "Lupita Nyong'o",
				"A family's serene beach vacation turns to chaos when their doppelgängers appear and begin to terrorize them.",
				49.90, image_2);
		session.save(movie2);
		session.flush();
		
		CinemaMovie movie3 = new CinemaMovie("A Beaultiful Mind","נפלאות התבונה", "Brian Grazer, Ron Howard", "Russell Crowe, Ed Harris, Jennifer Connelly",
				"After John Nash, a brilliant but asocial mathematician, accepts secret work in cryptography, his life takes a turn for the nightmarish.",
				30.00, image_3);
		session.save(movie3);
		session.flush();
		
		CinemaMovie movie4 = new CinemaMovie("The Lion King","מלך האריות", "Don Hahn", "Matthew Broderick, Jeremy Irons, James Earl Jones",
				"Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself.",
				36.00, image_4);
		session.save(movie4);
		session.flush();
		
		ComingSoonMovie movie5 = new ComingSoonMovie("Ice Age","עידן הקרח", "Lori Forte", "Denis Leary, John Leguizamo, Ray Romano",
				"The story revolves around sub-zero heroes: a woolly mammoth, a saber-toothed tiger, a sloth and a prehistoric combination of a squirrel and rat, known as Scrat.",
				image_5);
		session.save(movie5);
		session.flush();
		
		OnDemandMovie movie6 = new OnDemandMovie("Inception","התחלה ","Emma Thomas , Christopher Nolan", "Leonardo DiCaprio, Joseph Gordon-Levitt , Elliot Page" , 
				"A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.", 25.90 ,image_6);
		movie6.setStreamingLink("https://www.youtube.com/watch?v=YoHD9XEInc0");
		session.save(movie6);
		session.flush();

		SirtyaBranch branch1 = new SirtyaBranch("Elm's street 25, Varrock");
		branch1.addHall(hall1);
		SirtyaBranch branch2 = new SirtyaBranch("Riverdale 29, Falador");
		branch2.addHall(hall1);
		SirtyaBranch branch3 = new SirtyaBranch("Wa7awee7 117, Lumbrige");
		branch3.addHall(hall1);
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
		movie5.getSirtyaBranch().add(branch3);

		Screening screening_1 = new Screening("01/06/2021", "22:30", movie1, branch1);
		Screening screening_2 = new Screening("02/06/2021", "23:45", movie1, branch2);
		Screening screening_3 = new Screening("01/06/2021", "20:30", movie2, branch1);
		Screening screening_4 = new Screening("03/06/2021", "22:00", movie1, branch2);
		Screening screening_5 = new Screening("04/06/2021", "19:30", movie2, branch1);
		Screening screening_6 = new Screening("02/06/2021", "23:45", movie2, branch3);
		Screening screening_7 = new Screening("02/06/2021", "20:45", movie3, branch1);
		Screening screening_8 = new Screening("03/06/2021", "16:45", movie4, branch3);
//		Screening screening_9 = new Screening("03/06/2021", "19:00", movie5, branch3);
		screening_1.setHall(hall1);
		screening_2.setHall(hall1);
		screening_3.setHall(hall1);
		screening_4.setHall(hall1);
		screening_5.setHall(hall1);
		screening_6.setHall(hall1);
		screening_7.setHall(hall1);
		screening_8.setHall(hall1);
		session.save(screening_1);
		session.save(screening_2);
		session.save(screening_3);
		session.save(screening_4);
		session.save(screening_5);
		session.save(screening_6);
		session.save(screening_7);
		session.save(screening_8);
//		session.save(screening_9);
		session.flush();
		
		Worker worker_1 = new GeneralManager();
		worker_1.setWokerUsername("Mohammadw26");
		worker_1.setWorkerEmail("Mohammadw996@gmail.com");
		worker_1.setWorkerID("206794018");
		worker_1.setWorkerName("Mohammad Wattad");
		worker_1.setWorkerPassword("wa7wa7");
		
		Worker worker_2 = new CustomerServiceEmployee();
		worker_2.setWokerUsername("Jerry98");
		worker_2.setWorkerEmail("Mohammadw996@gmail.com");
		worker_2.setWorkerID("206794018");
		worker_2.setWorkerName("Jerry wa7wa7");
		worker_2.setWorkerPassword("wa7wa7");
		session.save(worker_1);
		session.save(worker_2);
		session.flush();
		session.getTransaction().commit();

	}
	
	public static <T> ArrayList<T> getAll(Class<T> object)  {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(object);
		query.from(object);
		ArrayList<T> data = (ArrayList<T>) session.createQuery(query).getResultList();
		return data;
	}

	
	static ArrayList<Movie> getAllMovies() throws Exception{
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
		query.from(Movie.class);
		ArrayList<Movie> data = (ArrayList<Movie>) session.createQuery(query).getResultList();
		/*for(Movie movie : data) {
			
		}*/
		return data;
	}
	
	static ArrayList<SirtyaBranch> getAllBranches() throws Exception{
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<SirtyaBranch> query = builder.createQuery(SirtyaBranch.class);
		query.from(SirtyaBranch.class);
		ArrayList<SirtyaBranch> data = (ArrayList<SirtyaBranch>) session.createQuery(query).getResultList();
		/*for(SirtyaBranch movie : data) {
			
		}*/
		return data;
	}
	
	static ArrayList<Screening> getAllScreenings() throws Exception{
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Screening> query = builder.createQuery(Screening.class);
		query.from(Screening.class);
		ArrayList<Screening> data = (ArrayList<Screening>) session.createQuery(query).getResultList();
		/*for(SirtyaBranch movie : data) {
			
		}*/
		return data;
	}
	
	static ArrayList<Worker> getAllWorkers() throws Exception{
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Worker> query = builder.createQuery(Worker.class);
		query.from(Worker.class);
		ArrayList<Worker> data = (ArrayList<Worker>) session.createQuery(query).getResultList();
		/*for(SirtyaBranch movie : data) {
			
		}*/
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
				//session.getSessionFactory().close();
			}
		}
		session.close();
	}


	
	
	
	
	
	
	//TODO private void modifyData (Object msg, ConnectionToClient client) {
		//	}

}

