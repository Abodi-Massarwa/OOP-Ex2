package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.node_data;
import utils.Point3D;

class Graph_AlgoTest {
	Graph_Algo x;
	DGraph y;
	@BeforeAll
	static void print_welcome() {
		System.out.println("welcome to GraphAlgoTest");
	}
	@BeforeEach
	void init_graph() {
		x= new Graph_Algo();
		y= new DGraph();
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
	void testInitGraph() {
		x.init(y);
		assertTrue(x.graph.equals(y));
	}

	@Test
	void testInitString() {
		assertDoesNotThrow(()->x.init("Test.txt"));
	}

	@Test
	void testSave() {
		x.init(y);
		assertDoesNotThrow(()->x.save("Test.txt"));
	}

	@Test
	void testIsConnected() {
		x.init(y);
		assertTrue(x.isConnected());

	}
	@Test
	void testShortestPathDist() {
		NodeData n0= new NodeData(new Point3D(0, 10));
		NodeData n1= new NodeData(new Point3D(10, 10));
		NodeData n2= new NodeData(new Point3D(10, 0));
		NodeData n3= new NodeData(new Point3D(0, 0));
		y.addNode(n0);
		y.addNode(n1);
		y.addNode(n2);
		y.addNode(n3);
		y.connect(0, 1, 700);
		y.connect(1, 2, 10);
		y.connect(2, 3, 12);
		y.connect(3, 0, 500);
		y.connect(0, 3, 0.5);
		y.connect(3, 1, 0.7);
		x.init(y);
		assertEquals(10.7, x.shortestPathDist(3, 2));
	}

	@Test
	void testShortestPath() {
		NodeData n0= new NodeData(new Point3D(0, 10));
		NodeData n1= new NodeData(new Point3D(10, 10));
		NodeData n2= new NodeData(new Point3D(10, 0));
		NodeData n3= new NodeData(new Point3D(0, 0));
		y.addNode(n0);
		y.addNode(n1);
		y.addNode(n2);
		y.addNode(n3);
		y.connect(0, 1, 700);
		y.connect(1, 2, 10);
		y.connect(2, 3, 12);
		y.connect(3, 0, 500);
		y.connect(0, 3, 0.5);
		y.connect(3, 1, 0.7);
		x.init(y);

		List<node_data> real= x.shortestPath(3, 2);
		List<node_data> expected= new ArrayList<node_data>();
		expected.add(n3);
		expected.add(n1);
		expected.add(n2);
		String s= real.toString();

		assertTrue(expected.containsAll(real));

	}
	@Test
	void testTSP() {
		NodeData n0= new NodeData(new Point3D(0, 10));
		NodeData n1= new NodeData(new Point3D(10, 10));
		NodeData n2= new NodeData(new Point3D(10, 0));
		NodeData n3= new NodeData(new Point3D(0, 0));
		y.addNode(n0);
		y.addNode(n1);
		y.addNode(n2);
		y.addNode(n3);
		y.connect(0, 1, 700);
		y.connect(1, 2, 10);
		y.connect(2, 3, 12);
		y.connect(3, 0, 500);
		y.connect(0, 3, 0.5);
		y.connect(3, 1, 0.7);
		x.init(y);
		List<node_data> real= x.shortestPath(3, 2);
		List<node_data> expected= new ArrayList<node_data>();
		expected.add(n3);
		expected.add(n1);
		expected.add(n2);
		String s= real.toString();

		assertTrue(expected.containsAll(real));

	}

}
