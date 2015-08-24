﻿package com.sxgis.hibernate.bean.bi_1_1;

import javax.persistence.*;

/**
 * Description: <br/>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> <br/>
 * Copyright (C), 2001-2010, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
@Entity
@Table(name = "person")
public class Person {
	// 标识属性
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int personid;
	
	// Person的name属性
	private String name;
	
	// 保留Person的age属性
	private int age;
	
	// 保留关联实体的addess属性
	/* 设置了mappedBy属性表明Person实体不控制关联关系， */
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Address.class)
	private Address address;

	// 无参数的构造器
	public Person() {
	}

	// 初始化全部属性的构造器
	public Person(int personid, String name, int age) {
		this.personid = personid;
		this.name = name;
		this.age = age;
	}

	// personid属性的setter和getter方法
	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public int getPersonid() {
		return this.personid;
	}

	// name属性的setter和getter方法
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// age属性的setter和getter方法
	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	// address属性的setter和getter方法
	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return this.address;
	}

}