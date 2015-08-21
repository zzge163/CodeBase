package com.sxgis.hibernate.bean.uni_n_1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 单向多对一：多个人对应单个地址
 * 
 * @author zhangzhe
 * 
 */
public class Test {

	public static void main(String[] args) {
		// 读取hibernate.cfg.xml文件
		Configuration cfg = new Configuration().configure();

		// 建立SessionFactory
		SessionFactory factory = cfg.buildSessionFactory();

		// 取得session
		Session session = null;
		try {
			session = factory.openSession();
			// 开启事务
			session.beginTransaction();

			Address a1 = new Address("Sector 7G");

			Person p1 = new Person();
			p1.setName("homer");
			p1.setAge(38);
			p1.setAddress(a1);
			
			Person p2 = new Person();
			p2.setName("Lenny");
			p2.setAge(35);
			p2.setAddress(a1);
			
			Person p3 = new Person();
			p3.setName("carl");
			p3.setAge(36);
			p3.setAddress(a1);

			session.save(p1);
			session.save(p2);
			session.save(p3);
			
//			Person pget = (Person) session.get(Person.class, 5);

			// 提交事务
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				if (session.isOpen()) {
					// 关闭session
					session.close();
				}
			}
		}
	}

}
