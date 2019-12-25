import java.io.*;
import java.util.*; 
import java.util.Scanner;
import java.lang.Math;
public class kmean  {
    public static int change = 0;

public static Scanner in;
public static Scanner inFile;
public static FileWriter outFile;
public static int numRows;
public static int numCols;
public static int[][] imgAry;
public static int numPts;
public static Point[] pointSet;
public static int K;
public static Point[] Kcentroids;




    public static class Point {

        public double Xcord;
        public double Ycord;
        public int Label;
        public double Distance;


        Point() {
            this.Xcord = 0;
            this.Ycord = 0;
            this.Label = 0;
            this.Distance = 0;
        }

        Point(double Xcord, double Ycord, int Label, double Distance) {
            this.Xcord = Xcord;
            this.Ycord = Ycord;
            this.Label = Label;
            this.Distance = Distance;
        }
    }

    public static void loadPointSet(Scanner inFile, Point[] pointSet) {
        //Step 0:
        int index = 0;
        int x;
        int y;
        while (inFile.hasNext()) {
            //Step 1:
            x = inFile.nextInt();
            y = inFile.nextInt();
            //Step 2:
            pointSet[index].Xcord = (double) x;
            pointSet[index].Ycord = (double) y;
            pointSet[index].Label = 0;
            pointSet[index].Distance = 99999.00;
            //Step 3:
            index++;
            //Step 4:
        } 


    }

    public static void assignLabel(Point[] pointSet, int K) {
        //Step 0:
        int front = 0;
        int back = numPts - 1;
        int label = 1;

        while (front <= back) {
            //Step 1:
            if (label > K) {
                label = 1;
            }
            //Step 2:
            pointSet[front].Label = label;
            front++;
            label++;
            //Step 3:
            pointSet[back].Label = label;
            back--;
            label++;
            //Step 4:
        }
    }

    public static void Point2Image(Point[] pointSet, int[][] imgAry) {
        for (int i = 0; i < pointSet.length; i++) {
            imgAry[(int) pointSet[i].Xcord][(int) pointSet[i].Ycord] = pointSet[i].Label;
        }
    }

    public static void kMeansClustering (Point[] pointSet, int K) throws Exception {

        //Step 0:
        int iteration = 0;
        //Step 1:
        assignLabel(pointSet, K); 
        do {
            //Step 2:
            Point2Image(pointSet,imgAry);
            printImage(imgAry, outFile, iteration);
            //Step 3:
            change = 0;
            //Step 4:
            computeCentroids(pointSet,Kcentroids);
            //Step 5:
            int index = 0;
            while (index < pointSet.length) {
                //Step 6:
                DistanceMinLable(pointSet[index], Kcentroids);
                //Step 7:
                index++;
                //Step 8:
            } 
            //Step 9:
            iteration++;
            //Step 10:
        } while (change > 2);
    }

    public static void computeCentroids(Point[] pointSet, Point[] Kcentroids) {  
        //Step 0:
        int label;
        double[] sumX = new double[K + 1];
        double[] sumY = new double[K + 1];
        double[] totalPt = new double[K + 1];
        for (int i = 0; i < K + 1; i++) {
            sumX[i] = 0.0;
            sumY[i] = 0.0;
            totalPt[i] = 0.0;
        }
        //Step 1:
        int index = 0;
        while (index < numPts) {
            //Step 2:
            label = pointSet[index].Label;
            sumX[label] += pointSet[index].Xcord;
            sumY[label] += pointSet[index].Ycord;
            totalPt[label]++;
            //Step 3:
            index++;
            //Step 4:
        }
        //Step 5:
        label = 1;
        while (label <= K) { 
        //Step 6:
        Kcentroids[label].Xcord = (sumX[label] / totalPt[label]);
        Kcentroids[label].Ycord = (sumY[label] / totalPt[label]);
        //Step 7:
        label++;
        //Step 8:
        }
    }


    public static void DistanceMinLable(Point pt, Point[] Kcentroids) {
        //Step 0:
        double minDist = 99999.00;
        int minLabel = 0;
        //Step 1:
        int label = 1;
        while (label <= K) {
            //Step 2:
            double dist = computeDist(pt, Kcentroids[label]);
            if (dist < minDist) {
                minLabel = label;
                minDist = dist;
            }
            //Step 3:
            label++;
            //Step 4:
        }
        //Step 5:
        pt.Distance = minDist;
        //Step 6:
        if (pt.Label != minLabel) {
            pt.Label = minLabel;
            change++;   
        }
    }

    public static double computeDist(Point pt, Point Kcentroidslabel) {
        return Math.sqrt(Math.pow(pt.Xcord - Kcentroidslabel.Xcord, 2) + Math.pow(pt.Ycord - Kcentroidslabel.Ycord, 2));
    }

    public static void printImage(int[][] imgAry, FileWriter outFile, int iteration) throws Exception {
        outFile.write("Result of Iteration " + iteration);
        for (int i = 0; i < imgAry.length; i++) {
            for (int j = 0; j < imgAry[0].length; j++) {
                if (imgAry[i][j] > 0) {
                    outFile.write(Integer.toString(imgAry[i][j]));
                } else {
                    outFile.write(" ");
                }
                
                
            }
            outFile.write("\n");
            
        }
    }

    public static void main(String[] args) throws Exception {
        

        // Step 0:
        in = new Scanner(System.in);
        inFile = new Scanner(new FileReader(args[0]));
        outFile = new FileWriter(args[1]);

        numRows = inFile.nextInt();
        numCols = inFile.nextInt();
        imgAry = new int[numRows][numCols];
        numPts = inFile.nextInt();
        pointSet = new Point[numPts];
        for (int i = 0; i < pointSet.length; i++) {
            pointSet[i] = new Point();
        }
        K = in.nextInt();
        Kcentroids = new Point[K + 1];
        for (int i = 0; i < Kcentroids.length; i++) {
            Kcentroids[i] = new Point();
        }
        
        // Step 1:
        loadPointSet(inFile,pointSet);
        // Step 2:
        kMeansClustering(pointSet, K);
        // Step 3:
        in.close();
        inFile.close();
        outFile.close();

   
    

    }
}