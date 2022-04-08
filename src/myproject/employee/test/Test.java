package myproject.employee.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import myproject.employee.pojo.Employee;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
		Configuration cfg = new Configuration();
		cfg.setProperty("hibernate.connection.driver_class", "oracle.jdbc.OracleDriver");
		cfg.setProperty("hibernate.connection.url", "jdbc:oracle:thin:@localhost:1521:xe");
		cfg.setProperty("hibernate.connection.user", "system");
		cfg.setProperty("hibernate.connection.password", "tiger");
		cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		cfg.setProperty("hibernate.show_sql", "true");
//		cfg.addResource("myproject/employee/resources/Employee.hbm.xml");
		cfg.addAnnotatedClass(Employee.class);
		sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Employee emp = new Employee();
		emp.setEno(103);
		emp.setEname("Garima");
		emp.setEsal(75412);
		emp.setEaddr("Indore");
		session.save(emp);
		tx.commit();
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close(); 
			sessionFactory.close();
		}
	}
}
