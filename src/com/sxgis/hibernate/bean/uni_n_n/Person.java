﻿package com.sxgis.hibernate.bean.uni_n_n;

import javax.persistence.*;
import java.util.*;

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

// person为实体对应的表名
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

	// N－N关联关系，使用Set来保存关联实体
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Address.class)
	@JoinTable(name = "person_address", joinColumns = @JoinColumn(name = "personid"), inverseJoinColumns = @JoinColumn(name = "addressid"))
	private Set<Address> addresses = new HashSet<Address>();

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

	// addresses属性的setter和getter方法
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<Address> getAddresses() {
		return this.addresses;
	}
}