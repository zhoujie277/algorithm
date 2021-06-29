package com.future.algoriithm.sparse;

import com.future.algoriithm.utils.PrintUtils;

public class SparseArrayDemo {

    private static int[][] initArray() {
        int[][] a = new int[12][12];
        a[1][2] = 3;
        a[2][3] = 4;
        return a;
    }

    private static int[][] initSparseArray(int[][] srcArray) {
        int row = 0;
        for (int i = 0; i < srcArray.length; i++) {
            for (int j = 0; j < srcArray[i].length; j++) {
                if (srcArray[i][j] != 0) {
                    row++;
                }
            }
        }
        int[][] sparseArray = new int[row + 1][3];
        sparseArray[0][0] = srcArray.length;
        sparseArray[0][1] = srcArray[0].length;
        sparseArray[0][2] = row;
        return sparseArray;
    }

    private static void backupArray(int[][] srcArray, int[][] sparseArray) {
        int rowIndex = 1;
        for (int i = 0; i < srcArray.length; i++) {
            for (int j = 0; j < srcArray[i].length; j++) {
                if (srcArray[i][j] != 0) {
                    sparseArray[rowIndex][0] = i;
                    sparseArray[rowIndex][1] = j;
                    sparseArray[rowIndex][2] = srcArray[i][j];
                    rowIndex++;
                }
            }
        }
    }

    private static int[][] restoreArray(int[][] backupArray) {
        int[][] dstArray = new int[backupArray[0][0]][backupArray[0][1]];
        for (int i = 1; i < backupArray.length; i++) {
            dstArray[backupArray[i][0]][backupArray[i][1]] = backupArray[i][2];
        }
        return dstArray;
    }



    public static void main(String[] args) {
        int a[][] = initArray();
        int[][] backupArray = initSparseArray(a);
        backupArray(a, backupArray);
        PrintUtils.print(backupArray);
        PrintUtils.println();
        int[][] dstArray = restoreArray(backupArray);

        PrintUtils.print(dstArray);
    }

}
