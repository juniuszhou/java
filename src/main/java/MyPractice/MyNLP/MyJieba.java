package MyPractice.MyNLP;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class MyJieba {
    public static class Note1{
        public int lastTag = -1;
        public double prob =  -100000.0;
    }
    public static int getMax(double[] d1 , double[] d2){

        if ((d1[0]+d2[0] > d1[1]+d2[1])
                && (d1[0]+d2[0] > d1[2]+d2[2])
                && (d1[0]+d2[0] > d1[3]+d2[3]))
            return 0;

        if ((d1[1]+d2[1] > d1[0]+d2[0])
                && (d1[1]+d2[1] > d1[2]+d2[2])
                && (d1[1]+d2[1] > d1[3]+d2[3]))
            return 1;

        if ((d1[2]+d2[2] > d1[1]+d2[1])
                && (d1[2]+d2[2] > d1[0]+d2[0])
                && (d1[2]+d2[2] > d1[3]+d2[3]))
            return 2;

            return 3;
    }
    public static Vector<Integer> myVeterbi(String input, Vector<HashMap<Character, Double>> v){
        Map<Character, Double> bm = v.get(0);
        Map<Character, Double> mm = v.get(1);
        Map<Character, Double> em = v.get(2);
        Map<Character, Double> sm = v.get(3);
        int len = input.length();
        int[][] prob = new int[len][4];
        double imp = -100000.0;
        System.out.println("not all tockenized.");
        //initial status probability.
        double[] initP = new double[4];
        initP[0] = -0.26268660809250016;
        initP[1] = imp;initP[2] = imp;
        initP[3] = -1.4652633398537678;

        // transform probability.
        double[][] transP = new double[4][4];
        for(int i = 0; i < 4; ++i)
            for(int j = 0; j < 4; ++j)
                transP[i][j] = imp;
        System.out.println("not all tockenized.");
        transP[0][1] = -0.916290731874155; //from b to m
        transP[0][2] = -0.510825623765990; //from b to e

        transP[1][1] = -1.2603623820268226; //from m to m
        transP[1][2] = -0.33344856811948514; //from m to e

        transP[2][0] = -0.5897149736854513; //from e to b
        transP[2][3] = -0.8085250474669937; //from e to s

        transP[3][0] = -0.7211965654669841; //from s to b
        transP[3][3] = -0.6658631448798212; //from s to s
        System.out.println("not all tockenized 9.");
        Note1[][] nn = new Note1[len][4];
        for(int i = 0; i < len; i++){
            for (int j = 0; j < 4; j++){
                nn[i][j] = new Note1();
            }
        }
        Character first = input.charAt(0);

        if (bm.get(first) != null){
            nn[0][0].prob = bm.get(first);
        }
        if (mm.get(first) != null){
            nn[0][1].prob = mm.get(first);
        }
        if (em.get(first) != null){
            nn[0][2].prob = em.get(first);
        }
        if (sm.get(first) != null){
            nn[0][3].prob = sm.get(first);
        }
        for (int i = 0; i < 4; ++i)
            System.out.println(" cur is " + first + nn[0][i].prob );

        System.out.println("not all tockenized 20.");
        for(int i = 1; i < len; ++i){
            Character c = input.charAt(i);
            double db = imp; double dm = imp; double de = imp; double ds = imp;
            if (bm.get(c) != null){
                db = bm.get(c);
            }
            if (mm.get(c) != null){
                dm = mm.get(c);
            }
            if (em.get(c) != null){
                de = em.get(c);
            }
            if (sm.get(c) != null){
                ds = sm.get(c);
            }
            System.out.println(" char is " + c);
            // compare different prevois tag and give the final value to nn.
            for (int j = 0; j < 4; j++){
                int max = 0;
                // set init value as from
                double cur = nn[i - 1][0].prob;
                System.out.println(" init prob is " + (i-1) + " 0 is " + cur );
                if (nn[i-1][0].lastTag != -1) {
                     cur += transP[nn[i - 1][0].lastTag][j];
                    System.out.println(" added prob is " + (i-1) + " 0 is " + cur );
                }

                for (int k = 1; k < 4; k++){
                    double dd = 0.0;
                    if (nn[i-1][k].lastTag != -1) {
                        dd = transP[nn[i-1][k].lastTag][j];
                    }
                    if (cur < (nn[i-1][k].prob + dd)){
                        cur = nn[i-1][k].prob + dd;
                        max = k;
                    }
                    System.out.println(" prob is " + (i-1) + " and " + k + " is " + cur );
                }
                nn[i][j].prob = cur ;
                if (v.get(j).get(input.charAt(i)) != null)
                    nn[i][j].prob += v.get(j).get(input.charAt(i));
                nn[i][j].lastTag = max;
                System.out.println(" set i is " + i + " index j is " + j +
                        " prob is " + nn[i][j].prob + " Tag is " + max);
            }
        }
        Vector<Integer> vv = new Vector<>();
        int lastTag = -1;
        if ( nn[len-1][2].prob > nn[len-1][3].prob){
            vv.add(0, 2);
            lastTag = nn[len-1][2].lastTag;
        }
        else{
            vv.add(0, 3);
            lastTag = nn[len-1][3].lastTag;
        }

        for(int i = len - 2; i >= 0; i--){
            vv.add(0, nn[i][lastTag].lastTag);
            lastTag = nn[i][lastTag].lastTag;
        }

        return vv;
    }

    // max match for all, left to right.
    public static Vector<Integer> splitChinese(String input, Map<String, Double> m, int pos){
        int len = input.length();
        //System.out.println("length is " + len);

        Vector<Integer> v = new Vector<>();
        int i = len;

        while (i > pos){
            String left = input.substring(pos, i);
            //System.out.println("left string is " + left);
            if (m.containsKey(left)){
                //System.out.println(left + " put into matched part. next is " + i);
                v = splitChinese(input, m, i);

                v.add(0,i);
                return v;
            }
            else {
            }
            i--;
        }

        return v;
    }

    public static  void main(String[] args) throws Exception{
        // 'B', 'M', 'E', 'S'
        Vector<HashMap<Character, Double>> v = LoadFileUtil.LoadTagTable();
         Map<Character, Double> bm = v.get(0);
         Map<Character, Double> mm = v.get(1);
         Map<Character, Double> em = v.get(2);
         Map<Character, Double> sm = v.get(3);

        Map<String, Double> dict = LoadFileUtil.LoadChineseDict();

        // String input = "我是一个人吗牛牪犇你也是一个人吗";
        String input = "我是一个好人牪";
        Vector<Integer> r = splitChinese(input, dict, 0);
        //r.sort();
        int s = 0;
        //int j = 0;
        for(Integer i : r){
            System.out.println(input.substring(s,i));
            s = i;
        }
        if (s < input.length()){
            System.out.println("not all tockenized.");
            r = myVeterbi(input, v);
        }

        for(Integer i : r){
            System.out.println(i);
            // input.substring(s,i)
            //s = i;
        }

    }
}
