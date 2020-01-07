package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
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
		DGraph y= new DGraph();
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

//	@Test
//	void testInitString() {
//		assertDoesNotThrow(()->x.init("Test.JSON"));
//	}

	@Test
	void testSave() {
		x.init(y);
		assertDoesNotThrow(()->x.save("Test.JSON"));
	}

	@Test
	void testIsConnected() {
		x.init(y);
		assertTrue(x.isConnected());
		
	}
	@Test
	void testShortestPathDist() {
		assertEquals(0.5+0.7, x.shortestPathDist(0, 1));
	}

	@Test
	void testShortestPath() {
		fail("Not yet implemented");
	}

	@Test
	void testTSP() {
		fail("Not yet implemented");
	}



	@Test
	void testCopy() {
		fail("Not yet implemented");
	}

	@Test
	void testRelaxation() {
		fail("Not yet implemented");
	}

	@Test
	void testMain() {
		fail("Not yet implemented");
	}

}
