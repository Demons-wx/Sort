package sort.heap.maxPQ;

/**
 * 基于堆的优先队列
 * 
 * 优先队列：
 * 		支持两种操作的数据结构：删除最大元素和插入元素。
 * 堆有序：
 * 		当一颗二叉树的每个结点都大于等于它的两个子结点时，它被称为堆有序。
 * 二叉堆：
 * 		是一组能够用堆有序的完全二叉树排序的元素，并在数组中按照层级存储(不使用数组的第一个位置)。
 * 性质：
 * 		位置K的结点的父结点的位置为K/2，而它两个子结点的位置分别是2K和2K+1
 * 性能：
 * 		插入元素：logN
 * 		删除最大元素：logN
 * @author Demons
 *
 * @param <Key>
 */
public class MaxPQ<Key extends Comparable<Key>> {

	private Key[] pq; // 基于堆的完全二叉树
	private int N = 0; // 存储于pq[1..N]中，pq[0]没有使用
	
	/**
	 * 创建一个最大容量为maxN的优先队列
	 * @param maxN
	 */
	public MaxPQ(int maxN){
		pq = (Key[]) new Comparable[maxN+1];
	}
	
	/**
	 * 返回队列是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return N == 0;
	}
	
	/**
	 * 返回队列中的元素个数
	 * @return
	 */
	public int size(){
		return N;
	}
	
	/**
	 * 向优先队列中插入一个元素
	 * @param v
	 */
	public void insert(Key v){
		pq[++N] = v;
		swim(N);
	}
	
	/**
	 * 删除并返回最大元素
	 * @return
	 */
	public Key delMax(){
		Key max = pq[1]; // 从根节点得到最大元素
		exch(1, N--); // 将其和最后一个结点交换,并抛弃
		pq[N+1] = null; // 防止越界
		sink(1);
		return max;
	}
	
	/**
	 * 比较
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean less(int i, int j){
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	/**
	 * 交换
	 * @param i
	 * @param j
	 */
	private void exch(int i, int j){
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	/**
	 * 由下至上的堆有序化(上浮)
	 * 如果堆有序化的状态因为某个结点变得比他的父结点更大而被打破，那么我们
	 * 就要通过交换它和它的父结点来修复堆。
	 * @param k
	 */
	private void swim(int k){
		while(k > 1 && less(k/2, k)){
			exch(k/2, k);
			k = k/2;
		}
	}
	
	/**
	 * 由上至下的堆有序化(下沉)
	 * 通过交换它和两个子结点中的较大者来恢复堆。
	 * @param k
	 */
	private void sink(int k){
		while(2*k <= N){
			int j = 2*k;
			if(j < N && less(j, j+1)) j++;
			if(!less(k,j)) break;
			exch(k, j);
			k = j;
		}
	}
}
