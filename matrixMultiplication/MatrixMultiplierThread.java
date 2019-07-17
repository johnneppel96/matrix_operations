package matrixMultiplication;
//@AUTHOR JOHN NEPPEL
public class MatrixMultiplierThread extends Thread{

	private int[][] matrixA;
	private int[][] matrixB;
	private int rowAIndex;
	private int colBIndex;
	private int result=0;
	
	public MatrixMultiplierThread(int[][] matrixA, int[][]matrixB, int rowAIndex, int colBIndex) {
		this.matrixA=matrixA;
		this.matrixB= matrixB;
		this.rowAIndex= rowAIndex;
		this.colBIndex= colBIndex;

		
		
	}
	
	public void run() {
		//This will iterate through the elements within the 'rowAIndex' index of matrixA
		//(which will be the same number of elements located within the columns of matrixB)
		// and multiply the elements of the row from matrixA to the corresponding column elements of matrixB.
		for(int i=0; i<matrixB.length; i++) {
			result = result + matrixA[rowAIndex][i]* matrixB[i][colBIndex];
			if(i==(matrixB.length)) {
				System.out.println("Result:" + result);
			}
		}
	}
	
	
	public int getResult() {
		return result;
	}
}
