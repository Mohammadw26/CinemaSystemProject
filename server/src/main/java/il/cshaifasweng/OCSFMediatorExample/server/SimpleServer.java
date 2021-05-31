package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.ScreeningsUpdateRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import il.cshaifasweng.OCSFMediatorExample.entities.Image;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;


public class SimpleServer extends AbstractServer {
	private static SessionFactory sessionFactory = getSessionFactory();
	private static Session session;
	private static List<Movie> moviesList;
	private static List<Screening> screeningsList;
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
					ArrayList<Movie> movieList = getAllMovies();
					
					client.sendToClient(new Message("#SendMovies",movieList));
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
			moviesList = getAllMovies();
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
					moviesList = getAllMovies();
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
	
	private void addScreening(ScreeningsUpdateRequest request, ConnectionToClient client) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Screening newScrn = new Screening(request.getDate(),request.getTime(),request.getMovie(),request.getBranch());
		session.save(newScrn);
		session.flush();
		session.getTransaction().commit();
		try {
			client.sendToClient(new Message("#RefreshAdd",request.getMovie()));
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
		File imagfile1 = new File(System.getProperty("user.dir") + "/Haunt.png");
		File imagfile2 = new File(System.getProperty("user.dir") + "/Us.png");
		File imagfile3 = new File(System.getProperty("user.dir") + "/ABeautifulMind.png");
		File imagfile4 = new File(System.getProperty("user.dir") + "/TheLionKing.png");
		File imagfile5 = new File(System.getProperty("user.dir") + "/IceAge.png");
		byte[] pixelsArray1 = new byte[(int) imagfile1.length()];
		byte[] pixelsArray2 = new byte[(int) imagfile2.length()];
		byte[] pixelsArray3 = new byte[(int) imagfile3.length()];
		byte[] pixelsArray4 = new byte[(int) imagfile4.length()];
		byte[] pixelsArray5 = new byte[(int) imagfile5.length()];
		try {
			FileInputStream Image1pixels = new FileInputStream(imagfile1);
			FileInputStream Image2pixels = new FileInputStream(imagfile2);
			FileInputStream Image3pixels = new FileInputStream(imagfile3);
			FileInputStream Image4pixels = new FileInputStream(imagfile4);
			FileInputStream Image5pixels = new FileInputStream(imagfile5);


			Image1pixels.read(pixelsArray1);
			Image2pixels.read(pixelsArray2);
			Image3pixels.read(pixelsArray3);
			Image4pixels.read(pixelsArray4);
			Image5pixels.read(pixelsArray5);
			Image1pixels.close();
			Image2pixels.close();
			Image3pixels.close();
			Image4pixels.close();
			Image5pixels.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		Image image_1 = new Image("Thumbnail", pixelsArray1);
		Image image_2 = new Image("Thumbnail", pixelsArray2);
		Image image_3 = new Image("Thumbnail", pixelsArray3);
		Image image_4 = new Image("Thumbnail", pixelsArray4);
		Image image_5 = new Image("Thumbnail", pixelsArray5);
		/*image_1.setImgURL(System.getProperty("user.dir") + "/Haunt.png");
		image_2.setImgURL(System.getProperty("user.dir") + "/Us.png");
		image_3.setImgURL(System.getProperty("user.dir") + "/ABeautifulMind.png");
		image_4.setImgURL(System.getProperty("user.dir") + "/TheLionKing.png");
		image_5.setImgURL(System.getProperty("user.dir") + "/IceAge.png");*/
		image_1.setImgURL("https://upload.wikimedia.org/wikipedia/en/3/36/Haunt2019Poster.jpg");
		image_2.setImgURL("https://upload.wikimedia.org/wikipedia/en/0/00/Us_%282019%29_theatrical_poster.png");
		image_3.setImgURL("https://upload.wikimedia.org/wikipedia/en/b/b8/A_Beautiful_Mind_Poster.jpg");
		image_4.setImgURL("https://upload.wikimedia.org/wikipedia/en/3/3d/The_Lion_King_poster.jpg");
		image_5.setImgURL("https://upload.wikimedia.org/wikipedia/en/3/3c/Ice_Age_%282002_film%29_poster.jpg");
		session.save(image_1);
		session.save(image_2);
		session.save(image_3);
		session.save(image_4);
		session.save(image_5);
		session.flush();

		Movie movie1 = new Movie("Haunt", "Eli Roth", "Katie Stevens",
				"On Halloween, a group of friends encounter an extreme haunted house that promises to feed on their darkest fears. The night turns deadly as they come to the horrifying realization that some nightmares are real.",
				44.90, image_1);
		session.save(movie1);
		session.flush();

		Movie movie2 = new Movie("Us", "Jason Blum", "Lupita Nyong'o",
				"A family's serene beach vacation turns to chaos when their doppelgängers appear and begin to terrorize them.",
				49.90, image_2);
		session.save(movie2);
		session.flush();
		
		Movie movie3 = new Movie("A Beaultiful Mind", "Brian Grazer, Ron Howard", "Russell Crowe, Ed Harris, Jennifer Connelly",
				"After John Nash, a brilliant but asocial mathematician, accepts secret work in cryptography, his life takes a turn for the nightmarish.",
				30.00, image_3);
		session.save(movie3);
		session.flush();
		
		Movie movie4 = new Movie("The Lion King", "Don Hahn", "Matthew Broderick, Jeremy Irons, James Earl Jones",
				"Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself.",
				36.00, image_4);
		session.save(movie4);
		session.flush();
		
		Movie movie5 = new Movie("Ice Age", "Lori Forte", "Denis Leary, John Leguizamo, Ray Romano",
				"The story revolves around sub-zero heroes: a woolly mammoth, a saber-toothed tiger, a sloth and a prehistoric combination of a squirrel and rat, known as Scrat.",
				39.90, image_5);
		session.save(movie5);
		session.flush();
		

		SirtyaBranch branch1 = new SirtyaBranch("Elm's street 25, Varrock");
		SirtyaBranch branch2 = new SirtyaBranch("Riverdale 29, Falador");
		SirtyaBranch branch3 = new SirtyaBranch("Wa7awee7 117, Lumbrige");
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
		Screening screening_9 = new Screening("03/06/2021", "19:00", movie5, branch3);
		session.save(screening_1);
		session.save(screening_2);
		session.save(screening_3);
		session.save(screening_4);
		session.save(screening_5);
		session.save(screening_6);
		session.save(screening_7);
		session.save(screening_8);
		session.save(screening_9);
		session.flush();
		
		session.getTransaction().commit();

	}
	
//	public static <T> List<T> getAll(Class<T> object)  {
//		CriteriaBuilder builder = session.getCriteriaBuilder();
//		CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
//		Root<T> rootEntry = criteriaQuery.from(object);
//		CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);
//
//		TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
//		return allQuery.getResultList();
//	}

	
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

