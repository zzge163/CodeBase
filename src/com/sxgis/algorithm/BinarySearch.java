package com.sxgis.algorithm;

/**
 * 二分查找又称折半查找，它是一种效率较高的查找方法。 　　【二分查找要求】：1.必须采用顺序存储结构 2.必须按关键字大小有序排列。
 * 
 * @author Administrator
 * 
 */
public class BinarySearch {
	public static void main(String[] args) {
		int[] src = new int[] { 1, 3, 5, 7, 9 };
		// System.out.println(binarySearch(src, 8 ));

		// System.out.println(binarySearch(src, 3));

		System.out.println(binarySearchInsert(src, 2));
		System.out.println(binarySearchInsert(src, 3));
		System.out.println(binarySearchInsert(src, 4));
		System.out.println(binarySearchInsert(src, 5));
		System.out.println(binarySearchInsert(src, 6));
		System.out.println(binarySearchInsert(src, 7));
		System.out.println(binarySearchInsert(src, 8));

		System.out.println(binarySearchInsert(src, 0));
		System.out.println(binarySearchInsert(src, 10));

		// System.out.println(binarySearch(src, 4));
		// System.out.println(binarySearch(src, 3, 0, src.length - 1));
	}

	public static int binarySearchInsert(int[] srcArray, int des) {
		if(des<=srcArray[0])
			return 0;
		
		int low = 0;
		int high = srcArray.length - 1;
		
		while (low <= high) {
			int middle = (low + high) / 2;
			if (des == srcArray[middle]) {
				return middle;
			} else if (middle - 1 >= 0 && des == srcArray[middle - 1]) {
				return middle - 1;
			} else if (middle - 1 >= 0 && des > srcArray[middle - 1]
					&& des < srcArray[middle]) {
				return middle;
			} else if (des < srcArray[middle]) {
				high = middle - 1;
			} else  {
				low = middle + 1;
			} 
			
//			else if (des > srcArray[middle]) {
//				low = middle + 1;
//			} else {
//				System.out.println(des);
//				System.out.println(low);
//				System.out.println(high);
//				System.out.println(middle);
//			}
			
		}
		return -1;
	}

	/**
	 * * 二分查找算法 * *
	 * 
	 * @param srcArray
	 *            有序数组 *
	 * @param des
	 *            查找元素 *
	 * @return des的数组下标，没找到返回-1
	 */
	public static int binarySearch(int[] srcArray, int des) {

		int low = 0;
		int high = srcArray.length - 1;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (des == srcArray[middle]) {
				return middle;
			} else if (des < srcArray[middle]) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return -1;
	}

	/**
	 * 二分查找特定整数在整型数组中的位置(递归)
	 *
	 * @paramdataset
	 * @paramdata
	 * @parambeginIndex
	 * @paramendIndex
	 * @returnindex
	 */
	public static int binarySearch(int[] dataset, int data, int beginIndex,
			int endIndex) {
		int midIndex = (beginIndex + endIndex) / 2;
		if (data < dataset[beginIndex] || data > dataset[endIndex]
				|| beginIndex > endIndex) {
			return -1;
		}
		if (data < dataset[midIndex]) {
			return binarySearch(dataset, data, beginIndex, midIndex - 1);
		} else if (data > dataset[midIndex]) {
			return binarySearch(dataset, data, midIndex + 1, endIndex);
		} else {
			return midIndex;
		}
	}

}