package dataStructure;

import java.util.Collection;
import java.util.HashMap;

public class DGraph implements graph{
	HashMap<Integer, EdgeData> edges;
	HashMap<Integer, Nodedata> vertices; //still unknown
	//NodeData fields: point3d (point) , (key)
	
	public DGraph() {
		this.vertices= new HashMap<Integer, Nodedata>();
	}
	
	@Override
	public node_data getNode(int key) {
		return (node_data) this.edges.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) { //consider src and dest as keys
		

	}

	@Override
	public void addNode(node_data n) {

		if(n instanceof Nodedata) {
			this.vertices.put(n.getKey(), (Nodedata) n);
			return;
		}
		System.out.println("n isn't instance of Nodedata");

	}

	@Override
	public void connect(int src, int dest, double w) {

		// TODO Auto-generated method stub

		
	}

	@Override
	public Collection<node_data> getV() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
