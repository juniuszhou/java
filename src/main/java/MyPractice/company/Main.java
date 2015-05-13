package company;

class Node{
    Dot dot;
    double radius;
    Node next;
    Node(Dot d, double r){dot = d;radius = r; next = null;}
}

class Dot{
    double x;
    double y;
    Dot(double a, double b){x = a; y = b;}
    Dot(){x = -1; y = -1;}
}

class Connected{
    Dot left = new Dot();
    Dot right = new Dot();

    Dot top = new Dot();
    Dot bottom = new Dot();

    Node head = null;

    void Connected(){}

}

public class Main {
/*
    static int ifIncluded(Node n, Node n2){
        if (n.radius > n2.radius){
            double dist = Math.sqrt((n.dot.x - n2.dot.x) * (n.dot.x - n2.dot.x) + (n.dot.y - n2.dot.y) * (n.dot.y - n2.dot.y));

            if (dist < (n.radius - n2.radius))
                return 1;
        }

        else{
            double dist = Math.sqrt((n.dot.x - n2.dot.x) * (n.dot.x - n2.dot.x) + (n.dot.y - n2.dot.y) * (n.dot.y - n2.dot.y));

            if (dist < (n2.radius - n.radius))
                return -1;
        }

        return 0;
    }

    static boolean ifConnected(Node n, Node n2){
        double dist = Math.sqrt((n.dot.x - n2.dot.x) * (n.dot.x - n2.dot.x) + (n.dot.y - n2.dot.y) * (n.dot.y - n2.dot.y));

        return dist < (n.radius + n2.radius);
    }

    static void crossDots(Dot dot, double radius, int w, int h, Dot[] d1, Dot[] d2){
        // line one, x = 0 to w and y = 0
        //then x - dot.x + dot.y = r
        //
        double tmp;
        double tmp2;

        tmp = radius * radius - dot.y * dot.y;
        if (tmp > 0) {
            tmp2 = Math.sqrt(tmp);
            d1.x = tmp + dot.x;
            d1.y = 0;

            d2.x = -tmp + dot.x;
            d2.y = 0;
        }

        //line two, y = 0 to h
        tmp = radius * radius - dot.x* dot.x;
        if (tmp > 0) {
            tmp2 = sqr(tmp);
            d1.x = tmp + dot.x;
            d1.y = 0;

            d2.x = -tmp + dot.x;
            d2.y = 0;
        }

    }

    static void addOrMerge(LinkedList<Connected> ll, Node n){
        for(int i = 0; i < ll.size(); i++){
            Connected c = ll.get(i);
            Node n3 = c.head;
            while (n3 != null){
                ifConnected(n, n3);

            }
        }

    }

    static boolean escape(int w, int h, int n, double[] x, double[] y, double[] r){
        LinkedList<Connected> ll = new LinkedList<Connected>();
        for(int i = 0; i < n; ++i){
            Node node = new Node(new Dot(x[i], y[i]), r[i]);
        }


        return true;
    }
*/
    public static void main(String[] args) {
        Connected c = new Connected();
        //Node n = new Node();


    }
}
