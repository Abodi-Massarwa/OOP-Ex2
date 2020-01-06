package dataStructure;

public class EdgeData implements edge_data {

	NodeData src;
	NodeData dest;
	private double weight;
	private int tag;
	private String info;
	
				/////////////////// constructors ////////////////////
	public EdgeData() {
		src= new NodeData();
		dest= new NodeData();
		weight=0;
		tag=0;
		info="";
	}
	public EdgeData(NodeData src, NodeData dest, double weight, int tag, String info) {
		this.src= src;
		this.dest= dest;
		this.weight=weight;
		this.info=info;
		this.tag=tag;
	}
	public EdgeData(EdgeData old) {
		this.src=old.src;
		this.dest=old.dest;
		this.weight=old.weight;
	}
	
	public EdgeData(NodeData src, NodeData dest, double weight) {
		this.src= src;
		this.dest= dest;
		this.weight=weight;
		tag=0;
		info="";
	}
		////////////////////// 
	
	
	public int getSrc() {
		return src.getKey();
	}
	@Override
	public int getDest() {
		return dest.getKey();
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public void setInfo(String s) {
		this.info=s;
	}

	@Override
	public int getTag() {
		return tag;
	}

	@Override
	public void setTag(int t) {
		this.tag=t;

	}
	//// to string function
	public String toString() {
		String ans="";
		ans+="("+this.src+"-->"+this.dest+")"+"Weight: "+this.weight;
		return ans;
		
	}

}
