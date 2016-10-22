package sort.selection;

import edu.princeton.cs.algs4.StdOut;

/**
 * 选择排序
 * 思想：
 * 		首先，找到数组中最小的那个元素，其次，将它和数组中第一个元素交换位置，
 * 		(如果第一个元素就是最小元素，那么它就和自己交换位置)
 * 		再次，在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置。如此往复直到整个数组有序。
 * 特点：
 * 		运行时间和输入无关。已经有序的数组和一个元素随机排列的数组所用的排序时间竟然一样
 * 		数据移动是最少的。每次交换都会改变两个数组元素的值，因此选择排序用了N次交换，交换次数和
 * 		数组的大小是线性关系。我们将研究的其他任何算法都不具备这种特征。
 * 
 * 对于长度为N的数组，选择排序需要大约N^2/2次比较和N次交换。
 * @author Demons
 *
 */
public class Selection {
	public static void sort(Comparable[] a){
		// 将 a[] 按升序排列
		int N = a.length;
		for (int i = 0; i < N; i++) {
			// 将a[i]和a[i+1...N]中最小的元素交换
			int min = i;
			for (int j = i+1; j < N; j++) {
				if(less(a[j],a[min])){
					min = j;
				}
			}
			exch(a, i, min);
		}
	}
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
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
//		String[] a = In.readStrings();
		
		sort(a);
		/**
		 * assert的用法：
		 * 		assert conditon 这里condition是一个必须为真(true)的表达式。如果表达式的结果为true，那么断言为真，并且无任何行动
		 *		如果表达式为false，则断言失败，则会抛出一个AssertionError对象。
		 *		assert condition:expr 冒号后跟的是一个表达式，通常用于断言失败后的提示信息
		 * 在Eclipse开发环境中，运行时，我们必须配置运行时的选项"Run"，在Arguments页面中的 "VM Arguments" 中填入-ea选项。才能让断言在运行时起作用。
		 */
		assert isSorted(a):"数组未排序";
		show(a);
		
	}
}
