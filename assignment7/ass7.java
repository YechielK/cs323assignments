import java.io.*;
import java.util.*; 
public class ass7  {

    static public class treeNode {
        String chStr;
        int prob;
        String code;
        treeNode left;
        treeNode right;
        treeNode next;

        treeNode() {
            chStr = "";
            prob = 0;
            code = "";
            left = null;
            right = null;
            next = null;
        }
        treeNode(String c, int p) {
            chStr = c;
            prob = p;
            code = "";
            left = null;
            right = null;
            next = null;
        }


        public void printNode(treeNode t, FileWriter DebugFile) throws Exception  {
            if (t == null) {
                return;
            }
            DebugFile.write("(" + checkNLCR(t.chStr) + "," + t.prob + ")");
            
            
            
            if (t.next == null) {
                DebugFile.write(" Next: null");
            } else {
                DebugFile.write(" Next: " + checkNLCR(t.chStr));
            }

            if (t.left == null) {
                DebugFile.write(" Next: null");
            } else {
                DebugFile.write(" Left: " + checkNLCR(t.left.chStr));

            }
            
            if (t.right == null) {
                DebugFile.write(" Right: null");
            } else {
                DebugFile.write(" Right: " + checkNLCR(t.right.chStr));
            }
            DebugFile.write("\n");
        }
    }

    static public class linkedList {
        treeNode listHead;

        linkedList() {
            listHead = new treeNode("dummy",0);
        }

        linkedList(treeNode newNode) {
            listHead = newNode;
        }

        public treeNode getListHead() {
            return listHead;
        }

        public treeNode insertNewNode(treeNode newNode) {
            treeNode spot = listHead;
            spot = this.findSpot(spot, newNode);
            if(spot.next == null) {
                return insert(spot, newNode);
            }
            newNode.next = spot.next;
            return insert(spot, newNode);
        }

        public treeNode findSpot(treeNode spot, treeNode newNode) {
            while((spot.next != null) && (spot.next.prob < newNode.prob)) {
                spot = spot.next;
            }
            return spot;
        }

        public treeNode insert(treeNode spot, treeNode newNode) {
            spot.next = newNode;
            return spot;
        }

        public void printList() {
            treeNode temp = listHead;
            System.out.print("listHead-->");
            while(temp != null) {
                System.out.print("(" + temp.chStr + "," + temp.prob + ",");
                if (temp.next == null) {break;}
                temp = temp.next;
                System.out.print(temp.chStr + ")-->");            
            }
            System.out.print("null)-->null");
            System.out.println();
        }

        public void printList(FileWriter DebugFile) throws Exception {

            treeNode temp = listHead;
            DebugFile.write("listHead-->");
            
            while(temp != null) {
/*                   if ((int)  temp.chStr.charAt(0) == 10 ) {
                    DebugFile.write("(" + "NL" + "," + temp.prob + ",");
                } else if ((int)temp.chStr.charAt(0) == 13) {
                    DebugFile.write("(" + "CR" + "," + temp.prob + ",");
                    } else {   */
                        DebugFile.write("(" + checkNLCR(temp.chStr) + "," + temp.prob + ",");
                    //}
                        if (temp.next == null) {break;}
                        temp = temp.next;
                        
                        DebugFile.write(checkNLCR(temp.chStr) + ")-->");   
                    
            }
            DebugFile.write("null)-->null");
            DebugFile.write("\n");
        }

        
    }

    static public class BinaryTree {
        treeNode root;

        BinaryTree() {
            root = null;
        }

        BinaryTree(treeNode newRoot) {
            root = newRoot;
        }

         public void preOrderTraversal(treeNode root,FileWriter DebugFile) throws Exception {
            if (isLeaf(root)) {
                root.printNode(root,DebugFile);
                return;
            } else {
                root.printNode(root,DebugFile);
                preOrderTraversal(root.left,DebugFile);
                preOrderTraversal(root.right,DebugFile);
            }
        }
        public void inOrderTraversal(treeNode root,FileWriter DebugFile)throws Exception {
            if (isLeaf(root)) {
                root.printNode(root,DebugFile);
                return;
            } else {
                inOrderTraversal(root.left,DebugFile);
                root.printNode(root,DebugFile);
                inOrderTraversal(root.right,DebugFile);
            }
        }
        public void postOrderTraversal(treeNode root,FileWriter DebugFile)throws Exception {
            if (isLeaf(root)) {
                root.printNode(root,DebugFile);
                return;
            } else {
                postOrderTraversal(root.left,DebugFile);
                postOrderTraversal(root.right,DebugFile);
                root.printNode(root,DebugFile);
            }
        } 

