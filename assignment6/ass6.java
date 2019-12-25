import java.io.*;
import java.util.*; 
import java.util.Scanner;

public class ass6 {

   public static class runLength {
        int numRows;
        int numCols;
        int minVal;
        int maxVal;
        int numRuns;
        int whichMethod;
        String nameEncodeFile;
        String nameDecodeFile;

        public runLength () {
            numRows = 0;
            numCols = 0;
            minVal = 0;
            maxVal = 0;
            numRuns = 0;
            whichMethod = 0;
            nameEncodeFile = "";
            nameDecodeFile = "";
        }

        public static void encodeMethod1(Scanner inFile, FileWriter encodeFile, int numRows, int numCols) {
            //Step 0:
            int row = 0;

            try {
                while(row < numRows) {
                    //Step 1:
                    int col = 0;
                    int length = 0;
                    int currVal = inFile.nextInt();

                    int nextVal = 0;

                    //Step 2:
                    encodeFile.write(new Integer(row).toString() + " ");
                    encodeFile.write(new Integer(col).toString() + " ");
                    encodeFile.write(new Integer(currVal).toString() + " ");
                    length++;
          
                    
                    while (true){
                        //Step 3:
                        col++;
                        if (col == numCols) {
                            encodeFile.write(new Integer(length).toString() + "\n");
                            break;
                        }
                        //Step 4:
                        nextVal = inFile.nextInt();
                        //Step 5:
                        if(nextVal == currVal) {
                            length++;
                        } else {    
                            encodeFile.write(new Integer(length).toString() + "\n");
                            currVal = nextVal;
                            length = 1;


                            encodeFile.write(new Integer(row).toString() + " ");
                            encodeFile.write(new Integer(col).toString() + " ");
                            encodeFile.write(new Integer(currVal).toString() + " ");
                        }
                        //Step 6:
                    } 
                        //Step 7:
                        row++;
                        //Step 8:
                }    
            }   catch (Exception e) {
                }       
        }
    
        public static void decodeMethod1(Scanner encodeFile, FileWriter decodeFile) {
        
            int rowSize = encodeFile.nextInt();
            int colSize = encodeFile.nextInt();
            int min = encodeFile.nextInt();
            int max = encodeFile.nextInt();
             //System.out.println(rowSize + " " + colSize + " " + min + " " + max); 
            int[][] imageArr = new int[rowSize][colSize];
            encodeFile.nextInt();
        
            while(encodeFile.hasNext()) {
                int row = encodeFile.nextInt();
                int col = encodeFile.nextInt();
                int gscale  = encodeFile.nextInt();
                int length = encodeFile.nextInt();
                 //System.out.println(row + " " + col + " " + gscale + " " + length);
                          
                while (length > 0) {
                    imageArr[row][col] = gscale;
                    length--;
                    col++;
                }
            }

            try {
                decodeFile.write(new Integer(rowSize).toString() + " ");
                decodeFile.write(new Integer(colSize).toString() + " ");
                decodeFile.write(new Integer(min).toString() + " ");
                decodeFile.write(new Integer(max).toString() + "\n");
                for (int i = 0; i < rowSize; i++) {
                    for (int j = 0; j < colSize; j++) {
                        decodeFile.write(new Integer(imageArr[i][j])  + " ");
                         //System.out.print(imageArr[i][j]);
                    }
                    decodeFile.write("\n");
                     //System.out.println();
                }
            } catch (Exception e) {

            }
        } 
    }



    public static void main(String[] args) throws Exception {

        Scanner inFile = new Scanner(new FileReader(args[0]));
        //Step 0:
        runLength length = new runLength();
        length.numRows = inFile.nextInt();
        length.numCols = inFile.nextInt(); 
        length.minVal = inFile.nextInt();
        length.maxVal = inFile.nextInt();

        length.whichMethod = 1;
        length.nameEncodeFile = "RunLength" + "_EncodeMethod_" + length.whichMethod;

        //Step 1:
        File outFile1 = new File(length.nameEncodeFile + ".txt");
        outFile1.createNewFile();

        FileWriter encodeFile = new FileWriter(length.nameEncodeFile + ".txt");

        //Step 2:
        encodeFile.write(new Integer(length.numRows).toString() + " ");
        encodeFile.write(new Integer(length.numCols).toString() + " ");
        encodeFile.write(new Integer(length.minVal).toString() + " ");
        encodeFile.write(new Integer(length.maxVal).toString() + "\n");
        encodeFile.write(new Integer(length.whichMethod).toString() + "\n");  
        //Step 3:
        runLength.encodeMethod1(inFile, encodeFile,length.numRows,length.numCols);
        //Step 4:
        encodeFile.close();
        //Step 5:
        Scanner encodeFile2 = new Scanner(new FileReader(length.nameEncodeFile + ".txt"));
        //Step 6:
        File outFile2 = new File(length.nameEncodeFile + "_Decoded.txt");
        //Step 7:
        FileWriter decodeFile = new FileWriter(length.nameEncodeFile + "_Decoded.txt");
        //Step 8:
        runLength.decodeMethod1(encodeFile2, decodeFile);
        //Step 9:
        encodeFile2.close();
        decodeFile.close();
    }
} 




