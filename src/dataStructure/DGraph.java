package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;


public class DGraph implements graph{
	public HashMap<String, EdgeData> edges;
	public HashMap<Integer, NodeData> vertices;
	private int mc;
	
	///// constructor
	public DGraph() {
		this.vertices= new HashMap<Integer, NodeData>();
		this.edges= new HashMap<String, EdgeData>();
		mc=0;
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
		mc++;
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
			mc++;
		}
		else System.out.println("n isn't instance of Nodedata");
	}

	@Override
	public void connect(int src, int dest, double w) {
		NodeData n1 = vertices.get(src);
		NodeData n2 = vertices.get(dest);
		EdgeData connect = new EdgeData(n1, n2, w);
		this.edges.put("("+n1.getKey()+","+n2.getKey()+")", connect);
	}

	
	@Override
	public Collection<node_data> getV() {
		ArrayList<node_data> newv= new ArrayList<node_data>();
		Iterator<Integer> it= this.vertices.keySet().iterator();
		while(it.hasNext()) {
			newv.add(this.vertices.get(it.next()));
		}
	return newv;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		ArrayList<edge_data> newe= new ArrayList<edge_data>();
		Iterator<String> it= this.edges.keySet().iterator();
		while(it.hasNext()) {
			newe.add(this.edges.get(it.next()));
		}
	return newe;
	}

	@Override
	public node_data removeNode(int key) {
		mc++;
		NodeData n1 = vertices.get(key);
		vertices.remove(key);
		return n1;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		mc++;
		EdgeData n1 = edges.get("("+src+","+dest+")");
		edges.remove("("+src+","+dest+")");
		return n1;
	}

	@Override
	public int nodeSize() {
		return vertices.size();
	}

	@Override
	public int edgeSize() {
		return edges.size();
	}

	@Override
	public int getMC() {
		return mc;
	}
}