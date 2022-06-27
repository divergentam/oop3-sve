package rs.ac.ni.oop3.tamara333.vezba7.zadatak8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Matrix {
    final Semaphore semaphore[][];
    final int[][] matrix;

    public Matrix(int m) {
        semaphore = new Semaphore[m][];
        for(int i = 0; i < m; i++){
            semaphore[i] = new Semaphore[m];
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j<m; j++){
                semaphore[i][j] = new Semaphore(1);
            }
        }

        matrix = new int[m][];
        for(int i = 0; i < m; i++){
            matrix[i] = new int[m];
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j<m; j++){
                matrix[i][j] = 0;
            }
        }
    }


    public boolean allowAccess(int i, int j) throws InterruptedException {
        return semaphore[i][j].tryAcquire();
    }

    public void deniedAccess(int i, int j){
        semaphore[i][j].release();
    }

}
