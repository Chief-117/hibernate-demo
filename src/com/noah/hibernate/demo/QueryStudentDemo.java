package com.noah.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.noah.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 創建session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			//開始
			session.beginTransaction();
			//查詢
			List<Student> theStudentList = 
					session.createQuery("from Student").list();
											//須注意此處的Student 需要符合 類別名
			//秀出學生
			showStudent(theStudentList);
			//查詢學生的lastName 為 Cena
			theStudentList = session.createQuery("from Student s where s.last_name = 'Cena'").list();
			
			//秀出lastName為Cena
			showStudent(theStudentList);
			
			//查詢 firstName 為 John 或者 Paul
			theStudentList = session.createQuery("from Student s where s.first_name = 'Noah' OR s.last_name = 'Walker'").list();
			showStudent(theStudentList);
			
			//查詢 @e-utc.com.tw結尾的資料
			theStudentList =  session.createQuery("from Student where email like '%@e-utc.com.tw'").list();
			showStudent(theStudentList);
			session.getTransaction().commit();
			
			System.out.println("完成!");
		} finally {
			factory.close();
		}
	}

	private static void showStudent(List<Student> theStudentList) {
		if(theStudentList.size()>0) {
			System.out.println("查到的資料如下:");
			for(Student student : theStudentList) {
				System.out.println(student);
			}			
		}else {
			System.out.println(">> 查無資料 <<");
		}
	}

}
