package com.sxgis.mongodb;

import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoConnTest {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		// 方式一：直接连接单一mongodb服务器（注意这种方式不会自动发现mongodb集群中的主服务器）
		MongoClient mongoClient = new MongoClient();
//		// 方式二（指定ip）：
//		MongoClient mongoClient = new MongoClient( "localhost" );
//		// 方式二（指定ip、端口）：
//		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
//		// 方式三：连接到mongodb服务器集群（会自动发现主服务器）
//		MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017),
//		                                      new ServerAddress("localhost", 27018),
//		                                      new ServerAddress("localhost", 27019)));

		DB db = mongoClient.getDB( "test" );
		
		// 2、安全验证
//		boolean auth = db.authenticate(myUserName, myPassword);
		
		// 3、获取所有集合（集合类似于mysql中的表）
		Set<String> colls = db.getCollectionNames();

		System.out.println("3、获取所有集合（集合类似于mysql中的表）-------------");
		for (String s : colls) {
		    System.out.println(s);
		}
		
		
		// 
	}

}
