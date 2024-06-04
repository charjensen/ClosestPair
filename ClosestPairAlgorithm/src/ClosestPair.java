import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.du.dudraw.*;

public class ClosestPair {

	public static void main(String[] args) {

		ArrayList<Point> S = new ArrayList<Point>();

		DUDraw.setCanvasSize(500, 500);
		DUDraw.setScale(0, 100);

		for (int i = 0; i < 10; i++) {
			Point p = new Point((int) (Math.random() * 100), (int) (Math.random() * 100));
			while (!S.contains(p)) {
				S.add(p);
				DUDraw.filledCircle(p.x, p.y, 0.5);
			}
		}

		Point[] closest = closestPairBrute(S);

		DUDraw.setPenColor(DUDraw.RED);
		DUDraw.circle(closest[0].x, closest[0].y, 0.8);
		DUDraw.circle(closest[1].x, closest[1].y, 0.8);

		System.out.println("brute force dist: " + distance(closest[0], closest[1]));

		DUDraw.pause(3000);

		Point[] closestDiv = closestPairDiv(S);

		DUDraw.setPenColor(DUDraw.BLUE);
		DUDraw.circle(closestDiv[0].x, closestDiv[0].y, 0.8);
		DUDraw.circle(closestDiv[1].x, closestDiv[1].y, 0.8);

		System.out.println("divide and conquer dist: " + distance(closestDiv[0], closestDiv[1]));

		DUDraw.pause(3000);

		String[] a = { " " };

		main(a);

	}

	public static Point[] closestPairBrute(ArrayList<Point> S) {

		double minDist = Double.MAX_VALUE;

		Point closest1 = S.get(0);
		Point closest2 = S.get(1);

		for (Point a : S) {
			for (Point b : S) {
				if (!b.equals(a)) {
					double dist = distance(a, b);
					if (minDist > dist) {
						minDist = dist;
						closest1 = a;
						closest2 = b;
					}
				}
			}
		}

		Point[] out = new Point[2];
		out[0] = closest1;
		out[1] = closest2;
		return out;

	}

	public static double distance(Point a, Point b) {

		double sqr1 = (b.x - a.x) * (b.x - a.x);
		double sqr2 = (b.y - a.y) * (b.y - a.y);

		return Math.abs(Math.sqrt(sqr1 + sqr2));

	}

	public static Point[] closestPairDiv(ArrayList<Point> S) {

		ArrayList<Point> X = new ArrayList<Point>(S);
		Collections.sort(X, new Comparator<Point>() {
			public int compare(Point a, Point b) {
				if (a.x < b.x) {
					return -1;
				} else if (a.x > b.x) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		ArrayList<Point> Y = new ArrayList<Point>(S);
		Collections.sort(Y, new Comparator<Point>() {
			public int compare(Point a, Point b) {
				if (a.y < b.y) {
					return -1;
				} else if (a.y > b.y) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		return closestPair(X, Y);

	}

	public static Point[] closestPair(ArrayList<Point> X, ArrayList<Point> Y) {

		if (X.size() <= 3)
			return closestPairBrute(X);

		ArrayList<Point> leftX = new ArrayList<Point>();
		ArrayList<Point> rightX = new ArrayList<Point>();
		ArrayList<Point> leftY = new ArrayList<Point>();
		ArrayList<Point> rightY = new ArrayList<Point>();

		int mid = X.size() / 2;

		for (int i = 0; i < X.size(); i++) {

			if (i < mid) {
				leftX.add(X.get(i));
				leftY.add(Y.get(i));
			} else {
				rightX.add(X.get(i));
				rightY.add(Y.get(i));
			}

		}

		ArrayList<Point> yCombined = new ArrayList<Point>();
		yCombined.addAll(leftY);
		yCombined.addAll(rightY);

		Point midPoint = X.get(mid);
		Point[] d1 = closestPair(leftX, Y);
		Point[] d2 = closestPair(rightX, Y);

		Point[] delta;

		if (distance(d1[0], d1[1]) < distance(d2[0], d2[1]))
			delta = d1;
		else
			delta = d2;

		double deltaDistance = distance(delta[0], delta[1]);
		ArrayList<Point> yP = new ArrayList<Point>();

		for (Point p : yCombined) {
			if ((Math.abs(midPoint.x - p.x) < deltaDistance))
				yP.add(p);
		}

		double deltaPrimeDistance = deltaDistance;

		Point[] closest = new Point[2];
		closest[0] = delta[0];
		closest[1] = delta[1];
		for (int i = 0; i < yP.size(); i++) {
			int min = (int) Math.min(7, yP.size());
			for (int j = i + 1; j < min; j++) {
				double newDist = distance(yP.get(i), (yP.get(j)));
				if (newDist < deltaPrimeDistance) {
					deltaPrimeDistance = newDist;
					closest[0] = yP.get(i);
					closest[1] = yP.get(j);
				}
			}

		}

		if (deltaPrimeDistance < deltaDistance) {
			return closest;
		} else
			return delta;

	}

}
