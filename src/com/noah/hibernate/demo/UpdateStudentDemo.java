package com.noah.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.noah.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 創建session factory
		SessionFactory factory = new Configuration()
								.configure()
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			//找出已有的Student
			int s1 = 1;
			session.beginTransaction();
			System.out.println("尋找學生ID為 "+ s1 +" 的資料" );
			Student student = session.get(Student.class, s1);			
			System.out.println("更新學生資料...");
			student.setFirst_name("Woo");
			session.getTransaction().commit();
			//更新所有資料
			session = factory.getCurrentSession();
			session.beginTransaction();
			//更新email
			System.out.println("更新所有的學生信箱....");
			session.createQuery("update Student set email = 'Woo@gamil.com'");
			session.getTransaction().commit();
			System.out.println("信箱更新完畢!");
			System.out.println("完成!");
		}finally {
			factory.close();
		}
	}

}
