package dataStructure;



import utils.Point3D;


public class NodeData implements node_data {
	private int key;
	private double Weight=1;
	  Point3D point;
	  static int id=0;
	  
	  
	
	public NodeData() {	
		key=id;
		id++;
		point= new Point3D();
	}
	
	public NodeData(Point3D point3d) {			
		this.key=id;
		id++;
		this.point=point3d;		
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
		String ans= "("+this.point.x()+","+this.point.y()+","+this.point.z()+" )";
		return ans;
	}
	
	public String toString() {
		return this.getInfo();
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

}
