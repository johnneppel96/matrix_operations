package saddlePointMatrix;

import java.util.ArrayList;

public class SaddlePointCalculator {

	public static void main(String args[]) throws InterruptedException {
		int [][] matrix= {{3,7,2,3,2}, {3,2,9,2,2}, {6,5,6,4,8}, {5,2,2,3,2}};
		String matrixName= "M";
		ArrayList <String> saddlePoints = new ArrayList<String>(); 
		
		//Each row within the matrix will be assigned to a RowThread
		RowThread rowThreads[]= new RowThread[matrix.length];
		
		//Each column within the matrix will be assigned a ColumnThread
		ColumnThread columnThreads[]= new ColumnThread[matrix[0].length];
		
		for(int i=0; i<rowThreads.length; i++) {
			//extracts the contents of the row at index 'i' from matrix
			int[] extractedRow = matrix[i];
			 //passes in the row index number and the extracted row-array
			rowThreads[i]= new RowThread(i, extractedRow);
			rowThreads[i].start();
		}
		
		for(int j=0; j<columnThreads.length;j++) {
			 //extracts the contents of the column at index 'i' from matrix
			int[] extractedColumn = extractColumnFromMatrix(matrix, j);
			 //passes in the column index number and the extracted column-array
			columnThreads[j]= new ColumnThread(j, extractedColumn);
			columnThreads[j].start();
		}
		
		for(int l=0; l<rowThreads.length; l++) {
			rowThreads[l].join();
		}
		
		for(int m=0; m<columnThreads.length; m++) {
			columnThreads[m].join();
		}
	
		
		for(int j=0; j<rowThreads.length; j++) { 
		for(int k=0; k<columnThreads.length; k++) {
			if (rowThreads[j].getMinValueFromRow()==columnThreads[k].getMaxNumFromCol()) {//this is the indicator of a saddle point
				//stores the name of the matrix along with the row/column index of where the saddle point was located and the value of it
				String saddlePointInstance= matrixName+ "[" + rowThreads[j].getRowIndexNum() +
						"," + columnThreads[k].getColIndexNum() + "]= " + columnThreads[k].getMaxNumFromCol();
				saddlePoints.add( saddlePointInstance);
			}
		}
		
		}
		
		printMatrix(matrix, matrixName);
		
		
		if(saddlePoints.isEmpty()==false) {
			System.out.println("The saddle points for Matrix " + matrixName + " is in:" );
			for(int i=0; i<saddlePoints.size(); i++) {
				System.out.println(saddlePoints.get(i));
			}
		}
		
		else {
			System.out.println("There are no saddle points for the given matrix");
		}
		
	}
	
	
	
	
	public static int[] extractColumnFromMatrix(int[][]matrix, int columnIndexToExtract) {
		 //given the number of elements within the column of the matrix
		int[]columnElements = new int[matrix[0].length];
		for(int i=0; i<matrix.length; i++) { //iterates through all of the elements within that column
			//transfers all of the elements within the specified column into the array
			columnElements[i] = matrix[i][columnIndexToExtract]; 
		}
		return columnElements;
	}
	
	public static void printMatrix(int[][]someMatrix, String name) {
		for(int i=0; i<someMatrix.length; i++) {
			if(i==0) {
				System.out.println("Matrix " +name);
			}
			for(int j=0; j<someMatrix[0].length;j++) {
		        System.out.print(someMatrix[i][j] + "  ");
		        if(j==(someMatrix[0].length-1)) {
		        	System.out.println();
		        }
			}
		}
	}
}
