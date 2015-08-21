package com.sxgis.hibernate.bean.bi_n_n;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 双向一对n：一个人对应n个地址，n个地址也可对应一个人
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

			//因为Person不控制关联关系，所以应通过Address实体来管理关联关系
			Address a1 = new Address();
			a1.setDetail("Sector 7G");
			lenny.getAddresses().add(a1);
			
			carl.getAddresses().add(a1);

			Address a2 = new Address();
			a2.setDetail("At Lenny Home");
			lenny.getAddresses().add(a2);
			
			Address a3 = new Address();
			a3.setDetail("At Carl Home");
			carl.getAddresses().add(a3);

			//保存Person实体，操作将会级联到Address实体。若保存Address实体，会导致连接表：person_address无内容
//			session.save(a1);
//			session.save(a2);
//			session.save(a3);
			
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
