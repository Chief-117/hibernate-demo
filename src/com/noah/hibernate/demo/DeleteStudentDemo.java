package com.noah.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.noah.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 創建session factory
		SessionFactory factory = new Configuration()
								.configure()
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			int s1 = 6;
			session.beginTransaction();
			System.out.println("尋找學生ID為 "+ s1 +" 的資料" );
			Student student = session.get(Student.class, s1);
			System.out.println("刪除學生資料...");
//			session.remove(student);// or delete (before 5.2)

			
			System.out.println("刪除ID為3的學生資料");
			session.createQuery("delete from Student where ID = 3").executeUpdate();
			session.getTransaction().commit();
			System.out.println("完成!");
		}finally {
			factory.close();
		}
	}

}
