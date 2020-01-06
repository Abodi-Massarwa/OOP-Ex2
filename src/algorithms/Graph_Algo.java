package algorithms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.json.simple.parser.ParseException;

import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;
import utils.MyMinHeap;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author Abode Massarweh, Mohamad Assi, Oday Shibli  
 *
 */
public class Graph_Algo implements graph_algorithms{

	public DGraph graph;
    private int mcSP, src, dest;
    private List<node_data> path;
	
	
	@Override
	public void init(graph g) {
		if(g instanceof DGraph) {
			this.graph=(DGraph) g;
		}
	}
	/**
	 * we chose JSON !
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void init(String file_name) { 

		Object obj=null;
		try {
			obj = new JSONParser().parse(new FileReader(file_name));
		} catch (IOException | ParseException e) {

			e.printStackTrace();
		}
		JSONObject jo= (JSONObject) obj;

		HashMap<Integer, NodeData> shallow= (HashMap<Integer, NodeData>) jo.get("vertices");
		Iterator<Integer>it= shallow.keySet().iterator();
		while(it.hasNext()) {
			int help= it.next();
			this.graph.vertices.put(help,new NodeData( shallow.get(help)));
		}
		//same goes for the Edges 
		HashMap<String,EdgeData> shallow2= (HashMap<String, EdgeData>) jo.get("edges");
		Iterator<String>it2= shallow2.keySet().iterator();
		while(it2.hasNext()) {
			String help2= it2.next();
			this.graph.edges.put(help2,new EdgeData( shallow2.get(help2)));
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(String file_name) {
		JSONObject jo= new JSONObject();
		jo.put("vertices", this.graph.vertices);
		jo.put("edges", this.graph.edges);
		PrintWriter pw=null;
		try {
			pw = new PrintWriter(file_name);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} 
		pw.write(jo.toJSONString()); 

		pw.flush(); 
		pw.close(); 
	}

	@Override
	public boolean isConnected() {
		int counter=0;
		int arr[]=new int[this.graph.vertices.size()];
		Iterator<Integer> it= this.graph.vertices.keySet().iterator();
		for (int i = 0; i < arr.length; i++) {
			arr[i]=this.graph.vertices.get(it.next()).getKey();
		}
		/// array of vertices by keys 
		boolean flag[]= new boolean[arr.length];
		for (int i = 0; i < flag.length; i++) {
			flag[i]=false;
		}
		////boolean flag inited to false 
		for (int i = 0; i < arr.length; i++) {
			if(flag[i]==false) {
				traverse(arr,flag,i);
				counter++;
				if(counter>1) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		if(src==dest) {
			System.out.println("-------------------------------------");
			System.out.println("src=dest!");
			System.out.println("-------------------------------------");
			return 0;
		}
		String info = "";
		for (node_data nodeData : graph.getV()) {
			nodeData.setWeight(Double.POSITIVE_INFINITY);
			nodeData.setTag(0);
		}
		graph.getNode(src).setWeight(0);
		distRec(src, dest, info);
		return graph.getNode(dest).getWeight();
	}


	@Override
	public List<node_data> shortestPath(int src, int dest) {
		if(graph.getNode(src)==null || graph.getNode(dest)==null) {
			System.out.println("-------------------------");
			System.out.println("dest # src == null!!");
			System.out.println("-------------------------");
		}
		Collection<node_data> ver= graph.getV();
		ArrayList<node_data> gI=initShortestPath(ver,src);
		MyMinHeap m=new MyMinHeap(gI);
		int y=0;
		node_data node;
		node_data r=gI.get(0);
		while(gI.size()>1 && r.getKey()!=dest && r.getWeight()!=Integer.MAX_VALUE ) {
			this.nChange(r.getKey(),m);
			node=m.extractMin();
			node.setWeight(-1*node.getWeight());
			r=gI.get(0);
		}
		if(graph.getNode(dest).getWeight()==Integer.MAX_VALUE) {
			reset(Integer.MAX_VALUE,src,dest);
			 for(node_data nod: ver) nod.setTag(0);    // reset all nodes tag
			return null;
		}
		path.clear();
		int p=dest;
		path.add(graph.getNode(p));
		while(p!=src) {
			p=graph.getNode(p).getTag();
			path.add(graph.getNode(p));
			graph.getNode(p).setWeight(-1*graph.getNode(p).getWeight());
		}
		reset((int) this.path.get(0).getWeight(),src,dest);
		// change the nodes in list from right to left
    	int pSize=path.size();
    	node_data node1;
    	for(; y<(int)pSize/2; y++) {
			node1= path.get(y);
			path.set(y, path.get(pSize-1-y));
			path.set(pSize-1-y, node1);
		}
    	// reset all nodes tag
		 for(node_data nod: ver) nod.setTag(0);
		return path;
	}
	
    
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		List<node_data>path= new ArrayList<node_data>();
		if (targets.size()<=1) {
			System.out.println("Error");
			if(targets.size()<1)
				return null;
			List<node_data>ans=new ArrayList<node_data>();
			NodeData ans1= new NodeData(targets.get(0));
			ans.add(ans1);
			return ans;
		}
		if(!isConnected()) {
			System.out.println("graph isn't strongly connected");
			return null;
		}
		int arr[]=new int [targets.size()];
		int j=0;
		for(Integer i:targets) {
			arr[j++]=i;
		}
		for (int i = 0; i < arr.length-1; i++) {
			if(i==0)	path.addAll(this.shortestPath(arr[i], arr[i+1]));
			else {
				List<node_data>path1=this.shortestPath(arr[i], arr[i+1]);
				path1.remove(0);
				path.addAll(path1);
			}
		}
		return null;

	}

	@Override
	public graph copy() {
		Iterator<String> it= this.graph.edges.keySet().iterator();
		Iterator<Integer> it2= this.graph.vertices.keySet().iterator();
		DGraph answer= new DGraph();

		while(it.hasNext()) {
			String next= it.next();
			answer.edges.put(next, new EdgeData( this.graph.edges.get(next)));
		}
		while(it2.hasNext()) {
			int next= it2.next();
			answer.vertices.put(next,new NodeData(this.graph.vertices.get(next)));
		}
		return answer;

	}
	/////////////////////////// Private function ////////////////////////////
	
	private void traverse(int arr[],boolean[] flag, int i) {
		ArrayList<Integer> jeran = findj(arr,i);
		if(flag[i]==true||jeran.size()==0) {
			flag[i]=true;
			return;
		}
		flag[i]=true;

		for(Integer j:jeran) {
			traverse(arr, flag,indexof(j,arr) );
		}
	}

	private int indexof(Integer j,int []arr) {
		int index=0;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]==j) {
				index=i;
			}
		}
		return index;
	}
	
	private void distRec(int src, int dest, String info) {
		if(graph.getNode(src).getTag() == 1 && src == dest) return;
		for (edge_data e : graph.getE(src)) {
			double oldWeight = graph.getNode(e.getDest()).getWeight();
			double newWeight = e.getWeight() + graph.getNode(e.getSrc()).getWeight();
			if(oldWeight > newWeight) {
				graph.getNode(e.getDest()).setWeight(newWeight);
				graph.getNode(e.getDest()).setInfo(info + "-->" + src);
				graph.getNode(e.getSrc()).setTag(1);
				distRec(e.getDest(), dest, info + "-->" + src);
			}	
		}
	}
	// private function for shortestpath function!
	private void reset(int tDest, int src, int dest) {
		this.src=src;
		this.dest=tDest;
		this.mcSP=graph.getMC();
		this.dest=dest;
	}

	private ArrayList<node_data> initShortestPath(Collection<node_data> ver, int src) {
		ArrayList<node_data> lTemp= new ArrayList<node_data>();
		for(node_data node: ver) {
			graph.getNode(node.getKey()).setTag(-1);
			graph.getNode(node.getKey()).setWeight(Integer.MAX_VALUE);
			lTemp.add(node);
			lTemp.get(lTemp.size()-1).setInfo((lTemp.size()-1)+"");
			if(node.getKey()==src)	graph.getNode(src).setWeight(0);
		}
		return lTemp;
	}
	
	public ArrayList<Integer> findj(int[] arr, int i) {
		ArrayList<Integer> jeran= new ArrayList<Integer>();
		Iterator<String> it= this.graph.edges.keySet().iterator();
		while(it.hasNext()) {
			String help=it.next();
			if(help.contains("("+arr[i]+",")) {
				jeran.add(this.graph.edges.get(help).getDest());
			}
					else if(help.contains("("+arr[i]+",")) {
						jeran.add(this.graph.edges.get(help).getSrc());
					}
		}
		return jeran;
	}
// private function for shortestpath function!
	private void nChange(int src,MyMinHeap h) {
		node_data temp=graph.getNode(src);
		node_data temp1;
		Collection<edge_data> col= graph.getE(src);
		for(edge_data edge: col) {
			temp1=graph.getNode(edge.getDest());
			if(0<temp1.getWeight()) {
			   if(temp1.getWeight()>edge.getWeight()+temp.getWeight()) {
				  temp1.setTag(temp.getKey());
				  h.changePriorety(Integer.parseInt(temp1.getInfo()), temp.getWeight()+edge.getWeight());
			   }
			}
		}
	}

}
