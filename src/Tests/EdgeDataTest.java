package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.EdgeData;
import dataStructure.NodeData;
import utils.Point3D;

class EdgeDataTest {
	EdgeData x;
	@BeforeAll
	static void print_welcome() {
		System.out.println("welcome to EdgeDataTest");
	}
	@BeforeEach
	void init_node() {
		x= new EdgeData(new NodeData(new Point3D(1, 2, 3)),new NodeData(new Point3D(4, 5, 6)), 1337);
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
	void testEdgeDataNodeDataNodeDataDouble() {
		assertEquals(1, x.getSrc_node().getPoint().x());
		assertEquals(2, x.getSrc_node().getPoint().y());
		assertEquals(3, x.getSrc_node().getPoint().z());
		assertEquals(4, x.getSrc_node().getPoint().x());
		assertEquals(5, x.getSrc_node().getPoint().y());
		assertEquals(6, x.getSrc_node().getPoint().z());
	}

	@Test
	void testEdgeDataEdgeData() {
		EdgeData n= new EdgeData(new NodeData(new Point3D(7, 8, 9)),new NodeData(new Point3D(10, 11, 12)), 1338);
		x= new EdgeData(n);
		assertAll(()->assertEquals(7, x.getSrc_node().getPoint().x()),
				()->assertEquals(8, x.getSrc_node().getPoint().y()),
				()->assertEquals(9, x.getSrc_node().getPoint().z()),
				()->assertEquals(10, x.getDest_node().getPoint().x()),
				()->assertEquals(11, x.getDest_node().getPoint().y()),
				()->assertEquals(12, x.getDest_node().getPoint().z()));
	}

	@Test
	void testGetSrc() {
		int src= x.getSrc();
		assertEquals(x.getSrc_node().getKey(), src);
	}

	@Test
	void testGetDest() {
		int dest= x.getDest();
		assertEquals(x.getDest_node().getKey(),dest);
	}
	@Test
	void testGetWeight() {
		assertEquals(1337, x.getWeight());
	}

	//	@Test
	//	void testGetInfo() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	void testSetInfo() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	void testGetTag() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	void testSetTag() {
	//		fail("Not yet implemented");
	//	}

	@Test
	void testToString() {
		assertEquals("(n"+x.getSrc_node().getKey()+"-->n"+x.getDest_node().getKey()+")Weight: "+x.getWeight(), "("+x.getSrc()+"-->"+x.getDest()+")"+"Weight: "+x.getWeight());
	}

}
