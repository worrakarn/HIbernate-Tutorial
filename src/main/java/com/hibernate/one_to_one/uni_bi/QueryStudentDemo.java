package com.hibernate.one_to_one.uni_bi;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// start a transaction
			session.beginTransaction();
			
			// read Instructors
			//int theId = 1;
			
			//Instructor theTnstructor = session.get(Instructor.class, theId);
			//System.out.println(theTnstructor.toString());
			
			//Query<Instructor> query = session.createQuery("select i from Instructor i join fetch i.instructorDetail where i.id=:theid");
			
			Query<Instructor> query = session.createQuery("select i from Instructor i join fetch i.instructorDetail",Instructor.class);
			
			//query.setParameter("theid", theId);
			
			List<Instructor> theInstructor = query.getResultList();
			
			theInstructor.forEach(System.out::println);
			
			theInstructor.stream().map(p->p.getInstructorDetail()).forEach(System.out::println);
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}





