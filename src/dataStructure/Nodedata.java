package dataStructure;

import java.io.Serializable;

import utils.Point3D;


public class NodeData implements node_data,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int key;
	private double Weight=Double.POSITIVE_INFINITY;
	 Point3D point;
	static int id=0;
	  
	public Point3D getPoint() {
		return this.point;
	}
	
	public NodeData() {	
		key=id;
		id++;
		point= new Point3D();
	}
	
	public NodeData(Point3D point3d) {			
		this.key=id;
		id++;
		this.point= new Point3D(point3d);		
	}
	
	public NodeData(NodeData nodeData) {
		this.key= nodeData.key;
		this.Weight= Double.POSITIVE_INFINITY;
		this.point= new Point3D(nodeData.point);
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
		
		return null;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTag(int t) {
		// TODO Auto-generated method stub

	}
	public String toString() {
		String ans="";
		return ans+="n"+this.key;
	}

}
