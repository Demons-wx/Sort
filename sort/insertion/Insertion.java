package sort.insertion;

import edu.princeton.cs.algs4.StdOut;

/**
 * 插入排序
 * 思路：
 * 		将第i个元素插入到前面已经有序的i-1个元素中。
 * 		首先，将a[i]和a[i-1]比较，如果a[i] < a[i-1] 交换位置
 * 		继续比较，a[i-1]和a[i-2]，小于则继续交换，大于则交换停止，此时a[]中前i个元素已经有序
 * 性能：
 * 		最好情况下：需要N-1次比较和0次交换。
 * 		平均情况下：需要~ N^2/4次比较和~ N^2/4次交换
 * 		最坏情况下：需要~ N^2/2次比较和~ N^2/2次交换
 * @author Demons
 *
 */
public class Insertion {
	public static void sort(Comparable[] a){
		// 将a[]按升序排列
		int N = a.length;
		for(int i=1; i<N; i++){
			// 将a[i] 插入到a[i-1]、a[i-2]、a[i-3]...之中
			for(int j=i; j>0 && less(a[j], a[j-1]); j--){
				exch(a, j, j-1);
			}
		}
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
