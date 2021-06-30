package il.cshaifasweng.OCSFMediatorExample.helpers;

import java.util.TimerTask;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import il.cshaifasweng.OCSFMediatorExample.entities.CasualBuyer;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMember;
import il.cshaifasweng.OCSFMediatorExample.entities.CinemaMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.ComingSoonMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.CustomerServiceEmployee;
import il.cshaifasweng.OCSFMediatorExample.entities.GeneralManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Image;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.OnDemandMovie;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.Rent;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.SirtyaBranch;
import il.cshaifasweng.OCSFMediatorExample.entities.TabPurchase;
import il.cshaifasweng.OCSFMediatorExample.entities.Ticket;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
// Create a class extends with TimerTask
public class ScheduledTask extends TimerTask {
	private static SessionFactory sessionFactory = getSessionFactory();
	private static Session session;
	
    // Add your task here
    public void run() {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Jerusalem"));
    	session = sessionFactory.openSession();
    	ArrayList <Rent> rentList = getAll(Rent.class);
    	for(Rent rent : rentList) {
    		String email = rent.getCustomer().getElectronicMail();
    		try {
				ArrayList<OnDemandMovie> tmp = getMoviesById(rent.getMovie().getId());
//				System.out.println(tmp.get(0).getId());
//				System.out.println(tmp.get(0).getDateTimeStart());
//				System.out.println(tmp.get(0).getDateTimeFinish());
				long diffHours = now.until(tmp.get(0).getDateTimeStart(), ChronoUnit.HOURS);
				long diffMins = now.until(tmp.get(0).getDateTimeStart(), ChronoUnit.MINUTES);
				//System.out.println(diff);
				if (diffHours < 1 && rent.getNotified() == false) {
					SendEmailTLS.SendMailTo(email, "Reminder", "the movie you ordered starts within the hour!\n So grab a popcorn and get ready for some entertainment :D");
					rent.setNotified(true);
					session.beginTransaction();
					session.save(rent);
					session.flush();
					session.getTransaction().commit();
				}
				if(diffMins < 1 && rent.getSentLink() == false) {
					SendEmailTLS.SendMailTo(email, "The Movie", rent.getMovie().getStreamingLink());
					rent.setSentLink(true);
					session.beginTransaction();
					session.save(rent);
					session.flush();
					session.getTransaction().commit();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		configuration.addAnnotatedClass(CustomerServiceEmployee.class);
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

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		return configuration.buildSessionFactory(serviceRegistry);
	}
    
    public static <T> ArrayList<T> getAll(Class<T> object) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(object);
		query.from(object);
		ArrayList<T> data = (ArrayList<T>) session.createQuery(query).getResultList();
		return data;
	}
    
    static ArrayList<OnDemandMovie> getMoviesById(int id) throws Exception {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<OnDemandMovie> query = builder.createQuery(OnDemandMovie.class);
		Root<OnDemandMovie> from = query.from(OnDemandMovie.class);
		query.select(from).where(builder.equal(from.get("id"), id));
		ArrayList<OnDemandMovie> data = (ArrayList<OnDemandMovie>) session.createQuery(query).getResultList();

		return data;
	}
}