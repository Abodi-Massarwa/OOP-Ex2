package dataStructure;

import java.io.Serializable;



public class EdgeData implements edge_data,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NodeData src;
	private NodeData dest;
	double weight;


	public EdgeData() {
		setSrc(new NodeData());
		setDest(new NodeData());
		weight=0;
	}
	public EdgeData(NodeData src, NodeData dest, double weight) {
		this.setSrc(src);
		this.setDest(dest);
		this.weight=weight;
	}
	public EdgeData(EdgeData old) {
		this.setSrc(new NodeData(old.getSrc()));
		this.setDest(new NodeData(old.getDest()));
		this.weight=old.weight;
	}

	@Override
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
		// TODO Auto-generated method stub
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
		ans+="("+this.getSrc()+"-->"+this.getDest()+")"+"Weight: "+this.weight;
		return ans;

	}
	public NodeData getSrc_node() {
		return this.src;
	}
	public NodeData getDest_node() {
		return this.dest;
	}
	public void setSrc(NodeData a) {
		this.src= new NodeData(a);
	}
	public void setDest(NodeData a) {
		this.dest= new NodeData(a);
	}

}
