

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;



public class TestDao {
	public static void main(String[] args) throws InterruptedException {

		// step 1 create javabean or pojo class object n
		// set the values using setter
		Person person = new Person();
		//person.setId(232);
		person.setName("Nirmal");

		Configuration configuration = new Configuration();
		configuration.configure().addAnnotatedClass(Person.class);// it will read all values from
									// hibernate.cfg.xml

	
        ServiceRegistry serviceRegisteryBuilder = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        //SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		SessionFactory factory = configuration.buildSessionFactory(serviceRegisteryBuilder);

		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();
		//transaction.begin();

		
		Person per1=(Person) session.get(Person.class, 1);
		System.out.println("-------------first-------"+per1);
		transaction.commit();
		session.close();
		System.out.println("----second session-----------");
		Session session2 = factory.openSession();
		session2.beginTransaction();
		
		
		Person per12=(Person) session2.get(Person.class, 1);
		System.out.println("-------------second-------"+per12);
		
/*		String update_qry="from Person where id=1";

		
	Query query=	session.createQuery(update_qry);
	query.setCacheable(true);
	//query.setInteger(0, 1);
	Person person2=(Person) query.uniqueResult();
			
	transaction.commit();
	session.close();
	

	System.out.println("----first--------------"+person2);
	Session session2 = factory.openSession();
	session2.beginTransaction();
	Query query1=	session2.createQuery("from Person where id=1");
	query1.setCacheable(true);

	Person person3=(Person) query1.uniqueResult();
			
	System.out.println(person3);
		*/
		
	session2.getTransaction().commit();
	session2.close();

	}

}
