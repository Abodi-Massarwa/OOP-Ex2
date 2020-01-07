package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.NodeData;
import utils.Point3D;

class NodeDataTest {
	
	NodeData x;
	Point3D a;
	@BeforeAll
	static void print_welcome() {
		System.out.println("welcome to NodeDataTest");
	}
	@BeforeEach
	void init_node() {
	x= new NodeData();
	@SuppressWarnings("unused")
	Point3D a=new Point3D(1,2);
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
	void testNodeDataPoint3D() {
		x= new NodeData(new Point3D(1,2));
		assertEquals(1, x.getPoint().x());
		assertEquals(2, x.getPoint().y());
	}

	@Test
	void testNodeDataNodeData() {
		x= new NodeData(new NodeData(new Point3D(1,2)));
		assertEquals(1, x.getPoint().x());
		assertEquals(2, x.getPoint().y());

	}

	@Test
	void testNodeDataInt() {
		x= new NodeData(600);
		assertEquals(600, x.getKey());
	}

	@Test
	void testGetKey() {
		x= new NodeData(600);
		assertEquals(600, x.getKey());

	}

	@Test
	void testGetLocation() {
		Point3D a=new Point3D(1,2);
		x= new NodeData(a);
		assertEquals(a, x.getLocation());
	}

	@Test
	void testSetLocation() {
		x.setLocation(a);
		assertEquals(a, x.getLocation());
	}

	@Test
	void testGetWeight() {
		x.setWeight(600);
		assertEquals(600, x.getWeight());
	}

	@Test
	void testSetWeight() {
		x.setWeight(600);
		assertEquals(600, x.getWeight());
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
		String suspect=x.toString();
		assertEquals("n"+x.getKey(), suspect);
	}

}
