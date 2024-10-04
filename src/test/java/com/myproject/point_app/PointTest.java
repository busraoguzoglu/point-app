package com.myproject.point_app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class PointTest {

    @Test
    void testDefaultConstructor() {
        Point p = new Point();
        assertEquals(1, p.y);
        assertEquals(-1, p.x);
    }

    @Test
    void testParameterizedConstructor() {
        Point p = new Point(5, 10);
        assertEquals(10, p.x);  // The order in constructor seems swapped
        assertEquals(5, p.y);
    }

    @Test
    void testCopyConstructor() {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(p1);
        assertEquals(p1.x, p2.x);
        assertEquals(p1.y, p2.y);
    }

    @Test
    void testXDistance() {
        Point p1 = new Point(5, 10);
        Point p2 = new Point(8, 12);
        assertEquals(2, p1.xDistance(p2));
    }

    @Test
    void testYDistance() {
        Point p1 = new Point(5, 10);
        Point p2 = new Point(8, 12);
        assertEquals(3, p1.yDistance(p2));
    }

    @Test
    void testManhattanDistance() {
        Point p1 = new Point(3, 3);
        Point p2 = new Point(6, 7);
        assertEquals(7, p1.manhattanDistance(p2));
    }

    @Test
    void testSquaredEuclideanDistance() {
        Point p1 = new Point(3, 3);
        Point p2 = new Point(6, 7);
        assertEquals(25, p1.squaredEuclideanDistance(p2));
    }

    @Test
    void testIsOutsideSpaceTrue() {
        Point p1 = new Point(12, 5);
        assertTrue(p1.isOutsideSpace());
    }

    @Test
    void testIsOutsideSpaceFalse() {
        Point p1 = new Point(5, 5);
        assertFalse(p1.isOutsideSpace());
    }

    @Test
    void testMoveNorth() {
        Point p = new Point(0, 0);
        p.move(Point.Direction.NORTH);
        assertEquals(1, p.y);
        assertEquals(0, p.x);
    }

    @Test
    void testMoveNortheast() {
        Point p = new Point(0, 0);
        p.move(Point.Direction.NORTHEAST);
        assertEquals(1, p.y);
        assertEquals(1, p.x);
    }

    @Test
    void testReflection() {
        Point p = new Point(5, 5);
        Point reflected = p.reflection(Point.origin);
        assertEquals(-5, reflected.x);
        assertEquals(-5, reflected.y);
    }

    @Test
    void testNeighborhoodManhattan() {
        Point p = new Point(0, 0);
        List<Point> candidates = new ArrayList<>();
        candidates.add(new Point(1, 1));
        candidates.add(new Point(2, 2));
        candidates.add(new Point(3, 3));

        List<Point> neighborhood = p.neighborhoodManhattan(candidates, 2);
        assertEquals(1, neighborhood.size());  // Only (1, 1) should be within the distance
    }

    @Test
    void testToString() {
        Point p = new Point(5, 6);
        assertEquals("(6,5)", p.toString());
    }
}
