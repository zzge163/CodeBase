package com.sxgis.hibernate.bean.uni_1_n;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 对应表字段：personid，name，age,主键是personid，没有设置外键
 * 
 * @author zhangzhe
 * 
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

	// 1－N关联关系，使用Set来保存关联实体
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Address.class)
	@JoinColumn(name = "personid", nullable = false)
	// @JoinColumn指定关联实体Address的外键personid
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

	@Override
	public String toString() {
		return "Person [id=" + personid + ", name=" + name + "]";
	}
}