        public boolean isLeaf(treeNode node) {
            if (node.left == null && node.right == null) {
                return true;
            }
            return false;
        }


        public void constructCharCode (treeNode T, String code,FileWriter DebugFile,String[] charCode) throws Exception {
            int index;
            if (isLeaf(T)) {
                T.code = code;
                
                index = (int) T.chStr.charAt(0);
                charCode[index] = code;
                //DebugFile.write(T.chStr + "\n" + T.code + "\n");
            } else {
                constructCharCode(T.left, code + "0",DebugFile, charCode);
                constructCharCode(T.right, code + "1",DebugFile, charCode);
            }
    
        }
    }
    
    static public void computeCharCounts(FileReader inFile,int []charCountAry ) throws Exception {
        int index;
        while (true) {
             index =  inFile.read();
             //System.out.print((char)index);
             if (index == -1 ) {break;}
            if (index < 0 || index > 255) {continue;}
            charCountAry[index]++;  
        }
    }

    static public void printCountAry(int[] charCountAry) {
        for (int i = 0; i < 256; i++) {
            if (charCountAry[i] == 0) {continue;}
            System.out.println(i + " " + (char)i + " " + charCountAry[i] );
        }   
    }

    static public void printCountAry(int[] charCountAry, FileWriter DebugFile) throws Exception  {
        for (int i = 0; i < 256; i++) {
            if (charCountAry[i] == 0) {continue;}
                DebugFile.write( i + " " + checkNLCR(Character.toString((char) i))  + " " + charCountAry[i] + "\n" );
        }
        
    }

    static public treeNode  constructHuffmanLList(int[] charCountAry, FileWriter DebugFile) throws Exception  {
        //Step 0:
        linkedList listHead = new linkedList();

        char chr;
        int prob;
        //Step 1:
        int index = 0;
        while(index < 256) {
            //Step 2:
            if (charCountAry[index] > 0) {
                chr = (char) index;
                prob = charCountAry[index];
                treeNode newNode = new treeNode(Character.toString(chr),prob);
                
                //newNode.printNode(newNode);
                listHead.insertNewNode(newNode);
                //listHead.printList(DebugFile);
                
            
            }
            //Step 3:
            index++;   
            //Step 4:
        }  
        listHead.printList(DebugFile); 
        return listHead.getListHead();
    }   

    static public treeNode constructHuffmanBinTree(linkedList ll, FileWriter DebugFile) throws Exception {

        treeNode listHead = ll.getListHead();

        while(listHead.next.next != null) {
            treeNode newNode = new treeNode();

            newNode.prob = listHead.next.prob + listHead.next.next.prob;
            newNode.chStr = checkNLCR(listHead.next.chStr) + checkNLCR(listHead.next.next.chStr);
            newNode.left = listHead.next;
            newNode.right = listHead.next.next;
            newNode.next = null;

            ll.insertNewNode(newNode);

            listHead.next = listHead.next.next.next;

            
            //ll.printList(DebugFile);
        }

        return listHead.next;

    }
    


    static public void Encode(FileReader inFile, FileWriter outFile, String[] charCode) throws Exception {
        
        while (true) {
            //Step 1,2:
            int index = inFile.read();
            if (index <= -1 ) {break;}
            //System.out.println(index + " "+(char)index);
            //Step 3:
            String code = charCode[index];
            //Step 4:
            outFile.write(code);
            //Step 5:
        }
    }

