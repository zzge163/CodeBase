package com.sxgis.basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: chengtai.he
 * @created:2009-12-9 上午09:59:57
 */
public class ParamTransfer {
	private Student stu = new Student();

	public void changeParam() {
		System.out.println(this.stu.getAge());
		System.out.println(this.stu.getName());
		
		new Transfer().transfer(stu);

		System.out.println(this.stu.getAge());
		System.out.println(this.stu.getName());
	}

	public static void main(String[] args) {
		new ParamTransfer().changeParam();
	}
}