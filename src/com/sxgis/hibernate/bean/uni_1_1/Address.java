package com.sxgis.hibernate.bean.uni_1_1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Address entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "address", catalog = "test")
public class Address implements java.io.Serializable {

	// Fields

	private Integer addressid;
	private String detail;

	// Constructors

	/** default constructor */
	public Address() {
	}

	/** full constructor */
	public Address(String detail) {
		this.detail = detail;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ADDRESSID", unique = true, nullable = false)
	public Integer getAddressid() {
		return this.addressid;
	}

	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}

	@Column(name = "DETAIL", length = 20)
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Address [addressid=" + addressid + ", detail=" + detail + "]";
	}

}