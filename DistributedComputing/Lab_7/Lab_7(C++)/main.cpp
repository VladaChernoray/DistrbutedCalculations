#include <iostream>
#include <tbb/parallel_for.h>
#include <tbb/blocked_range.h>
#include <ctime>
#include <chrono>

using namespace tbb;
using namespace std;
using namespace chrono;

const int n = 100;

double A[n][n];
double B[n][n];
double C[n][n];

class MatrixMultiplication {
public:
    void operator()(blocked_range<int> range) const {
        for (int i = range.begin(); i != range.end(); i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }
};


int main() {
    for (int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            std::srand(unsigned(time(nullptr)));
            A[i][j] = 1;
            B[i][j] = rand() + i * j;
            C[i][j] = 0;
        }
    }
    auto start = high_resolution_clock::now();
    parallel_for(blocked_range<int>(0,n), MatrixMultiplication());
    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<milliseconds>(stop - start);
    cout << "Time of execution: " << (double)duration.count() << "ms";
    return 0;
}

