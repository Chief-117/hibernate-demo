package com.noah.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.noah.hibernate.demo.entity.Student;

public class PKDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			// 創建3個student object
			System.out.println("創建多個學生object.....");
			Student s1 = new Student("Magic", "Johnson", "MagicJohnson@e-utc.com.tw");
			Student s2 = new Student("John", "Cena", "JohnCena@e-utc.com.tw");
			Student s3 = new Student("Paul", "Walker", "PaulWalker@e-utc.com.tw");
			// start transaction
			session.beginTransaction();
			// 儲存 student object
			session.persist(s1); // save在hibernate 6.0之後已經被deprecated
			session.persist(s2);
			session.persist(s3);
			// commit transaction
			session.getTransaction().commit();
			System.out.println("完成!");
		} finally {
			factory.close();
		}

	}

}
