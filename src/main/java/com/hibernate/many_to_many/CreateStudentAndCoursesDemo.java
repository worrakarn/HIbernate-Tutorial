package com.hibernate.many_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import com.hibernate.entity.Review;
import com.hibernate.entity.Student;

public class CreateStudentAndCoursesDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// start a transaction
			session.beginTransaction();
						
			// create a course
			Course tempCourse1 = new Course("Pacman - How To Score One Million Points");
			Course tempCourse2 = new Course("Ball - How To Play Ball");
						
			// save the course
			System.out.println("\nSaving the course ...");
			session.save(tempCourse1);
			System.out.println("Saved the course: " + tempCourse1);
			
			session.save(tempCourse2);
			System.out.println("Saved the course: " + tempCourse2);
			
			// create the students
			Student tempStudent = new Student("John", "Doe", "john@luv2code.com");
			
			// add courses to the student
			tempStudent.addCourse(tempCourse1);
			tempStudent.addCourse(tempCourse2);
			
			// save the students
			System.out.println("\nSaving students ...");
			session.save(tempStudent);
			System.out.println("Saved students: " + tempCourse1.getStudents());
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			
			// add clean up code
			session.close();
			
			factory.close();
		}
	}

}





