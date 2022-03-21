package com.noah.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.noah.hibernate.demo.entity.Employee;
import com.noah.hibernate.demo.entity.Student;

public class CRUDEmployeeDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			System.out.println("創建開始");

//			讀取
			@SuppressWarnings("unchecked")
			List<Employee> employeeList = session.createQuery("from Employee").getResultList();//list() hibernate 5.2 已deprecated 
			for(Employee employee:employeeList) {
				System.out.println(employee);
			}
//			刪除
			int num = 5;
			Employee employee = session.get(Employee.class, num);
			session.remove(employee);
//			創建
			Employee e1 = new Employee("noah","tseng","noah.tseng@gmail.com");
			session.persist(e1);//save()在5.2以前可使用
//			更新
			int num2 = 6;
			Employee employeeUpdate = session.get(Employee.class, num2);
			employeeUpdate.setFirst_name("update2");
			
			session.getTransaction().commit();
			System.out.println("完成");
		} finally {
			factory.close();
		}
	}
}
