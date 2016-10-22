package sort.shell;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
/**
 * 希尔排序
 * 		加强版的插入排序
 * 思想：
 * 		使数组中任意间隔为h的元素都是有序的，这样的数组被称为h有序数组。
 * 		换句话说，一个h有序的数组就是h个互相独立的有序数组编织在一起组成的一个数组。
 * 		h初始值 > N/3，h=h/3，当h递减到1时，排序完成。
 * @author Demons
 *
 */
public class Shell {

	public static void sort(Comparable[] a){
		// 将a[]按照升序排列
		int N = a.length;
		int h = 1;
		while(h < N/3){
			h = 3 * h + 1; // 1,4,13,40,121....
		}
		while(h >= 1){
			// 将数组变为h有序
			for (int i = h; i < N; i++) {
				// 将a[i]插入到a[i-h],a[i-2*h],a[i-3*h]...之中
				for (int j = i; j >= h && less(a[j], a[j-h]); j-=h) {
					exch(a, j, j-h);
				}
			}
			h = h / 3;
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
		String[] a = In.readStrings();
		sort(a);
		assert isSorted(a);
		show(a);
		
	}
}
