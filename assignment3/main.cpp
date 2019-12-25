#include <iostream>
#include <fstream>
#include <sstream> 
using namespace std;

class treeNode {
    public:
    string chStr;
    int prob;
    string code;
    treeNode *left;
    treeNode *right;
    treeNode *next;

    treeNode() {
        chStr = "";
        prob = 0;
        code = "";
        left = NULL;
        right = NULL;
        next = NULL;
    }

    treeNode(string c, int p) {
        chStr = c;
        prob = p;
        code = "";
        left = NULL;
        right = NULL;
        next = NULL;
    }

    void printNode(treeNode *T) {
        if (T == NULL) {
            return;
        }
        //cout << "(" << T->chStr << "," << T->prob<<")";

        if (T->next == NULL) {
            //cout << " Next: null";
        } else {
            //cout << " Next: " << T->next->chStr;
        }
      
        if (T->left == NULL ) {
            //cout << " Left: null";
        } else {
            //cout << " Left: " << T->left->chStr;
        }
        
        if (T->right == NULL) {
            //cout << " Right: null";
        } else {
            //cout << " Right: "<<T->right->chStr;
        }
        //cout<<endl;
    }
    
        void printNode(treeNode *T, ofstream& outFile) {
        if (T == NULL) {
            return;
        }
        outFile << "(" << T->chStr << "," << T->prob<<")";

        if (T->next == NULL) {
            outFile << " Next: null";
        } else {
            outFile << " Next: " << T->next->chStr;
        }
      
        if (T->left == NULL ) {
            outFile << " Left: null";
        } else {
            outFile << " Left: " << T->left->chStr;
        }
        
        if (T->right == NULL) {
            outFile << " Right: null";
        } else {
            outFile << " Right: " << T->right->chStr;
        }
        outFile << endl;
    }
};

class linkedList {
    public:
    treeNode *listHead;

    linkedList() {
         treeNode *dummy = new treeNode("dummy",0);
         listHead = dummy;
    }

    linkedList(treeNode *newNode) {
        listHead = newNode;
    }

    treeNode *insertNewNode(treeNode *listHead,treeNode *newNode) {
        treeNode *spot = new treeNode();
        spot = this->findSpot(listHead,newNode);

        if (spot->next == NULL) {
            spot->next = newNode;
            return spot;
        }

        newNode->next = spot->next;
        spot->next = newNode;
        return spot;
        }    
           
    treeNode *findSpot (treeNode *spot,treeNode *newNode) {
        while ((spot->next != NULL) && (spot->next->prob < newNode->prob)) {
           spot = spot->next;
        }
        return spot;
    }

    void print() {
        treeNode *temp = listHead;
        //cout << "listHead-->";
        while(temp != NULL){
            //cout << "(" << temp->chStr << "," << temp->prob << ",";
            if (temp->next == NULL) {break;}
            temp = temp->next;
            //cout << temp->chStr << ")-->";
        }
        //cout << "NULL)-->NULL" << endl;
    }

     void print(ofstream& outFile) {
        treeNode *temp = listHead;
        outFile<<"listHead-->";
        while(temp != NULL){
            outFile << "(" << temp->chStr << "," << temp->prob << ",";
            if (temp->next == NULL) {break;}
            temp = temp->next;
            outFile << temp->chStr << ")-->";
        }
        outFile << "NULL)-->NULL" << endl;
    } 
};

class HuffmanBinaryTree {
    public:
    treeNode *root;
    linkedList *linkedlist;


    HuffmanBinaryTree() {
        treeNode *dummy = new treeNode("dummy",0);
        root = dummy;
        linkedList* ll = new linkedList();
    }

    void constructHuffmanLList(ifstream& inFile, ofstream& outFile) {
        //Step 1:
        linkedList* ll = new linkedList();

        string line;    
        char chStr;
        int prob;

        if (inFile.is_open()) {
            
            while(getline (inFile,line)) {
                //Step 2:
                stringstream ss(line);
                ss >> chStr;
                ss >> prob;
                //Step 3:
                ll->print();
                ll->print(outFile);
                //Step 4:
                ll->insertNewNode(ll->listHead, new treeNode(string(1,chStr),prob));
                //Step 5:
            }   
        }
    ll->print();
    
    ll->print(outFile);
    root = ll->listHead;
    
    linkedlist = ll;
    }