    static public void Decode(FileReader inFile, FileWriter outFile, BinaryTree bt) throws Exception {
        //Step 1
        treeNode spot = bt.root;
        int oneBit;
        while(true) {
            //Step 2
            if (bt.isLeaf(spot)) {
                outFile.write(spot.chStr);
                oneBit = inFile.read();
                if (oneBit == -1) { 
                    break;
                }
                spot = bt.root;
                } else {
                    oneBit = inFile.read();
                    if (oneBit == -1) {
                        System.out.println("Error:  The compress file is corrupted!");
                    }
                }

            //Step 4
            if (oneBit == 48) {
                spot = spot.left;
            } else if (oneBit == 49) {
                spot = spot.right;
            } else {
                System.out.println("Error! The compress file contains invalid character");
                if (bt.isLeaf(spot)) {System.out.println("here leaf");}
                return;
            }
        }
        
    }

    static public void userInterface(BinaryTree bt,String[] charCode) throws Exception {
        
        Scanner in = new Scanner(System.in);
        
        String nameOrg;
        String nameCompress;
        String nameDeCompress;
        char yesNo = 'a';
        //Step 1:
        while (true) {
        System.out.println("yo u want to encode a file? Y - yes, N - no");
         yesNo = in.next().charAt(0);
        
        if (yesNo == 'N') {
            return;
        } else if (yesNo == 'Y') {
            System.out.println("enter file name to be compressed");
            nameOrg = in.nextLine(); 

            System.out.print("File Name: ");
            nameOrg = in.nextLine().trim();

        } else {
            continue;
        }
        //System.out.println(nameOrg);

        //Step 2:
        nameCompress = nameOrg.substring(0,nameOrg.length() - 4) + "_Compressed.txt";
        //System.out.println(nameCompress);
        nameDeCompress = nameOrg.substring(0,nameOrg.length() - 4) + "_DeCompressed.txt";
        //System.out.println(nameDeCompress);
        //Step 3:
        FileReader orgFile = new FileReader(nameOrg);
        FileWriter compFile = new FileWriter(nameCompress);
        FileWriter deCompFile = new FileWriter(nameDeCompress);
        //System.out.println(compFile);
       
        //Step 4:
        Encode(orgFile,compFile, charCode);
        //Step 5:
        compFile.close();
        //Step 6:
        FileReader compFile2 = new FileReader(nameCompress);
        //Step 7:
        Decode(compFile2,deCompFile, bt);
        System.out.println("k done");
        //Step 8:
        orgFile.close();
        compFile2.close();
        deCompFile.close();
        //Step 9:
    }

 

        
    }

    static public String checkNLCR(String chStr) throws Exception{
        if ((int)  chStr.charAt(0) == 10 ) {
            return "NL";
        } else if ((int)chStr.charAt(0) == 13) {
            return "CR";
            } else if ((int)chStr.charAt(0) == 32) {
                return "space";
            } else {  
                return chStr;
            } 
    }
    public static void main(String[] args) throws Exception {

        FileReader inFile = new FileReader(args[0]);  

        File nameDebugFile = new File(args[0].substring(0,args[0].length() - 4) + "_DeBug.txt");
        nameDebugFile.createNewFile();
        FileWriter DebugFile = new FileWriter(args[0].substring(0,args[0].length() - 4) + "_DeBug.txt");
        //System.out.println(nameDebugFile);
        
        int[] charCountAry = new int[256];
        String[] charCode = new String[256];
        
        //Step 1:
        computeCharCounts(inFile,charCountAry);
        //Step 2:
        printCountAry(charCountAry,DebugFile);
        //printCountAry(charCountAry);
        //Step 3:
        linkedList ll = new linkedList(constructHuffmanLList(charCountAry,DebugFile));
        //Step 4:
        BinaryTree bt = new BinaryTree(constructHuffmanBinTree(ll, DebugFile));
        //Step 5:
        bt.constructCharCode(bt.root,"",DebugFile,charCode);
        //Step 6:
        //ll.printList(DebugFile);
        //Step 7:
        DebugFile.write("Pre-Order Traversal \n");
        bt.preOrderTraversal(bt.root,DebugFile);
        DebugFile.write("In-Order Traversal \n");
        bt.inOrderTraversal(bt.root,DebugFile);
        DebugFile.write("Post-Order Traversal \n");
        bt.postOrderTraversal(bt.root,DebugFile);  
        //Step 8:
        userInterface(bt,charCode);
        //Step 9:
        inFile.close();
        DebugFile.close();
    }
}
