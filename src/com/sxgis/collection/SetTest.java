package com.sxgis.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author qianj
 * @version 1.0.0
 * @2011-7-12 上午11:40:19
 */
public class SetTest {
	public static void main(String[] args) {
		Set set = new HashSet();

		set.add(new String("11"));
		set.add(new String("222"));

		Iterator i = set.iterator();// 先迭代出来

		while (i.hasNext()) {// 遍历
			System.out.println(i.next());
		}

	}
}