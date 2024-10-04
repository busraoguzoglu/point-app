package com.myproject.point_app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class PointTest {

    private Point point;

    // Before each test, initialize the point to start at (0, 0)
    @BeforeEach
    void setup() {
        point = new Point();
    }

    // 1. A Point must start from (0,0)
    @Test
    void testPointStartsAtOrigin() {
        assertEquals(0, point.x);
        assertEquals(0, point.y);
    }

    // 2. manhattanDistance(Point) returns the Manhattan distance
    @Test
    void testManhattanDistance() {
        Point p2 = new Point(3, 4);
        int distance = point.manhattanDistance(p2);
        assertEquals(7, distance);  // |3 - 0| + |4 - 0| = 7
    }

    // 3. squaredEuclideanDistance(Point) returns the Squared Euclidean distance
    @Test
    void testSquaredEuclideanDistance() {
        Point p2 = new Point(3, 4);
        int distance = point.squaredEuclideanDistance(p2);
        assertEquals(25, distance);  // (3 - 0)^2 + (4 - 0)^2 = 9 + 16 = 25
    }

    // 4. All distances must be positive integers
    @Test
    void testAllDistancesArePositive() {
        Point p2 = new Point(3, 4);
        int manhattanDistance = point.manhattanDistance(p2);
        int euclideanDistance = point.squaredEuclideanDistance(p2);
        assertTrue(manhattanDistance > 0);
        assertTrue(euclideanDistance > 0);
    }

    // 5. Manhattan distance squared must be greater than or equal to Squared Euclidean distance
    @Test
    void testManhattanGreaterThanOrEqualToEuclidean() {
        Point p2 = new Point(3, 4);
        int manhattanDistanceSquared = (int) Math.pow(point.manhattanDistance(p2), 2);
        int squaredEuclideanDistance = point.squaredEuclideanDistance(p2);
        assertTrue(manhattanDistanceSquared >= squaredEuclideanDistance);
    }

    // 6. move(Direction) moves the Point in the correct direction
    @Test
    void testMoveNorth() {
        point.move(Point.Direction.NORTH);
        assertEquals(1, point.y);
        assertEquals(0, point.x);
    }

    @Test
    void testMoveEast() {
        point.move(Point.Direction.EAST);
        assertEquals(0, point.y);
        assertEquals(1, point.x);
    }

    // 7. The system must give an Exception if a Point is created outside the square
    @Test
    void testPointOutsideBoundsThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Point(12, 12);  // Coordinates are outside the 11x11 grid
        });
        assertEquals("Point is outside the defined space", exception.getMessage());
    }

    // 8. reflection(Point) returns the reflection of the Point over a given origin, throws exception if out of bounds
    @Test
    void testReflection() {
        Point p2 = new Point(3, 4);
        Point reflected = p2.reflection(Point.origin);
        assertEquals(-3, reflected.x);
        assertEquals(-4, reflected.y);
    }

    @Test
    void testReflectionThrowsExceptionOutsideBounds() {
        Point p2 = new Point(10, 10);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            p2.reflection(new Point(2, 2));  // Reflection would result in out-of-bounds point
        });
        assertEquals("Reflected point is outside the defined space", exception.getMessage());
    }

    // 9. Point trying to escape the boundaries must reflect over (0, 0)
    @Test
    void testMoveOutsideBoundsReflectsOverOrigin() {
        point = new Point(11, 0);
        point.move(Point.Direction.EAST);
        assertEquals(-11, point.x);  // Point should reflect to the left side of the square
        assertEquals(0, point.y);
    }

    // 10. neighborhoodManhattan(List<Point>, int) returns Points with Manhattan distance smaller than or equal to the given value
    @Test
    void testNeighborhoodManhattan() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(3, 3));
        points.add(new Point(5, 5));
        
        List<Point> neighborhood = point.neighborhoodManhattan(points, 3);
        assertEquals(2, neighborhood.size());  // Only (1,1) and (3,3) are within Manhattan distance <= 3
    }
}
