package com.sxgis.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

public class GridFSTest {
	MongoClient connection;
	DB db;
	DBCollection collection;
	GridFS myFS;

	String mongoDBHost = "127.0.0.1";
	int mongoDBPort = 27017;
	String dbName = "testGridfs";
	String collectionName = "fs";

	String collectionNameFiles = "fs.files";

	public GridFSTest() throws UnknownHostException, MongoException,
			NoSuchAlgorithmException {
		_init();
	}

	public GridFSTest(String mongoDBHost, int mongoDBPort, String dbName,
			String collectionName) throws UnknownHostException, MongoException,
			NoSuchAlgorithmException {
		this.mongoDBHost = mongoDBHost;
		this.mongoDBPort = mongoDBPort;
		this.dbName = dbName;
		this.collectionName = collectionName;
		_init();
	}

	public static void main(String[] args) throws MongoException, IOException,
			NoSuchAlgorithmException {
		GridFSTest t = new GridFSTest();

		String fileName = "F:/Hadoop.pdf";
		String name = "Hadoop.pdf";

		// 把文件保存到gridfs中，并以文件的md5值为id
		 t.save(new FileInputStream(fileName), name);

		// 据文件名从gridfs中读取到文件
		GridFSDBFile gridFSDBFile = t.getByFileName(name);
		if (gridFSDBFile != null) {
			System.out.println("filename:" + gridFSDBFile.getFilename());
			System.out.println("md5:" + gridFSDBFile.getMD5());
			System.out.println("length:" + gridFSDBFile.getLength());
			System.out.println("uploadDate:" + gridFSDBFile.getUploadDate());

			System.out.println("--------------------------------------");
			gridFSDBFile.writeTo(System.out);
		} else {
			System.out.println("can not get file by name:" + name);
		}
	}

	private void _init() throws UnknownHostException, MongoException,
			NoSuchAlgorithmException {
		connection = new MongoClient(mongoDBHost, mongoDBPort);
		db = connection.getDB(dbName);
		collection = db.getCollection(collectionName);
		myFS = new GridFS(db);
	}

	/**
	 * 用给出的id，保存文件，透明处理已存在的情况 id 可以是string，long，int，org.bson.types.ObjectId 类型
	 * 
	 * @param in
	 * @param id
	 */
	public void save(InputStream in, Object id) {
		DBObject query = new BasicDBObject("_id", id);
		GridFSDBFile gridFSDBFile = myFS.findOne(query);

		if (gridFSDBFile != null)
			return;

		GridFSInputFile gridFSInputFile = myFS.createFile(in);
		gridFSInputFile.save();
		return;
	}

	/**
	 * 据id返回文件
	 * 
	 * @param id
	 * @return
	 */
	public GridFSDBFile getById(Object id) {
		DBObject query = new BasicDBObject("_id", id);
		GridFSDBFile gridFSDBFile = myFS.findOne(query);
		return gridFSDBFile;
	}

	/**
	 * 据文件名返回文件，只返回第一个
	 * 
	 * @param fileName
	 * @return
	 */
	public GridFSDBFile getByFileName(String fileName) {
		DBObject query = new BasicDBObject("_id", fileName);

		collection = db.getCollection(collectionNameFiles);
		myFS = new GridFS(db);
		GridFSDBFile gridFSDBFile = myFS.findOne(query);

		return gridFSDBFile;
	}
}