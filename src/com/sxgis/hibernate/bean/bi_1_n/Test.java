package com.sxgis.hibernate.bean.bi_1_n;

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

			Person p1 = new Person();
			p1.setName("Homer");
			p1.setAge(39);

			//为Person添加2个关联实体
			//因为Person不控制关联关系，所以应通过Address实体来管理关联关系
			Address a1 = new Address();
			a1.setDetail("Sector 7G");
			a1.setPerson(p1);

			Address a2 = new Address();
			a2.setDetail("At Homer Home");
			a2.setPerson(p1);

			//保存Address实体，操作将会级联到Person实体。
			//但Person实体不控制关联关系，因此保存Person不会级联到Address实体
			session.save(a1);
			session.save(a2);

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
