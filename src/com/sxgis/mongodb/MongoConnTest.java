package com.sxgis.mongodb;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class MongoConnTest {

	public static void main(String[] args) throws UnknownHostException {
		// 方式一：直接连接单一mongodb服务器（注意这种方式不会自动发现mongodb集群中的主服务器）
//		MongoClient mongoClient = new MongoClient();
		// // 方式二（指定ip）：
		// MongoClient mongoClient = new MongoClient( "localhost" );
		// // 方式二（指定ip、端口）：
		 MongoClient mongoClient = new MongoClient( "localhost" , 3333 );
		// // 方式三：连接到mongodb服务器集群（会自动发现主服务器）
		// MongoClient mongoClient = new MongoClient(Arrays.asList(new
		// ServerAddress("localhost", 27017),
		// new ServerAddress("localhost", 27018),
		// new ServerAddress("localhost", 27019)));

		DB db = mongoClient.getDB("test");
//		db.getMongo().slaveOk();
		db.slaveOk();

		// 2、安全验证
		// boolean auth = db.authenticate(myUserName, myPassword);

		// 3、获取所有集合（集合类似于mysql中的表）
		Set<String> colls = db.getCollectionNames();

		System.out.println("3、获取所有集合（集合类似于mysql中的表）-------------");
		for (String s : colls) {
			System.out.println(s);
		}

		// 4、获取单一集合
		DBCollection coll = db.getCollection("person");

		// 5、设置 write concern
		mongoClient.setWriteConcern(WriteConcern.JOURNALED);

		// 6、增加文档（类似向数据库中增加一条记录）
//		System.out.println("5、增加文档（类似向数据库中增加一条记录）-------------");
//		BasicDBObject doc = new BasicDBObject("name", "MongoDB")
//				.append("type", "database").append("count", 1)
//				.append("info", new BasicDBObject("x", 203).append("y", 102));
//		coll.insert(doc);

		// 7、findOne()方法：查找集合中的第一个文档
		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);

		// 8、添加多条文档
//		System.out.println("8、查询集合中文档总个数-------------");
//		for (int i = 0; i < 10; i++) {
//			coll.insert(new BasicDBObject("i", i));
//		}
//		System.out.println(coll.getCount());

		// 9、使用游标查询所有文档
		System.out.println("9、使用游标查询所有文档-------------");
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}

		// 10、查询单一文档
		System.out.println("10、查询单一文档-------------");
		BasicDBObject query = new BasicDBObject("i", 7);
		// query = new BasicDBObject("j", new BasicDBObject("$ne",
		// 3)).append("k",
		// new BasicDBObject("$gt", 10));
		cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}

		// 11、查询多个文档
		System.out.println("11、查询多个文档-------------");
		// e.g. find all where i > 5
		query = new BasicDBObject("i", new BasicDBObject("$gt", 5));
		cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}

		// i.e. 20 < i <= 30
		System.out.println("11、查询多个文档-------------");
		query = new BasicDBObject("i", new BasicDBObject("$gt", 2).append(
				"$lte", 5));
		cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}

		// 12、创建索引
		// create index on "i", 升序，（1代表升序、2代表降序）。
		System.out.println("12、创建索引-------------");
		// coll.createIndex(new BasicDBObject("i", 1));
		// coll.createIndex(new BasicDBObject("name", 2));

		// 13、查询集合上的索引
		System.out.println("13、查询集合上的索引-------------");
		List<DBObject> list = coll.getIndexInfo();
		for (DBObject o : list) {
			System.out.println(o);
		}

		// 14、查询所有数据库实例名称
		System.out.println("14、查询所有数据库实例名称 -------------");
		for (String s : mongoClient.getDatabaseNames()) {
			System.out.println(s);
		}

		// 15、删除数据库实例
		System.out.println("15、删除数据库实例 -------------");
		// mongoClient.dropDatabase("zzDB");

	}
}
