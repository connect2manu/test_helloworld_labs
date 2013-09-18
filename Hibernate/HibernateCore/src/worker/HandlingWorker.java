package worker;

import java.util.List;
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class HandlingWorker {
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;
	
	   public static void main(String[] args) {
	      try{
	    	  	Configuration configuration = new Configuration();
	    	    configuration.configure();
	    	    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
	    	    factory = configuration.buildSessionFactory(serviceRegistry);
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	      HandlingWorker handle = new HandlingWorker();

	      /* Add few worker records in database */
	      Integer worker1 = handle.addWorker("Ram", "Prasad", 1000);
	      Integer worker2 = handle.addWorker("Rahul", "Das", 5000);
	      Integer worker3 = handle.addWorker("Steven", "Abraham", 10000);

	      /* List down all the workers */
	      handle.listWorkers();

	      /* Update worker's records */
	      handle.updateWorker(worker1, 5000);

	      /* Delete an worker from the database */
	      handle.deleteWorker(worker2);

	      /* List down new list of the workers */
	      handle.listWorkers();
	   }
	   /* Method to CREATE an worker in the database */
	   public Integer addWorker(String fname, String lname, int salary){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer workerID = null;
	      try{
	         tx = session.beginTransaction();
	         Worker worker = new Worker(fname, lname, salary);
	         workerID = (Integer) session.save(worker); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return workerID;
	   }
	   /* Method to  READ all the employees */
	   public void listWorkers( ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List employees = session.createQuery("FROM Worker").list(); 
	         for (Iterator iterator = 
	                           employees.iterator(); iterator.hasNext();){
	        	 Worker worker = (Worker) iterator.next(); 
	            System.out.print("First Name: " + worker.getFirstName()); 
	            System.out.print("  Last Name: " + worker.getLastName()); 
	            System.out.println("  Salary: " + worker.getSalary()); 
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	   /* Method to UPDATE salary for an worker */
	   public void updateWorker(Integer WorkerID, int salary ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Worker worker = 
	                    (Worker)session.get(Worker.class, WorkerID); 
	         worker.setSalary( salary );
			 session.update(worker); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	   /* Method to DELETE an worker from the records */
	   public void deleteWorker(Integer WorkerID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Worker worker = 
	                   (Worker)session.get(Worker.class, WorkerID); 
	         session.delete(worker); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
}
