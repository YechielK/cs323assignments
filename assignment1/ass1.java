import java.io.*;
//import java.util.*; 

public class ass1 {

    private static void printAry(FileWriter OutFile, int[] charCounts) throws Exception {
      for (int i = 0; i < 256; i++) {
        if (charCounts[i] == 0) {continue;}
        OutFile.write("char" + i + " " + charCounts[i] + "\r\n" );
      }
    }

    public static void main(String[] args) throws Exception {
      //Step 0:
      FileReader InFile = new FileReader(args[0]);  
      FileWriter OutFile = new FileWriter(args[1]); 
      int[] charCounts = new int[256];
      int index;

      while (true) {
          //Step 1,2:
          index = InFile.read();
          if (index == -1 ) {break;}
          if (index < 0 || index > 255) {continue;}
          //Step 3:
          charCounts[index]++;
          //Step 4
      }

      //Step 5:
      printAry(OutFile,charCounts);
      //Step 6:
      InFile.close();
      OutFile.close();
    } 
} 




