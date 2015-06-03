import entities.Gebruiker;
import entities.Student;
import org.hibernate.Session;

public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        saveStudentKlas(session);
        getStudentTest();

        session.getTransaction().commit();

        HibernateUtil.getSessionFactory().close();
    }

    private static void saveStudentKlas(Session session) {
        Student student = new Student("Barack", "Obama");
        Gebruiker test = new Gebruiker("Hoye", "Lam", "bla@bla.nl", "1234");
        student.setKlas();

        session.save(student);
        session.save(student);
    }

    private static void getStudentTest() {
        Student barack = (Student) HibernateUtil.getSessionFactory().getCurrentSession()
                .createQuery("from Student where voornaam = ? and achternaam = ?")
                .setString(0, "Barack")
                .setString(1, "Obama")
                .list().get(0);
        System.out.println(barack);
    }
}