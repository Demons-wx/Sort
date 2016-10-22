package sort.merge;

import edu.princeton.cs.algs4.StdOut;

/**
 * 自底向上的归并排序
 * 思想：
 * 		自底向上的归并排序会多次遍历整个数组，根据子数组大小进行两两归并。
 * 		子数组的大小sz的初始值为1，每次加倍。最后一个子数组的大小只有在数组大小是sz的偶数倍
 * 		的时候才会等于sz(否则它会比sz小)。
 * @author Demons
 *
 */
public class MergeBU {
	private static Comparable[] aux; //归并所需的辅助数组
	
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
	
	public static void sort(Comparable[] a){
		// 进行lgN次两两归并
		int N = a.length;
		aux = new Comparable[N];
		for(int sz = 1; sz < N; sz = sz+sz){ // sz子数组大小
			for(int lo = 0; lo <= N - sz; lo += sz+sz){ // lo:子数组索引
				merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
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
