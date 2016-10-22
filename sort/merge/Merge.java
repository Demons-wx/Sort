package sort.merge;

import edu.princeton.cs.algs4.StdOut;

/**
 * 归并排序
 * 		自顶向下的归并排序
 * 思想：
 * 		如果她能将两个子数组排序，它就能够通过归并两个子数组来将整个数组排序。
 * 		sort()方法的作用其实是在于安排多次merge()方法调用的正确顺序。
 * 实现：
 * 		要对子数组a[lo..hi]进行排序，先将它分为a[lo..mid] 和 a[mid+1..hi]两部分，分别通过递归调用将他们单独排序，
 * 		最后将有序的子数组归并为最终的排序结果。
 * 结论：
 * 		对于长度为N的任意数组，自顶向下的归并排序需要 1/2NlogN 至 NlogN 次比较。
 * 		对于长度为N的任意数组，自顶向下的归并排序最多需要访问数组 6NlogN 次。
 * @author Demons
 *
 */
public class Merge {
	
	private static Comparable[] aux; // 归并所需的辅助数组
	
	public static void sort(Comparable[] a){
		aux = new Comparable[a.length];	// 一次性分配空间
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		// 将数组a[lo..hi]排序
		if(hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo ,mid); // 递归的将左半边排序
		sort(a, mid+1, hi); // 递归的将右半边排序
		if(less(a[mid+1], a[mid])){ // 当右半边的第一个元素大于左半边的最后一个元素时，视为数组已经有序，跳过归并
			merge(a, lo, mid, hi); // 归并结果
		}
	}

	/* 原地归并的抽象方法 */
	public static void merge(Comparable[] a, int lo, int mid, int hi){
		// 将a[lo..mid] 和 a[mid+1..hi] 归并
		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			// 将a[lo..hi] 复制到 aux[lo..hi]
			aux[k]  = a[k];
		}
		for (int k = lo; k <= hi; k++) { 
			// 归并回a[lo..hi]
			if(i > mid){
				a[k] = aux[j++]; // 左半边用尽，取右半边元素
			}else if(j > hi){
				a[k] = aux[i++]; // 右半边用尽，取左半边元素
			}else if(less(aux[j], aux[i])){
				a[k] = aux[j++]; // 右半边的当前元素小于左半边的当前元素，取右半边元素
			}else{
				a[k] = aux[i++]; // 右半边的当前元素大于等于左半边当前元素，取左半边元素
			}
		}
	}
	
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
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
//		Integer[] a = {8,4,22,45,1,23,67}; // Integer数组实现了Comparable接口
		Character[] a = {'M','E','R','G','E','S','O','R','T','E','X','A','M','P','L','E'};
		sort(a);
		assert isSorted(a);
		show(a);
		
	}
}
