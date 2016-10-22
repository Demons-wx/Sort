package sort.quick;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 快速排序
 * 思路：
 * 		通过递归的调用切分来实现排序。
 * 实现：
 * 		1，先取a[lo]作为切分元素v，即那个将会被排定的元素。
 * 		2，在循环中，a[i]小于v时，我们增大i。a[j]大于v时，我们减小j。
 * 		3，当遇到a[i]大于v且a[j]小于v时，我们交换a[i]和a[j]。保证i左侧的元素都不大于v，j右侧的元素都不小于V。
 * 		4，当指针相遇时，我们交换a[lo]和a[j],切分结束。这样切分值就留在了a[j]中了。
 * 性能：
 * 		时间复杂度：NlogN
 * @author Demons
 *
 */
public class Quick {
	
	public static void sort(Comparable[] a){
//		StdRandom.shuffle(a);	// 清除对输入的依赖
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return; // 终止递归
		int j = partition(a, lo, hi);	// 切分
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}
	
	private static int partition(Comparable[] a, int lo, int hi){
		int i = lo, j = hi+1;	// 左右扫描指针
		Comparable v = a[lo];	// 切分元素
		while(true){
			// 扫描左右，检查扫描是否结束并交换元素
			while(less(a[++i], v)){
				if(i == hi) break;
			}
			while(less(v, a[--j])){
				if(j == lo) break;
			}
			if(i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);	// 将v=a[j]放入正确的位置
		return j;
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
		Integer[] a = {1,2,3,4,5,6,7,8}; // Integer数组实现了Comparable接口
		sort(a);
		assert isSorted(a):"数组未排序";
		show(a);
	}
}
