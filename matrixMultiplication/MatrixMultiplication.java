package matrixMultiplication;

import java.util.Scanner;
//@AUTHOR JOHN NEPPEL
public class MatrixMultiplication {
	static Scanner console;

	public static void main(String[] args) throws InterruptedException {
		int[][] matrixA = {{3,7}, {3,2}, {6,5}, {4,8}};
		int[][] matrixB= {{3,7,2}, {3,2,9}};
		int[][] resultMatrix; //result of matrix A* matrix B
		MatrixMultiplierThread[] threads;

		//in matrixA*matrixB, the number of columns in matrixA MUST be the same as the number of rows in matrixB
	    if(matrixA[0].length != matrixB.length) {
	    	System.out.println("The number of columns in Matrix A is NOT the same number of rows in Matrix B");
	    	System.out.println("Therefore, no multiplication between the matricies can occur");
	    	return;
	    }
		
		
	  //the result matrix will have the number of rows from matrixA, number of columns from matrixB
		resultMatrix= new int[matrixA.length][matrixB[0].length]; 
		
		
		//Each thread will be assigned an element within the result matrix to calculate.
		//The number of elements is equal to the number of rows of matrixA* number of columns matrixB
		threads = new MatrixMultiplierThread[matrixA.length*matrixB[0].length];
		
		int threadNumber=0; //this will be used as a thread counter
		for(int i=0; i<matrixA.length; i++) {
			for(int j=0; j<matrixB[0].length;j++) {
		     //The threads will calculate the result matrix elements row by row.
		     threads[threadNumber]= new MatrixMultiplierThread(matrixA,matrixB, i, j);
		     threads[threadNumber].start();
		     threadNumber++;
			}
		}
		
		for(int k=0; k< resultMatrix.length; k++) {
			if(k==0) {
				threadNumber=0; //makes sure that the variable resets
			}
			for(int l=0; l<resultMatrix[0].length;l++) {
				threads[threadNumber].join();
				//row by row, the calculated result is filled into the appropriate result matrix element
				resultMatrix[k][l] = threads[threadNumber].getResult(); 
				threadNumber++;
			}
		}
		
		printMatrix(matrixA,"Matrix A:" );
		printMatrix(matrixB, "Matrix B:");
		printMatrix(resultMatrix, "Matrix C (Result):");
		
	}
	
	
	public static void printMatrix(int[][]someMatrix, String name) {
		for(int i=0; i<someMatrix.length; i++) {
			if(i==0) {
				System.out.println(name);
			}
			for(int j=0; j<someMatrix[0].length;j++) {
		        System.out.print(someMatrix[i][j] + "  ");
		        if(j==(someMatrix[0].length-1)) {
		        	System.out.println();
		        }
			}
		}
	}
	
	
	
	// IGNORE THE FOLLOWING METHODS; THEY'RE LEFT OVER FROM ANOTHER ALGORITHM I WAS PROPOSING
	public static int[] extractColumnFromMatrix(int[][]matrix, int columnIndex) {
		int[]columnElements = new int[matrix[0].length];
		for(int i=0; i<matrix[0].length; i++) {
			columnElements[i] = matrix[i][columnIndex]; //transfers all of the elements within the specified column into the array
		}
		return columnElements;
	}

	
	public static int[][] fillMatrixElements(int[][]matrix) {
      for(int row=0;row<matrix.length;row++) {
    	  for(int column=0;column<matrix[row].length;column++) {
    		  System.out.println("Enter the number of row#: "+ row+ " column# " + column);
    		  matrix[row][column]= console.nextInt();
    	  }
      }
      return matrix;
	}
}