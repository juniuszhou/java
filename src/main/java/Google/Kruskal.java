package Google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class UnionSearch {
    int[] a;
    public void init(int len) {
        a = new int[len];
        for(int i=0;i<len;i++) a[i] = i;
    }
}

class Point {
    int x;
    int y;
    int distance;
    public Point(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.distance = d;
    }
}
public class Kruskal {
    public void kruskal(int[][] g, int s) {
        UnionSearch us = new UnionSearch();
        us.init(g.length);
        ArrayList<Point> array = new ArrayList<Point>();
        Collections.sort(array, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.distance - o2.distance;
            }
        });

        for(int i=0;i<g.length;i++)
            for(int j=0;j<g.length;j++) {

            }


    }
}
