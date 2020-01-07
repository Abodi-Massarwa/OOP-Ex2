package dataStructure;



import java.io.Serializable;
import java.util.ArrayList;

public class MyMinHeap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<node_data> h;

	//////////////////////////  constructors  ///////////////////////////

	public MyMinHeap() {
		h = new ArrayList<node_data>();
	}

	public MyMinHeap(ArrayList<node_data> listN) {
		h = listN;
		heapBuild();
	}

	///////// 
	public void heapBuild() {
		for (int i=h.size() / 2;i >= 0;i--) {
			i--;
			Heapify(i);
		}
	}


	public node_data extractMin() {

		if (h.size() <= 0) return null;
		node_data min = h.get(0);
		h.set(0, h.get(h.size() - 1));
		h.get(0).setInfo(""+0);
		h.remove(h.size() - 1);
		Heapify(0);
		return min;
	}



	public void Heapify(int i) {
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int smallest = i;
		if (left < h.size()  && lessThan(left, smallest))
			smallest = left;
		if (right < h.size()  && lessThan(right, smallest))
			smallest = right;
		if (smallest != i) {
			swap(smallest, i);
			Heapify(smallest);
		}
	}

	public void minHeapifyUp(int i) {
		int parent;
		if(i==0)
			return;
		if((double)(i%2)!=0)
			parent=(i-1)/2;
		else parent=(i-2)/2;
		if(lessThan(i, parent))
			swap(parent,i);
		minHeapifyUp(parent);
	}

	private void swap(int n1, int n2) {
		node_data t = h.get(n1);
		h.set(n1, h.get(n2));
		h.get(n1).setInfo(n1+"");
		h.set(n2, t);
		h.get(n2).setInfo(n2+"");
	}

	public boolean lessThan(int n1, int n2) {
		if(h.get(n1).getWeight()<h.get(n2).getWeight())   return true;
		return false;
	}

	public void changeP(int n, double weight) {
		h.get(n).setWeight(weight);
		minHeapifyUp(n);
	}





}