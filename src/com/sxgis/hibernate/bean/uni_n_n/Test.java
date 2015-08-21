package com.sxgis.hibernate.bean.uni_n_n;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 单向一对n：一个人对应n个地址
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

			Person lenny = new Person();
			lenny.setName("lenny");
			lenny.setAge(39);

			Person carl = new Person();
			carl.setName("carl");
			carl.setAge(35);

			Address a1 = new Address("Sector 7G");
			Address a2 = new Address("Lenny Home");
			Address a3 = new Address("Carl Home");

			lenny.getAddresses().add(a1);
			lenny.getAddresses().add(a2);
			carl.getAddresses().add(a3);
			
			session.save(lenny);
			session.save(carl);

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
