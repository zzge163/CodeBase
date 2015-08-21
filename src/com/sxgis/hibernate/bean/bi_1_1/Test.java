package com.sxgis.hibernate.bean.bi_1_1;

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

			Person p1 = new Person();
			p1.setName("burns");
			p1.setAge(81);

			Address a1 = new Address();
			a1.setDetail("Nuclear Power Station");

			// 设置两个实体的关联关系，
			// 因为Person实体不控制关联关系(指插入时，但查询时两方均可查询到对方)，因此让Address实体控制关联关系
			a1.setPerson(p1);

			session.save(a1);

			// Address a = (Address) session.get(Address.class, 1);
			// Person p = (Person) session.get(Person.class, 1);

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
