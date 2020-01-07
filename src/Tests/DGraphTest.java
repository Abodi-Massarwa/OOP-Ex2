package Tests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.node_data;

class DGraphTest {
	DGraph x;
	EdgeData y;
	NodeData z;
	@BeforeAll
	static void print_welcome() {
		System.out.println("welcome to DGraphTest");
	}
	@BeforeEach
	void init_node() {
		x= new DGraph();
		y= new EdgeData(new NodeData(599), new NodeData(600), 601);
		z= new NodeData(1337);
		x.addEdge(y);
		x.addNode(z);
	}
	@AfterEach
	void cleanup() {
		System.out.println("cleaning up .....");
	}
	@AfterAll
	static void print_bye() {
		System.out.println("Goodbye !");
	}	
	@Test
	void testDGraph() {
		assertEquals(2, x.getMC());
	}

	@Test
	void testGetNode() {
		NodeData n= new NodeData(1337);
		x.vertices.put(n.getKey(), n);
		assertEquals(n, x.getNode(1337));
	}

	@Test
	void testAddEdge() {
		//x.addEdge(new EdgeData(y));
		assertEquals(601, x.getEdge(y.getSrc(), y.getDest()).getWeight());

	}
	@Test
	void testGetEdgeString() {
		//x.addEdge(new EdgeData(y));
		assertEquals(601, x.getEdge("("+y.getSrc()+","+y.getDest()+")").getWeight());
	}

	@Test
	void testGetEdgeIntInt() {
		assertEquals(601, x.getEdge(y.getSrc(), y.getDest()).getWeight());
	}

	@Test
	void testAddNode() {
		x.addNode(z);
		assertEquals(1337, x.getNode(z.getKey()).getKey());
	}

	@Test
	void testConnect() {
		x.addNode(new NodeData(1338));
		x.connect(1337, 1338, 100);
		assertEquals(100, x.getEdge(1337, 1338).getWeight());
	}

	@Test
	void testGetV() {
		ArrayList<node_data> a= new ArrayList<node_data>();
		a.add(z);
		assertEquals(a, x.getV());
	}

	@Test
	void testGetE() {
		ArrayList<edge_data> a= new ArrayList<edge_data>();
		a.add(y);
		assertEquals(a, x.getE(599));
	}

	@Test
	void testRemoveNode() {
		x.removeNode(1337);
		assertTrue(x.vertices.isEmpty());
	}

	@Test
	void testRemoveEdge() {
		x.removeEdge(599, 600);
		assertTrue(x.edges.isEmpty());
	}

	@Test
	void testNodeSize() {
		assertEquals(1, x.vertices.size());
	}

	@Test
	void testEdgeSize() {
		assertEquals(1, x.edges.size());
	}

	@Test
	void testGetMC() {
		assertEquals(2, x.getMC());
	}

}
