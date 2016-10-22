package sort.heap;

import edu.princeton.cs.algs4.StdOut;

/**
 * 堆排序
 * 堆排序可以分为两个阶段：
 * 		堆的构造阶段：将原始数组重新组织安排进一个堆中。
 * 		下沉排序阶段：按递减的顺序取出所有元素，并得到排序结果。
 * 性能：
 * 		将N个元素排序，堆排序只需要少于2NlogN+2N次比较以及一半次数的交换
 * @author Demons
 *
 */
public class Heap {

	// 不能被实例化
	private Heap(){}
	
	public static void sort(Comparable[] a){
		int N = a.length;
		// 构造堆，堆有序 
		for (int k = N/2; k >= 1; k--) { // 我们只需要扫描数组中一半的元素，因为可以跳过大小为1的子堆
			sink(a, k, N);
		}
		// 下沉排序
		while(N >1){
			exch(a, 1, N--); // 将最大的元素a[i]和a[N]交换，并将最大元素删除，放入堆缩小后空出来的位置。
			sink(a, 1, N); // 修复堆，再将堆中最大的元素置换出来
		}
	}
	
	private static void sink(Comparable[] a, int k, int N){
		while(2*k <= N){
			int j = 2*k; // j,j+1为k的两个子结点
			if(j < N && less(a, j, j+1)) j++; // 找到较大的子结点
			if(!less(a, k, j)) break; // 如果a[k]>a[j],已经置换完成
			exch(a, k, j); 
			k = j;
		}
	}
	
	// 二叉堆不使用数组的第一个元素a[0],所以要将数组的索引减1，以将a[0]至a[N-1]排序
	private static boolean less(Comparable[] a ,int i, int j){
		return a[i-1].compareTo(a[j-1]) < 0;
	}
	
	private static void exch(Object[] a, int i, int j){
		Object swap = a[i-1];
		a[i-1] = a[j-1];
		a[j-1] = swap;
	}
	
	private static boolean less(Comparable v, Comparable w) {
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
		// 从标准输入中读取字符串，将他们排序并输出
		Character[] a = {'M','E','R','G','E','S','O','R','T','E','X','A','M','P','L','E'};
		sort(a);
		assert isSorted(a):"数组未排序";
		show(a);
	}
}







