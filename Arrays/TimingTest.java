

import java.util.Vector;
import java.util.ArrayList;
import java.util.Random;

public class TimingTest {


    public static void main(String[] args) {
        System.out.println("Begin Testing \n----------------------------------------------------");
        ArrayList arrayL = new ArrayList();
        ArrayList arrayLSet = new ArrayList(55000000);
        Vector vec = new Vector();
        Vector vecSet = new Vector(55000000);
        int[] arr = new int[55000000];
        Random rnd = new Random();
        double arrayListTime = 0;
        double arrayListTimeSum = 0;
        double arrayListSetTime = 0;
        double vectorTime = 0;
        double vectorTimeSum = 0;
        double vectorSetTime = 0;
        double arrayTime = 0;
        double arrayTimeSum = 0;
        long startRun = 0;
        long finishRun = 0;
        long startConstruct = 0;
        long finishConstruct =0;
        double sum =0;

        long start = System.currentTimeMillis();
        for (int j = 0; j < 5; j++) {
            if (j == 0) {
                startRun = System.currentTimeMillis();
                startConstruct=startRun;
                for (int i = 0; i < 55000000; i++) {
                    arrayL.add(rnd.nextInt());
                }
                finishConstruct = System.currentTimeMillis();
                int x =0;
                sum=0;

                while(x<arrayL.size()){
                    sum=sum + (int)arrayL.get(x);
                    x++;
                }
                finishRun=System.currentTimeMillis();
                arrayListTime= ((double)(finishConstruct-startConstruct)) / 1000;
                arrayListTimeSum = (((double)(finishRun - startRun)) / 1000) - arrayListTime;
                arrayL=null;
                System.out.println("Array List Construction Time:" + arrayListTime + " seconds (Growing List)");


            }
            else if (j == 1) {
                startRun = System.currentTimeMillis();
                for (int i = 0; i < 55000000; i++) {
                    arrayLSet.add(rnd.nextInt());
                }
                finishRun=System.currentTimeMillis();
                arrayListSetTime= ((double)(finishRun - startRun)) / 1000;
                arrayLSet=null;
                System.out.println("Array List Construction Time:" + arrayListSetTime + " seconds (Set List)");
                System.out.println("Array List Time to Sum: " + arrayListTimeSum+ " seconds \n----------------------------------------------------");

            }
            else if (j == 2) {
                startRun = System.currentTimeMillis();
                startConstruct=startRun;
                for (int i = 0; i < 55000000; i++) {
                    vec.add(rnd.nextInt());
                }
                finishConstruct=System.currentTimeMillis();
                int x =0;
                sum=0;
                while(vec.size()>x){
                    sum = sum + (int)vec.elementAt(x);
                    x++;
                }
                finishRun=System.currentTimeMillis();
                vectorTime= ((double)(finishConstruct-startConstruct))/1000;
                vectorTimeSum = (((double)(finishRun-startRun)) / 1000) - vectorTime;
                vec=null;
                System.out.println("Vector Construction Time:" + vectorTime + " seconds (Growing Vector)");



            }
            else if (j == 3) {
                startRun = System.currentTimeMillis();
                for (int i = 0; i < 55000000; i++) {
                    vecSet.add(rnd.nextInt());
                }
                finishRun=System.currentTimeMillis();
                vectorSetTime= ((double)(finishRun-startRun))/1000;
                vecSet=null;
                System.out.println("Vector Construction Time:" + vectorSetTime + " seconds (Set Vector)");
                System.out.println("Vector Time to Sum: " + vectorTimeSum+ " seconds \n----------------------------------------------------");
            }
            else {
                startRun = System.currentTimeMillis();
                startConstruct=startRun;
                for (int i = 0; i < 55000000; i++) {
                    arr[i] = rnd.nextInt();
                }

                finishConstruct = System.currentTimeMillis();
                sum=0;
                for (double y:arr){
                    sum = sum + y;
                }
                finishRun=System.currentTimeMillis();
                arrayTime= ((double)(finishConstruct-startConstruct))/1000;
                arrayTimeSum = (((double)(finishRun - startRun)) / 1000) - arrayTime;
                arr=null;
                System.out.println("Array Construction Time:" + arrayTime + " seconds");
                System.out.println("Array Time to sum: " + arrayTimeSum+ " seconds \n----------------------------------------------------");
            }

        }

        long finish = System.currentTimeMillis();
        System.out.println("Total Time:" + ((double) (finish - start)) / 1000 + " seconds");

    }
}