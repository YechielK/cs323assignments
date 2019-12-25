#include <iostream>
#include <fstream>
#include <sstream>
using namespace std;

void EoF (ifstream& inFile, ofstream& outFile) {
    int data;
    while (inFile >> data) {
        cout<< data << endl;
        outFile << data << endl;
    }
}


int main(int argc,char* argv[]) {

    ifstream inFile1,inFile2;
    ofstream outFile;

    inFile1.open(argv[1]);
    inFile2.open(argv[2]);
    outFile.open(argv[3]);

    int data1,data2;

    inFile1 >> data1;
    inFile2 >> data2;

    while (!inFile1.eof() && !inFile2.eof() ) {
        if (data1 < data2) {
            cout<<data1<<endl;
            outFile<<data1<<endl;
            inFile1>>data1;
        } else  {
            cout<<data2<<endl;
            outFile<<data2<<endl;
            inFile2>>data2;
        }
    }
    if (inFile1.eof()) {
        cout<<data2<<endl;
        outFile<<data2<<endl;
        EoF(inFile2,outFile);
    } else {
        cout<<data1<<endl;
        outFile<<data1<<endl;
        EoF(inFile1,outFile);
    }
    inFile1.close();
    inFile2.close();
    outFile.close();
}
