import entities.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.PostgreSQLDialect;

public class App {
	private static SessionFactory factory;
	Gebruiker gebruiker = new Gebruiker("hoye", "lam", "bla@bla.nl", "1234");
	Categorie categorie = new Categorie("SmartPhones");
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

		//ME.actie1();
		
		ME.actie3();
		//ME.addGebruiker("hoye", "lam", "bl23s4a@bla.nl", "1234");
		//ME.PlaatsAdvertentie("gratis iPhone", "mooi ding", 97, true, "28-11-15");
		
		
		HibernateUtil.getSessionFactory().close();
	}
	
	public void actie1(){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Advertentie advertentie1 = new Advertentie("iPhone", "mooi", 78, true, "22-06-2015", gebruiker, categorie);
			session.save(advertentie1);
			session.save(gebruiker);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				e.printStackTrace();
			}
		} finally{
			session.close();
		}
	}
	
	public void actie2(){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Gebruiker gebruiker1 = new Gebruiker("Hoye", "Lam", "bl1a@bla.nl", "1234");
			Gebruiker gebruiker2 = new Gebruiker ("Rinesh" , "Ramadhin", "Rinesh@rinshesh.nl", "1234");
			Gebruiker gebruiker3 = new Gebruiker("Ho Ye", "lam", "bla123@bla.nl", "1234");
			Gebruiker gebruiker4 = new Gebruiker("John", "Mata", "bla21412@bla.nl", "1234");
			Advertentie advertentie1 = new Advertentie("iPhone", "mooi", 78, true, "22-06-2015", gebruiker1, categorie);
			Bod bod1 = new Bod(70,"21-06-2015",advertentie1, gebruiker2);
			Bod bod2 = new Bod(80,"22-06-2015",advertentie1, gebruiker3);
			Bod bod3 = new Bod(50,"23-06-2015",advertentie1, gebruiker4);

			session.save(gebruiker1);
			session.save(gebruiker2);
			session.save(gebruiker3);
			session.save(gebruiker4);
			session.save(advertentie1);
			session.save(bod1);
			session.save(bod2);
			session.save(bod3);
			
			tx.commit();
			actie2Betaal(gebruiker2);
			
			
		} catch (HibernateException e) {
			if (tx != null) {
				e.printStackTrace();
			}
		} finally{
			session.close();
		}
	}

	public void actie2Betaal(Gebruiker gebruiker){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			BetalingsGegevens betalingsGegevens = new BetalingsGegevens(451464161, "Rinesh", gebruiker);
			session.save(betalingsGegevens);			
			tx.commit();			
		} catch (HibernateException e) {
			if (tx != null) {
				e.printStackTrace();
			}
		} finally{
			session.close();
		}
	}
	
	public void actie3(){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Categorie categorie= new Categorie("SmartPhones");
			Categorie subCategorie = new Categorie("Apple");
			Advertentie advertentie1 = new Advertentie("iPhone", "mooi", 78, true, "22-06-2015", gebruiker, subCategorie);
					
			subCategorie.setSubCategorie(categorie);
			session.save(advertentie1);
			session.save(subCategorie);			
			tx.commit();			
		} catch (HibernateException e) {
			if (tx != null) {
				e.printStackTrace();
			}
		} finally{
			session.close();
		}
		
	}
	
	public void actie4(){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Categorie categorie= new Categorie("SmartPhones");
			Categorie subCategorie = new Categorie("Apple");
			Advertentie advertentie1 = new Advertentie("iPhone", "mooi", 78, true, "22-06-2015", gebruiker, subCategorie);
					
			subCategorie.setSubCategorie(categorie);
			session.save(advertentie1);
			session.save(subCategorie);			
			tx.commit();			
		} catch (HibernateException e) {
			if (tx != null) {
				e.printStackTrace();
			}
		} finally{
			session.close();
		}		
	}
	
	
	public void addGebruiker(String voornaam, String achternaam,String email, String wachtwoord) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Gebruiker gebruiker = new Gebruiker(voornaam, achternaam,email,wachtwoord);
			session.save(gebruiker);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				e.printStackTrace();
			}
		} finally{
			session.close();
		}
	}
	
	public void PlaatsAdvertentie(String naam, String beschrijving, Integer startPrijs, Boolean actief, String startDatum) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				e.printStackTrace();
			}
		} finally{
			session.close();
		}

	}
}