package com.sxgis.hibernate.bean.uni_1_n;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 对应表字段：addressid，detail，personid（该字段为外键，此处非常特殊，本类中不能有personid这个field及其getter()
 * setter()方法）
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
@Entity
@Table(name = "address")
public class Address {
	// 标识属性
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressid;
	// 地址详细信息
	private String detail;

	// 无参数的构造器
	public Address() {
	}

	// 初始化detail属性的构造器
	public Address(String detail) {
		this.detail = detail;
	}

	// addressid属性的setter和getter方法
	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}

	public int getAddressid() {
		return this.addressid;
	}

	// detail属性的setter和getter方法
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDetail() {
		return this.detail;
	}

	@Override
	public String toString() {
		return "Address [addressid=" + addressid + ", detail=" + detail + "]";
	}

}