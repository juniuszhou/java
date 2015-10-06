package MyPractice.MyNLP;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.*;
import java.util.concurrent.SynchronousQueue;


class Dataset{
    public int[] features = new int[40]; // could be 0, 1
    public int result = -1; // could be 0,1
}

class Pair{
    public int featureId = -1; // 0 to 39
    //public int featureV = -1;
    public int resultId = -1; // id same with value. just one output.

    @Override
    public int hashCode() {
        return resultId * 1000000 + featureId;
    }
}

class MyMap{
    private Map<Pair, Double> mData = new HashMap<>();
    public void put(Pair p, Double d) {
        boolean b = false;
        Map.Entry<Pair, Double> r = null;
        for(Map.Entry<Pair, Double> e : mData.entrySet()){
            if(e.getKey().featureId == p.featureId && e.getKey().resultId == p.resultId){
                b = true;
                r = e;
                break;
            }
        }
        if(b){

            mData.replace(r.getKey(), d);
        }
        mData.put(p, d);
    }
    public Set<Map.Entry<Pair, Double> > entrySet(){return mData.entrySet();}
    public boolean exist(Pair p) {
        for(Map.Entry<Pair, Double> e : mData.entrySet()){
            if(e.getKey().featureId == p.featureId && e.getKey().resultId == p.resultId){
                return true;
            }
        }
        return false;
    }

    public Double getValue(Pair p){
        for(Map.Entry<Pair, Double> e : mData.entrySet()){
            if(e.getKey().featureId == p.featureId && e.getKey().resultId == p.resultId){
                return e.getValue();
            }
        }
        return null;
    }
}
public class MyMaxEntropy {
    public static Set<Integer> classSet = new HashSet<>();
    public static Vector<Dataset> trainingData = new Vector<>();
    public static Vector<Dataset> newData = new Vector<>();
    public static MyMap lambdas = new MyMap();
    public static MyMap m = new MyMap();

    public static BufferedWriter bw = null;

    public static void Log(String str) throws Exception {
        String path = "D:\\meLog.txt";
        if (bw == null){
            bw = new BufferedWriter(new FileWriter(path));
        }
        bw.write(str);
        // bw.newLine();
    }

    public static void Log(double str) throws Exception {
        String path = "D:\\meLog.txt";
        if (bw == null){
            bw = new BufferedWriter(new FileWriter(path));
        }
        Double f = str;
        bw.write(f.toString());
        // bw.newLine();
    }

    public static void normalizeExp(Map<Integer, Double> mm) {
        double max = 0.0;
        for(Map.Entry<Integer, Double> e : mm.entrySet()){
            if(e.getValue() > max){
                max = e.getValue();
            }
        }

        double sum = 0.0;
        //Prevents numeric underflow by subtracting the max. References: http://www.youtube.com/watch?v=-RVM21Voo7Q
        for(Map.Entry<Integer, Double> entry : mm.entrySet()) {
            Double value = Math.exp(entry.getValue() - max);
            mm.replace(entry.getKey(), value);
            sum += value;
        }

        if(sum!=0.0) {
            for(Map.Entry<Integer, Double> entry : mm.entrySet()) {
                mm.replace(entry.getKey(), entry.getValue() / sum);
            }
        }
    }

    public static void normalizeExp(MyMap mm) {
        double max = 0.0;
        for(Map.Entry<Pair, Double> e : mm.entrySet()){
            if(mm.getValue(e.getKey()) > max){
                max = mm.getValue(e.getKey());
            }
        }

        double sum = 0.0;
        //Prevents numeric underflow by subtracting the max. References: http://www.youtube.com/watch?v=-RVM21Voo7Q
        for(Map.Entry<Pair, Double> entry : mm.entrySet()) {
            Double value = Math.exp(entry.getValue() - max);
            mm.put(entry.getKey(), value);

            sum += value;
        }

        if(sum!=0.0) {
            for(Map.Entry<Pair, Double> entry : mm.entrySet()) {
                mm.put(entry.getKey(), entry.getValue()/sum);
            }
        }
    }

    public static void generateData() throws Exception{
        Random r = new Random();
        int i = 0;
        while (i < 100){
            Dataset d = new Dataset();
            int j = 0;
            while (j < 40){
                if (r.nextBoolean())
                    d.features[j] = 0;
                else
                    d.features[j] = 1;
                Log(d.features[j]);
                Log("\t");
                j++;
            }
            if (r.nextBoolean())
                d.result = 0;
            else
                d.result = 1;
            trainingData.add(d);
            Log(d.result);
            Log("\n");
            i++;
        }

        Log("kkkkkkkkkk over\n");
    }

    public static void predictDataset(Vector<Dataset> newData) {
    }

    public static void PrintLambdas() throws Exception{
        Log("PrintLambdas &&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n");
        double[][] dd = new double[2][40];
        for(Map.Entry<Pair, Double> e : lambdas.entrySet()){
            dd[e.getKey().resultId][e.getKey().featureId] = e.getValue();
        }
        for(int i = 0 ; i < 2; i ++){
            for(int j = 0; j < 40; j++){
                Log(dd[i][j]);
                Log("\t");
            }
            Log("\n");
        }
        Log("PrintLambdas &&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n");
    }

