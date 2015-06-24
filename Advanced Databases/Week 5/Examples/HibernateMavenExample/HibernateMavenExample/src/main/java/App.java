
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entities.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.PostgreSQLDialect;

public class App {
	private static SessionFactory factory;
	private Date currentDate;
	//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
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

		ME.actie1();// Voer scenario 1
		ME.actie2();// Voer scenario 2
		ME.actie3();// Voer scenario 3
		ME.actie4();// Voer scenario 4
		//ME.test();
		HibernateUtil.getSessionFactory().close();
	}
	
	//Scenario 1 maak gebruiker en dat gebruiker maakt een advertentie
	public void actie1(){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Gebruiker gebruiker = new Gebruiker("hoye", "lam", "bla@bla.nl", "1234");
			Categorie categorie = new Categorie("SmartPhones");
			Advertentie advertentie1 = new Advertentie("iPhone", "mooi", 78, true,  gebruiker, categorie);
			session.save(advertentie1);
			session.save(categorie);
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
	
	//Scenario 2 meerdere gebruikers bieden op de advertentie
	public void actie2(){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Advertentie advertentie1 = (Advertentie) session.load(Advertentie.class, 1);
			Gebruiker gebruiker2 = new Gebruiker ("Rinesh" , "Ramadhin", "Rinesh@rinshesh.nl", "1234");
			Gebruiker gebruiker3 = new Gebruiker("Ho Ye", "lam", "bla123@bla.nl", "1234");
			Gebruiker gebruiker4 = new Gebruiker("John", "Mata", "bla21412@bla.nl", "1234");
			
			Bod bod1 = new Bod(70, advertentie1, gebruiker2);
			Bod bod2 = new Bod(80, advertentie1, gebruiker3);
			Bod bod3 = new Bod(50, advertentie1, gebruiker4);

			session.save(gebruiker2);
			session.save(gebruiker3);
			session.save(gebruiker4);
			session.save(bod1);
			session.save(bod2);
			session.save(bod3);
			
			tx.commit();
			actie2Betaal(gebruiker3);
			
			
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
			BetalingsGegevens betalingsGegevens = new BetalingsGegevens(451464161, "Ho Ye", gebruiker);
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
	
	//Scenario ik voeg subcategorie aan een main categorie
	public void actie3(){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Categorie categorie = (Categorie) session.load(Categorie.class, 1);
			Categorie subCategorie = new Categorie("Apple");
			//Advertentie advertentie1 = (Advertentie) session.load(Advertentie.class, 3);
			subCategorie.setParentCategorie(categorie);
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
			Gebruiker gebruiker  = (Gebruiker) session.load(Gebruiker.class, 1);
			Gebruiker gebruiker2 = (Gebruiker) session.load(Gebruiker.class, 2);
			Gebruiker gebruiker3 = (Gebruiker) session.load(Gebruiker.class, 3);
			Gebruiker gebruiker4 = (Gebruiker) session.load(Gebruiker.class, 4);
			
			Categorie categorie= new Categorie("Laptop");
			Categorie subCategorie = new Categorie("MSI");
			subCategorie.setParentCategorie(categorie);
			Advertentie advertentie2 = new Advertentie("MSI Laptop", "Goede staat", 500, true, gebruiker, categorie);
			
			AdvertentieReactie advertentieReactie1 = new AdvertentieReactie("meer informatie graag", advertentie2, gebruiker2);
			AdvertentieReactie advertentieReactie2 = new AdvertentieReactie("Is het beeldscherm mooi genoeg?", advertentie2, gebruiker3);
			AdvertentieReactie advertentieReactie3 = new AdvertentieReactie("Is het beschadigd ergens?", advertentie2, gebruiker4);
			
			session.save(subCategorie);
			session.save(advertentieReactie1);
			session.save(advertentieReactie2);
			session.save(advertentieReactie3);
			tx.commit();			
			
		} catch (HibernateException e) {
			if (tx != null) {
				e.printStackTrace();
			}
		} finally{
			session.close();
		}		
	}
	
	public void test(){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Bod ");
			List<?> list = query.list();
			Bod bod = (Bod)list.get(0);
			System.out.println(bod);
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