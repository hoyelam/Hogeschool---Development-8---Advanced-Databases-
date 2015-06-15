import entities.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.PostgreSQLDialect;

public class App {
	private static SessionFactory factory;

	public static void main(String[] args) {
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		App ME = new App();		

		session.getTransaction().commit();

		Integer idGebruiker1 = ME.addGebruiker("hoye", "lam", "bla@bla.nl", "1234");
		HibernateUtil.getSessionFactory().close();
	}

	public Integer addGebruiker(String voornaam, String achternaam,String email, String wachtwoord) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer idGebruiker = null;
		try {
			tx = session.beginTransaction();
			Gebruiker gebruiker = new Gebruiker(voornaam, achternaam,email,wachtwoord);
			idGebruiker = (Integer) session.save(gebruiker);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				e.printStackTrace();
			}
		} finally{
			session.close();
		}
		return idGebruiker;
	}

}