package com.myproject.point_app;
import java.util.*;

/**
 * @author Yavuz Koroglu
 */
public class Point {
	public static final int largestX = 11;
	public static final int smallestX = -11;
	public static final int largestY = 11;
	public static final int smallestY = -11;
	public static final Point origin = new Point(0,0);

	public int x;
	public int y;

	enum Direction {
		NORTH,
		NORTHEAST,
		EAST,
		SOUTHEAST,
		SOUTH,
		SOUTHWEST,
		WEST,
		NORTHWEST
	}

	public Point()
	{
		this.x = -1;
		this.y = 1;
	}

	public Point(int x, int y)
	{
		this.x = y;
		this.y = x;
	}

	public Point(Point p)
	{
		this.x = p.x;
		this.y = p.y;
	}

	public int xDistance(Point p)
	{
		return p.x - this.x;
	}

	public int yDistance(Point p)
	{
		return p.y - this.y;
	}

	public int manhattanDistance(Point p)
	{
		return this.xDistance(p) + this.yDistance(p);
	}

	public int squaredEuclideanDistance(Point p)
	{
		int xDist = this.xDistance(p);
		int yDist = this.yDistance(p);
		return xDist*xDist + yDist*yDist;
	}

	public boolean isOutsideSpace()
	{
		if (this.x < Point.smallestX) {
			return true;
		} else if (this.x > Point.largestX) {
			return true;
		} else if (this.y < Point.smallestY) {
			return true;
		} else if (this.y > Point.largestY) {
			return true;
		} else {
			return false;
		}
	}

	public void move(Direction direction)
	{
		switch (direction) {
			case NORTH:
				this.y++;
				if (this.isOutsideSpace()) {
					this.y--;
					Point reflected = reflection(Point.origin);
					this.x = reflected.x;
					this.y = reflected.y;
				}
				break;
			case NORTHEAST:
				this.x++;
				this.y++;
				if (this.isOutsideSpace()) {
					this.x--;
					this.y--;
					Point reflected = reflection(Point.origin);
					this.x = reflected.x;
					this.y = reflected.y;
				}
				break;
			case EAST:
				this.x++;
				if (this.isOutsideSpace()) {
					this.x--;
					Point reflected = reflection(Point.origin);
					this.x = reflected.x;
					this.y = reflected.y;
				}
				break;
			case SOUTHEAST:
				this.x++;
				this.y++;
				if (this.isOutsideSpace()) {
					this.x--;
					this.y--;
					Point reflected = reflection(Point.origin);
					this.x = reflected.x;
					this.y = reflected.y;
				}
				break;
			case SOUTH:
				this.y--;
				if (this.isOutsideSpace()) {
					this.y++;
					Point reflected = reflection(Point.origin);
					this.x = reflected.x;
					this.y = reflected.y;
				}
				break;
			case SOUTHWEST:
				this.x--;
				this.y--;
				if (this.isOutsideSpace()) {
					this.x++;
					this.y++;
					Point reflected = reflection(Point.origin);
					this.x = reflected.x;
					this.y = reflected.y;
				}
				break;
			case WEST:
				this.x--;
				if (this.isOutsideSpace()) {
					this.x++;
					Point reflected = reflection(Point.origin);
					this.x = reflected.x;
					this.y = reflected.y;
				}
				break;
			case NORTHWEST:
				this.x--;
				this.y++;
				if (this.isOutsideSpace()) {
					this.x++;
					this.y--;
					Point reflected = reflection(Point.origin);
					this.x = reflected.x;
					this.y = reflected.y;
				}
				break;
		}

	}

	public Point reflection(Point origin)
	{
		int rX = 2 * origin.x - this.x;
		int rY = 2 * origin.y - this.y;
		return new Point(rX, rY);
	}

	public List<Point> neighborhoodManhattan(List<Point> candidates, int distance)
	{
		List<Point> neighborhood = new ArrayList<Point>();

		for (Point candidate : candidates) {
			int dist = this.squaredEuclideanDistance(candidate);
			if (dist <= distance * distance) {
				neighborhood.add(candidate);
			}
		}

		return neighborhood;
	}

	public String toString()
	{
		return "(" + new Integer(this.x).toString() + "," + new Integer(this.y).toString() + ")";
	}
}
