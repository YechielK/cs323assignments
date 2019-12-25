#include <iostream>
#include <fstream>
using namespace std;

int main(int argc,char* argv[]) {
    ifstream inFile;
    inFile.open(argv[1]);
    ofstream outFile1,outFile2;
    outFile1.open(argv[2]);
    outFile2.open(argv[3]);
    
    int data;
    int max = 0;
    while (inFile >> data) {
        if (data > max) {
            max = data;
        }
    }
  
    max++;
    inFile.close();
    
    int * bucket = new int [max];
    data = 0;
    for (int i = 0; i < max; i++) {
        bucket[i] = 0;
    }

    int m = 0;
    inFile.open(argv[1]);
    while(inFile >> data) {  
        bucket[data]++; 
        for (int i = 0; i < max; i++) {
            m = bucket[i];
            while (m > 0) {
                outFile2 << i << " ";
                m--;
            }
        }
        outFile2 << endl;
    }
    for (int i = 0; i < max; i++) {
        while (bucket[i] > 0) {
            outFile1 << i << " ";
            bucket[i]--;
        }   
    }
}