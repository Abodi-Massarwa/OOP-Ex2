package dataStructure;

public class EdgeData implements edge_data {


	NodeData src;
	NodeData dest;
	double weight;
	
	
	public EdgeData() {
		src= new NodeData();
		dest= new NodeData();
		weight=0;
	}
	public EdgeData(NodeData src, NodeData dest, double weight) {
		this.src= src;
		this.dest= dest;
		this.weight=weight;
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

}
