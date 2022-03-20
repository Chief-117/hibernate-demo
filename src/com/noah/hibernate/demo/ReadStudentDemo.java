package com.noah.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.noah.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 創建session factory
		SessionFactory factory = new Configuration()
								.configure()
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			//創建student object
			Student s1 = new Student("Noah","Test","noah.Test@e-utc.com.tw");
			//start transaction
			session.beginTransaction();
			//儲存 student object
			System.out.println("儲存 student.....");
			System.out.println(s1);
			session.persist(s1); // save在hibernate 6.0之後已經被deprecated
			session.getTransaction().commit();
			
			System.out.println("儲存 student..... ID為 : " + s1.getId());
			System.out.println("\n讀取學生object..... ID為 : " + s1.getId());
			//commit transaction
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			//讀取某ID的Student Object
			Student student = session.get(Student.class,s1.getId());
			
			System.out.println("讀取完成 "+student);
			session.getTransaction().commit();
			
			System.out.println("完成!");
		}finally {
			session.close();
			factory.close();
		}
	}

}
