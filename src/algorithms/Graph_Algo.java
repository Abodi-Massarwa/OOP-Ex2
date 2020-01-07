package algorithms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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
	
    public Graph_Algo() {

    	graph= new DGraph();

    	path=new ArrayList<node_data>();

    }
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
	
		int arr[]=new int[this.graph.vertices.size()];
		Iterator<Integer> it= this.graph.vertices.keySet().iterator();
		for (int i = 0; i < arr.length; i++) {
			arr[i]=this.graph.vertices.get(it.next()).getKey();
		}
		LinkedList<int[]> ans = new LinkedList<int[]>();
		int[] s = new int[graph.nodeSize()];
		int n=0;
		recc(src,dest,ans,s,arr,n);
		arr=ans.getFirst();
		System.out.println(arr[0]);
		return null;
	}
	
    
	private void recc(int src2, int dest2, LinkedList<int[]> ans, int[] s, int[] arr,int n) {
		
		List<Integer> jeran = findj(arr,src2);
		if (src2==dest2) {
			ans.add(s);
			int[] d = new int[graph.nodeSize()];
			s=d;
			n=0;
			return;
		}
		if (graph.getNode(src2).getTag()==1) {
			return;
		}
		s[n]=src2;
		n++;
		graph.getNode(src2).setTag(1);
		for (Integer i : jeran) {
			recc(i,dest2,ans,s,arr,n);
		}	
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
	/////////////////////////// Private functions ////////////////////////////
	
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

	public static void main(String[] args) {
		Point3D p1 = new Point3D(1, 2);
		Point3D p2 = new Point3D(3, 4);
		Point3D  p3= new Point3D(5, 6);
		Point3D p4 = new Point3D(7, 8);
		Point3D p5 = new Point3D(7, 8);
		Point3D p6 = new Point3D(7, 8);
		Point3D p7 = new Point3D(7, 8);
		NodeData n0= new NodeData(p1);
		NodeData n1= new NodeData(p2);
		NodeData n2= new NodeData(p3);
		NodeData n3= new NodeData(p4);
		NodeData n4= new NodeData(p5);
		NodeData n5= new NodeData(p6);
		NodeData n6= new NodeData(p7);
		DGraph a= new DGraph();
		a.addNode(n0);
		a.addNode(n1);
		a.addNode(n2);
		a.addNode(n3);
		a.addNode(n4);
		//a.addNode(n5);
		//a.addNode(n6);
		EdgeData e1 = new EdgeData(n0, n1, 2);
		EdgeData e2 = new EdgeData(n1, n2, 1);
		EdgeData e3 = new EdgeData(n2, n3, 1);
		EdgeData e4 = new EdgeData(n2, n4, 1);
		EdgeData e5 = new EdgeData(n3, n0, 1);
		EdgeData e6 = new EdgeData(n4, n0, 1);
		EdgeData e7 = new EdgeData(n0, n3, 300);
		a.addEdge(e1);
//		a.addEdge(e2);
//		a.addEdge(e3);
//		a.addEdge(e4);
//		a.addEdge(e5);
//		a.addEdge(e6);
//		a.addEdge(e7);
		Graph_Algo a1 = new Graph_Algo();
		a1.init(a);
		
System.out.println(a1.shortestPath(0, 1));
		//System.out.println(a1.weight_list(n0.getKey()));
		String file_name="Algo.JSON";
		//a1.save(file_name);
		Graph_Algo json= new Graph_Algo();
		//json.init(file_name);
		List<Integer>targets= new ArrayList<Integer>();
		targets.add(1);
		targets.add(3);
		targets.add(4);
		//targets.add(e);
		//System.out.println(a1.shortestPathDist(4, 3));
		//System.out.println(a1.shortestPath(4, 3));
	}
}