    void constructHuffmanBinTree( ofstream& outFile ) {
        
          treeNode *listHead = linkedlist->listHead;

            while(listHead->next->next!=NULL){
            //Step 1:
            treeNode *newNode = new treeNode();

            newNode->prob = listHead->next->prob + listHead->next->next->prob;
            newNode->chStr = listHead->next->chStr + listHead->next->next->chStr;   
            newNode->left = listHead->next;
            newNode->right = listHead->next->next;
            newNode->next = NULL;
            //Step 2:
            linkedlist->insertNewNode(listHead,newNode);
            //Step 3:
            listHead->next = listHead->next->next->next;
            //Step 4:
            linkedlist->print(outFile);

            //newNode->printNode(newNode);   
            ////Step 5:         
        }
        //Step 6:
        root = listHead->next;
    }

    bool isLeaf(treeNode *T) {
        if (T->left == NULL && T->right == NULL){
            return true;
        }
        return false;
    }

    void constructCharCode(treeNode *T, string code, ofstream& outFile) {
        if (isLeaf(T)) {
            T->code = code;
            //cout << T->chStr << endl << T->code << endl;
            outFile << T->chStr << endl << T->code << endl;
        } else {
            constructCharCode (T->left,code + "0", outFile);
            constructCharCode (T->right,code + "1", outFile);   
        }
    }

 
    void preOrderTraversal(treeNode *T, ofstream& outFile) {
        if (isLeaf(T)) {
            T->printNode(T,outFile);
            return;
        } else {
            T->printNode(T,outFile);
            preOrderTraversal(T->left, outFile);
            preOrderTraversal(T->right, outFile);
        }
    }

    void inOrderTraversal(treeNode *T, ofstream& outFile) {
       if (isLeaf(T)) {
            T->printNode(T,outFile);
            return;
        } else {
            inOrderTraversal(T->left, outFile);
            T->printNode(T,outFile);
            inOrderTraversal(T->right, outFile);
        }
    }

    void postOrderTraversal(treeNode *T, ofstream& outFile) {
       if (isLeaf(T)) {
            T->printNode(T,outFile);
            return;
        } else {
            postOrderTraversal(T->left, outFile);
            postOrderTraversal(T->right, outFile);
            T->printNode(T,outFile);
        }
    }
};

  

int main(int argc,char* argv[]) {
    //Step 0:
    ifstream inFile;
    inFile.open(argv[1]);

    ofstream outFile1,outFile2,outFile3;
    
    outFile1.open(argv[2]);
    outFile2.open(argv[3]);
    outFile3.open(argv[4]);
    
    HuffmanBinaryTree *hbt = new HuffmanBinaryTree();
    //cout << "LIST" << endl;
    //Step 1:
    hbt->constructHuffmanLList(inFile,outFile3);
    //cout << "TREE" << endl;
    //Step 2:
    hbt->constructHuffmanBinTree(outFile3);
    //Step 3:
    //cout << "the pre-order traversal of the constructed Huffman Binary tree: \n" << endl;
    outFile2 << "the pre-order traversal of the constructed Huffman Binary tree: \n";
    hbt->preOrderTraversal(hbt->root,outFile2);
    //Step 4:
    //cout << "the in-order  traversal of the constructed Huffman Binary tree: \n" << endl;
    outFile2 << "the in-order  traversal of the constructed Huffman Binary tree: \n";
    hbt->inOrderTraversal(hbt->root,outFile2);
    //Step 5:
    //cout << "the post-order traversal of the constructed Huffman Binary tree: \n" << endl;
    outFile2 << "the post-order traversal of the constructed Huffman Binary tree: \n";
    hbt->postOrderTraversal(hbt->root,outFile2);
    //Step 6:
    //cout << "CODE" << endl;
    hbt->constructCharCode(hbt->root,"",outFile1);

    //Step 7:
    inFile.close();
    outFile1.close();
    outFile2.close();
    outFile3.close();
}