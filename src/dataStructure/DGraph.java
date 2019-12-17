package dataStructure;

import java.util.Collection;
import java.util.HashMap;

import utils.Point3D;

public class DGraph implements graph{
	HashMap<String, EdgeData> edges;
	HashMap<Integer, NodeData> vertices; //still unknown
	//NodeData fields: point3d (point) , (key)
	
	public DGraph() {
		this.vertices= new HashMap<Integer, NodeData>();
		this.edges= new HashMap<String, EdgeData>();
	}
	
	@Override
	public node_data getNode(int key) {
		return (node_data) this.vertices.get(key);
	}
	/**
	 * in this private method we will specify how Edges are added to our
	 *  HashMap DS with a String key (a,b) such that a&b are Integers src , dest
	 * @param a
	 */
	
	public void addEdge(EdgeData a) {
		this.edges.put("("+a.src.getKey()+","+a.dest.getKey()+")", a);
		return;
		
	}
	
	public edge_data getEdge(String str) {
		return this.edges.get(str);
	}
	
	/**
	 *  we could easily add src and int to string then
		search for the desired edge in the hashmap (src,dest) as key 
	 */
	@Override
	public edge_data getEdge(int src, int dest) { 
		String target= "("+src+","+dest+")";
		return this.edges.get(target);
	}

	@Override
	public void addNode(node_data n) {

		if(n instanceof NodeData) {
			this.vertices.put(n.getKey(), (NodeData) n);
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
	public static void main(String[] args) {
		EdgeData ed = new EdgeData(new NodeData(new Point3D(1, 1, 1)),new NodeData(new Point3D(2, 2, 2)), 1);
		DGraph dg = new DGraph();
		dg.addEdge(ed);
		System.out.println("Edges hashmap to String: "+dg.edges.toString());
		System.out.println("\nSearching for edge with (0,1) key "+dg.getEdge(0, 1)); //worked 100%
		System.out.println("\nSearching for edge with -->(0,1)<-- String "+dg.getEdge("(0,1)")); //worked 100%
		
	}

}
