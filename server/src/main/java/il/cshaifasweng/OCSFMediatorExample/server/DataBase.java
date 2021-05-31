package il.cshaifasweng.OCSFMediatorExample.server;

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

import il.cshaifasweng.OCSFMediatorExample.entities.Image;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;

public class DataBase {
	private static Session session;
	
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
		//File imagfile2 = new File(System.getProperty("user.dir") + "/Haunt.png");
		byte[] pixelsArray1 = new byte[(int) imagfile1.length()];
		byte[] pixelsArray2 = new byte[(int) imagfile1.length()];
		try {
			FileInputStream Image1pixels = new FileInputStream(imagfile1);

			Image1pixels.read(pixelsArray1);
			Image1pixels.read(pixelsArray2);
			Image1pixels.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Image image_1 = new Image("Thumbnail", pixelsArray1);
		Image image_2 = new Image("Thumbnail", pixelsArray2);
		session.save(image_1);
		session.save(image_2);
		session.flush();

		Movie movie1 = new Movie("Haunt", "Eli Roth", "Katie Stevens",
				"On Halloween, a group of friends encounter an extreme haunted house that promises to feed on their darkest fears. The night turns deadly as they come to the horrifying realization that some nightmares are real.",
				44.90, image_1);
		session.save(movie1);
		session.flush();

		Movie movie2 = new Movie("Us", "Jason Blum", "Lupita Nyong'o",
				"A family's serene beach vacation turns to chaos when their doppelgängers appear and begin to terrorize them.",
				48.90, image_2);
		session.save(movie2);
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

		Screening screening_1 = new Screening("01/06/2020", "22:30", movie1, branch1);
		Screening screening_2 = new Screening("02/06/2020", "23:45", movie1, branch2);
		Screening screening_3 = new Screening("01/06/2020", "20:30", movie2, branch1);
		Screening screening_4 = new Screening("03/06/2020", "22:00", movie1, branch2);
		Screening screening_5 = new Screening("04/06/2020", "19:30", movie2, branch1);
		Screening screening_6 = new Screening("02/06/2020", "23:45", movie2, branch3);
		session.save(screening_1);
		session.save(screening_2);
		session.save(screening_3);
		session.save(screening_4);
		session.save(screening_5);
		session.save(screening_6);
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
	}

}
