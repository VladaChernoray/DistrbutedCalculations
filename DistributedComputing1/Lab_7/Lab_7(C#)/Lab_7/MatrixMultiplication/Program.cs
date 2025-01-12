﻿using System;
using System.Diagnostics;
using System.Threading.Tasks;

namespace MatrixMultiplication {
    internal class Program {
        #region Parallel_Loop
        static void multiplyMatrices(double[,] A, double[,] B, double[,] C){
            int n = A.GetLength(1);
            int ARows = A.GetLength(0);
            
            Parallel.For(0, ARows, i => {
                for (int j = 0; j < n; j++){
                    double temp = 0;
                    for (int k = 0; k < n; k++){
                        temp += A[i, k] * B[k, j];
                    }
                    C[i, j] = temp;
                }
            });
        }
        #endregion
        
        #region Helper_Methods
        static double[,] makeRandomMatrix(int n) {
            double[,] matrix = new double[n, n];
            Random rand = new Random();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i, j] = rand.Next(100);
                }
            }
            return matrix;
        }

        private static void printMatrix(double[,] matrix) {
            for (int x = 0; x < matrix.GetLength(0); x++) {
                for (int y = 0; y < matrix.GetLength(1); y++) {
                    Console.Write("[{0}] ", matrix[x, y]);
                }
                Console.WriteLine();
            }
        }
        #endregion
        
        public static void Main(string[] args) {
            const int n = 100;
            double[,] A = makeRandomMatrix(n);
            double[,] B = makeRandomMatrix(n);
            double[,] C = new double[n, n];

            Stopwatch stopwatch = new Stopwatch();
            stopwatch.Start();

            multiplyMatrices(A, B, C);
            stopwatch.Stop();
            Console.WriteLine("Time of execution: {0}ms", stopwatch.ElapsedMilliseconds);
            Console.Error.WriteLine("Press any key to exit.");
            Console.ReadKey();
        }
    }
}