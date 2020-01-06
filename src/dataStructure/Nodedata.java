package dataStructure;

import utils.Point3D;


public class NodeData implements node_data {
	private int key,tag;
	private double Weight=Double.POSITIVE_INFINITY;
	Point3D point;
	static int id=0;
	private String info;
	  
	 
					//////////////// constructors //////////////
	public NodeData() {	
		key=id;
		id++;
		point= new Point3D();
		info="";
		tag=0;
	}
	
	public NodeData(Point3D point3d) {			
		this.key=id;
		id++;
		this.point= new Point3D(point3d);	
		info="";
		tag=0;
	}
	
	public NodeData(NodeData nodeData) {
		this.key= nodeData.key;
		this.Weight= Double.POSITIVE_INFINITY;
		this.point= new Point3D(nodeData.point);
		info="";
		tag=0;
	}
	public NodeData(int key) {
		this.key=key;
	
	}

	
	
	@Override
	public int getKey() {	
		return key;
	}

	@Override
	public Point3D getLocation() {		
		return this.point;
	}

	@Override
	public void setLocation(Point3D p) {
		this.point=p;

	}

	@Override
	public double getWeight() {		
		return Weight;
	}

	@Override
	public void setWeight(double w) {
		this.Weight=w;

	}

	@Override
	public String getInfo() {		
		return info;
	}

	@Override
	public void setInfo(String s) {
		info=s;

	}

	@Override
	public int getTag() {
		return tag;
	}

	@Override
	public void setTag(int t) {
		tag=t;

	}
	public String toString() {
		String ans="";
		return ans+="n"+this.key;
	}

}
