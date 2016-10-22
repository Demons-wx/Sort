package sort.quick;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 三向切分的快速排序
 * 思路：
 * 		从左至右遍历数组一次，维护一个指针lt使得a[lo..lt-1]中的元素都小于v；
 * 		一个指针gt使得a[gt+1..hi]中的元素都大于v；
 * 		一个指针i，使得a[lt..i-1]中的元素都等于v；
 * 		当while循环中的i==gt成立时，a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]成立。
 * 图示：
 * 		算法P189
 * 性能：
 * 		对于包含大量重复元素的数组，三向切分将排序时间从线性对数级别降低到了线性级别。
 * 		经过精心调优的快速排序在绝大多数计算机上的绝大多数应用中都会比其他基于比较的
 * 		排序算法更快。
 * @author Demons
 *
 */
public class Quick3way {
	
	public static void sort(Comparable[] a){
		StdRandom.shuffle(a);	// 清除对输入的依赖
		sort(a, 0, a.length-1);
	}
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return;
		int lt = lo, i = lo+1, gt = hi;
		Comparable v = a[lo]; // 用来比较的v，为数组的第一个元素
		while(i <= gt){
			int cmp = a[i].compareTo(v);
			if(cmp < 0){ // a[i] < v
				exch(a, lt++, i++); // 将a[lt]和a[i]交换，将lt和i加1；
			}else if(cmp > 0){ // a[i] > v
				exch(a, i, gt--); // 将a[gt]和a[i]交换，将gt减1；
			}else{ // a[i] = v
				i++; 
			} // 现在a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]成立
		}
		sort(a, lo, lt-1);
		sort(a, gt+1, hi);
	}
	// 比较
		private static boolean less(Comparable v, Comparable w){
			return v.compareTo(w) < 0;
		}
		// 交换
		private static void exch(Comparable[] a, int i, int j){
			Comparable t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
		private static void show(Comparable[] a){
			// 在单行中打印数组
			for(int i=0; i<a.length; i++){
				StdOut.print(a[i]+" ");
			}
			StdOut.println();
		}
		public static boolean isSorted(Comparable[] a){
			// 测试数组元素是否有序
			for(int i=1; i<a.length; i++){
				if(less(a[i], a[i-1])){
					return false;
				}
			}
			return true;
		}
		public static void main(String[] args) {
			// 从标准输入中读取字符串，将他们排序并输出
			Integer[] a = {8,4,22,45,1,23,67}; // Integer数组实现了Comparable接口
			sort(a);
			assert isSorted(a):"数组未排序";
			show(a);
		}
}
