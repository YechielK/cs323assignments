#include <iostream>
#include <fstream>
#include <sstream> 
using namespace std;

class listNode {
    public:
    string chStr;
    int prob;
    listNode *next;

    listNode() {
        chStr = "";
        prob = 0;
        next = NULL;
    }

    listNode(string c, int p) {
        chStr = c;
        prob = p;
        next = NULL;
    }
    
};

class linkedList {
    public:
    listNode *listHead;

    linkedList() {
         listNode *dummy = new listNode("dummy",0);
         listHead = dummy;
    }

    listNode *insertNewNode(listNode *listHead,listNode *newNode) {
        listNode *spot = new listNode();
        spot = this->findSpot(listHead,newNode);

        if (spot->next == NULL) {
            spot->next = newNode;
            return spot;
        }

        newNode->next = spot->next;
            spot->next = newNode;
            return spot;
        }    
           
    listNode *findSpot (listNode *spot,listNode *newNode) {
        while ((spot->next != NULL) && (spot->next->prob < newNode->prob)) {
           spot = spot->next;
        }
        return spot;
    }

    void print() {
        listNode *temp = listHead;
        cout << "listHead-->";
        while(temp != NULL){
            cout << "(" << temp->chStr << "," << temp->prob << ",";
            if (temp->next == NULL) {break;}
            temp = temp->next;
            cout << temp->chStr << ")-->";
        }
        cout << "NULL)-->NULL" << endl;
    }

     void print(ofstream& outFile) {
        listNode *temp = listHead;
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


  

int main(int argc,char* argv[]) {
    
    //Step 1:
    linkedList* ll = new linkedList();

    //Step 2:
    ifstream inFile;
    ofstream outFile;
    inFile.open(argv[1]);
    outFile.open(argv[2]);
    string line;    
    char chStr;
    int prob;
    
    if (inFile.is_open()) {
        while(getline (inFile,line)) {
            //Step 3:
            stringstream ss(line);
            ss >> chStr;
            ss >> prob;
            //ll->print();
            //Step 4:
            ll->print(outFile);
            //Step 5:
            ll->insertNewNode(ll->listHead, new listNode(string(1,chStr),prob));
            //Step 6:
        }   
    }
    //ll->print();
    ll->print(outFile);
    //Step 7:
    inFile.close();
    outFile.close();
}