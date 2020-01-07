package algorithms;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author AbedElkareem Massarweh, Muhammad Assi, Oday Mahamid  
 *
 */
public class Graph_Algo implements graph_algorithms,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DGraph graph;
	@Override
	public void init(graph g) {
		if(g instanceof DGraph) {
			this.graph=(DGraph) g;
		}
	}
	@Override
	public void init(String file_name) { 
		ObjectInputStream os =null;
		boolean flag=true;
		DGraph read=null;
		try {
			os= new ObjectInputStream(new FileInputStream(file_name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(flag) {
			try {
				read= (DGraph) os.readObject();
			} catch (ClassNotFoundException e) {e.printStackTrace();}
			catch (EOFException e) {
				flag=false; break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.graph=read;
	}
	@Override
	public void save(String file_name) {
		ObjectOutputStream om = null;
		try {
			om = new ObjectOutputStream(new FileOutputStream(file_name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			om.writeObject(this.graph);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			om.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public boolean isConnected() {
		Iterator<Integer> it= this.graph.vertices.keySet().iterator();
		int ver1[]= new int[this.graph.vertices.size()];
		int i=0;
		while(it.hasNext()) {
			int next= it.next();
			ver1[i++]=next;
		}
		for (int j = 0; j < ver1.length; j++) {
			for (int j2 = 0; j2 < ver1.length; j2++) {				
				if(shortestPathDist(ver1[j], ver1[j2])==Double.POSITIVE_INFINITY) {
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
		ArrayList<ArrayList<Integer>> a1=printAllPaths(src, dest);
		List<node_data> ans= null;
		double sum=0;
		double distance= shortestPathDist(src, dest);
		for(ArrayList<Integer> i: a1) {
			sum=calculate_path(i);
			if(sum==distance) {
				ans=tolistofnodedata(i);
			}
		}
		return ans;
	}
	private List<node_data> tolistofnodedata(ArrayList<Integer> i) {
		List<node_data> nodes= new ArrayList<node_data>();
		for(Integer k: i) {
			nodes.add(this.graph.vertices.get(k));
		}
		return nodes;
	}
	private double calculate_path(ArrayList<Integer> i) {
		int arr[]= new int[i.size()];
		double sum=0;
		int k=0; 
		for(Integer num: i ) {
			arr[k++]=num;
		}
		for (int j = 0; j < arr.length-1; j++) {
			sum+=this.graph.edges.get("("+arr[j]+","+arr[j+1]+")").getWeight();
		}
		return sum;
	}


	private ArrayList<ArrayList<Integer>> printAllPaths(int src, int dest)  
	{ 
		boolean[] isVisited = new boolean[this.graph.vertices.size()]; 
		ArrayList<Integer> pathList = new ArrayList<>(); 
		int arr[]= new int[this.graph.vertices.size()];
		int i=0;
		Iterator<Integer> it= this.graph.vertices.keySet().iterator();
		while(it.hasNext()) {
			arr[i++]=it.next();
		}
		pathList.add(src); 
		int k=0;
		ArrayList<ArrayList<Integer>> allpaths= new ArrayList<ArrayList<Integer>>();
		printAllPathsUtil(src, dest, isVisited, pathList,arr,allpaths,k); 
		return allpaths;
	} 


	private void printAllPathsUtil(int u, int d, 
			boolean[] isVisited, 
			ArrayList<Integer> localPathList,int[]arr, ArrayList<ArrayList<Integer>> allpaths, int k) { 
		ArrayList<Integer> neighbours= findj(arr,u);
		isVisited[u] = true; 
		if (u==d)  
		{  
			ArrayList<Integer> test= new ArrayList<Integer>();
			test.addAll(localPathList);
			allpaths.add(test);

			isVisited[u]= false; 
			return ; 
		} 
		for (Integer i : neighbours)  
		{ 
			if (!isVisited[i]) 
			{ 
				localPathList.add(i); 
				printAllPathsUtil(i, d, isVisited, localPathList,arr,allpaths,k); 
				localPathList.remove(i); 
			} 
		} 
		isVisited[u] = false; 
	} 
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		List<node_data> tsp= new ArrayList<node_data>();
		int target_arr[]= new int [targets.size()];
		int i=0;
		for(Integer j: targets) {
			target_arr[i++]=j;
		}
		for (int j = 0; j < target_arr.length-1; j++) {
			if(j!=0&&j!=target_arr.length-1) {
				System.out.println(tsp.size());
				tsp.remove(tsp.size()-1);
			}
			tsp.addAll(shortestPath(target_arr[j],target_arr[j+1]));
		}
		return tsp;
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
	public ArrayList<Integer> findj(int[] arr, int i) {
		ArrayList<Integer> jeran= new ArrayList<Integer>();
		Iterator<String> it= this.graph.edges.keySet().iterator();
		while(it.hasNext()) {
			String help=it.next();
			if(help.contains("("+arr[i]+",")) {
				jeran.add(this.graph.edges.get(help).getDest());
			}
		}
		return jeran;
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
}