    public static void PrintMM()throws Exception{
        Log("%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
        double[][] dd = new double[2][40];
        for(Map.Entry<Pair, Double> e : m.entrySet()){
            dd[e.getKey().resultId][e.getKey().featureId] = e.getValue();
        }
        for(int i = 0 ; i < 2; i ++){
            for(int j = 0; j < 40; j++){
                Log(dd[i][j]);
                Log("\t");
            }
            Log("\n");
        }
        Log("%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
    }
    public static void printEpFj_model(MyMap mM)throws Exception{
        Log("##########################\n");
        double[][] dd = new double[2][40];
        for(Map.Entry<Pair, Double> e : mM.entrySet()){
            dd[e.getKey().resultId][e.getKey().featureId] = e.getValue();
        }
        for(int i = 0 ; i < 2; i ++){
            for(int j = 0; j < 40; j++){
                Log(dd[i][j]);
                Log("\t");
            }
            Log("\n");
        }
        Log("########################\n");

    }

    public static void estimateModelParameters() throws Exception{
        double increment = 0.01;
        for(Dataset r : trainingData) {

            for(int i = 0; i < 40; ++i) {
                if(r.features[i] == 0) continue;

                Pair p = new Pair();
                p.featureId = i;
                p.resultId = r.result;

                if (m.exist(p)){
                    m.put(p, m.getValue(p) + increment);
                }
                else{
                    m.put(p, increment);
                }
            }
        }
        normalizeExp(m);
        PrintMM();
        IIS(trainingData, m, 100);
    }

    public static void IIS(Vector<Dataset> trainingData, MyMap m, double Cmax) throws Exception{

        for(int iteration=0;iteration<20;++iteration) {
            Log("Iteration "+iteration + " ---------------------------\n");
            PrintLambdas();

            MyMap EpFj_model = new MyMap();

            for(Map.Entry<Pair, Double> f : m.entrySet()) {
                Pair tp = f.getKey();
                EpFj_model.put(tp, 0.0);
            }

            //calculate the model probabilities
            for(Dataset r : trainingData) {
                Map<Integer, Double> classScores = new HashMap<>();

                for(int i = 0; i < 2; ++i) {
                    //get each class 's probability according to feature class pairs ' weight.
                    double score = calculateClassScore(r.features, i);
                    // for the first time run, the score is zero. since lambda not given value.
                    classScores.put(i, score);
                }
                normalizeExp(classScores);

                for(Map.Entry<Integer, Double> entry : classScores.entrySet()) {
                    Integer theClass = entry.getKey(); // 0 or 1
                    //score the class 's prob.
                    Double score = entry.getValue();

                    double probabilityFraction = score/100;

                    for(int k = 0; k < 40; ++k) {
                        Pair p = new Pair();

                        p.featureId = k; p.resultId = theClass;

                        // System.out.println(" " + p.featureId + " resutl is " + p.resultId);

                        // Map.Entry<Pair, Double> e = EpFj_model.exist(p);
                        if (EpFj_model.exist(p)){
                            EpFj_model.put(p, EpFj_model.getValue(p) + probabilityFraction);
                            //System.out.print(" \t" + p.featureId + " resutl is " + p.resultId + " " + probabilityFraction);
                        } else {
                            EpFj_model.put(p, probabilityFraction);
                            //System.out.print(" \t" + p.featureId + " resutl is " + p.resultId + " " +  e.getValue() + probabilityFraction);
                        }
                    }
                    //System.out.println();
                }
            }

            printEpFj_model(EpFj_model);

            Double minimumNonInfiniteLambdaWeight = null;
            Double maximumNonInfiniteLambdaWeight = null;

            for(Map.Entry<Pair, Double> featureClassCounts : EpFj_model.entrySet()) {
                Pair p = featureClassCounts.getKey();
                // System.out.println(" MAP " + p.featureId + " resutl is " + p.resultId);
                Double EpFj_observed_value = m.getValue(p);
                Double EpFj_model_value = featureClassCounts.getValue();
                Log(" observed as " + EpFj_observed_value + " model is " + EpFj_model_value + "\n");

                if(EpFj_observed_value == null || EpFj_observed_value==0.0) {
                    lambdas.put(p, -100000.0);
                    //infiniteLambdaWeights.add(tp);
                }
                else if(EpFj_model_value == null || EpFj_model_value==0.0) {
                    lambdas.put(p, 100000.0);
                    //infiniteLambdaWeights.add(tp);
                }
                else if(Math.abs(EpFj_observed_value - EpFj_model_value)<=1e-8) {
                    continue;
                }
                else {
                    //the formula below can't produce a +inf or -inf value
                    // just put the unassigned prob value to each feature class evenly.

                    // System.out.println("come here and ");
                    double deltaJ = Math.log(EpFj_observed_value/EpFj_model_value)/Cmax;
                    // int ii = 1;
                    // System.out.println("come here and " + p.featureId +  " " + p.resultId + " deltaJ is " + deltaJ);
                    double newValue = deltaJ;
                    if (lambdas.getValue(p) != null) {
                        newValue += lambdas.getValue(p);

                        // System.out.println(" lambda updated as " + newValue);
                    }
                    lambdas.put(p, newValue); //update lamdas by delta

                    if(minimumNonInfiniteLambdaWeight==null || newValue<minimumNonInfiniteLambdaWeight) {
                        minimumNonInfiniteLambdaWeight=newValue;
                    }
                    if(maximumNonInfiniteLambdaWeight==null || newValue>maximumNonInfiniteLambdaWeight) {
                        maximumNonInfiniteLambdaWeight=newValue;
                    }
                }

            }
        }
    }


    public static Double calculateClassScore(int [] x, int theClass) {
        double score = 0;
        for(int i = 0; i < 40; ++i) {
            Pair p = new Pair(); p.featureId = i; p.resultId = theClass;
            Double lambdaWeight = lambdas.getValue(p);
            if(lambdaWeight!=null) {//ensure that the feature is in the dictionary
                score+=lambdaWeight;
            }
        }
        return score;
    }

    public static  void main(String[] args) throws Exception{
        generateData();
        // PrintMM();
        estimateModelParameters();
    }
}
