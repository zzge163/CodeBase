package com.sxgis.hibernate.bean.uni_1_1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 单向一对一：一个人对应一个地址
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

			session.save(p1);

			
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
