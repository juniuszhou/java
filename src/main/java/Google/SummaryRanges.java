import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.*;

/**
 * Created by junius on 16-6-18.
 */
public class SummaryRanges {
    private TreeSet<Interval> queue = new TreeSet<Interval>(new Comparator<Interval>() {
        @Override
        public int compare(Interval o1, Interval o2) {
            return o1.start - o2.start;
        }
    });

    public SummaryRanges() {

    }

    public void addNum(int val) {
        Interval node = new Interval(val, val);
        Interval ceiling = queue.ceiling(node);
        Interval floor = queue.floor(node);
        if (floor == null) {
            if (ceiling == null) {
                queue.add(node);
            } else if (ceiling.start == val + 1) {
                ceiling.start = val;
            } else queue.add(node);
        } else if (floor.end == val - 1) {
            if (ceiling == null) floor.end = val;
            else if (ceiling.start == val + 1) {
                queue.remove(ceiling);
                queue.remove(floor);
                queue.add(new Interval(floor.start, ceiling.end));
            } else floor.end = val;
        } else if (val > floor.end + 1){
            if (ceiling == null) queue.add(node);
            else if (ceiling.start == val + 1) ceiling.start = val;
            else queue.add(node);
        }
    }

    public List<Interval> getIntervals() {
        Iterator<Interval> it = queue.iterator();
        List<Interval> list = new LinkedList<Interval>();
        while (it.hasNext()) list.add(it.next());
        return list;
    }

    public static void main(String[] args) {
        SummaryRanges s = new SummaryRanges();
        s.addNum(1);
        System.out.println(s.getIntervals());
        s.addNum(3);
        System.out.println(s.getIntervals());
        s.addNum(2);
        System.out.println(s.getIntervals());
        s.addNum(4);
        System.out.println(s.getIntervals());
        s.addNum(5);
        System.out.println(s.getIntervals());
        s.addNum(3);
        System.out.println(s.getIntervals());

    }
}
