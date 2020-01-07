package dataStructure;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import algorithms.Graph_Algo;
import utils.Point3D;
import utils.StdDraw;
class Caller extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1578703881149276292L;
	JButton b;
	JLabel text;
	JProgressBar p;
	int i=0;
	Graphs collection;

	public Caller(Graphs n) {
		this.setLayout(new FlowLayout());
		text= new JLabel("contributed by AbedElkareem Massarweh");
		this.add(text);
		this.collection=n;
		p= new JProgressBar(0, 30);
		javax.swing.Timer t= new javax.swing.Timer(500, this);
		b= new JButton("Click for Graph");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				t.start();

			}
		});

		this.setBackground(Color.RED);

		this.add(b);
		this.add(p);
		this.setVisible(true);
		this.setSize(450, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(i==30) {
			this.collection.printgraph();//// change with Graph one !
			dispose();
		}
		i+=10;
		p.setValue(i);

	}


}

public class Graphs implements Collection<DGraph> {
	public static Color[] Colors = {Color.blue, Color.cyan,
			Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};
	static double Eps=0.2;
	static double Eps2=5;
	ArrayList<DGraph> graphs;

	public Graphs() {
		this.graphs= new ArrayList<DGraph>();
	}

	public void printgraph() {

		StdDraw.setCanvasSize(700, 700);
		double max_x=Double.NEGATIVE_INFINITY;
		double min_x=Double.POSITIVE_INFINITY;
		double max_y=Double.NEGATIVE_INFINITY;
		double min_y=Double.POSITIVE_INFINITY;
		for(DGraph i1:this.graphs) {
			Iterator<Integer> it1=i1.vertices.keySet().iterator();
			while(it1.hasNext()) {
				int help= it1.next();
				NodeData chosen= i1.vertices.get(help);
				double current_x=chosen.point.x();
				double current_y=chosen.point.y();
				if(current_x>max_x) {
					max_x=current_x;
				}
				if(current_y>max_y) {
					max_y=current_y;
				}
				if(current_x<min_x) {
					min_x=current_x;
				}
				if(current_y<min_y) {
					min_y=current_y;
				}
			}
		}
		StdDraw.setXscale(min_x-Eps2,max_x+Eps2);
		StdDraw.setYscale(min_y-Eps2, max_y+Eps2);
		for(DGraph i:this.graphs) {
			StdDraw.setPenColor(Color.RED);
			Iterator<String> it= i.edges.keySet().iterator();
			while(it.hasNext()) {
				StdDraw.setPenColor(Color.RED);
				String next=it.next();
				int key_src=i.edges.get(next).getSrc();
				int key_dest=i.edges.get(next).getDest();
				double weight=i.edges.get(next).getWeight();
				double x1=i.edges.get(next).getSrc_node().point.x();
				double y1=i.edges.get(next).getSrc_node().point.y();
				double x2=i.edges.get(next).getDest_node().point.x();
				double y2=i.edges.get(next).getDest_node().point.y();
				StdDraw.line(x1,y1, x2, y2);
				StdDraw.setPenColor(Color.BLUE);
				StdDraw.filledCircle(x1, y1, 0.2);
				StdDraw.filledCircle(x2, y2, 0.2);
				StdDraw.setPenColor(Color.MAGENTA);
				StdDraw.text(x1, y1+Eps,""+key_src );
				StdDraw.text(x2, y2+Eps,""+key_dest);
				StdDraw.textLeft((x1+x2)/2, (y1+y2)/2,""+weight);
				StdDraw.setPenColor(Color.yellow);
				StdDraw.filledCircle((((x1+x2)/2)+(x2))/2, (((y1+y2)/2)+(y2))/2, 0.15);
			}
		}
		///in case of only vertices without edges
		for(DGraph j:this.graphs) {
			Iterator<Integer> it= j.vertices.keySet().iterator();
			while(it.hasNext()) {
				int next= it.next();
				int key=j.vertices.get(next).getKey();
				double x=j.vertices.get(next).point.x();
				double y=j.vertices.get(next).point.y();
				StdDraw.setPenColor(Color.BLUE);
				StdDraw.filledCircle(x, y, 0.2);
				StdDraw.setPenColor(Color.MAGENTA);
				StdDraw.text(x, y+Eps,""+key );

			}
		}
	}

	@Override
	public int size() {
		return this.graphs.size();
	}
	@Override
	public boolean isEmpty() {
		return this.graphs.isEmpty();
	}
	@Override
	public boolean contains(Object o) {
		return this.graphs.contains(o);
	}
	@Override
	public Iterator<DGraph> iterator() {
		return this.graphs.iterator();
	}
	@Override
	public Object[] toArray() {
		return this.graphs.toArray();
	}
	@Override
	public <T> T[] toArray(T[] a) {
		return this.graphs.toArray(a);
	}
	@Override
	public boolean add(DGraph e) {
		return this.graphs.add(e);
	}
	@Override
	public boolean remove(Object o) {
		return this.graphs.remove(o);
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		return this.graphs.containsAll(c);
	}
	@Override
	public boolean addAll(Collection<? extends DGraph> c) {
		return this.graphs.addAll(c);
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		return this.graphs.removeAll(c);
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		return this.graphs.retainAll(c);
	}
	@Override
	public void clear() {
		this.graphs.clear();
		return;
	}
	public void print() {
		@SuppressWarnings("unused")
		Caller frame= new Caller(this);

	}
}
