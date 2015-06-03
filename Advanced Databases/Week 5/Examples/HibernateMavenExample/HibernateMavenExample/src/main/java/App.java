import entities.Klas;
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
        Klas klas = new Klas("inf1a");
        student.setKlas(klas);

        session.save(klas);
        session.save(student);
    }

    private static void getStudentTest() {
        Student barack = (Student) HibernateUtil.getSessionFactory().getCurrentSession()
                .createQuery("from Student where voornaam = ? and achternaam = ?")
                .setString(0, "Barack")
                .setString(1, "Obama")
                .list().get(0);
        System.out.println(barack);


        if (! barack.getAchternaam().equals("Obama")) {
            throw new IllegalStateException("Achternaam klopt niet!");
        }
        if (! barack.getKlas().getKlasCode().equals("inf1a")) {
            throw new IllegalStateException("Klas klopt niet");
        }
    }
}