package com.noah.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.noah.hibernate.demo.entity.Student;

public class CreatStudentDemo {

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
			System.out.println("創建學生object.....");
			Student s1 = new Student("Noah","Tseng","noah.tseng@e-utc.com.tw");
			//start transaction
			session.beginTransaction();
			//儲存 student object
			session.persist(s1); // save在hibernate 6.0之後已經被deprecated
			//commit transaction
			session.getTransaction().commit();
			System.out.println("完成!");
		}finally {
			factory.close();
		}
	}

}
